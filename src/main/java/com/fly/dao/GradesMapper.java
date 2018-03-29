package com.fly.dao;

import com.fly.pojo.Grades;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradesMapper {

    int deleteByPrimaryKey(Integer cId);

    int insert(Grades record);

    int insertSelective(Grades record);

    Grades selectByPrimaryKey(Integer cId);

    List<Grades> selectGradsByPage();

    int updateByPrimaryKeySelective(Grades record);

    int updateByPrimaryKey(Grades record);
}