package study.dev.thboard3.reply.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import study.dev.thboard3.reply.model.ReplyVo;

import java.util.List;

@Mapper
public interface ReplyMapper {
    
    /* 댓글 리스트 */
    List<ReplyVo> selectReply(@Param("boardSno") Integer boardSno);
    /* 카운트 */
    int selectReplyCount(@Param("boardSno") Integer boardSno);
    /* 상세 */
    ReplyVo selectReplyDetail(@Param("boardSno") Integer boardSno, @Param("replySno") Integer replySno);
    /* 댓글 순번 시퀀스 조회 */
    int selectMaxReplySno();
    /* 저장/수정 */
    void mergeReply(ReplyVo replyVo);
    /* 삭제 */
    void deleteReply(@Param("boardSno") Integer boardSno, @Param("replySno") Integer replySno);
}
