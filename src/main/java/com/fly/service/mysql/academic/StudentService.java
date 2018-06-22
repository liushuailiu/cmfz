package com.fly.service.mysql.academic;

import com.fly.dao.mysql.student.StudentsMapper;
import com.fly.pojo.academic.Students;
import com.fly.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fanliyang
 * 教务
 */
@Service
public class StudentService {

    @Autowired
    private StudentsMapper studentsMapper;

    public Page selectStuByPage(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Students> students = studentsMapper.selectStudentAll();
        PageInfo pageInfo = new PageInfo(students);
        return new Page(pageInfo);
    }

    public Page batchStudents(List<Students> students) {
        boolean flag = true;
        for (Students s : students){
            Integer count = studentsMapper.insertSelective(s);
            if(count<1) {
                flag = false;
            }
        }

        Page page = flag ? new Page(200):new Page(500);
        return page;

    }

    /**
     * 查询学生,导出excel
     * @return
     */
    public List<Students> downloadForExcel() {
        return studentsMapper.selectStudentAll();
    }
}
