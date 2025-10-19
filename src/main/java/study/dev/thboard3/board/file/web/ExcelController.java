package study.dev.thboard3.board.file.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import study.dev.thboard3.board.file.service.ExcelService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

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

    @PostMapping("/fileUpload")
    public String fileUpload(ModelAndView mv,
                                   @RequestParam("file1") MultipartFile file1,
                                   @RequestParam("file2") MultipartFile file2,
                                   @RequestParam("file3") MultipartFile file3) {


        String uploadPath = "D:/upload/";

        saveFile(uploadPath, file1);
        saveFile(uploadPath, file2);
        saveFile(uploadPath, file3);

        return "redirect:/fileUploadView";
    }

    private String saveFile(String filePath, MultipartFile file) {

        String fileUUid = UUID.randomUUID().toString();
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        String saveFileName = fileUUid + extension;

        String savePath = filePath + saveFileName;

        try {

            if (!file.isEmpty()) {
                file.transferTo(new File(savePath));
            }
            log.info("성공~");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "성공";
    }

    @GetMapping("/fileUploadView")
    public ModelAndView fileUploadView(ModelAndView mv) {
        mv.setViewName("fileUpload");
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
