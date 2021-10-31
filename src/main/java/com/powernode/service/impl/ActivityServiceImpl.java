package com.powernode.service.impl;

import com.powernode.beans.Activity;
import com.powernode.beans.Page;
import com.powernode.mapper.ActivityMapper;
import com.powernode.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> getAll(Page page) {

        int start = (page.getCurrentPage() - 1) * page.getRowsPerPage();


        return activityMapper.getAll(start,page.getRowsPerPage()) ;
    }

    @Override
    public int getCount() {
        return activityMapper.getCount();
    }
}
