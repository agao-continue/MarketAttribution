package com.hy.CpmScheduler;

import com.hy.pojo.ActivityAnalysis;
import com.hy.pojo.CpmEx;
import com.hy.service.CpmExService;
import com.hy.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public class scheduler {
    @Autowired
    private MarketService marketService;
    @Autowired
    private CpmExService cpmExService;
    @Scheduled(cron = "0/10 * * * * *")
    public void cpm(){
        List<ActivityAnalysis>list=marketService.findCpm();
        list.stream().forEach(x->{
            if(x.getCpm().intValue()>50){
                //将异常信息插入cpm_ex异常表中
                CpmEx ex=new CpmEx();
                ex.setCpm(x.getCpm());
                ex.setCid(Integer.parseInt(x.getCampaignId()));
                ex.setCname(x.getCampaignName());
                ex.setCpmDate(new Date());
                ex.setCpmDiff(x.getCpm().subtract(new BigDecimal(60)));
                cpmExService.insertEX(ex);
            }
        });
    }
}
