package com.hy.pojo;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ActivityPlatformGroup {
    private String campaignName;

    private String media;

    private BigDecimal cost;

    private Long exposureCount;

    private Long exposureUserCount;

    private BigDecimal avgImpressionFrequency;

    private Long clickCount;

    private Long clickUserCount;

    private Long monthlyActiveMemberCount;

    private BigDecimal monthlyActiveMemberGmv;

    private Long newMemberAcquisitionCount;

    private BigDecimal newMemberAcquisitionGmv;

    private Long nextMonthActiveMemberCount;

    private BigDecimal nextMonthActiveMemberGmv;

    private Long nextMonthNewMemberCount;

    private BigDecimal nextMonthNewMemberGmv;

    private BigDecimal cpm;
    private BigDecimal roi;
    private BigDecimal cac;
    private BigDecimal mamcRate;
    private BigDecimal mamcGmvRate;
    private BigDecimal rnmRoi;
    private BigDecimal rnmCac;
    private BigDecimal nmcRate;
    private BigDecimal nmcGmvRate;
    private BigDecimal pcmaGmv;
    private BigDecimal mpcmaGmv;
    private BigDecimal pcnGmv;
    private BigDecimal panGmv;
    private BigDecimal masmRate;
    private BigDecimal mrrRate;



}