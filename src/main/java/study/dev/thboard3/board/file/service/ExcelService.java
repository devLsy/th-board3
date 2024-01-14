package study.dev.thboard3.board.file.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import study.dev.thboard3.board.file.model.PageInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Service("excelService")
public class ExcelService {

    @Value("classpath:pages.json")
    private Resource pagesJsonResources;

    /**
     * 엑셀 다운로드 처리
     * @param paramData
     * @param response
     * @param pageGubun
     * @throws IOException
     */
    public void download(String paramData, HttpServletResponse response, String pageGubun) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        //json파일에서 페이지 정보 읽어서 map으로 반환
        Map<String, PageInfo> pageMap = objectMapper.readValue(pagesJsonResources.getInputStream(), new TypeReference<Map<String, PageInfo>>() {});
        //구분자에 해당되는 json파일의 내용이 담긴 객체
        PageInfo pageInfo = pageMap.get(pageGubun);
        // map타입의 list로 반환
        List<Map<String, String>> dataList = convertJsonToList(paramData);

        if (pageInfo != null) {
            // 엑셀 생성/다운로드 처리
            excelParsingProcess(dataList, response, pageInfo.getSheetName(), pageInfo.getFileName(), pageInfo.getHeaders());
        }
    }

    /**
     * 엑셀 파싱, 다운로드 처리
     * @param dataList
     * @param response
     * @param sheetName
     * @param fileName
     * @param headers
     * @throws IOException
     */
    private void excelParsingProcess(List<Map<String, String>> dataList,
                                     HttpServletResponse response,
                                     String sheetName,
                                     String fileName,
                                     String[] headers) throws IOException {
        //엑셀 생성
        Workbook wb = new XSSFWorkbook();
        //시트 생성
        Sheet sheet = wb.createSheet(sheetName);

        // header row  세팅
        Row headerRow = sheet.createRow(0);

        int cellNum = 0;
        for (String header : headers) {
            //헤더값 세팅
            headerRow.createCell(cellNum++).setCellValue(header);
        }

        // body row 세팅
        int rowNum = 1;
        for (Map<String, String> data : dataList) {
           Row row = sheet.createRow(rowNum++);

           cellNum = 0;
           //데이터 세팅
           for (String key : data.keySet()) {
               row.createCell(cellNum++).setCellValue(data.get(key));
           }
        }

        String extension = ".xlsx";

        // HTTP 응답 설정
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName + extension, "UTF-8"));

        wb.write(response.getOutputStream());
        wb.close();
    }

    /**
     * json 형식 데이터를 map타입의 list로 반환한다.
     * @param jsonData
     * @return
     * @throws JsonProcessingException
     */
    private List<Map<String, String>> convertJsonToList(String jsonData) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonData, new TypeReference<List<Map<String, String>>>() {});
    }


}
