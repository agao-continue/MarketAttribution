package com.hy.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.excelTemp.ActivityDirectionExcel;
import com.hy.excelTemp.ActivityPlatformInfluencergradeGroupExcel;
import com.hy.mapper.ActivityPlatformInfluencergradeGroupMapper;
import com.hy.pojo.ActivityPlatformInfluencergradeGroup;
import com.hy.result.PageResult;
import com.hy.result.PageResultQuery;
import com.hy.service.ActivityInfluencergradeService;
import com.hy.service.MarketService;
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
public class ActivityInfluencergradeServiceImpl implements ActivityInfluencergradeService {
    @Autowired
    private ActivityPlatformInfluencergradeGroupMapper activityPlatformInfluencergradeGroupMapper;

    @Override
    public PageResult<ActivityPlatformInfluencergradeGroup> findActivityInfluence(PageResultQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<ActivityPlatformInfluencergradeGroup> list= activityPlatformInfluencergradeGroupMapper.findActivityInfluence(query);
        PageInfo<ActivityPlatformInfluencergradeGroup> info = new PageInfo<>(list);
        return new PageResult(info.getPageNum(),info.getPageSize(),info.getTotal(),info.getList());
    }

    @Override
    public void findActivityInfluenceDl(HttpServletResponse response, PageResultQuery query) throws IOException {
        PageResult<ActivityPlatformInfluencergradeGroup> page=findActivityInfluence(query);
        List<ActivityPlatformInfluencergradeGroup> list=page.getList();
        List<ActivityPlatformInfluencergradeGroupExcel>collect=list.stream().map((x)->{
            ActivityPlatformInfluencergradeGroupExcel excel=new ActivityPlatformInfluencergradeGroupExcel();
            BeanUtils.copyProperties(x,excel);
            return excel;
        }).collect(Collectors.toList());
        //分页逻辑
        String filename =System.currentTimeMillis()+"";
        //获取样式信息
        HorizontalCellStyleStrategy horizontalCellStyleStrategy= ExcelUtil.getHorizontalCellStyleStrategy(response,filename);
        //填充样式让其生效，并输出ac的内容
        EasyExcel.write(response.getOutputStream(), ActivityPlatformInfluencergradeGroupExcel.class)// 调用EasyExcelFactory的静态方法，返回ExcelWriterBuilder实例
                .registerWriteHandler(horizontalCellStyleStrategy) // 调用父类AbstractExcelWriterParameterBuilder的方法
                .sheet("分活动分媒介分等级") // 调用ExcelWriterBuilder自身的方法
                .doWrite(collect);// 执行写入
    }
}
