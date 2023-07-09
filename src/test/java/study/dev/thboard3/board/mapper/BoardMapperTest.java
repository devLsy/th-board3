package study.dev.thboard3.board.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import study.dev.thboard3.board.model.BoardVo;
import study.dev.thboard3.board.service.BoardService;
import study.dev.thboard3.cmm.model.CmmnVo;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BoardMapperTest {

    @Autowired
    BoardService boardService;

    @Test
    @DisplayName("게시글등록")
    @Commit
    @Disabled
    public void 게시글등록() throws Exception {
        //givin
        BoardVo boardVo = new BoardVo();
        boardVo.setBoardSno(55);
        boardVo.setTitle("연준 기준금리 인하");
        boardVo.setContent("투자자들 환호");
        boardVo.setUserId("lsy");

        boardService.mergeBoard(boardVo);
        //when

        //then
    }

    @Test
    @DisplayName("게시글조회")
    @Commit
    @Disabled
    public void 게시글조회() throws Exception {
        //givin
        boardService.selectBoard(new CmmnVo());
        //when

        //then
    }

    @Test
    @DisplayName("게시글상세조회")
    @Commit
    @Disabled
    public void 게시글상세조회() throws Exception {
        //givin
        boardService.selectBoardDetail(17);
        //when

        //then
    }

}