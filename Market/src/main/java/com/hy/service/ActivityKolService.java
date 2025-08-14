package com.hy.service;

import com.hy.pojo.ActivityKolGroup;
import com.hy.result.PageResult;
import com.hy.result.PageResultQuery;

public interface ActivityKolService {
    PageResult<ActivityKolGroup> findActivityKolGroup(PageResultQuery query);
}
