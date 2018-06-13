package com.fly.view.teaching;

import com.fly.pojo.teaching.Students;
import com.fly.service.teaching.StudentService;
import com.fly.util.Page;
import com.fly.util.system.PoiUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stu")
@CrossOrigin
/**
 * @author fanliyang
 */
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private PoiUtils poiUtils;

    /**
     * 下载excel表格
     * @param  response
     * @throws IOException
     */
    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {

        String[] tableHeader = {"姓名","地址","年龄","录入时间","登记人","是否家访","来源渠道"
                ,"状态","电话","QQ","学历","微信"};

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("学生表");
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        for (int i = 0 ; i<tableHeader.length;i++){
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(tableHeader[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i,50*100);
        }

        List<Students> list = studentService.downloadForExcel();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < list.size(); i++) {
            HSSFRow hssfRow = sheet.createRow(i+1);
            Students students = list.get(i);
            hssfRow.createCell(0).setCellValue(students.getName());
            hssfRow.createCell(1).setCellValue(students.getAddress());
            hssfRow.createCell(2).setCellValue(students.getAge());
            hssfRow.createCell(3).setCellValue(simpleDateFormat.format(students.getCreatetime()));
            hssfRow.createCell(4).setCellValue(students.getCreateuser());
            hssfRow.createCell(5).setCellValue(students.getIshome());
            hssfRow.createCell(6).setCellValue(students.getMsgsource());
            hssfRow.createCell(7).setCellValue(students.getPerstate());
            hssfRow.createCell(8).setCellValue(students.getPhone());
            hssfRow.createCell(9).setCellValue(students.getQq());
            hssfRow.createCell(10).setCellValue(students.getStustatus());
            hssfRow.createCell(11).setCellValue(students.getWeixin());
        }


        String fileName = "学生.xls";
        fileName = URLEncoder.encode(fileName,"UTF8");
        OutputStream outputStream = response.getOutputStream();
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename="+fileName);
        response.setContentType("application/vnd.ms-excel");
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();

    }

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

        List<String[]> strings = poiUtils.getWorkbookValue(file);
        List<Students> students = new ArrayList<>();
        for (String[] str : strings ) {

            Students student = new Students();

            student.setName(str[1]);
            student.setAge(Integer.valueOf(str[2]));
            student.setSex(str[3]);
            student.setPhone(str[4]);
            student.setStustatus(str[5]);


            student.setPerstate(str[6]);
            student.setMsgsource(str[7]);
            student.setAddress(str[8]);
            student.setQq(str[9]);
            student.setWeixin(str[10]);

            student.setContent(str[11]);
            student.setLearnforward(str[12]);
            student.setIsvalid(str[13]);
            student.setIspay(str[14]);

            student.setMoney(Double.valueOf(str[15]));
            student.setCreateuser(str[16]);
            student.setPremoney(Double.valueOf(str[17]));

            students.add(student);

        }

        return studentService.batchStudents(students);

    }

}
