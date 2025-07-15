package com.hy.controller;

import com.hy.pojo.ActivityAnalysis;
import com.hy.pojo.ActivityAnalysisExample;
import com.hy.pojo.Alog;
import com.hy.result.ContentResult;
import com.hy.service.AlogService;
import com.hy.service.MarketService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hy.result.Result;

import java.util.List;

@RestController
@MapperScan("com.hy.mapper")
public class MarketController {
    @Autowired
    private MarketService marketService;
    @Autowired
    private AlogService alogService;
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
}
