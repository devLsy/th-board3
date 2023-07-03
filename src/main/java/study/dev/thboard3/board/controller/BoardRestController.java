package study.dev.thboard3.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import study.dev.thboard3.board.model.BoardVo;
import study.dev.thboard3.board.service.BoardService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/board")
public class BoardRestController {

    private final BoardService boardService;

    /**
     * 게시글 목록(ajax)
     * @param boardVo
     * @param session
     * @return
     */
    @GetMapping("")
    public ResponseEntity list(@ModelAttribute BoardVo boardVo, HttpSession session) throws Exception {
        return boardService.selectBoardList(boardVo, session);
    }

    /**
     * 게시글 등록/수정
     * @param boardVo
     * @param br
     * @return
     * @throws Exception
     */
    @PostMapping("")
    public ResponseEntity reg(@RequestBody @Valid BoardVo boardVo, BindingResult br) throws Exception {
        return boardService.mergeBoard(boardVo, br);
    }

    /**
     * 게시글 상세
     * @param boardSno
     * @return
     * @throws Exception
     */
    @GetMapping("/{boardSno}")
    public ResponseEntity detail(@PathVariable Long boardSno) throws Exception {
        return boardService.getBoardDetail(boardSno);
    }
}
