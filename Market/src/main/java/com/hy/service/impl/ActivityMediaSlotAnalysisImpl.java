package com.hy.service.impl;

import com.hy.mapper.ActivityMediaSlotAnalysisMapper;
import com.hy.service.ActivityMediaSlotAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ActivityMediaSlotAnalysisImpl implements ActivityMediaSlotAnalysisService {
    @Autowired
    ActivityMediaSlotAnalysisMapper activityMediaSlotAnalysisMapper;

    @Override
    public List<String> findContactPoint() {
      return activityMediaSlotAnalysisMapper.findContactPoint();
    }
}
