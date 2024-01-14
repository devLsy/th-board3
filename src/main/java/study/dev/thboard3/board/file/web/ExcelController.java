package study.dev.thboard3.board.file.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import study.dev.thboard3.board.file.service.ExcelService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelService excelService;

    @GetMapping("/excelView1")
    public ModelAndView excelView1(ModelAndView mv) {
        mv.setViewName("excelView1");
        return mv;
    }

    @GetMapping("/excelView2")
    public ModelAndView excelView2(ModelAndView mv) {
        mv.setViewName("excelView2");
        return mv;
    }

    @GetMapping("/excelView3")
    public ModelAndView excelView3(ModelAndView mv) {
        mv.setViewName("excelView3");
        return mv;
    }

    /**
     * 엑셀 다운로드 처리
     * @param paramData
     * @param pageGubun
     * @param response
     */
    @GetMapping("/exceldownload")
    public void downloadExcel(@RequestParam(name = "selectedData") String paramData,
                              @RequestParam(name = "pageGubun") String pageGubun,
                              HttpServletResponse response) throws IOException {

        excelService.download(paramData, response, pageGubun);
    }
}
