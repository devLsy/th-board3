package study.dev.thboard3.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import study.dev.thboard3.model.UserVo;

@SpringBootTest
@Slf4j
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("사용자등록")
    @Commit
    public void 사용자등록() throws Exception {
        //givin
        UserVo userVo = new UserVo();
        userVo.setUserId("lsy");
        userVo.setUserPassword("1234");
        userVo.setUserName("어흥");
        userVo.setUserEmail("lsy@naver.com");
        //when
        userService.insertUser(userVo);
        //then
    }

}