package study.dev.thboard3.board.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.dev.thboard3.board.model.BoardVo;
import study.dev.thboard3.board.service.BoardService;
import study.dev.thboard3.cmm.model.CmmnVo;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardRestController {

    private final BoardService boardService;

    /**
     * 게시글 조회
     * @param cmmnVo
     * @return
     * @throws Exception
     */
    @GetMapping("")
    public ResponseEntity selectBoard(CmmnVo cmmnVo) throws Exception {
        return boardService.selectBoard(cmmnVo);
    }

    /**
     * 게시글 등록/수정(merge)
     * @param boardVo
     * @return
     * @throws Exception
     */
    @PostMapping("")
    public ResponseEntity mergeBoard(BoardVo boardVo) throws Exception {
        log.info("boardVo = [{}]", boardVo);
        return boardService.mergeBoard(boardVo);
    }

    /**
     * 게시글 삭제
     * @param boardSno
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{boardSno}")
    public ResponseEntity deleteBoard(@PathVariable Integer boardSno) throws Exception {
        return boardService.deleteBoard(boardSno);
    }



}
