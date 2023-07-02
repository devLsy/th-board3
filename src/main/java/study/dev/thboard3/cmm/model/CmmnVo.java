package study.dev.thboard3.cmm.model;

import lombok.Data;

@Data
public class CmmnVo {

    /* search field */
    private String type;                //검색 타입
    private String keyword;             //검색 키워드

    /* pagination field */
    private int currentPage = 1;      //현재 페이지
    private int totalCount;       //전체 건수
    private int firstRecordIndex; // 첫번째 게시물 인덱스
    private int lastRecordIndex;  // 마지막 게시물 인덱스

    /* date field */
    private String regDate;         //등록일
    private String modDate;         //수정일
    
}
