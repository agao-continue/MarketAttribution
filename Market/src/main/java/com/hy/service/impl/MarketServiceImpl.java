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
import java.util.Collections;
import java.util.List;
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
        //调用工具类生成样式的调用
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = ExcelUtil.getHorizontalCellStyleStrategy(response,filename);
        //拷贝数据，ActivityAnalysis拷贝到ActivityAnalysisExcel，消除多余单元格
        List<ActivityAnalysisExcel> collect = ac.stream().map((aa)->{
            ActivityAnalysisExcel excel = new ActivityAnalysisExcel();
            BeanUtils.copyProperties(aa,excel);
            return excel;
        }).collect(Collectors.toList());
        //填充样式让其生效，并输出ac的内容
        EasyExcel.write(response.getOutputStream(), ActivityAnalysisExcel.class)
                .registerWriteHandler(horizontalCellStyleStrategy)
                .sheet("归因概览")
                .doWrite(collect);
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
        //设置计数指标
        if(status.equals("1")){
            //为曝光次数时，曝光月活转化率=月活会员数/曝光次数、曝光拉新转化率=拉新会员数/曝光次数
            emacRate = new BigDecimal(ctest.getMonthlyActiveMemberCount()).divide(new BigDecimal(ctest.getExposureCount()), 2, BigDecimal.ROUND_HALF_UP);
            etnccRate = new BigDecimal(ctest.getNewMemberAcquisitionCount()).divide(new BigDecimal(ctest.getExposureCount()), 2, BigDecimal.ROUND_HALF_UP);
            ctest.setEmacRate(emacRate);
            ctest.setEtnccRate(etnccRate);
        }
        else{
            //为曝光人数时，曝光月活转化率=月活会员数/曝光人数、曝光拉新转化率=拉新会员数/曝光人数
            emacRate = new BigDecimal(ctest.getMonthlyActiveMemberCount()).divide(new BigDecimal(ctest.getExposureUserCount()), 2, BigDecimal.ROUND_HALF_UP);
            etnccRate = new BigDecimal(ctest.getNewMemberAcquisitionCount()).divide(new BigDecimal(ctest.getExposureUserCount()), 2, BigDecimal.ROUND_HALF_UP);
            ctest.setEmacRate(emacRate);
            ctest.setEtnccRate(etnccRate);
        }
        return ctest;
    }

}
