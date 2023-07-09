package study.dev.thboard3.board.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import study.dev.thboard3.board.mapper.BoardMapper;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardMapper boardMapper;

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

    /**
     * 상세
     * @param boardSno
     * @param mv
     * @return
     */
    @GetMapping("/mode/{boardSno}")
    public ModelAndView detail(@PathVariable Integer boardSno, ModelAndView mv) {
        mv.addObject("info", boardMapper.selectBoardDetail(boardSno)).setViewName("detail");
        return mv;
    }


}
