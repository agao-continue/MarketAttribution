package com.hy.controller;

import com.hy.api.DissApi;
import com.hy.pojo.TbDistributor;
import com.hy.pojo.TbUser;
import com.hy.result.ContentResult;
import com.hy.result.Result;
import com.hy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DissApi dissApi;
    @GetMapping("selectByUid")
    public TbUser selectByUid(@RequestParam("uid") String uid) {
        TbUser user= userService.selectByUid(uid);
        List< TbDistributor> list =dissApi.selectById(uid);
        user.setDistributors(list);
        return user;
    }

    @GetMapping("/testSearch")
    public String testSearch(){
        return "testSearch";
    }
    @GetMapping("/testPay")
    public String testPay(){
        return "testPay";
    }
}
