package com.hy.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.excelTemp.PlatformDeliveryExcel;
import com.hy.excelTemp.PlatformDeliveryPortExcel;
import com.hy.mapper.ActivityMediaSlotAnalysisMapper;
import com.hy.pojo.MediaSlot;
import com.hy.pojo.PlatBean;
import com.hy.service.ActivityMediaSlotAnalysisService;
import com.hy.utils.ExcelUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ActivityMediaSlotAnalysisImpl implements ActivityMediaSlotAnalysisService {
    @Autowired
    ActivityMediaSlotAnalysisMapper activityMediaSlotAnalysisMapper;

    @Override
    public List<String> findContactPoint() {
      return activityMediaSlotAnalysisMapper.findContactPoint();
    }

    @Override
    public PageInfo<MediaSlot> findMediaSlot(String cid, String point, String orderField, String orderAD,int pageNum,int pageSize,String media) {
        PageHelper.startPage(pageNum, pageSize);
        List<MediaSlot> mediaSlots = activityMediaSlotAnalysisMapper.findMediaSlot(cid,point,orderField,orderAD,media);
        PageInfo<MediaSlot> info = new PageInfo<>(mediaSlots);
        return info;
    }

    @Override
    public void findMediaSlotDl(HttpServletResponse response, String cid, String point, String orderField, String orderAD,int pageNum,int pageSize,String media) throws IOException {
        PageInfo<MediaSlot> con = findMediaSlot(cid,point,orderField,orderAD,pageNum,pageSize,media);
        // 获取PageInfo中的列表数据
        List<MediaSlot> mediaSlotList = con.getList();
        //设置文件名
        String filename = System.currentTimeMillis()+"";
        // 调用工具类方法设置响应头并创建Excel样式策略，处理中文文件名编码问题
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = ExcelUtil.getHorizontalCellStyleStrategy(response,filename);
        //填充样式让其生效，并输出ac的内容
        EasyExcel.write(response.getOutputStream(), MediaSlot.class)// 调用EasyExcelFactory的静态方法，返回ExcelWriterBuilder实例
                .registerWriteHandler(horizontalCellStyleStrategy) // 调用父类AbstractExcelWriterParameterBuilder的方法
                .sheet("媒介归因排名") // 调用ExcelWriterBuilder自身的方法
                .doWrite(mediaSlotList);// 执行写入
    }

    @Override
    public List<String> findAllMedia() {
        return activityMediaSlotAnalysisMapper.findAllMedia();
    }

    @Override
    public Map<String, Map<String, List<PlatBean>>> platformDeliveryComparison(String cid1, String cid2, String field) {
        List<PlatBean>list=activityMediaSlotAnalysisMapper.platformDeliveryComparison(cid1,cid2,field);
        Map<String,List<PlatBean>>collect= list.stream().collect(Collectors.groupingBy((x)->x.getCname()));
        HashMap<String,Map<String,List<PlatBean>>> map=new HashMap<>();
        collect.forEach((k,v)->{
           Map<String,List<PlatBean>>collect1= v.stream().collect(Collectors.groupingBy((y)->y.getMedia()));
           map.put(k,collect1);
        });
        return map;
    }

    @Override
    public void platformDeliveryComparisonDl(HttpServletResponse response, String cid1, String cid2, String field) throws IOException {
//        PageHelper.startPage(1, 1);不需要分页
        List<PlatBean>list=activityMediaSlotAnalysisMapper.platformDeliveryComparison(cid1,cid2,field);
        List<PlatformDeliveryExcel> collect =list.stream().map((x)->{
            PlatformDeliveryExcel excel=new PlatformDeliveryExcel();
            BeanUtils.copyProperties(x,excel);
            return excel;
        }).collect(Collectors.toList());
        String filename =System.currentTimeMillis()+"";
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = ExcelUtil.getHorizontalCellStyleStrategy(response,filename);
        EasyExcel.write(response.getOutputStream(), PlatformDeliveryExcel.class)
                .registerWriteHandler(horizontalCellStyleStrategy)
                .sheet("媒介平台投放对比下载").doWrite(collect);
    }

    @Override
    public Map<String, Map<String, List<PlatBean>>> distributionOfSubMediaPlatforms(String cid1, String cid2, String field) {
        List<PlatBean>list=activityMediaSlotAnalysisMapper.distributionOfSubMediaPlatforms(cid1,cid2,field);
        Map<String,List<PlatBean>>collect= list.stream().collect(Collectors.groupingBy((x)->x.getMedia()));
        HashMap<String,Map<String,List<PlatBean>>> map=new HashMap<>();
        collect.forEach((k,v)->{
            Map<String,List<PlatBean>>collect1= v.stream().collect(Collectors.groupingBy((y)->y.getCname()));
            map.put(k,collect1);
        });
        return map;
    }

    @Override
    public void distributionOfSubMediaPlatformsDl(HttpServletResponse response, String cid1, String cid2, String field) throws IOException {
        List<PlatBean>list=activityMediaSlotAnalysisMapper.distributionOfSubMediaPlatforms(cid1,cid2,field);
        List<PlatformDeliveryExcel> collect =list.stream().map((x)->{
            PlatformDeliveryExcel excel=new PlatformDeliveryExcel();
            BeanUtils.copyProperties(x,excel);
            return excel;
        }).collect(Collectors.toList());
        String filename =System.currentTimeMillis()+"";
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = ExcelUtil.getHorizontalCellStyleStrategy(response,filename);
        EasyExcel.write(response.getOutputStream(), PlatformDeliveryExcel.class)
                .registerWriteHandler(horizontalCellStyleStrategy)
                .sheet("分媒介平台分布下载").doWrite(collect);
    }

    @Override
    public Map<String, Map<String, List<PlatBean>>> mediaContactComposition(String cid1, String cid2, String field) {
        List<PlatBean>list=activityMediaSlotAnalysisMapper.mediaContactComposition(cid1,cid2,field);
        Map<String,List<PlatBean>>collect= list.stream().collect(Collectors.groupingBy((x)->x.getMedia()));
        HashMap<String,Map<String,List<PlatBean>>> map=new HashMap<>();
        collect.forEach((k,v)->{
            Map<String,List<PlatBean>>collect1= v.stream().collect(Collectors.groupingBy((y)->y.getCname()));
            map.put(k,collect1);
        });
        return map;
    }

    @Override
    public void mediaContactCompositionDl(HttpServletResponse response, String cid1, String cid2, String field) throws IOException {
        List<PlatBean>list=activityMediaSlotAnalysisMapper.mediaContactComposition(cid1,cid2,field);
        List<PlatformDeliveryPortExcel> collect =list.stream().map((x)->{
            PlatformDeliveryPortExcel excel=new PlatformDeliveryPortExcel();
            BeanUtils.copyProperties(x,excel);
            return excel;
        }).collect(Collectors.toList());
        String filename =System.currentTimeMillis()+"";
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = ExcelUtil.getHorizontalCellStyleStrategy(response,filename);
        EasyExcel.write(response.getOutputStream(), PlatformDeliveryPortExcel.class)
                .registerWriteHandler(horizontalCellStyleStrategy)
                .sheet("分媒介分触点构成下载").doWrite(collect);
    }
}
