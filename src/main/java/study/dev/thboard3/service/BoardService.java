package study.dev.thboard3.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.dev.thboard3.mapper.BoardMapper;
import study.dev.thboard3.model.BoardVo;

import java.util.List;

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
    public List<BoardVo> selectBoardList() throws Exception{
        return boardMapper.selectBoardList();
    }

    /**
     * 게시글 등록
     * @param boardVo
     */
    @Transactional
    public void insertBoard(BoardVo boardVo) throws Exception{
        boardMapper.insertBoard(boardVo);
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
    public BoardVo selectBoardDetail(Long boardSno) throws Exception{
        return boardMapper.selectBoardDetail(boardSno);
    }

    /**
     * 게시글 수정 
     * @param boardVo
     */
    public void updateBoard(BoardVo boardVo) {
        boardMapper.updateBoard(boardVo);
    }

    /**
     * 게시글 삭제 
     * @param boardSno
     */
    public void deleteBoard(Long boardSno) {
        boardMapper.deleteBoard(boardSno);
    }


}
