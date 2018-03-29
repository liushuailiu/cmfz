package com.fly.view;

import com.fly.service.GradsService;
import com.fly.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grad")
public class GradsController {
    @Autowired
    GradsService service ;
    @RequestMapping("/page")
    public Page selectGrads(@RequestParam("page")Integer page, @RequestParam("limit") Integer limit){
        PageHelper.startPage(page,limit);
        PageInfo pageInfo = new PageInfo(service.selectGrads());
        Page p = new Page(pageInfo);
        return p;
    }

}
