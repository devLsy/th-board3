package study.dev.thboard3.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.dev.thboard3.model.BoardVo;
import study.dev.thboard3.service.BoardService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class BoardRestController {

    private final BoardService boardService;

    /**
     * 게시글 등록
     * @param boardVo
     * @return
     */
    @PostMapping("/reg")
    public ResponseEntity reg(@RequestBody BoardVo boardVo) throws Exception {
        return boardService.insertBoardProc(boardVo);
    }
}
