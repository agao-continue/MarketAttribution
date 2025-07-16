package com.hy.excelTemp;

import com.alibaba.excel.annotation.ExcelProperty;
import com.hy.pojo.Alog;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class ActivityAnalysisExcel {
    @ExcelProperty("活动名称")
    private String campaignName;

    @ExcelProperty("活动ID")
    private String campaignId;

    @ExcelProperty("开始日期")
    private Date startDate;

    @ExcelProperty("结束日期")
    private Date endDate;

    @ExcelProperty("成本")
    private BigDecimal cost;

    @ExcelProperty("监控率")
    private BigDecimal monitorRate;

    @ExcelProperty("曝光次数")
    private Long exposureCount;

    @ExcelProperty("曝光用户数")
    private Long exposureUserCount;

    @ExcelProperty("点击次数")
    private Long clickCount;

    @ExcelProperty("点击用户数")
    private Long clickUserCount;

    @ExcelProperty("月活会员数")
    private Long monthlyActiveMemberCount;

    @ExcelProperty("月活会员GMV")
    private BigDecimal monthlyActiveMemberGmv;

    @ExcelProperty("新会员获取数")
    private Long newMemberAcquisitionCount;

    @ExcelProperty("新会员获取GMV")
    private BigDecimal newMemberAcquisitionGmv;

    @ExcelProperty("同期月活用户数")
    private Long periodicMonthlyActiveUserCount;

    @ExcelProperty("同期月活用户GMV")
    private BigDecimal periodicMonthlyActiveUserGmv;

    @ExcelProperty("同期新会员数")
    private Long periodicNewMemberCount;

    @ExcelProperty("同期新会员GMV")
    private BigDecimal periodicNewMemberGmv;

    @ExcelProperty("次月活会员数")
    private Long nextMonthActiveMemberCount;

    @ExcelProperty("次月活会员GMV")
    private BigDecimal nextMonthActiveMemberGmv;

    @ExcelProperty("次月新会员数")
    private Long nextMonthNewMemberCount;

    @ExcelProperty("次月新会员GMV")
    private BigDecimal nextMonthNewMemberGmv;

    @ExcelProperty("活动类型")
    private String activityType;

}
