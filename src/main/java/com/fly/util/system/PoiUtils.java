package com.fly.util.system;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.Date;

/**
 * @author fly
 */
public class PoiUtils {

    public static void main(String[] args) throws IOException {
        //得到桌面路劲
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        String desktop = fileSystemView.getHomeDirectory().getPath();
        //设置excel文件名称
        String filePath = desktop + "/test.xls";

        //准备输出流,写文件
        File file = new File(filePath);
        OutputStream outputStream = new FileOutputStream(file);

//        准备一个excel文件
//        HSSF － 提供读写Microsoft Excel XLS格式档案的功能。
//        XSSF － 提供读写Microsoft Excel OOXML XLSX格式档案的功能。
//        HWPF － 提供读写Microsoft Word DOC97格式档案的功能。
//        XWPF － 提供读写Microsoft Word DOC2003格式档案的功能。
//        HSLF － 提供读写Microsoft PowerPoint格式档案的功能。
//        HDGF － 提供读Microsoft Visio格式档案的功能。
//        HPBF － 提供读Microsoft Publisher格式档案的功能。
//        HSMF － 提供读Microsoft Outlook格式档案的功能。

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

        // 日期格式化
        HSSFCellStyle hssfCellStyle = hssfWorkbook.createCellStyle();
        HSSFCreationHelper creationHelper = hssfWorkbook.getCreationHelper();
        hssfCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));

        // 保留两位数字
        HSSFCellStyle hssfCellStyle1 = hssfWorkbook.createCellStyle();
        hssfCellStyle1.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));

        // 准备工作表
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("第一个工作表");
        hssfSheet.setColumnWidth(2,20*256);
        // 开始创建第一行
        HSSFRow hssfRow = hssfSheet.createRow(0);
        // 设置第一行的每一列
        hssfRow.createCell(0).setCellValue("学号");
        hssfRow.createCell(1).setCellValue("姓名");
        hssfRow.createCell(2).setCellValue("登记时间");
        hssfRow.createCell(3).setCellValue("学费");
        hssfRow.createCell(4).setCellValue("班主任姓名");
        hssfRow.createCell(5).setCellValue("专业老师备注");
        hssfRow.createCell(6).setCellValue("班主任备注");
        hssfRow.createCell(7).setCellValue("评分");
        hssfRow.setHeightInPoints(30);

        HSSFRow hssfRow1 = hssfSheet.createRow(1);
        hssfRow1.createCell(0).setCellValue("1");
        hssfRow1.createCell(1).setCellValue("樊立扬");

        HSSFCell hssfCell = hssfRow1.createCell(2);
        hssfCell.setCellStyle(hssfCellStyle);
        hssfCell.setCellValue(new Date());

        HSSFCell hssfCell1 = hssfRow1.createCell(3);
        hssfCell1.setCellStyle(hssfCellStyle1);
        hssfCell1.setCellValue(10000.598);

        hssfWorkbook.setActiveSheet(0);
        hssfWorkbook.write(outputStream);
        outputStream.close();

    }

}
