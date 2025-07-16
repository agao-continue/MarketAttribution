package com.hy.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AlogDiff {
    private BigDecimal cpmDiff;
    private BigDecimal roiDiff;
    private BigDecimal cacDiff;
    private BigDecimal mamcRateDiff;
    private BigDecimal mamcGmvRateDiff;
    private BigDecimal rnmRoiDiff;
    private BigDecimal rnmCacDiff;
    private BigDecimal nmcRateDiff;
    private BigDecimal nmcGmvRateDiff;
    private BigDecimal pcmaGmvDiff;
    private BigDecimal mpcmaGmvDiff;
    private BigDecimal pcnGmvDiff;
    private BigDecimal panGmvDiff;
    private BigDecimal masmRateDiff;
    private BigDecimal mrrRateDiff;
}