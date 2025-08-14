package com.hy.controller;

import com.hy.pojo.TbDistributor;
import com.hy.result.ContentResult;
import com.hy.result.Result;
import com.hy.service.SelectByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diss")
public class DissController {
    @Autowired
    private SelectByIdService  selectByIdService;
    //根据分销商id查询
    @GetMapping("/selectById")
    public List<TbDistributor> selectById(@RequestParam String id) {
        List<TbDistributor> tbDistributor = selectByIdService.selectById(id);
        return tbDistributor;
    }
}
