package study.dev.thboard3.user.model;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import study.dev.thboard3.model.CmmVo;

import javax.validation.constraints.NotBlank;

@Data
public class UserVo extends CmmVo {

    private Long no;                //rownum
    private Long userSno;           //사용자 순번
    
    @NotBlank(message = "아이디는 필수값이고 빈값이거나 공백이 올 수 없습니다.")
    private String userId;          //사용자 아이디
    private String userPassword;    //사용자 비밀번호
    private String userName;        //사용자명
    private String userEmail;       //사용자 이메일
    private char useYn;             //사용여부

    /**
     * 비밀번호 암호화
     * @param passwordEncoder
     * @return
     */
    public UserVo hashPassword(PasswordEncoder passwordEncoder) {
        this.userPassword = passwordEncoder.encode(this.userPassword);
        return this;
    }

    /**
     * 비밀번호 비교
     * @param rawPassword
     * @param passwordEncoder
     * @return
     */
    public boolean checkPassword(String rawPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(rawPassword, this.userPassword);
    }


}
