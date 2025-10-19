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

}
