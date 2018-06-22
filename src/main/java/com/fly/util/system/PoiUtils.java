package com.fly.util.system;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fly
 */

@Component
public class PoiUtils {

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    public List<String[]> getWorkbookValue(MultipartFile file){

        Workbook workbook = this.getWorkBook(file);

        List<String[]> list = new ArrayList<>();

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            //便利每一张表
            Sheet sheet = workbook.getSheetAt(i);
            if(sheet==null){
                continue;
            }

            for (int j = sheet.getFirstRowNum()+1 ; j <= sheet.getLastRowNum() ; j++) {
                //便利每一行
                Row row = sheet.getRow(j);
                if(row==null){
                    continue;
                }

                String[] strings = new String[row.getPhysicalNumberOfCells()];

                for (int k = row.getFirstCellNum(); k < row.getLastCellNum() ; k++) {
                    Cell cell = row.getCell(k);
                    if(cell == null){
                        continue;
                    }

                    String value = this.getCellValue(cell);
                    strings[k] = value;
                }
                list.add(strings);
            }
        }
        return list;
    }

    /**
     * 根据文件不同后缀,得到不同类型的工作簿
     * @param file
     * @return
     */
    private Workbook getWorkBook(MultipartFile file){
        Workbook workbook = null;
        if(file != null){
            // 得到上传文件名称
            String fileName = file.getOriginalFilename();

            try {
                InputStream inputStream = file.getInputStream();
                // 如果是xls文件
                if(fileName.endsWith(XLS)) {
                    workbook = new HSSFWorkbook(inputStream);
                }else if(fileName.endsWith(XLSX)){
                    workbook = new XSSFWorkbook(inputStream);
                }

            } catch (IOException e) {

            }

        }
        return workbook;
    }

    private String getCellValue(Cell cell){
        String value = "";
        if(cell != null){
            switch (cell.getCellTypeEnum()){
                case NUMERIC:
                    // 9 --> 9.00
                    cell.setCellType(CellType.STRING);
                    value = cell.getStringCellValue();
                    break;
                case ERROR:
                    value = "非法内容";
                    break;
                case STRING:
                    value = cell.getStringCellValue();
                    break;
                case BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case BLANK:
                case _NONE:
                    value = "";
                    break;
                case FORMULA:
                    value = cell.getCellFormula();
                    break;
                default:
                    value = "未知";
                    break;
            }
        }
        return value;
    }

}
