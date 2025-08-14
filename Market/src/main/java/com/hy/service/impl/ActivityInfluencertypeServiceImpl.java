package com.hy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.mapper.ActivityPlatformInfluencertypeGroupMapper;
import com.hy.pojo.ActivityPlatformInfluencertypeGroup;
import com.hy.result.PageResult;
import com.hy.result.PageResultQuery;
import com.hy.service.ActivityInfluencertypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class ActivityInfluencertypeServiceImpl implements ActivityInfluencertypeService {
    @Autowired
    private ActivityPlatformInfluencertypeGroupMapper activityInfluencertypeGroupMapper;
    @Override
    public PageResult<ActivityPlatformInfluencertypeGroup> findActivityInfluencertype(PageResultQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<ActivityPlatformInfluencertypeGroup>list=activityInfluencertypeGroupMapper.findActivityInfluencertype(query);
        PageInfo<ActivityPlatformInfluencertypeGroup> info = new PageInfo<>(list);
        return new PageResult(info.getPageNum(),info.getPageSize(),info.getTotal(),list);
    }

    @Override
    public void findActivityInfluencertypeDl(HttpServletResponse response, PageResultQuery query) {

    }
}
