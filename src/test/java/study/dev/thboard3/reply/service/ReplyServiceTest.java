package study.dev.thboard3.reply.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import study.dev.thboard3.reply.model.ReplyVo;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ReplyServiceTest {

    @Autowired
    ReplyService replyService;

    @Test
    @DisplayName("댓글등록")
    @Commit
    public void 댓글등록() throws Exception {
        //givin
        ReplyVo replyVo = new ReplyVo();
        replyVo.setBoardSno(63);
        replyVo.setReplyContent("마르고 닳도록");
        replyVo.setUserId("dak");
        //when
        replyService.mergeReply(replyVo);
        //then
    }

}