package com.powernode.mapper;

import com.powernode.beans.Activity;
import com.powernode.beans.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityMapper extends BaseMapper<Activity,String>{

    List<Activity> getAll(@Param("start") int start,@Param("rp") int rp);
    int getCount();
}
