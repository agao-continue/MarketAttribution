package com.hy.back;

import com.hy.api.DissApi;
import com.hy.pojo.TbDistributor;
import com.hy.result.Result;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public class DissFallBack implements FallbackFactory<DissApi> {

    @Override
    public DissApi create(Throwable cause) {
        return new DissApi() {
            @Override
            public List<TbDistributor> selectById(String id){
                return new ArrayList<TbDistributor>();
            };
        };
    }
}
