package com.fly.dao;

import com.fly.pojo.LogMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface LogMessageMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(LogMessage record);

    int insertSelective(LogMessage record);

    LogMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogMessage record);

    int updateByPrimaryKeyWithBLOBs(LogMessage record);

    int updateByPrimaryKey(LogMessage record);
}