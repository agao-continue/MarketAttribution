package com.hy.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.hy.mapper.ActivityPlatformGroupMapper;
import com.hy.pojo.*;
import com.hy.result.ContentResult;
import com.hy.result.PageResult;
import com.hy.result.PageResultQuery;
import com.hy.service.*;
import com.hy.utils.ExcelUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hy.result.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import static com.hy.utils.AlogUtil.getAlogDiff;

@RestController
@RequestMapping ("/market")
public class MarketController {
    @Autowired
    private MarketService marketService;
    @Autowired
    private AlogService alogService;
    @Autowired
    private ActivityMediaSlotAnalysisService activityMediaSlotAnalysisService;
    @Autowired
    private ActivityGroupService activityGroupService;
    @Autowired
    ActivityPlatformGroupService activityPlatformGroupService;
    @Autowired
    ActivityPlatformContentGroupService  activityPlatformContentGroupService;
    @Autowired
    ActivityDirectionService activityDirectionService;
    @Autowired
    ActivityInfluencergradeService activityInfluencergradeService;
    @Autowired
    ActivityInfluencertypeService  activityInfluencertypeService;
    @Autowired
    ActivityKolService activityKolService;
    @Autowired
    DownloadAllService downloadAllService;
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
    //分页查询当前活动下全部媒介归因排名(触点)
    @GetMapping("/findMediaSlot")
    public Result findMediaSlot(String cid,String point,String orderField,String orderAD,int pageNum, int pageSize,String media) {
        PageInfo<MediaSlot> mediaSlot = activityMediaSlotAnalysisService.findMediaSlot(cid,point,orderField,orderAD,pageNum,pageSize,media);
        return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,mediaSlot);
    }
    //媒介归因排名下载
    @GetMapping("/findMediaSlotDl")
    public void findMediaSlotDl(HttpServletResponse response,String cid,String point,String orderField,String orderAD,int pageNum,int pageSize,String media) throws IOException {
        activityMediaSlotAnalysisService.findMediaSlotDl(response,cid,point,orderField,orderAD,pageNum,pageSize,media);
    }
    //查询所有媒介排名
    @GetMapping("/findAllMedia")
    public Result findAllMedia() {
        List<String> list = activityMediaSlotAnalysisService.findAllMedia();
        return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,list);
    }
    //按活动查询
    @PostMapping("/findActivityGroup")
    public Result findActivityGroup(@RequestBody PageResultQuery query) {
        List<ActivityGroup> list = activityGroupService.findActivityGroup(query.getCname());
        return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,list);
    }
    //分活动分媒介查询
    @PostMapping("/findActivityPlatformGroup")
    public Result findActivityPlatformGroup(@RequestBody PageResultQuery query) {
       PageResult<ActivityPlatformGroup> list= activityPlatformGroupService.findActivityPlatformGroup(query);
       return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,list);
    }
    //分活动分媒介分内容
    @PostMapping("/findActivityPlatformContentGroup")
    public Result findActivityPlatformContentGroup(@RequestBody PageResultQuery query) {
        PageResult<ActivityPlatformContentGroup> list = activityPlatformContentGroupService.findActivityPlatformContentGroup(query);
        return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,list);
    }
    //分活动分媒介分内容方向
    @PostMapping("/findActivityDirection")
    public Result findActivityDirection(@RequestBody PageResultQuery query) {
        PageResult<ActivityPlatformContentDirectionGroup> list =activityDirectionService.findActivityDirection(query);
        return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,list);
    }
    //分活动分媒介分内容方向下载
    @PostMapping("/findActivityDirectionDl")
    public void findActivityDirectionDl(HttpServletResponse response,@RequestBody PageResultQuery query) throws IOException {
        activityDirectionService.findActivityDirectionDl(response,query);
    }
    //分活动分媒介分博主等级
    @PostMapping("/findActivityInfluence")
    public Result findActivityInfluence(@RequestBody PageResultQuery query) {
        PageResult<ActivityPlatformInfluencergradeGroup> list =activityInfluencergradeService.findActivityInfluence(query);
        return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,list);
    }
    //分活动分媒介分博主等级下载
    @PostMapping("/findActivityInfluenceDl")
    public void findActivityInfluenceDl(HttpServletResponse response,@RequestBody PageResultQuery query) throws IOException {
        activityInfluencergradeService.findActivityInfluenceDl(response,query);
    }
    //分活动分媒介分类型
    @PostMapping("/findActivityInfluencertype")
    public Result findActivityInfluencertype(@RequestBody PageResultQuery query) {
       PageResult<ActivityPlatformInfluencertypeGroup> list= activityInfluencertypeService.findActivityInfluencertype(query);
       return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,list);
    }
    //分活动分媒介分类型下载
    @PostMapping("/findActivityInfluencertypeDl")
    public void findActivityInfluencertypeDl(HttpServletResponse response,@RequestBody PageResultQuery query) throws IOException {
        activityInfluencertypeService.findActivityInfluencertypeDl(response,query);
    }
    //分活动分帖子
    @PostMapping("/findActivityKolGroup")
    public Result findActivityKolGroup(@RequestBody PageResultQuery query) {
        PageResult<ActivityKolGroup>list=activityKolService.findActivityKolGroup(query);
        return  new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,list);
    }
    //kol全部统一下载
    @PostMapping("/downloadAllKol")
    public void downloadAllKol(HttpServletResponse response,@RequestBody PageResultQuery query) throws IOException {
        downloadAllService.downloadAll(response,query);
    }

//    ///////////////////////////////多活动归因//////////////////////////////////////

    //查找某一类型下的活动名称
    @GetMapping("/findTypeAndCname")
    public Result findTypeAndCname() {
        Map<String,List<String>> map=marketService.findTypeAndCname();
        return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,map);
    }
    //媒介平台投放对比
    @GetMapping("/platformDeliveryComparison")
    public Result platformDeliveryComparison(String cid1,String cid2,String field) {
        Map<String,Map<String,List<PlatBean>>> map=activityMediaSlotAnalysisService.platformDeliveryComparison(cid1,cid2,field);
        return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,map);
    }
    //媒介平台投放对比下载
    @GetMapping("/platformDeliveryComparisonDl")
    public void platformDeliveryComparisonDl(HttpServletResponse response,String cid1,String cid2,String field) throws IOException {
        activityMediaSlotAnalysisService.platformDeliveryComparisonDl(response,cid1,cid2,field);
    }
    //分媒介平台分布
    @GetMapping("/distributionOfSubMediaPlatforms")
    public Result distributionOfSubMediaPlatforms(String cid1,String cid2,String field) {
        Map<String,Map<String,List<PlatBean>>>map=activityMediaSlotAnalysisService.distributionOfSubMediaPlatforms(cid1,cid2,field);
        return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,map);
    }
    //分媒介平台分布下载
    @GetMapping("/distributionOfSubMediaPlatformsDl")
    public void distributionOfSubMediaPlatformsDl(HttpServletResponse response,String cid1,String cid2,String field) throws IOException {
        activityMediaSlotAnalysisService.distributionOfSubMediaPlatformsDl(response,cid1,cid2,field);
    }
    //分媒介分触点构成
    @GetMapping("/mediaContactComposition")
    public Result mediaContactComposition(String cid1,String cid2,String field) {
        Map<String,Map<String,List<PlatBean>>>map=activityMediaSlotAnalysisService.mediaContactComposition(cid1,cid2,field);
        return new Result(ContentResult.SUCCESS_CODE,ContentResult.SUCCESS_MESSAGE,map);
    }
    //分媒介分触点构成下载
    @GetMapping("/mediaContactCompositionDl")
    public void mediaContactCompositionDl(HttpServletResponse response,String cid1,String cid2,String field) throws IOException {
        activityMediaSlotAnalysisService.mediaContactCompositionDl(response,cid1,cid2,field);
    }
}
