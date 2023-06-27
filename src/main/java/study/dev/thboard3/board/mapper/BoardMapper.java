package study.dev.thboard3.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import study.dev.thboard3.board.model.BoardVo;

import java.util.List;

@Mapper
public interface BoardMapper {

    /* 게시글 목록 조회 */
    List<BoardVo> selectBoardList();

    /* 게시글 등록 */
    int insertBoard(BoardVo boardVo);

    /* 게시글 등록/수정 */
    int mergeBoard(BoardVo boardVo);

    /* 게시글 전체 카운트 */
    int selectBoardCnt();

    /* 게시글 상세 */
    BoardVo selectBoardDetail(@Param("boardSno") Long boardSno);

    /* 게시글 순번 시퀀스 조회 */
    Long selectMaxBoardSno();

    /* 게시글 수정 */
    int updateBoard(BoardVo boardVo);
    
    /* 게시글 삭제 */
    int deleteBoard(@Param("boardSno") Long boardSno);

}
