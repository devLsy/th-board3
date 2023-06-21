package study.dev.thboard3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import study.dev.thboard3.service.BoardService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 목록
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list() throws Exception {
        ModelAndView mv = new ModelAndView("pages/list");
        return mv.addObject("list", boardService.selectBoardList());
    }
}
