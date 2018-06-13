package com.fly.service.teaching;

import com.fly.dao.teaching.StudentsMapper;
import com.fly.pojo.teaching.Students;
import com.fly.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fanliyang
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
}
