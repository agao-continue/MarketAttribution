package com.hy.service;

import com.github.pagehelper.PageInfo;
import com.hy.pojo.MediaSlot;
import com.hy.pojo.PlatBean;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ActivityMediaSlotAnalysisService {

    List<String> findContactPoint() ;

    PageInfo<MediaSlot> findMediaSlot(String cid, String point, String orderField, String orderAD, int pageNum, int pageSize,String media);

    void findMediaSlotDl(HttpServletResponse response, String cid, String point, String orderField, String orderAD,int pageNum,int pageSize,String media) throws IOException;

    List<String> findAllMedia();

    Map<String, Map<String, List<PlatBean>>> platformDeliveryComparison(String cid1, String cid2, String field);

    void platformDeliveryComparisonDl(HttpServletResponse response, String cid1, String cid2, String field) throws IOException;

    Map<String, Map<String, List<PlatBean>>> distributionOfSubMediaPlatforms(String cid1, String cid2, String field);

    void distributionOfSubMediaPlatformsDl(HttpServletResponse response, String cid1, String cid2, String field) throws IOException;

    Map<String, Map<String, List<PlatBean>>> mediaContactComposition(String cid1, String cid2, String field);

    void mediaContactCompositionDl(HttpServletResponse response, String cid1, String cid2, String field) throws IOException;
}
