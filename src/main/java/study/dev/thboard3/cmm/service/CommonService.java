package study.dev.thboard3.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.dev.thboard3.cmm.model.PaginationInfo;
import study.dev.thboard3.user.model.UserVo;

import javax.servlet.http.HttpSession;

@Service
@Slf4j
@RequiredArgsConstructor
//공통 서비스
public class CommonService {

    /**
     * 세션 사용자 정보 조회
     * @param session
     * @return
     */
    public UserVo getSessionUserInfo(HttpSession session) {
        return (UserVo) session.getAttribute("userVo");
    }

    /**
     * 페이지네이션 객체 세팅
     * @param currentPage
     * @param totalCount
     * @return
     */
    public PaginationInfo getPagination(int currentPage, int totalCount) {
        PaginationInfo pageVo = new PaginationInfo();
        pageVo.setCurrentPage(currentPage);
        pageVo.setTotalCount(totalCount);
        return pageVo;
    }
}
