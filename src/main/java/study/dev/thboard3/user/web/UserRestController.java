package study.dev.thboard3.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import study.dev.thboard3.user.model.UserVo;
import study.dev.thboard3.user.service.UserService;

import javax.validation.Valid;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/api")
public class UserRestController {

    private final UserService userService;

    /**
     * 회원가입
     * @param userVo
     * @param br
     * @return
     */
    @PostMapping("/reg")
    public ResponseEntity reg(@RequestBody @Valid UserVo userVo, BindingResult br) throws Exception {
        return userService.regUser(userVo, br);
    }

    /**
     * 아이디 중복 체크
     * @param userId
     * @return
     * @throws Exception
     */
    @PostMapping("/checkDuplicateId/{userId}")
    public ResponseEntity checkDupliacteId(@PathVariable String userId) throws Exception {
        return userService.checkDuplicateId(userId);
    }
}
