package com.hy.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.excelTemp.ActivityDirectionExcel;
import com.hy.mapper.ActivityPlatformContentDirectionGroupMapper;
import com.hy.pojo.ActivityPlatformContentDirectionGroup;
import com.hy.pojo.ConversionIncome;
import com.hy.result.ContentResult;
import com.hy.result.PageResult;
import com.hy.result.PageResultQuery;
import com.hy.service.ActivityDirectionService;
import com.hy.utils.ExcelUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityDirectionServiceImpl implements ActivityDirectionService {
    @Autowired
    private ActivityPlatformContentDirectionGroupMapper activityDirectionMapper;
    @Override
    public PageResult<ActivityPlatformContentDirectionGroup> findActivityDirection(PageResultQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<ActivityPlatformContentDirectionGroup> list =activityDirectionMapper.findActivityDirection(query);
        PageInfo<ActivityPlatformContentDirectionGroup> info=new PageInfo<>(list);
        return new PageResult(info.getPageNum(),info.getPageSize(),info.getTotal(),list);
    }

    @Override
    public void findActivityDirectionDl(HttpServletResponse response, PageResultQuery query) throws IOException {
        PageResult<ActivityPlatformContentDirectionGroup> page=findActivityDirection(query);
        //获取PageResult中的数据集合
        List<ActivityPlatformContentDirectionGroup> list=page.getList();
        //拷贝数据到<ActivityDirectionGroupExcel>
        List<ActivityDirectionExcel>collect =list.stream().map((x)->{
            ActivityDirectionExcel ex=new ActivityDirectionExcel();
            BeanUtils.copyProperties(x,ex);
            return ex;
        }).collect(Collectors.toList());
        //分页逻辑
        String filename =System.currentTimeMillis()+"";
        //获取样式信息
        HorizontalCellStyleStrategy horizontalCellStyleStrategy= ExcelUtil.getHorizontalCellStyleStrategy(response,filename);
        //填充样式让其生效，并输出ac的内容
        EasyExcel.write(response.getOutputStream(), ActivityDirectionExcel.class)// 调用EasyExcelFactory的静态方法，返回ExcelWriterBuilder实例
                .registerWriteHandler(horizontalCellStyleStrategy) // 调用父类AbstractExcelWriterParameterBuilder的方法
                .sheet("分活动分媒介分内容方向") // 调用ExcelWriterBuilder自身的方法
                .doWrite(collect);// 执行写入
    }

}
