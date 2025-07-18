package com.hy.service;

import com.hy.pojo.MediaSlot;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ActivityMediaSlotAnalysisService {

    List<String> findContactPoint() ;

    List<MediaSlot> findMediaSlot(String cid,String point,String orderField,String orderAD);

    void findMediaSlotDl(HttpServletResponse response, String cid, String point, String orderField, String orderAD) throws IOException;
}
