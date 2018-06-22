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
@RequestMapping(value = "/stu",name = "学生管理")
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

        // 定义表头
        String[] tableHeader = {"姓名","地址","年龄","录入时间","登记人","是否家访","来源渠道"
                ,"状态","电话","QQ","学历","微信"};

        // 创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 设置单元格样式
        HSSFCellStyle style = workbook.createCellStyle();
        //居中
        style.setAlignment(HorizontalAlignment.CENTER);

        // 创建第一个工作表
        HSSFSheet sheet = workbook.createSheet("学生表");

        // 创建第一行
        HSSFRow row = sheet.createRow(0);

        for (int i = 0 ; i<tableHeader.length;i++){
            // 创建单元格
            HSSFCell cell = row.createCell(i);
            // 给单元格设置内容
            cell.setCellValue(tableHeader[i]);
            //将单元格居中
            cell.setCellStyle(style);
            //自动添加列
            sheet.autoSizeColumn(i);
            //列宽
            sheet.setColumnWidth(i,50*100);
        }


        // 获取要导出的所有学生
        List<Students> list = studentService.downloadForExcel();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < list.size(); i++) {
            // 从第二行开始
            HSSFRow hssfRow = sheet.createRow(i+1);
            // 每一行对应的学生
            Students students = list.get(i);
            // 给每个单元格赋值
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

        // 设置excel文件名称
        String fileName = "学生.xls";
        //避免下载文件名出现乱码
        fileName = URLEncoder.encode(fileName,"UTF8");
        //开始输出工作簿
        OutputStream outputStream = response.getOutputStream();
        // 重置response设置
        response.reset();
        response.setHeader("Content-disposition", "attachment;filename="+fileName);
        response.setContentType("application/vnd.ms-excel");
        // 发送工作簿
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
        // 得到每一行内容
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
