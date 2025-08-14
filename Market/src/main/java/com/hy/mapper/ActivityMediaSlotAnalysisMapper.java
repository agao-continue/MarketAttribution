package com.hy.mapper;

import com.github.pagehelper.PageInfo;
import com.hy.pojo.ActivityMediaSlotAnalysis;
import com.hy.pojo.ActivityMediaSlotAnalysisExample;
import java.util.List;

import com.hy.pojo.MediaSlot;
import com.hy.pojo.PlatBean;
import org.apache.ibatis.annotations.Param;

public interface ActivityMediaSlotAnalysisMapper {
    int countByExample(ActivityMediaSlotAnalysisExample example);

    int deleteByExample(ActivityMediaSlotAnalysisExample example);

    int insert(ActivityMediaSlotAnalysis record);

    int insertSelective(ActivityMediaSlotAnalysis record);

    List<ActivityMediaSlotAnalysis> selectByExample(ActivityMediaSlotAnalysisExample example);

    int updateByExampleSelective(@Param("record") ActivityMediaSlotAnalysis record, @Param("example") ActivityMediaSlotAnalysisExample example);

    int updateByExample(@Param("record") ActivityMediaSlotAnalysis record, @Param("example") ActivityMediaSlotAnalysisExample example);

    List<String> findContactPoint();

    List<MediaSlot> findMediaSlot(@Param("cid") String cid, @Param("point") String point, @Param("orderField") String orderField, @Param("orderAD") String orderAD,@Param("media")  String media);

    List<String> findAllMedia();

    List<PlatBean> platformDeliveryComparison(@Param("cid1")String cid1,@Param("cid2") String cid2,@Param("field") String field);

    List<PlatBean> distributionOfSubMediaPlatforms(@Param("cid1")String cid1,@Param("cid2") String cid2,@Param("field") String field);

    List<PlatBean> mediaContactComposition(@Param("cid1")String cid1,@Param("cid2") String cid2,@Param("field") String field);


}