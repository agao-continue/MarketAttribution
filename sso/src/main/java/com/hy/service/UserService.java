package com.hy.service;

import com.hy.pojo.TbUser;

public interface UserService {
    TbUser selectByUid(String uid);
}
