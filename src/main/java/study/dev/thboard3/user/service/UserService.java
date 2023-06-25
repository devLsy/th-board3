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

import javax.validation.ValidationException;
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
    public List<UserVo> selectUserList() throws Exception{
        return userMapper.selectUserList();
    }

    /**
     * 사용자 정보 저장
     * @param userVo
     */
    @Transactional
    public ResponseEntity regUser(UserVo userVo, BindingResult br) throws Exception{
        //parameter 검증 실패
        if (br.hasErrors()) {
            invokeErrors(this.getClass().getName(), br);
        }
        //결과 코드값
        Map<String, Object> resultMap = new HashMap<>();
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
    public UserVo loginProc(UserVo userVo, BindingResult br) throws Exception{
        //parameter 검증 실패
        if (br.hasErrors()) {
            invokeErrors(this.getClass().getName(), br);
        }
        UserVo findUser = userMapper.selectByUserId(userVo.getUserId());
        log.info("findUser = {}", findUser);
        return (findUser.checkPassword(userVo.getUserPassword(), passwordEncoder) == true ? findUser : null);
    }

    /**
     * 아이디 중복 확인
     * @param userId
     * @return
     */
    public ResponseEntity checkDuplicateId(String userId) throws Exception{
        //결과 코드값
        Map<String, Object> resultMap = new HashMap<>();

        if (userId != null) {
            int count = userMapper.selectIdCnt(userId);
            resultMap.put("code", (count > 0) ? ResultCode.DUPLICATE.getCode() : ResultCode.FAIL.getCode());
        }
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
    
    /**
     * 사용 유저 전체 카운트 조회
     * @return
     */
    public int selectAllUsers() throws Exception{
        return userMapper.selectAllUsers();
    }

    /**
     * 사용자 상세 정보
     * @param userId
     * @return
     */
    public UserVo selectUserDetail(String userId) throws Exception{
        return userMapper.selectUserDetail(userId);
    }

    /**
     * 사용자 정보 수정
     * @param userVo
     */
    @Transactional
    public void updateUser(UserVo userVo) throws Exception{
        userMapper.updateUser(userVo);
    }

    /**
     * 사용자 삭제
     * @param userSno
     * @param userId
     */
    @Transactional
    public void delUser(Long userSno, String userId) throws Exception{
        userMapper.delUser(userSno, userId);
    }

    @Transactional
    public void regUser(UserVo userVo) throws Exception{
    }
}
