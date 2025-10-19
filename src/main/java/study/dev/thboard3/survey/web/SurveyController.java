package study.dev.thboard3.survey.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        String currentUserId = "lsy";
        // 2. Service 호출 및 데이터 획득 (예외처리 없음)
        List<Map<String, Object>> externalSurveyList = surveyService.getExternalSurveyList(currentUserId);
        // 3. 모델에 데이터 추가 및 뷰 설정
        mv.addObject("surveyList", externalSurveyList);
        mv.addObject("userId", currentUserId);
        mv.setViewName("surveyMain");
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
}
