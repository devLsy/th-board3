package study.dev.thboard3.survey.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import study.dev.thboard3.survey.service.SurveyService;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;

    /**
     * (외부) 사용자 설문 응시 목록
     * @param mv
     * @return
     */
    @GetMapping("/ext/list")
    public ModelAndView extSurveylist(ModelAndView mv) {
        // 1. 🔑 사용자 ID 하드코딩 (테스트용)
        String currentUserId = "nyj";
        // 2. Service 호출 및 데이터 획득 (예외처리 없음)
        List<Map<String, Object>> externalSurveyList = surveyService.getExternalSurveyList(currentUserId);
        // 3. 모델에 데이터 추가 및 뷰 설정
        mv.addObject("surveyList", externalSurveyList);
        mv.addObject("userId", currentUserId);
        mv.setViewName("surveyMain");
        return mv;
    }

    /**
     * (외부) 설문 등록 화면으로 이동 및 새 응시 TOKEN 준비
     * @param mv
     * @param userId URL 파라미터로 받은 사용자 ID
     * @return surveyReg.html
     */
    @GetMapping("/ext/reg")
    public ModelAndView register(ModelAndView mv, @RequestParam("userId") String userId) {

//        String userIdParam = "lsy";

        // 1. Service를 호출하여 다음 응시 순번(SESSION_KEY)을 계산
        // 💡 이 로직은 Service나 Mapper에 별도로 구현되어 있어야 함.
        String nextSessionKey = surveyService.getNextSessionKey(userId);
//        String nextSessionKey = surveyService.getNextSessionKey(userIdParam);

        // 2. 모델에 데이터 추가
        // 이 데이터는 surveyReg.html의 Hidden Field에 바인딩됩니다.
        mv.addObject("userId", userId);
        mv.addObject("sessionKey", nextSessionKey);

        // 3. 뷰 이름 설정
        mv.setViewName("surveyReg");

        return mv;
    }

    /**
     * (외부) 특정 응시 회차의 상세 내용 조회
     * @param mv
     * @param sessionKey URL 파라미터로 받은 응시 회차 KEY
     * @return
     */
    @GetMapping("/ext/detail")
    public ModelAndView detail(ModelAndView mv, @RequestParam("userId") String userId, @RequestParam("sessionKey") String sessionKey) {

        String currentUserId = userId;

        // 2. Service 호출하여 특정 응시의 상세 데이터 조회 (예외처리 없음)
        Map<String, Object> surveyDetail = surveyService.getSurveyDetail(currentUserId, sessionKey);

        // 3. 모델에 데이터 추가
        mv.addObject("detailData", surveyDetail);
        mv.addObject("userId", currentUserId);
        mv.addObject("sessionKey", sessionKey); // 화면에서 재사용 가능하도록 추가

        // 4. 뷰 이름 설정
        mv.setViewName("surveyDetail");

        return mv;
    }

    @PostMapping("/ext/save")
    @ResponseBody // JSON 데이터를 받고 JSON 또는 HTTP 상태 코드를 반환
    public String saveSurvey(@RequestBody Map<String, Object> requestData) {

        @SuppressWarnings("unchecked") // answers가 List<Map>임을 가정
        List<Map<String, Object>> answers = (List<Map<String, Object>>) requestData.get("answers");

        if (answers == null || answers.isEmpty()) {
            log.warn("저장할 응답 데이터가 없습니다.");
            return "NO_DATA";
        }

        try {
            // Service를 호출하여 모든 응답을 DB에 저장
            surveyService.saveSurveyAnswers(answers);
            log.info("설문 응답 {}건 저장 완료. SessionKey: {}", answers.size(), requestData.get("sessionKey"));
            return "SUCCESS";
        } catch (Exception e) {
            log.error("설문 저장 중 오류 발생. SessionKey: {}", requestData.get("sessionKey"), e);
            // 🚨 실제 개발 환경에서는 HTTP 500 에러 코드를 반환해야 합니다.
            return "FAIL";
        }
    }
}
