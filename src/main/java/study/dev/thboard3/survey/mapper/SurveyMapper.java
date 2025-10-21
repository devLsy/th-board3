package study.dev.thboard3.survey.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SurveyMapper {
    // 1. (외부) 사용자 응시 목록 조회
    List<Map<String, Object>> selectExtSurveyList(@Param("userId") String userId);

    // 2. (외부) 사용자별 상세 응시 조회
    Map<String, Object> selectSurveyDetail(@Param("userId") String userId, @Param("sessionKey") String sessionKey);

    // 3. 설문 응답 저장 (단일 문항에 대한 응답 저장)
    void insertSurveyAnswers(List<Map<String, Object>> answers);

    // 4. 특정 사용자의 SESSION_KEY 최대 순번 조회
    Integer selectMaxSessionKeyNumber(@Param("userId") String userId);

    // 5. 특정 세션키의 데이터 삭제
    int deletePreviousAnswers(@Param("sessionKey") String sessionKey);
    
    // 6. 사용자 전체의 설문 응시 목록 
    List<Map<String, Object>> selectIntSurveyList();

    // 7. 설문지 동적 생성을 위한 데이터 문항 + 선택지 조회
    List<Map<String, Object>> selectSurveyQuestionsForRegister();
}
