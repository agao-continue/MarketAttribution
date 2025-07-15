package com.hy.service;

import com.hy.pojo.ActivityAnalysis;
import com.hy.pojo.Alog;

import java.util.List;

public interface MarketService {

    String test();

    List<ActivityAnalysis> selectAll();

    List<ActivityAnalysis> findNewList();

    ActivityAnalysis getAaByCid(String cid);
}
