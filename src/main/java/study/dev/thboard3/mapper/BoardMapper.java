package study.dev.thboard3.mapper;

import org.apache.ibatis.annotations.Mapper;
import study.dev.thboard3.model.BoardVo;

import java.util.List;

@Mapper
public interface BoardMapper {

    /* 게시글 목록 조회 */
    List<BoardVo> selectBoardList();
}
