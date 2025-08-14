package com.hy.service;

import com.hy.pojo.ActivityPlatformInfluencertypeGroup;
import com.hy.result.PageResult;
import com.hy.result.PageResultQuery;

import javax.servlet.http.HttpServletResponse;

public interface ActivityInfluencertypeService {
    PageResult<ActivityPlatformInfluencertypeGroup> findActivityInfluencertype(PageResultQuery query);

    void findActivityInfluencertypeDl(HttpServletResponse response, PageResultQuery query);
}
