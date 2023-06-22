package study.dev.thboard3.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.dev.thboard3.mapper.UserMapper;
import study.dev.thboard3.model.UserVo;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

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
    public void insertUser(UserVo userVo) {
        userMapper.insertUser(userVo);
    }

    /**
     * 사용자 정보 확인(로그인 시 활용)
     * @param userId
     * @return
     */
    public UserVo selectByUserId(String userId) {
        return userMapper.selectByUserId(userId);
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
}
