package com.powernode.controller;

import com.powernode.beans.Activity;
import com.powernode.beans.Page;
import com.powernode.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/act")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping("indexActView")
    public String index(){
        return  "/workbench/activity/index";
    }

    @RequestMapping("getAll.json")
    @ResponseBody
    public Page load(Page page){
        List<Activity> all = activityService.getAll(page);

        int count = activityService.getCount();

        //总记录数
        page.setTotalRows(count);
        //每页显示多少行
        Integer rowsPerPage = page.getRowsPerPage();
        page.setData(all);
        //总页数
        if (count%rowsPerPage==0){
            page.setTotalPages(count/rowsPerPage);
        }
        else {
            page.setTotalPages((count/rowsPerPage)+1);
        }

        return page;
    }
}
