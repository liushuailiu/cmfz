package com.fly.service;

import com.fly.dao.GradesMapper;
import com.fly.pojo.Grades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradsService {
    @Autowired
    GradesMapper mapper;
    public List<Grades> selectGrads(){
        return mapper.selectGradsByPage();
    }
}
