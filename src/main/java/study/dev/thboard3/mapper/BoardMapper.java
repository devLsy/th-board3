package study.dev.thboard3.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import study.dev.thboard3.model.BoardVo;

import java.util.List;

@Mapper
public interface BoardMapper {

    /* 게시글 목록 조회 */
    List<BoardVo> selectBoardList();

    /* 게시글 등록 */
    void insertBoard(BoardVo boardVo);

    /* 게시글 전체 카운트 */
    int selectBoardCnt();

    /* 게시글 상세 */
    BoardVo selectBoardDetail(@Param("boardSno") Long boardSno);

    /* 게시글 수정 */
    void updateBoard(BoardVo boardVo);
    
    /* 게시글 삭제 */
    void deleteBoard(@Param("boardSno") Long boardSno);

}
