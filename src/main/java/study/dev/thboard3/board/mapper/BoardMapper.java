package study.dev.thboard3.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import study.dev.thboard3.board.model.BoardVo;
import study.dev.thboard3.cmm.model.CmmnVo;

import java.util.List;

@Mapper
public interface BoardMapper {

    /* 게시글 조회 */
    List<BoardVo> selectBoard(CmmnVo cmmnVo);
    /* 게시글 카운트 */
    int selectBoardCount(CmmnVo cmmnVo);
    /* 게시글 상세 조회 */
    BoardVo selectBoardDetail(@Param("boardSno") Integer boardSno);
    /*게시글 순번 시퀀스 조회*/
    int selectMaxBoardSno();
    /*게시글 등록/수정(merge)*/
    void mergeBoard(BoardVo boardVo);
    /*게시글 삭제*/
    void deleteBoard(@Param("boardSno") Integer boardSno);

}
