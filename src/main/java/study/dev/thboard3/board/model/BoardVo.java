package study.dev.thboard3.board.model;

import lombok.Data;
import study.dev.thboard3.cmm.model.CmmnVo;

import java.util.ArrayList;
import java.util.List;

@Data
public class BoardVo extends CmmnVo {

    private Integer boardSno;
    private String title;
    private String content;
    private String userId;
    private String useYn;
    private int replyCnt;

    private List<BoardVo> boardList = new ArrayList<>();

}
