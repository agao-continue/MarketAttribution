package com.hy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.mapper.ActivityKolGroupMapper;
import com.hy.pojo.ActivityKolGroup;
import com.hy.result.PageResult;
import com.hy.result.PageResultQuery;
import com.hy.service.ActivityKolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityKolImpl implements ActivityKolService {
    @Autowired
    private ActivityKolGroupMapper kolGroupMapper;

    @Override
    public PageResult<ActivityKolGroup> findActivityKolGroup(PageResultQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<ActivityKolGroup>list=kolGroupMapper.findActivityKolGroup(query);
        PageInfo<ActivityKolGroup>info=new PageInfo<>(list);
        return new PageResult<>(info.getPageNum(), info.getPageSize(), info.getTotal(), list);
    }
}
