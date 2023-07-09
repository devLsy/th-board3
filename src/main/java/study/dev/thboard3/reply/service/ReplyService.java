package study.dev.thboard3.reply.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.dev.thboard3.model.enu.ResultCode;
import study.dev.thboard3.reply.mapper.ReplyMapper;
import study.dev.thboard3.reply.model.ReplyVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyService {

    private final ReplyMapper replyMapper;

    /**
     * 댓글 리스트
     * @param boardSno
     * @return
     * @throws Exception
     */
    public ResponseEntity selectReply(Integer boardSno) throws Exception{
        //resultMap
        Map<String, Object> resultMap = new HashMap<String, Object>();

        List<ReplyVo> replyList = replyMapper.selectReply(boardSno);
        resultMap.put("list", replyList);
        
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    /**
     * 댓글 상세
     * @param boardSno
     * @param replySno
     * @return
     */
    public ResponseEntity selectReplyDetail(Integer boardSno, Integer replySno) throws Exception{
        //resultMap
        Map<String, Object> resultMap = new HashMap<String, Object>();

        ReplyVo replyDetail = replyMapper.selectReplyDetail(boardSno, replySno);
        resultMap.put("info", replyDetail);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    /**
     * 저장/수정
     * @param replyVo
     * @return
     */
    public ResponseEntity mergeReply(ReplyVo replyVo) throws Exception{
        //resultMap
        Map<String, Object> resultMap = new HashMap<String, Object>();
        replyVo.setUserId("dak");
        if(replyVo.getReplySno() == null) replyVo.setReplySno(replyMapper.selectMaxReplySno());
        replyMapper.mergeReply(replyVo);
        resultMap.put("code", ResultCode.SUCCESS.getCode());

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    /**
     * 댓글 삭제
     * @param boardSno
     * @param replySno
     * @return
     * @throws Exception
     */
    public ResponseEntity deleteReply(Integer boardSno, Integer replySno) throws Exception{
        //resultMap
        Map<String, Object> resultMap = new HashMap<String, Object>();

        replyMapper.deleteReply(boardSno, replySno);
        resultMap.put("code", ResultCode.SUCCESS.getCode());
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
