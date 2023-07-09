package study.dev.thboard3.board.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    /**
     * 게시글 조회 화면
     * @param mv
     * @return
     */
    @GetMapping("")
    public ModelAndView list(ModelAndView mv) {
        mv.setViewName("main");
        return mv;
    }


}
