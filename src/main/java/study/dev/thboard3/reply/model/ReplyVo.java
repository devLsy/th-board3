package study.dev.thboard3.reply.model;

import lombok.Data;
import study.dev.thboard3.cmm.model.CmmnVo;

@Data
public class ReplyVo extends CmmnVo {

    private Integer replySno;
    private Integer boardSno;
    private String replyContent;
    private String userId;
    
}
