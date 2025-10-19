package study.dev.thboard3.survey.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SurveyMapper {
    /**
     * (외부)사용자가 응시한 설문 목록 조회
     * @param userId
     * @return
     */
    List<Map<String, Object>> selectExtSurveyList(@Param("userId") String userId);

    /**
     * 특정 사용자의 특정 응시 회차에 대한 상세 정보를 조회합니다.
     * @param userId 사용자 ID
     * @param sessionKey 응시 회차 KEY
     * @return 피벗된 단일 응시 상세 정보 (Map)
     */
    Map<String, Object> selectSurveyDetail(@Param("userId") String userId, @Param("sessionKey") String sessionKey);
}
