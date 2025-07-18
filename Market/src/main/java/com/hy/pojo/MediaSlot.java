package com.hy.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MediaSlot {
        // 媒介平台和触点类型
        @ExcelProperty("活动名称")
        private String compaignName;

        @ExcelProperty("媒介平台")
        private String media;

        @ExcelProperty("触点类型")
        private String contactPoint;

        @ExcelProperty("活动ID")
        private String compaignId;

        // 一、九个基本数据
        @ExcelProperty("成本")
        private BigDecimal cost;

        @ExcelProperty("曝光量")
        private BigDecimal exposureCount;

        @ExcelProperty("曝光用户数")
        private BigDecimal exposureUserCount;

        @ExcelProperty("点击量")
        private BigDecimal clickCount;

        @ExcelProperty("点击用户数")
        private BigDecimal clickUserCount;

        @ExcelProperty("月活跃会员数")
        private BigDecimal monthlyActiveMemberCount;

        @ExcelProperty("月活跃会员GMV")
        private BigDecimal monthlyActiveMemberGmv;

        @ExcelProperty("新会员获取数")
        private BigDecimal newMemberAcquisitionCount;

        @ExcelProperty("新会员获取GMV")
        private BigDecimal newMemberAcquisitionGmv;

        // 二、九个基本数据的占比指标
        @ExcelProperty("成本占比")
        private BigDecimal costRatio;

        @ExcelProperty("曝光量占比")
        private BigDecimal exposureCountRatio;

        @ExcelProperty("曝光用户数占比")
        private BigDecimal exposureUserCountRatio;

        @ExcelProperty("点击量占比")
        private BigDecimal clickCountRatio;

        @ExcelProperty("点击用户数占比")
        private BigDecimal clickUserCountRatio;

        @ExcelProperty("月活跃会员数占比")
        private BigDecimal monthlyActiveMemberCountRatio;

        @ExcelProperty("月活跃会员GMV占比")
        private BigDecimal monthlyActiveMemberGmvRatio;

        @ExcelProperty("新会员获取数占比")
        private BigDecimal newMemberAcquisitionCountRatio;

        @ExcelProperty("新会员获取GMV占比")
        private BigDecimal newMemberAcquisitionGmvRatio;

        // 三、十七个计算数据
        @ExcelProperty("千次曝光成本(CPM)")
        private BigDecimal cpm;

        @ExcelProperty("单次点击成本(CPC)")
        private BigDecimal cpc;

        @ExcelProperty("点击率(CTR)")
        private BigDecimal ctr;

        @ExcelProperty("月活跃会员ROI")
        private BigDecimal monthlyActiveRoi;

        @ExcelProperty("月活跃会员获客成本")
        private BigDecimal monthlyActiveCac;

        @ExcelProperty("月活跃会员贡献占比")
        private BigDecimal monthlyActiveContriRate;

        @ExcelProperty("月活跃会员GMV贡献占比")
        private BigDecimal monthlyActiveGmvContriRate;

        @ExcelProperty("新会员ROI")
        private BigDecimal newMemberRoi;

        @ExcelProperty("新会员获客成本")
        private BigDecimal newMemberCac;

        @ExcelProperty("新会员贡献占比")
        private BigDecimal newMemberContriRate;

        @ExcelProperty("新会员GMV贡献占比")
        private BigDecimal newMemberGmvContriRate;

        @ExcelProperty("活动期间人均GMV(MAU)")
        private BigDecimal activityPeriodPerMAU_GMV;

        @ExcelProperty("次月人均GMV(MAU)")
        private BigDecimal nextMonthPerMAU_GMV;

        @ExcelProperty("活动期间人均GMV(新会员)")
        private BigDecimal activityPeriodPerNew_GMV;

        @ExcelProperty("次月人均GMV(新会员)")
        private BigDecimal nextMonthPerNew_GMV;

        @ExcelProperty("月活跃会员次月留存率")
        private BigDecimal mauNextMonthRetentionRate;

        @ExcelProperty("新会员次月留存率")
        private BigDecimal newMemberNextMonthRetentionRate;

        // 四、十七个计算数据的均值
        @ExcelProperty("CPM均值")
        private BigDecimal cpmAvg;

        @ExcelProperty("CPC均值")
        private BigDecimal cpcAvg;

        @ExcelProperty("CTR均值")
        private BigDecimal ctrAvg;

        @ExcelProperty("月活跃会员ROI均值")
        private BigDecimal roiAvg;

        @ExcelProperty("月活跃会员获客成本均值")
        private BigDecimal cacAvg;

        @ExcelProperty("月活跃会员贡献占比均值")
        private BigDecimal mamcRateAvg;

        @ExcelProperty("月活跃会员GMV贡献占比均值")
        private BigDecimal mamcGmvRateAvg;

        @ExcelProperty("新会员ROI均值")
        private BigDecimal rnmRoiAvg;

        @ExcelProperty("新会员获客成本均值")
        private BigDecimal rnmCacAvg;

        @ExcelProperty("新会员贡献占比均值")
        private BigDecimal nmcRateAvg;

        @ExcelProperty("新会员GMV贡献占比均值")
        private BigDecimal nmcGmvRateAvg;

        @ExcelProperty("活动期间人均GMV均值(MAU)")
        private BigDecimal pcmaGmvAvg;

        @ExcelProperty("次月人均GMV均值(MAU)")
        private BigDecimal mpcmaGmvAvg;

        @ExcelProperty("活动期间人均GMV均值(新会员)")
        private BigDecimal pcnGmvAvg;

        @ExcelProperty("次月人均GMV均值(新会员)")
        private BigDecimal panGmvAvg;

        @ExcelProperty("月活跃会员次月留存率均值")
        private BigDecimal masmRateAvg;

        @ExcelProperty("新会员次月留存率均值")
        private BigDecimal mrrRateAvg;

        // 五、十七个计算数据的差值指标
        @ExcelProperty("CPM差值")
        private BigDecimal cpmDiff;

        @ExcelProperty("CPC差值")
        private BigDecimal cpcDiff;

        @ExcelProperty("CTR差值")
        private BigDecimal ctrDiff;

        @ExcelProperty("月活跃会员ROI差值")
        private BigDecimal monthlyActiveRoiDiff;

        @ExcelProperty("月活跃会员获客成本差值")
        private BigDecimal monthlyActiveCacDiff;

        @ExcelProperty("月活跃会员贡献占比差值")
        private BigDecimal monthlyActiveContriRateDiff;

        @ExcelProperty("月活跃会员GMV贡献占比差值")
        private BigDecimal monthlyActiveGmvContriRateDiff;

        @ExcelProperty("新会员ROI差值")
        private BigDecimal newMemberRoiDiff;

        @ExcelProperty("新会员获客成本差值")
        private BigDecimal newMemberCacDiff;

        @ExcelProperty("新会员贡献占比差值")
        private BigDecimal newMemberContriRateDiff;

        @ExcelProperty("新会员GMV贡献占比差值")
        private BigDecimal newMemberGmvContriRateDiff;

        @ExcelProperty("活动期间人均GMV差值(MAU)")
        private BigDecimal activityPeriodPerMAU_GMVDiff;

        @ExcelProperty("次月人均GMV差值(MAU)")
        private BigDecimal nextMonthPerMAU_GMVDiff;

        @ExcelProperty("活动期间人均GMV差值(新会员)")
        private BigDecimal activityPeriodPerNew_GMVDiff;

        @ExcelProperty("次月人均GMV差值(新会员)")
        private BigDecimal nextMonthPerNew_GMVDiff;

        @ExcelProperty("月活跃会员次月留存率差值")
        private BigDecimal mauNextMonthRetentionRateDiff;

        @ExcelProperty("新会员次月留存率差值")
        private BigDecimal newMemberNextMonthRetentionRateDiff;
}
