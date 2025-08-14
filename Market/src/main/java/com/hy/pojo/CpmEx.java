package com.hy.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class CpmEx {

    private Integer cid;

    private String cname;

    private BigDecimal cpm;

    private Date cpmDate;

    private BigDecimal cpmDiff;
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public BigDecimal getCpm() {
        return cpm;
    }

    public void setCpm(BigDecimal cpm) {
        this.cpm = cpm;
    }

    public Date getCpmDate() {
        return cpmDate;
    }

    public void setCpmDate(Date cpmDate) {
        this.cpmDate = cpmDate;
    }

    public BigDecimal getCpmDiff() {
        return cpmDiff;
    }

    public void setCpmDiff(BigDecimal cpmDiff) {
        this.cpmDiff = cpmDiff;
    }
}