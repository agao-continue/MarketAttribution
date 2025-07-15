package com.hy.service.impl;

import com.hy.mapper.ActivityAnalysisMapper;
import com.hy.pojo.ActivityAnalysis;
import com.hy.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    private ActivityAnalysisMapper activityAnalysisMapper;

    @Override
    public String test() {
        return "ok";
    }

    @Override
    public List<ActivityAnalysis> selectAll() {
        return activityAnalysisMapper.selectByExample(null);
    }

    @Override
    public List<ActivityAnalysis> findNewList() {
        return activityAnalysisMapper.findNewList();
    }

    @Override
    public ActivityAnalysis getAaByCid(String cid) {
        return activityAnalysisMapper.selectByCid(cid);
    }
}
