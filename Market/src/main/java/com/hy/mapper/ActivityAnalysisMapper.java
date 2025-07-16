package com.hy.mapper;

import com.hy.pojo.ActivityAnalysis;
import com.hy.pojo.ActivityAnalysisExample;
import java.util.List;

import com.hy.pojo.Alog;
import com.hy.pojo.AlogAvg;
import org.apache.ibatis.annotations.Param;

public interface ActivityAnalysisMapper {
    int countByExample(ActivityAnalysisExample example);

    int deleteByExample(ActivityAnalysisExample example);

    int insert(ActivityAnalysis record);

    int insertSelective(ActivityAnalysis record);

    List<ActivityAnalysis> selectByExample(ActivityAnalysisExample example);

    int updateByExampleSelective(@Param("record") ActivityAnalysis record, @Param("example") ActivityAnalysisExample example);

    int updateByExample(@Param("record") ActivityAnalysis record, @Param("example") ActivityAnalysisExample example);

    List<ActivityAnalysis> findNewList();

    Alog getAlog(@Param("cid") String cid);

    ActivityAnalysis selectByCid(String cid);

    AlogAvg getAlogAvg(String cid);
}