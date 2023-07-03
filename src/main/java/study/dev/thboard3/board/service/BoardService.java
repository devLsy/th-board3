package study.dev.thboard3.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import study.dev.thboard3.board.mapper.BoardMapper;
import study.dev.thboard3.board.model.BoardVo;
import study.dev.thboard3.cmm.model.CmmnVo;
import study.dev.thboard3.cmm.model.PaginationInfo;
import study.dev.thboard3.cmm.model.ResultCode;
import study.dev.thboard3.user.model.UserVo;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static study.dev.thboard3.cmm.utils.ValidatorUtils.invokeErrors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final CommonService commonService;
    private final BoardMapper boardMapper;

    /**
     * 게시글 목록 조회
     * @return
     */
    public ModelAndView selectBoardList(ModelAndView mv) throws Exception{
        mv.setViewName("pages/board/list");
        return mv;
    }

    /**
     * 게시글 목록 조회(ajax)
     * @param cmmnVo
     * @param session
     * @return
     */
    public ResponseEntity selectBoardList(CmmnVo cmmnVo, HttpSession session) throws Exception{
        //resultMap
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //사용자 세션 정보
        UserVo sessionUserInfo = commonService.getSessionUserInfo(session);
        //게시글 전체 카운트
        int boardCount = boardMapper.selectBoardCnt(cmmnVo);
        //페이지네이션 세팅
        PaginationInfo pageVo = commonService.getPagination(cmmnVo.getCurrentPage(), boardCount);
        cmmnVo.setFirstRecordIndex(pageVo.getFirstRecordIndex());
        cmmnVo.setLastRecordIndex(pageVo.getLastRecordIndex());
        //게시글 리스트
        List<BoardVo> boardList = boardMapper.selectBoardList(cmmnVo);
//        log.info("sessionUserInfo = {}", sessionUserInfo);
        resultMap.put("list", boardList);
        resultMap.put("paging", pageVo);
        resultMap.put("totalCount", boardCount);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    /**
     * 게시글 등록/수정
     * @param boardVo
     * @param br
     */
    public ResponseEntity mergeBoard(BoardVo boardVo, BindingResult br) throws Exception{
        //parameter 검증 실패
        if (br.hasErrors()) {
            invokeErrors(this.getClass().getName(), br);
        }
        //결과 코드값
        Map<Object, Object> resultMap = new HashMap<>();
        //화면에서 넘어온 게시글 순번이 없는 경우(게시글 등록)엔 시퀀스 값으로 세팅
        if(boardVo.getBoardSno() == null) boardVo.setBoardSno(boardMapper.selectMaxBoardSno());
        //쿼리 성공 시
        int count = boardMapper.mergeBoard(boardVo);
        //쿼리 성공 시
        if (count > 0) {
            resultMap.put("no", boardVo.getBoardSno());
            resultMap.put("code", ResultCode.SUCCESS.getCode());
        //쿼리 실패 시
        } else {
            resultMap.put("code", ResultCode.FAIL.getCode());
        }
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    /**
     * 게시글 상세
     * @param boardSno
     * @return
     */
    public ResponseEntity getBoardDetail(Long boardSno) throws Exception{

        BoardVo findBoard = null;

        if (boardSno != null) {
            findBoard = boardMapper.selectBoardDetail(boardSno);
        }
        return new ResponseEntity<>(findBoard, HttpStatus.OK);
    }

    /**
     * 게시글 삭제 
     * @param boardSno
     */
    @Transactional
    public int deleteBoard(Long boardSno) throws Exception{
        return boardMapper.deleteBoard(boardSno);
    }


}
