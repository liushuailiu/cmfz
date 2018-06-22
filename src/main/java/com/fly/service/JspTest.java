package com.fly.service;

import com.fly.dao.teaching.StudentsMapper;
import com.fly.pojo.teaching.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fly
 */


@Service
public class JspTest {

    @Autowired
    StudentsMapper studentsMapper;

    public List<Students> select(){

        return studentsMapper.selectStudentAll();
    }

}
