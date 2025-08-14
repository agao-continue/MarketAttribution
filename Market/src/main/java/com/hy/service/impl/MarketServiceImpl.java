package com.hy.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.hy.excelTemp.ActivityAnalysisExcel;
import com.hy.mapper.ActivityAnalysisMapper;
import com.hy.pojo.ActivityAnalysis;
import com.hy.pojo.ConversionIncome;
import com.hy.service.MarketService;
import com.hy.utils.ExcelUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public void anDownload(HttpServletResponse response) throws IOException {
        //获取数据
        List<ActivityAnalysis> ac = activityAnalysisMapper.selectByExample(null);
        //设置文件名
        String filename =System.currentTimeMillis() + ".xlsx";
        // 调用工具类方法设置响应头并创建Excel样式策略，处理中文文件名编码问题
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = ExcelUtil.getHorizontalCellStyleStrategy(response,filename);
        //拷贝数据，ActivityAnalysis拷贝到ActivityAnalysisExcel，消除多余单元格
        List<ActivityAnalysisExcel> collect = ac.stream().map((aa)->{
            ActivityAnalysisExcel excel = new ActivityAnalysisExcel();
            BeanUtils.copyProperties(aa,excel);
            return excel;
        }).collect(Collectors.toList());
        //填充样式让其生效，并输出ac的内容
        EasyExcel.write(response.getOutputStream(), ActivityAnalysisExcel.class)// 调用EasyExcelFactory的静态方法，返回ExcelWriterBuilder实例
                .registerWriteHandler(horizontalCellStyleStrategy) // 调用父类AbstractExcelWriterParameterBuilder的方法
                .sheet("归因概览") // 调用ExcelWriterBuilder自身的方法
                .doWrite(collect);// 执行写入
    }

    @Override
    public ConversionIncome getConvertionIncome(String cid,String status) {
        List<ConversionIncome> c=activityAnalysisMapper.getConvertionIncome(cid);
        ConversionIncome ctest=null;
        if(c.size()>0){
            ctest=c.get(0);
        }
        BigDecimal emacRate = BigDecimal.ZERO;  // 初始化为0
        BigDecimal etnccRate = BigDecimal.ZERO; // 初始化为0
        BigDecimal emacRateDiff = BigDecimal.ZERO;  // 初始化为0
        BigDecimal etnccRateDiff = BigDecimal.ZERO; // 初始化为0
        //设置计数指标与差值
        if(status.equals("1")){
            //为曝光次数时，曝光月活转化率=月活会员数/曝光次数、曝光拉新转化率=拉新会员数/曝光次数
            emacRate = new BigDecimal(ctest.getMonthlyActiveMemberCount()).divide(new BigDecimal(ctest.getExposureCount()), 2, BigDecimal.ROUND_HALF_UP);
            etnccRate = new BigDecimal(ctest.getNewMemberAcquisitionCount()).divide(new BigDecimal(ctest.getExposureCount()), 2, BigDecimal.ROUND_HALF_UP);
            ctest.setEmacRate(emacRate);
            ctest.setEtnccRate(etnccRate);
            // 3. 确保 emacRateAvg1、etnccRateAvg1 非空
            if (ctest.getEmacRateAvg1() != null) {
                emacRateDiff = emacRate.subtract(ctest.getEmacRateAvg1());
                ctest.setEmacRateDiff(emacRateDiff); // 赋值回 ctest
            }
            if (ctest.getEtnccRateAvg1() != null) {
                etnccRateDiff = emacRate.subtract(ctest.getEtnccRateAvg1());
                ctest.setEtnccRateDiff(etnccRateDiff); // 赋值回 ctest
            }
        }
        else{
            //为曝光人数时，曝光月活转化率=月活会员数/曝光人数、曝光拉新转化率=拉新会员数/曝光人数
            emacRate = new BigDecimal(ctest.getMonthlyActiveMemberCount()).divide(new BigDecimal(ctest.getExposureUserCount()), 2, BigDecimal.ROUND_HALF_UP);
            etnccRate = new BigDecimal(ctest.getNewMemberAcquisitionCount()).divide(new BigDecimal(ctest.getExposureUserCount()), 2, BigDecimal.ROUND_HALF_UP);
            ctest.setEmacRate(emacRate);
            ctest.setEtnccRate(etnccRate);
            // 3. 确保 emacRateAvg1、etnccRateAvg1 非空
            if (ctest.getEmacRateAvg1() != null) {
                emacRateDiff = emacRate.subtract(ctest.getEmacRateAvg2());
                ctest.setEmacRateDiff(emacRateDiff); // 赋值回 ctest
            }
            if (ctest.getEtnccRateAvg1() != null) {
                etnccRateDiff = emacRate.subtract(ctest.getEtnccRateAvg2());
                ctest.setEtnccRateDiff(etnccRateDiff); // 赋值回 ctest
            }
        }
        return ctest;
    }

    @Override
    public void conversionIncomeDl(HttpServletResponse response,String cid,String status) throws IOException {
        ConversionIncome con = getConvertionIncome(cid,status);
        //包装成集合对象，才能dowrite
        ArrayList<ConversionIncome> arr = new ArrayList<>();
        arr.add(con);
        //设置文件名
        String filename = System.currentTimeMillis()+"";
        // 调用工具类方法设置响应头并创建Excel样式策略，处理中文文件名编码问题
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = ExcelUtil.getHorizontalCellStyleStrategy(response,filename);
        //填充样式让其生效，并输出ac的内容
        EasyExcel.write(response.getOutputStream(), ConversionIncome.class)// 调用EasyExcelFactory的静态方法，返回ExcelWriterBuilder实例
                .registerWriteHandler(horizontalCellStyleStrategy) // 调用父类AbstractExcelWriterParameterBuilder的方法
                .sheet("转化效益分析") // 调用ExcelWriterBuilder自身的方法
                .doWrite(arr);// 执行写入
    }

    @Override
    public Map<String, List<String>> findTypeAndCname() {
        List<ActivityAnalysis>aa=activityAnalysisMapper.selectByExample(null);
        Map<String,List<ActivityAnalysis>>collect=aa.stream().collect(Collectors.groupingBy((x)->x.getActivityType()));
        HashMap<String,List<String>> map=new HashMap<>();
        collect.forEach((x,y)->{
            List<String>l1=y.stream().map((y1)->y1.getCampaignName()).collect(Collectors.toList());
            map.put(x,l1);
        });
        return map;
    }

    @Override
    public List<ActivityAnalysis> findCpm() {
        return activityAnalysisMapper.findCpm();
    }

}
