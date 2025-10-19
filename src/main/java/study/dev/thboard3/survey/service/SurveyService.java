package study.dev.thboard3.survey.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.dev.thboard3.survey.mapper.SurveyMapper;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SurveyService {

    private final SurveyMapper surveyMapper;

    /**
     * (외부) 특정 사용자가 응시한 설문 목록을 조회하는 서비스 메서드
     * @param userId 로그인한 사용자의 ID
     * @return 응시 회차별 요약 정보 리스트
     */
    public List<Map<String, Object>> getExternalSurveyList(String userId) {
        // 1. (필요하다면) 비즈니스 로직 처리 (예: 권한 검사, 입력값 검증)
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("사용자 ID는 필수입니다.");
        }
        // 2. Mapper를 호출하여 데이터베이스 작업 수행
        List<Map<String, Object>> surveyList = surveyMapper.selectExtSurveyList(userId);
        return surveyList;
    }

    /**
     * 특정 사용자의 특정 응시 회차 상세 정보를 조회합니다.
     * @param userId 사용자 ID
     * @param sessionKey 응시 회차 KEY
     * @return 단일 응시 상세 정보 Map
     */
    public Map<String, Object> getSurveyDetail(String userId, String sessionKey) {
        if (userId == null || sessionKey == null) {
            throw new IllegalArgumentException("사용자 ID와 세션 키는 필수입니다.");
        }
        return surveyMapper.selectSurveyDetail(userId, sessionKey);
    }

    // 💡 여러 건의 INSERT가 하나의 트랜잭션으로 묶여야 합니다.
    @Transactional
    public void saveSurveyAnswers(List<Map<String, Object>> answers) {
        if (answers == null || answers.isEmpty()) return;

        String sessionKey = (String) answers.get(0).get("sessionKey");

        // 1. 🚨 [핵심 수정] INSERT 전에 기존 응답을 삭제합니다.
        surveyMapper.deletePreviousAnswers(sessionKey);

        // 2. 새로운 응답을 일괄 삽입합니다. (이전 데이터가 삭제되었으므로 충돌 없음)
        surveyMapper.insertSurveyAnswers(answers);
    }

    @Transactional(readOnly = true)
    public String getNextSessionKey(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be empty.");
        }

        Integer maxNumber = surveyMapper.selectMaxSessionKeyNumber(userId);

        String upperUserId = userId.toUpperCase();

        int nextNumber = maxNumber + 1;

        if (nextNumber > 99999) {
            // 🚨 순번 초과 시 예외 처리 또는 로깅 필요
            throw new RuntimeException("Session key number limit exceeded for user: " + upperUserId);
        }

        String formattedNumber = String.format("%05d", nextNumber);

        // 5. 최종 SESSION_KEY 조합
        return upperUserId + "_" + formattedNumber;
    }
}
