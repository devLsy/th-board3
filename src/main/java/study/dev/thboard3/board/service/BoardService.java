package study.dev.thboard3.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.dev.thboard3.board.mapper.BoardMapper;
import study.dev.thboard3.board.model.BoardVo;
import study.dev.thboard3.cmm.model.CmmnVo;
import study.dev.thboard3.cmm.model.PaginationInfo;
import study.dev.thboard3.cmm.service.CmmnService;
import study.dev.thboard3.model.enu.ResultCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardMapper boardMapper;
    private final CmmnService cmmnService;

    /**
     * 게시글 조회
     * @param cmmnVo
     * @return
     * @throws Exception
     */
    public ResponseEntity selectBoard(CmmnVo cmmnVo) throws Exception {
        //resultMap
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<BoardVo> boardList = new ArrayList<>();
        int boardCount = 0;
        boardCount = boardMapper.selectBoardCount(cmmnVo);

        PaginationInfo pageVo = cmmnService.setPagination(cmmnVo.getCurrentPage(), boardCount);
        cmmnVo.setFirstRecordIndex(pageVo.getFirstRecordIndex());
        cmmnVo.setLastRecordIndex(pageVo.getLastRecordIndex());

        boardList = boardMapper.selectBoard(cmmnVo);

        if(boardCount > 0) {
            resultMap.put("list", boardList);
            resultMap.put("count", boardCount);
            resultMap.put("paging", pageVo);
        }
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    /**
     * 게시글 상세 조회
     * @param boardSno
     * @return
     * @throws Exception
     */
    public ResponseEntity selectBoardDetail(Integer boardSno) throws Exception{
        return new ResponseEntity<>(boardMapper.selectBoardDetail(boardSno), HttpStatus.OK);
    }

    /**
     * 게시글 등록/수정(merge)
     * @param boardVo
     * @return
     * @throws Exception
     */
    @Transactional
    public ResponseEntity mergeBoard(BoardVo boardVo) throws Exception {
        //resultMap
        Map<String, Object> resultMap = new HashMap<String, Object>();

        boardVo.setUserId("lsy");

        if(boardVo.getBoardSno() == null) boardVo.setBoardSno(boardMapper.selectMaxBoardSno());
        boardMapper.mergeBoard(boardVo);

        resultMap.put("code", ResultCode.SUCCESS.getCode());
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    /**
     * 게시글 삭제
     * @param boardSno
     * @return
     * @throws Exception
     */
    @Transactional
    public ResponseEntity deleteBoard(Integer boardSno) throws Exception {
        //resultMap
        Map<String, Object> resultMap = new HashMap<String, Object>();

        boardMapper.deleteBoard(boardSno);
        resultMap.put("code", ResultCode.SUCCESS.getCode());
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
