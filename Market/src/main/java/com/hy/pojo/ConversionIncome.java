package com.hy.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ConversionIncome {
    @ExcelProperty("花费")
    private BigDecimal cost;
    @ExcelProperty("曝光次数")
    private Long exposureCount;
    @ExcelProperty("曝光人数")
    private Long exposureUserCount;
    @ExcelProperty("月活会员数")
    private Long monthlyActiveMemberCount;
    @ExcelProperty("拉新会员数")
    private Long newMemberAcquisitionCount;
    @ExcelProperty("曝光月活转化率")
    private BigDecimal emacRate;
    @ExcelProperty("曝光拉新转化率")
    private BigDecimal etnccRate;
    @ExcelProperty("平均曝光月活转化率(次数)")
    private BigDecimal emacRateAvg1;
    @ExcelProperty("平均曝光拉新转化率(次数)")
    private BigDecimal etnccRateAvg1;
    @ExcelProperty("平均曝光月活转化率(人数)")
    private BigDecimal emacRateAvg2;
    @ExcelProperty("平均曝光拉新转化率(人数)")
    private BigDecimal etnccRateAvg2;
    @ExcelProperty("曝光月活转化率差值")
    private BigDecimal emacRateDiff;
    @ExcelProperty("曝光拉新转化率差值")
    private BigDecimal etnccRateDiff;
}
