package study.dev.thboard3.survey.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

//    /**
//     * 등록폼
//     * @param mv
//     * @return
//     */
//    @GetMapping("/reg")
//    public ModelAndView regForm(ModelAndView mv) {
//        log.info("reg~~");
//        mv.setViewName("reg");
//        return mv;
//    }
//
//    /**
//     * 상세
//     * @param boardSno
//     * @param mv
//     * @return
//     */
//    @GetMapping("/mod/{boardSno}")
//    public ModelAndView detail(@PathVariable Integer boardSno, ModelAndView mv) {
//        mv.addObject("info", boardMapper.selectBoardDetail(boardSno))
//                        .addObject("replyList", replyMapper.selectReply(boardSno))
//                        .setViewName("reg");
//        return mv;
//    }


}
