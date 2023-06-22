package study.dev.thboard3.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import study.dev.thboard3.model.UserVo;

import java.util.List;

@Mapper
public interface UserMapper {

    /* 사용자 목록 조회 */
    List<UserVo> selectUserList();

    /* 사용자 정보 저장 */
    void insertUser(UserVo userVo);

    /* 사용자 정보 확인(로그인 시 활용) */
    UserVo selectByUserId(@Param("userId") String userId);

    /* 아이디로 카운트 조회 */
    int selectIdCnt(@Param("userId") String userId);

    /* 사용자 상세 정보 */
    UserVo selectUserDetail(@Param("userId") String userId);

    /* 사용자 정보 수정 */
    void updateUser(UserVo userVo);

    /* 사용자 삭제 */
    void delUser(@Param("userSno") Long userSno, @Param("userId") String userId);
}
