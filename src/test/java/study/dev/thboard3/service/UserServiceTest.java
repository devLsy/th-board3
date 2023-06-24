package study.dev.thboard3.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import study.dev.thboard3.user.model.UserVo;
import study.dev.thboard3.user.service.UserService;

@SpringBootTest
@Slf4j
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("사용자등록")
    @Commit
    @Disabled
    public void 사용자등록() throws Exception {
        //givin
        UserVo userVo = new UserVo();
        userVo.setUserId("lsy");
        userVo.setUserPassword("1234");
        userVo.setUserName("어흥");
        userVo.setUserEmail("lsy@naver.com");
        //when
        userService.regUser(userVo);
        //then
    }

    @Test
    @DisplayName("사용자상세")
    @Commit
    @Disabled
    public void 사용자상세() throws Exception {
        //givin
        UserVo userVo = new UserVo();
        userVo.setUserId("lsy");
        //when
        userService.selectUserDetail(userVo.getUserId());
        //then
    }

    @Test
    @DisplayName("아이디로카운트")
    @Commit
    @Disabled
    public void 아이디로카운트() throws Exception {
        //givin
        UserVo userVo = new UserVo();
        userVo.setUserId("lsy");
        //when
        userService.selectIdCnt(userVo.getUserId());
        //then
    }

    @Test
    @DisplayName("사용유저전체카운트조회")
    @Commit
    @Disabled
    public void 사용유저전체카운트조회() throws Exception {
        //givin
        UserVo userVo = new UserVo();
        userVo.setUserId("lsy");
        //when
        userService.selectAllUsers();
        //then
    }

    @Test
    @DisplayName("사용자정보확인")
    @Commit
    @Disabled
    public void 사용자정보확인() throws Exception {
        //givin
        UserVo userVo = new UserVo();
        userVo.setUserId("lsy");
        //when
        //then
    }

    @Test
    @DisplayName("사용자정보수정")
    @Commit
    @Disabled
    public void 사용자정보수정() throws Exception {
        //givin
        UserVo userVo = new UserVo();
        userVo.setUserId("lsy");
        userVo.setUserSno(2L);
        userVo.setUserEmail("lsy@gmail.com");
        //when
        userService.updateUser(userVo);
        //then
    }
    
    @Test
    @DisplayName("사용자삭제")
    @Commit
    @Disabled
    public void 사용자삭제() throws Exception {
        //givin
        UserVo userVo = new UserVo();
        userVo.setUserSno(2L);
        userVo.setUserId("lsy");
        //when
        userService.delUser(userVo.getUserSno(), userVo.getUserId());
        //then
    }
    
    @Test
    @DisplayName("암호화")
    @Commit
    @Disabled
    public void 암호화() throws Exception {
        //givin
        UserVo userVo = new UserVo();
        userVo.setUserPassword("1234");
        //when
        userVo.hashPassword(passwordEncoder);
        log.info("userVo = {}", userVo.getUserPassword());
        //then
    }



}