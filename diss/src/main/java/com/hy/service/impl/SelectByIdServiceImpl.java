package com.hy.service.impl;

import com.hy.mapper.TbDistributorMapper;
import com.hy.pojo.TbDistributor;
import com.hy.service.SelectByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectByIdServiceImpl implements SelectByIdService {
    @Autowired
    private TbDistributorMapper DistributorMapper;

    @Override
    public List<TbDistributor> selectById(String id) {
        List<TbDistributor> tbList=DistributorMapper.selectByUid(id);
        return tbList;
    }
}
