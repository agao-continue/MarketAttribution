package com.hy.service.impl;

import com.hy.mapper.TbUserMapper;
import com.hy.pojo.TbUser;
import com.hy.pojo.TbUserExample;
import com.hy.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser getUserByUsnAndPsw(String username, String password) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria ex = example.createCriteria();
        ex.andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
        return tbUsers!=null& tbUsers.size()>0?tbUsers.get(0):null;
    }

    @Override
    public TbUser getUserByUsername(String username) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria ex = example.createCriteria();
        ex.andUsernameEqualTo(username);
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
        return tbUsers!=null& tbUsers.size()>0?tbUsers.get(0):null;
    }
}
