package study.dev.thboard3.survey.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.dev.thboard3.cmm.model.CmmnVo;
import study.dev.thboard3.survey.service.SurveyService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/survey")
public class SurveyRestController {

    private final SurveyService surveyService;

    @GetMapping("")
    public ResponseEntity selectSurvey(CmmnVo cmmnVo) throws Exception {
        return surveyService.getExternalSurveyList(cmmnVo);
    }
}
