package com.fly.service.oracle;

import com.fly.dao.oracle.scott.EmpMapper;
import com.fly.pojo.oracle.scott.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fly
 */
@Service
public class EmpService {

    @Autowired
    private EmpMapper empMapper;

    public List<Emp> selectAllEmp(){
        return empMapper.selectEmpAll();
    }

}
