package com.hy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.mapper.ActivityPlatformContentGroupMapper;
import com.hy.pojo.ActivityPlatformContentGroup;
import com.hy.pojo.ActivityPlatformGroup;
import com.hy.result.PageResult;
import com.hy.result.PageResultQuery;
import com.hy.service.ActivityPlatformContentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityPlatformContentGroupServiceImpl implements ActivityPlatformContentGroupService {
    @Autowired
    private ActivityPlatformContentGroupMapper activityPlatformContentGroupMapper;
    @Override
    public PageResult<ActivityPlatformContentGroup> findActivityPlatformContentGroup(PageResultQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<ActivityPlatformContentGroup>list= activityPlatformContentGroupMapper.findActivityPlatformContentGroup(query);
        PageInfo<ActivityPlatformContentGroup> info=new PageInfo<>(list);
        return new PageResult<>(info.getPageNum(),info.getPageSize(),info.getTotal(),info.getList());
    }
}
