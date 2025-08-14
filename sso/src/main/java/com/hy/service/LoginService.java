package com.hy.service;

import com.hy.pojo.TbUser;

public interface LoginService {
    TbUser getUserByUsnAndPsw(String username, String password);

    TbUser getUserByUsername(String username);
}
