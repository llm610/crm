package com.powernode.service;

import com.powernode.beans.Activity;
import com.powernode.beans.Page;

import java.util.List;

public interface ActivityService {
    List<Activity> getAll(Page page);
    int getCount();
}
