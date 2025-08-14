package com.hy.service.impl;

import com.hy.mapper.TbUserMapper;
import com.hy.pojo.TbUser;
import com.hy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser selectByUid(String uid) {
        return tbUserMapper.selectByPrimaryKey(Integer.parseInt(uid));
    }
}
