package com.hy.service;

import com.hy.pojo.ActivityAnalysis;
import com.hy.pojo.Alog;
import com.hy.pojo.ConversionIncome;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface MarketService {

    String test();

    List<ActivityAnalysis> selectAll();

    List<ActivityAnalysis> findNewList();

    ActivityAnalysis getAaByCid(String cid);

    void anDownload(HttpServletResponse response) throws IOException;

    ConversionIncome getConvertionIncome(String cid,String status);

    void conversionIncomeDl(HttpServletResponse response,String cid,String status) throws IOException;


}
