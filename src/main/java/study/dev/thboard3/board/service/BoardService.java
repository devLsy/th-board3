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
import study.dev.thboard3.model.enu.ResultCode;

import java.util.HashMap;
import java.util.Map;

import static study.dev.thboard3.cmm.utils.ValidatorUtils.invokeErrors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    /**
     * 게시글 목록 조회
     * @return
     */
    public ModelAndView selectBoardList(BoardVo boardVo, ModelAndView mv) throws Exception{
        mv.addObject("list", boardMapper.selectBoardList()).addObject("boardVo", boardVo).setViewName("pages/board/list");
        return mv;
    }

    /**
     * 게시글 등록/수정
     * @param boardVo
     * @param br
     */
    public ResponseEntity mergeBoard(BoardVo boardVo, BindingResult br) {
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
     * 게시글 전체 카운트
     * @return
     */
    public int selectBoardCnt() throws Exception{
        return boardMapper.selectBoardCnt();
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
