package com.hy.pojo;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ConversionIncome {
    private BigDecimal cost;
    private Long exposureCount;
    private Long exposureUserCount;
    private Long monthlyActiveMemberCount;
    private Long newMemberAcquisitionCount;
    private BigDecimal emacRate;
    private BigDecimal etnccRate;
}
