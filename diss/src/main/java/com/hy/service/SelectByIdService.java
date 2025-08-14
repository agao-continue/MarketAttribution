package com.hy.service;

import com.hy.pojo.TbDistributor;

import java.util.List;

public interface SelectByIdService {
    List<TbDistributor> selectById(String id);
}
