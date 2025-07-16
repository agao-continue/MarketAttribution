package com.hy.utils;

import com.hy.pojo.Alog;
import com.hy.pojo.AlogAvg;
import com.hy.pojo.AlogDiff;

public class AlogUtil {
    public static AlogDiff getAlogDiff(Alog alog, AlogAvg alogAvg) {
        AlogDiff diff = new AlogDiff();
        diff.setCacDiff(alog.getCac().subtract(alogAvg.getCacAvg()));
        diff.setRoiDiff(alog.getRoi().subtract(alogAvg.getRoiAvg()));
        diff.setCacDiff(alog.getCac().subtract(alogAvg.getCacAvg()));
        diff.setMamcRateDiff(alog.getMamcRate().subtract(alogAvg.getMamcRateAvg()));
        diff.setMamcGmvRateDiff(alog.getMamcGmvRate().subtract(alogAvg.getMamcGmvRateAvg()));
        diff.setRnmRoiDiff(alog.getRnmRoi().subtract(alogAvg.getRnmRoiAvg()));
        diff.setRnmCacDiff(alog.getRnmCac().subtract(alogAvg.getRnmCacAvg()));
        diff.setNmcRateDiff(alog.getNmcRate().subtract(alogAvg.getNmcRateAvg()));
        diff.setNmcGmvRateDiff(alog.getNmcGmvRate().subtract(alogAvg.getNmcGmvRateAvg()));
        diff.setPcmaGmvDiff(alog.getPcmaGmv().subtract(alogAvg.getPcmaGmvAvg()));
        diff.setMpcmaGmvDiff(alog.getMpcmaGmv().subtract(alogAvg.getMpcmaGmvAvg()));
        diff.setPcnGmvDiff(alog.getPcnGmv().subtract(alogAvg.getPcnGmvAvg()));
        diff.setPanGmvDiff(alog.getPanGmv().subtract(alogAvg.getPanGmvAvg()));
        diff.setMasmRateDiff(alog.getMasmRate().subtract(alogAvg.getMasmRateAvg()));
        diff.setMrrRateDiff(alog.getMrrRate().subtract(alogAvg.getMrrRateAvg()));
        return diff;
    }
}
