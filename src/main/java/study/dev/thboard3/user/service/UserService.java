package study.dev.thboard3.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import study.dev.thboard3.model.enu.ResultCode;
import study.dev.thboard3.user.mapper.UserMapper;
import study.dev.thboard3.user.model.UserVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static study.dev.thboard3.cmm.utils.ValidatorUtils.invokeErrors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * 사용자 목록 조회
     * @return
     */
    public List<UserVo> selectUserList() {
        return userMapper.selectUserList();
    }

    /**
     * 사용자 정보 저장
     * @param userVo
     */
    public ResponseEntity regUser(UserVo userVo, BindingResult br) throws Exception{
        //parameter 검증 실패
        if (br.hasErrors()) {
            invokeErrors(this.getClass().getName(), br);
        }
        //결과 코드값
        Map<Object, Object> resultMap = new HashMap<>();
        //비밀번호 암호화
        String encodePassword = userVo.hashPassword(passwordEncoder).getUserPassword();
        log.info("encodePassword = {}", encodePassword);

        int count = userMapper.insertUser(userVo);
        resultMap.put("code", (count > 0) ? ResultCode.SUCCESS.getCode() : ResultCode.FAIL.getCode());
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    /**
     * 사용자 정보 확인(로그인 시 활용)
     * @param userVo
     * @param br
     * @return
     */
    public String loginProc(UserVo userVo, BindingResult br) {
        if (br.hasErrors()) {
            invokeErrors(this.getClass().getName(), br);
        }
        UserVo findUser = userMapper.selectByUserId(userVo.getUserId());
        return (findUser.checkPassword(userVo.getUserPassword(), passwordEncoder) == true ? findUser.getUserId() : "none");
    }

    /**
     * 아이디로 카운트 조회
     * @param userId
     * @return
     */
    public int selectIdCnt(String userId) {
        return userMapper.selectIdCnt(userId);
    }

    /**
     * 사용 유저 전체 카운트 조회
     * @return
     */
    public int selectAllUsers() {
        return userMapper.selectAllUsers();
    }

    /**
     * 사용자 상세 정보
     * @param userId
     * @return
     */
    public UserVo selectUserDetail(String userId) {
        return userMapper.selectUserDetail(userId);
    }

    /**
     * 사용자 정보 수정
     * @param userVo
     */
    public void updateUser(UserVo userVo) {
        userMapper.updateUser(userVo);
    }

    /**
     * 사용자 삭제
     * @param userSno
     * @param userId
     */
    public void delUser(Long userSno, String userId) {
        userMapper.delUser(userSno, userId);
    }

    public void regUser(UserVo userVo) {
    }
}
