package study.dev.thboard3.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import study.dev.thboard3.model.BoardVo;

@SpringBootTest
@Slf4j
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    @DisplayName("게시글등록")
    @Commit
    @Disabled
    public void 게시글등록() throws Exception {
        //givin
        BoardVo boardVo = new BoardVo();
        boardVo.setTitle("오염수방출!!!");
        boardVo.setContent("오염수 방출하면... 해산물 먹어야 되나 먹지 말아야 되나?");
        boardVo.setUserId("lsy");
        //when
        boardService.insertBoardProc(boardVo);
        //then
    }

    @Test
    @DisplayName("게시글상세")
    @Commit
    @Disabled
    public void 게시글상세() throws Exception {
        boardService.selectBoardDetail(2L);
    }
    
    @Test
    @DisplayName("게시글전체카운트")
    @Commit
    @Disabled
    public void 게시글전체카운트() throws Exception {
        boardService.selectBoardCnt();
    }

    @Test
    @DisplayName("게시글목록")
    @Commit
    @Disabled
    public void 게시글목록() throws Exception {
        boardService.selectBoardList();
    }
    
    @Test
    @DisplayName("게시글수정")
    @Commit
//    @Disabled
    public void 게시글수정() throws Exception {
        BoardVo boardVo = new BoardVo();
        boardVo.setTitle("제목만 수정한다.");
        boardVo.setContent("내용도 수정한다.");
        boardVo.setBoardSno(2L);
        boardService.updateBoard(boardVo);
    }
    
    @Test
    @DisplayName("게시글삭제")
    @Commit
    @Disabled
    public void 게시글삭제() throws Exception {
        boardService.deleteBoard(2L);
    }

}