package study.dev.thboard3.board.file.model;

import lombok.Data;

@Data
//pages.json의 필드를 담을 객체
public class PageInfo {

    private String sheetName;   //시트명
    private String fileName;    //엑셀파일명
    private String headers[];   //헤더 목록
}
