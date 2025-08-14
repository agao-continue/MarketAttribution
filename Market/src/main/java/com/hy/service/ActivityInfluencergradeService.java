package com.hy.service;

import com.hy.pojo.ActivityPlatformInfluencergradeGroup;
import com.hy.result.PageResult;
import com.hy.result.PageResultQuery;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ActivityInfluencergradeService {


    PageResult<ActivityPlatformInfluencergradeGroup> findActivityInfluence(PageResultQuery query);

    void findActivityInfluenceDl(HttpServletResponse response, PageResultQuery query) throws IOException;
}
