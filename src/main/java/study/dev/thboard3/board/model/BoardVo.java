package study.dev.thboard3.board.model;

import lombok.Data;
import study.dev.thboard3.model.CmmVo;

import javax.validation.constraints.NotBlank;

@Data
public class BoardVo extends CmmVo {

    private Long no;            //rownum
    private Long boardSno;      //게시글 순번

    @NotBlank(message = "제목은 필수값이고 빈값이거나 공백이 올 수 없습니다.")
    private String title;       //제목

    @NotBlank(message = "내용은 필수값이고 빈값이거나 공백이 올 수 없습니다.")
    private String content;     //내용

    @NotBlank(message = "아이디는 필수값이고 빈값이거나 공백이 올 수 없습니다.")
    private String userId;      //작성자 아이디
    private String userName;      //작성자
    private char useYn;         //사용여부
    private Integer replyCnt;   //댓글개수



}
