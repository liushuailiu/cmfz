package com.fly.view.teaching;

import com.fly.service.teaching.StudentService;
import com.fly.util.Page;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/stu")
@CrossOrigin
/**
 * @author fanliyang
 */
public class StudentController {

    @Autowired
    private StudentService studentService;

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    /**
     * 分页查询所有学生
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("/page")
    public Page selectStuAll(Integer page,Integer limit){
        return studentService.selectStuByPage(page,limit);
    }

    /**
     * 用户上传excel,解析excel,将excel数据加入到数据库中
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Page uploadStuByExcel(@RequestParam("file")MultipartFile file){
        Workbook workbook = this.getWorkBook(file);
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
                for (int k = row.getFirstCellNum(); k <= row.getLastCellNum() ; k++) {
                    Cell cell = row.getCell(k);
                    if(cell == null){
                        continue;
                    }
                    String value = cell.getStringCellValue();
                    System.out.println(value);
                }
            }
        }
        return null;
    }

    /**
     * 根据文件不同后缀,得到不同类型的工作簿
     * @param file
     * @return
     */
    private Workbook getWorkBook(MultipartFile file){
        Workbook workbook = null;
        if(file != null){
            String fileName = file.getOriginalFilename();

            try {
                InputStream inputStream = file.getInputStream();
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

}
