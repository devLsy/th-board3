package study.dev.thboard3.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import study.dev.thboard3.board.model.BoardVo;
import study.dev.thboard3.board.service.BoardService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 목록 화면
     * @return
     */
    @GetMapping("")
    public ModelAndView list(ModelAndView mv) throws Exception {
        return boardService.selectBoardList(mv);
    }




}
