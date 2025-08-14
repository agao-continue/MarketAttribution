package com.hy.api;

import com.hy.back.DissFallBack;
import com.hy.pojo.TbDistributor;
import com.hy.result.Result;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
//@FeignClient("diss-service")
@FeignClient(value = "diss-service", fallbackFactory =  DissFallBack.class)
public interface DissApi {
    //根据分销商id查询
    @GetMapping("/diss/selectById")
    public List<TbDistributor> selectById(@RequestParam String id);
}
