package study.dev.thboard3.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.dev.thboard3.model.BoardVo;
import study.dev.thboard3.service.BoardService;

import javax.validation.Valid;

import static study.dev.thboard3.cmm.utils.ValidatorUtils.invokeErrors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board/api")
public class BoardRestController {

    private final BoardService boardService;

    /**
     * 게시글 등록
     * @param boardVo
     * @return
     */
    @PostMapping("/reg")
    public ResponseEntity reg(@RequestBody @Valid BoardVo boardVo, BindingResult br) throws Exception {
        //parameter 검증 실패
        if (br.hasErrors()) {
            invokeErrors(this.getClass().getName(), br);
        }
        return boardService.insertBoardProc(boardVo, br);
    }
}
