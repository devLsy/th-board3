package study.dev.thboard3.model;

import lombok.Data;

@Data
public class BoardVo extends CmmVo{

    private Long no;            //rownum
    private Long boardSno;      //게시글 순번
    private String title;       //제목
    private String content;     //내용
    private String userId;      //작성자 아이디
    private char useYn;         //사용여부
    private Integer replyCnt;   //댓글개수



}
