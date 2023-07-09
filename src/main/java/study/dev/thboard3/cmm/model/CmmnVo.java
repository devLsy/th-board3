package study.dev.thboard3.cmm.model;

import lombok.Data;

@Data
public class CmmnVo {

    private String keyword;
    private String type;

    private int currentPage = 1;      //현재 페이지
    private int totalCount;       //전체 건수
    private int firstRecordIndex; // 첫번째 게시물 인덱스
    private int lastRecordIndex;  // 마지막 게시물 인덱스

    private int no;
    private String regDate;
    private String modDate;
}
