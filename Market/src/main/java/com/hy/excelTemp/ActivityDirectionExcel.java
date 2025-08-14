package com.hy.excelTemp;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ActivityDirectionExcel {
    @ExcelProperty("活动名称")
    private String campaignName;

    @ExcelProperty("媒介平台")
    private String media;

    @ExcelProperty("内容方向")
    private String contentDirection;

    @ExcelProperty("花费")
    private BigDecimal cost;

    @ExcelProperty("曝光次数")
    private Long exposureCount;

    @ExcelProperty("曝光人数")
    private Long exposureUserCount;

    @ExcelProperty("平均曝光频次")
    private BigDecimal avgImpressionFrequency;

    @ExcelProperty("点击次数")
    private Long clickCount;

    @ExcelProperty("点击人数")
    private Long clickUserCount;

    @ExcelProperty("月活会员数")
    private Long monthlyActiveMemberCount;

    @ExcelProperty("月活会员GMV")
    private BigDecimal monthlyActiveMemberGmv;

    @ExcelProperty("拉新会员数")
    private Long newMemberAcquisitionCount;

    @ExcelProperty("拉新会员GMV")
    private BigDecimal newMemberAcquisitionGmv;

    @ExcelProperty("*次月月活会员数")
    private Long nextMonthActiveMemberCount;

    @ExcelProperty("*次月月活会员GMV")
    private BigDecimal nextMonthActiveMemberGmv;

    @ExcelProperty("*次月拉新会员数")
    private Long nextMonthNewMemberCount;

    @ExcelProperty("*次月拉新会员GMV")
    private BigDecimal nextMonthNewMemberGmv;
    // 三、十五个计算数据
    @ExcelProperty("千次曝光成本(CPM)")
    private BigDecimal cpm;  // 对应 SQL 中的 cpm

    @ExcelProperty("月活跃会员ROI")
    private BigDecimal roi;  // 对应 SQL 中的 roi

    @ExcelProperty("月活跃会员获客成本")
    private BigDecimal cac;  // 对应 SQL 中的 cac

    @ExcelProperty("月活跃会员贡献占比")
    private BigDecimal mamcRate;  // 对应 SQL 中的 mamcRate

    @ExcelProperty("月活跃会员GMV贡献占比")
    private BigDecimal mamcGmvRate;  // 对应 SQL 中的 mamcGmvRate

    @ExcelProperty("新会员ROI")
    private BigDecimal rnmRoi;  // 对应 SQL 中的 rnmRoi

    @ExcelProperty("新会员获客成本")
    private BigDecimal rnmCac;  // 对应 SQL 中的 rnmCac

    @ExcelProperty("新会员贡献占比")
    private BigDecimal nmcRate;  // 对应 SQL 中的 nmcRate

    @ExcelProperty("新会员GMV贡献占比")
    private BigDecimal nmcGmvRate;  // 对应 SQL 中的 nmcGmvRate

    @ExcelProperty("活动期间人均GMV(MAU)")
    private BigDecimal pcmaGmv;  // 对应 SQL 中的 pcmaGmv

    @ExcelProperty("次月人均GMV(MAU)")
    private BigDecimal mpcmaGmv;  // 对应 SQL 中的 mpcmaGmv

    @ExcelProperty("活动期间人均GMV(新会员)")
    private BigDecimal pcnGmv;  // 对应 SQL 中的 pcnGmv

    @ExcelProperty("次月人均GMV(新会员)")
    private BigDecimal panGmv;  // 对应 SQL 中的 panGmv

    @ExcelProperty("月活跃会员次月留存率")
    private BigDecimal masmRate;  // 对应 SQL 中的 masmRate

    @ExcelProperty("新会员次月留存率")
    private BigDecimal mrrRate;  // 对应 SQL 中的 mrrRate
}
