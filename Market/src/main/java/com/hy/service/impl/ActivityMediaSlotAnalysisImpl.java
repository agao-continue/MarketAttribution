package com.hy.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.hy.mapper.ActivityMediaSlotAnalysisMapper;
import com.hy.pojo.ConversionIncome;
import com.hy.pojo.MediaSlot;
import com.hy.service.ActivityMediaSlotAnalysisService;
import com.hy.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ActivityMediaSlotAnalysisImpl implements ActivityMediaSlotAnalysisService {
    @Autowired
    ActivityMediaSlotAnalysisMapper activityMediaSlotAnalysisMapper;

    @Override
    public List<String> findContactPoint() {
      return activityMediaSlotAnalysisMapper.findContactPoint();
    }

    @Override
    public List<MediaSlot> findMediaSlot(String cid,String point,String orderField,String orderAD) {
        return activityMediaSlotAnalysisMapper.findMediaSlot(cid,point,orderField,orderAD);
    }

    @Override
    public void findMediaSlotDl(HttpServletResponse response, String cid, String point, String orderField, String orderAD) throws IOException {
        List<MediaSlot> con = findMediaSlot(cid,point,orderField,orderAD);
        //设置文件名
        String filename = System.currentTimeMillis()+"";
        // 调用工具类方法设置响应头并创建Excel样式策略，处理中文文件名编码问题
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = ExcelUtil.getHorizontalCellStyleStrategy(response,filename);
        //填充样式让其生效，并输出ac的内容
        EasyExcel.write(response.getOutputStream(), MediaSlot.class)// 调用EasyExcelFactory的静态方法，返回ExcelWriterBuilder实例
                .registerWriteHandler(horizontalCellStyleStrategy) // 调用父类AbstractExcelWriterParameterBuilder的方法
                .sheet("媒介归因排名") // 调用ExcelWriterBuilder自身的方法
                .doWrite(con);// 执行写入
    }
}
