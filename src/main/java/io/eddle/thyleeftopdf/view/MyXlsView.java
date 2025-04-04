package io.eddle.thyleeftopdf.view;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import java.util.Map;

@Component("my_xls_view")
public class MyXlsView extends AbstractXlsView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //엑셀 시트 생성
        Sheet sheet = workbook.createSheet();
        //로우 생성  sheet.createRow(index)
        Row row = sheet.createRow(0);

        //열생성 및 데이터 설정 setCellValue 값 설정
        for(int i = 0; i < 5; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(i);
        }
    }
}
