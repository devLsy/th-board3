package study.dev.thboard3.cmm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.dev.thboard3.cmm.model.PaginationInfo;

@Service
@Slf4j
public class CmmnService {


    /**
     * 페이지네이션 세팅
     * @param currentPage
     * @param totalCount
     * @return
     */
    public PaginationInfo setPagination(int currentPage, int totalCount) {
        PaginationInfo pageVo = new PaginationInfo();
        pageVo.setCurrentPage(currentPage);
        pageVo.setTotalCount(totalCount);
        return pageVo;
    }
}
