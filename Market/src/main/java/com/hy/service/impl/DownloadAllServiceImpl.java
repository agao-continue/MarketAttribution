package com.hy.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.hy.excelTemp.*;
import com.hy.mapper.ActivityPlatformInfluencergradeGroupMapper;
import com.hy.pojo.*;
import com.hy.result.PageResult;
import com.hy.result.PageResultQuery;
import com.hy.service.*;
import com.hy.utils.ExcelUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DownloadAllServiceImpl implements DownloadAllService {
    //分活动1
    @Autowired
    private ActivityGroupService activityGroupService;
    // 分活动分媒介2
    @Autowired
    ActivityPlatformGroupService activityPlatformGroupService;
    // 分活动分媒介分内容形式3
    @Autowired
    ActivityPlatformContentGroupService activityPlatformContentGroupService;
    // 分活动分媒介分内容方向4
    @Autowired
    ActivityDirectionService activityDirectionService;
    // 分活动分媒介分达人等级5
    @Autowired
    ActivityInfluencergradeService activityInfluencergradeService;
    // 分活动分媒介分达人类型6
    @Autowired
    ActivityInfluencertypeService  activityInfluencertypeService;
    // 分活动分帖子7
    @Autowired
    ActivityKolService activityKolService;

    @Override
    public void downloadAll(HttpServletResponse response, PageResultQuery query) throws IOException {
        switch(query.getStatus()){
            //分活动1
            case 1:
                List<ActivityGroup> list1 = activityGroupService.findActivityGroup(query.getCname());
                List<ActivityGroupExcel> collect1 =list1.stream().map((x)->{
                    ActivityGroupExcel excel1=new ActivityGroupExcel();
                    BeanUtils.copyProperties(x,excel1);
                    return excel1;
                }).collect(Collectors.toList());
                String filename1 =System.currentTimeMillis()+"";
                HorizontalCellStyleStrategy horizontalCellStyleStrategy1 = ExcelUtil.getHorizontalCellStyleStrategy(response,filename1);
                EasyExcel.write(response.getOutputStream(), ActivityGroupExcel.class)
                        .registerWriteHandler(horizontalCellStyleStrategy1)
                        .sheet("分活动").doWrite(collect1);
                break;
            // 分活动分媒介2
            case 2:
                PageResult<ActivityPlatformGroup> page2= activityPlatformGroupService.findActivityPlatformGroup(query);
                List<ActivityPlatformGroup> list2=page2.getList();
                List<ActivityPlatformGroupExcel> collect2 =list2.stream().map((x)->{
                    ActivityPlatformGroupExcel excel2=new ActivityPlatformGroupExcel();
                    BeanUtils.copyProperties(x,excel2);
                    return excel2;
                }).collect(Collectors.toList());
                String filename2 =System.currentTimeMillis()+"";
                HorizontalCellStyleStrategy horizontalCellStyleStrategy2 = ExcelUtil.getHorizontalCellStyleStrategy(response,filename2);
                EasyExcel.write(response.getOutputStream(), ActivityPlatformGroupExcel.class)
                        .registerWriteHandler(horizontalCellStyleStrategy2)
                        .sheet("分活动分媒介").doWrite(collect2);
                break;
            // 分活动分媒介分内容形式3
            case 3:
                PageResult<ActivityPlatformContentGroup> page3 = activityPlatformContentGroupService.findActivityPlatformContentGroup(query);
                List<ActivityPlatformContentGroup> list3=page3.getList();
                List<ActivityPlatformContentGroupExcel> collect3 =list3.stream().map((x)->{
                    ActivityPlatformContentGroupExcel excel3=new ActivityPlatformContentGroupExcel();
                    BeanUtils.copyProperties(x,excel3);
                    return excel3;
                }).collect(Collectors.toList());
                String filename3 =System.currentTimeMillis()+"";
                HorizontalCellStyleStrategy horizontalCellStyleStrategy3 = ExcelUtil.getHorizontalCellStyleStrategy(response,filename3);
                EasyExcel.write(response.getOutputStream(), ActivityPlatformContentGroupExcel.class)
                        .registerWriteHandler(horizontalCellStyleStrategy3)
                        .sheet("分活动分媒介分内容").doWrite(collect3);
                break;
            // 分活动分媒介分内容方向4
            case 4:
                PageResult<ActivityPlatformContentDirectionGroup> page4 =activityDirectionService.findActivityDirection(query);
                List<ActivityPlatformContentDirectionGroup> list4=page4.getList();
                List<ActivityPlatformContentDirectionGroupExcel> collect4 =list4.stream().map((x)->{
                    ActivityPlatformContentDirectionGroupExcel excel4=new ActivityPlatformContentDirectionGroupExcel();
                    BeanUtils.copyProperties(x,excel4);
                    return excel4;
                }).collect(Collectors.toList());
                String filename4 =System.currentTimeMillis()+"";
                HorizontalCellStyleStrategy horizontalCellStyleStrategy4 = ExcelUtil.getHorizontalCellStyleStrategy(response,filename4);
                EasyExcel.write(response.getOutputStream(), ActivityPlatformContentDirectionGroupExcel.class)
                        .registerWriteHandler(horizontalCellStyleStrategy4)
                        .sheet("分活动分媒介分内容方向").doWrite(collect4);
                break;
            // 分活动分媒介分达人等级5
            case 5:
                PageResult<ActivityPlatformInfluencergradeGroup> page5=activityInfluencergradeService.findActivityInfluence(query);
                List<ActivityPlatformInfluencergradeGroup> list5=page5.getList();
                List<ActivityPlatformInfluencergradeGroupExcel> collect5 =list5.stream().map((x)->{
                    ActivityPlatformInfluencergradeGroupExcel excel=new ActivityPlatformInfluencergradeGroupExcel();
                    BeanUtils.copyProperties(x,excel);
                    return excel;
                }).collect(Collectors.toList());
                String filename5 =System.currentTimeMillis()+"";
                HorizontalCellStyleStrategy horizontalCellStyleStrategy5 = ExcelUtil.getHorizontalCellStyleStrategy(response,filename5);
                EasyExcel.write(response.getOutputStream(), ActivityPlatformInfluencergradeGroupExcel.class)
                        .registerWriteHandler(horizontalCellStyleStrategy5)
                        .sheet("分活动分媒介分达人等级").doWrite(collect5);
                break;
            // 分活动分媒介分达人类型6
            case 6:
                PageResult<ActivityPlatformInfluencertypeGroup> page6= activityInfluencertypeService.findActivityInfluencertype(query);
                List<ActivityPlatformInfluencertypeGroup> list6=page6.getList();
                List<ActivityPlatformInfluencertypeGroupExcel> collect6 =list6.stream().map((x)->{
                    ActivityPlatformInfluencertypeGroupExcel excel6=new ActivityPlatformInfluencertypeGroupExcel();
                    BeanUtils.copyProperties(x,excel6);
                    return excel6;
                }).collect(Collectors.toList());
                String filename6 =System.currentTimeMillis()+"";
                HorizontalCellStyleStrategy horizontalCellStyleStrategy6 = ExcelUtil.getHorizontalCellStyleStrategy(response,filename6);
                EasyExcel.write(response.getOutputStream(), ActivityPlatformInfluencertypeGroupExcel.class)
                        .registerWriteHandler(horizontalCellStyleStrategy6)
                        .sheet("分活动分媒介分达人类型").doWrite(collect6);
                break;
            // 分活动分帖子7
            case 7:
                PageResult<ActivityKolGroup>page7=activityKolService.findActivityKolGroup(query);
                List<ActivityKolGroup> list7=page7.getList();
                List<ActivityKolGroupExcel> collect7 =list7.stream().map((x)->{
                    ActivityKolGroupExcel excel7=new ActivityKolGroupExcel();
                    BeanUtils.copyProperties(x,excel7);
                    return excel7;
                }).collect(Collectors.toList());
                String filename7 =System.currentTimeMillis()+"";
                HorizontalCellStyleStrategy horizontalCellStyleStrategy7 = ExcelUtil.getHorizontalCellStyleStrategy(response,filename7);
                EasyExcel.write(response.getOutputStream(), ActivityKolGroupExcel.class)
                        .registerWriteHandler(horizontalCellStyleStrategy7)
                        .sheet("分活动分媒介分达人类型").doWrite(collect7);
                break;

        }
    }
}
