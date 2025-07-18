package com.hy.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.hy.pojo.*;
import com.hy.result.ContentResult;
import com.hy.service.ActivityMediaSlotAnalysisService;
import com.hy.service.AlogService;
import com.hy.service.MarketService;
import com.hy.utils.ExcelUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hy.result.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.hy.utils.AlogUtil.getAlogDiff;

@RestController
@MapperScan("com.hy.mapper")
public class MarketController {
    @Autowired
    private MarketService marketService;
    @Autowired
    private AlogService alogService;
    @Autowired
    private ActivityMediaSlotAnalysisService activityMediaSlotAnalysisService;
    //test
    @GetMapping("/test")
    public Result test() {
        String str = marketService.test();
        return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,str);
    }
    //查询所有
    @GetMapping("/selectAllAnalysis")
    public Result selectAllAnalysis() {
        List<ActivityAnalysis> list = marketService.selectAll();
        return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,list);
    }
    //查询最新活动列表(倒序)
    @GetMapping("/findNewAcListGzy")
    public Result findNewAcListGzy() {
        List<ActivityAnalysis> list = marketService.findNewList();
        return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,list);
    }
    //单活动全部指标（基本指标＋算法指标）
    @GetMapping("/findAllTargetGzy")
    public Result findAllTargetGzy(String cid) {
        Alog alog = alogService.getAlog(cid);
        ActivityAnalysis activityAnalysis = marketService.getAaByCid(cid);
        activityAnalysis.setAlog(alog);
        return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,activityAnalysis);
    }
    //整体活动指标（同一活动下的均值计算与涨跌计算）
    @GetMapping("/getOverallIndicatorGzy")
    public Result getOverallIndicatorGzy(String cid) {
        ActivityAnalysis activityAnalysis = marketService.getAaByCid(cid);//基本信息
        Alog alog = alogService.getAlog(cid);
        AlogAvg alogAvg =alogService.getAlogAvg(cid);//均值计算
        AlogDiff alogDiff =getAlogDiff(alog,alogAvg);//涨跌计算
        activityAnalysis.setAlogAvg(alogAvg);
        activityAnalysis.setAlogDiff(alogDiff);
        return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,activityAnalysis);
    }
    /**
     * 下载Excel表格的控制器方法
     * @param response HttpServletResponse对象，用于向客户端返回响应
     * @throws IOException 如果在处理HTTP响应时发生I/O错误
     */
    @GetMapping("/anDownloadGzy")
    public void andownload(HttpServletResponse response) throws IOException {
        // 调用Service层的方法处理Excel下载业务逻辑，将响应对象传递给Service
        marketService.anDownload(response);
    }
    //转化收益分析
    @GetMapping("/getConversionIncomeGzy")
    public Result getConversionIncomeGzy(String cid,String status) {
        ConversionIncome list=marketService.getConvertionIncome(cid,status);
        return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,list);
    }
    //转化效益分析Excel下载
    @GetMapping("/conversionIncomeDl")
    public void conversionIncomeDl(HttpServletResponse response,String cid ,String status) throws IOException {
        marketService.conversionIncomeDl(response,cid,status);
    }
    //查询所有触点类型
    @GetMapping("/findContactPointGzy")
    public Result findContactPointGzy() {
        List<String> list = activityMediaSlotAnalysisService.findContactPoint();
        return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,list);
    }
    //查询当前活动下全部媒介归因排名
    @GetMapping("/findMediaSlot")
    public Result findMediaSlot(String cid,String point,String orderField,String orderAD) {
        List<MediaSlot> mediaSlot = activityMediaSlotAnalysisService.findMediaSlot(cid,point,orderField,orderAD);
        return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,mediaSlot);
    }
    //媒介归因排名下载
    @GetMapping("/findMediaSlotDl")
    public void findMediaSlotDl(HttpServletResponse response,String cid,String point,String orderField,String orderAD) throws IOException {
        activityMediaSlotAnalysisService.findMediaSlotDl(response,cid,point,orderField,orderAD);
    }
}
