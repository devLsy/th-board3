package study.dev.thboard3.model;

import lombok.Data;

@Data
public class UserVo extends CmmVo{

    private Long no;                //rownum
    private Long userSno;           //사용자 순번
    private String userId;          //사용자 아이디
    private String userPassword;    //사용자 비밀번호
    private String userName;        //사용자명
    private String userEmail;       //사용자 이메일
    private char useYn;             //사용여부
}
