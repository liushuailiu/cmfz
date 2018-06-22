package com.fly.view.teaching;

import com.fly.service.mysql.permission.UserService;
import com.fly.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author fly
 */
@CrossOrigin
@RequestMapping(value = "/consultant",name = "咨询师管理")
@RestController
public class ConsultantController {

    @Autowired
    private UserService systemUserService;


    /**
     * 查询所有咨询师
     * @return List<user>
     */
    @PostMapping(value = "/query",name = "查询所有咨询师")
    public Page selectUserAll( @RequestParam("page") Integer page , @RequestParam("limit") Integer limit){
        return systemUserService.queryUserRoleByConsultant(page,limit);
    }

}
