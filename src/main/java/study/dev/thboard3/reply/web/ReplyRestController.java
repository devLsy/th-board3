package study.dev.thboard3.reply.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.dev.thboard3.reply.model.ReplyVo;
import study.dev.thboard3.reply.service.ReplyService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/reply")
public class ReplyRestController {

    private final ReplyService replyService;


    /**
     * 댓글 등록/수정
     * @param replyVo
     * @return
     * @throws Exception
     */
    @PostMapping("")
    public ResponseEntity mergeReply(ReplyVo replyVo) throws Exception {
        return replyService.mergeReply(replyVo);
    }

    /**
     * 댓글 삭제
     * @param replyVo
     * @return
     * @throws Exception
     */
    @DeleteMapping("")
    public ResponseEntity deleteReply(ReplyVo replyVo) throws Exception {
        return replyService.deleteReply(replyVo.getBoardSno(), replyVo.getReplySno());
    }
}
