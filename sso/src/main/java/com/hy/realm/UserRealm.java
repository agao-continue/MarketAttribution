package com.hy.realm;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.hy.pojo.TbUser;
import com.hy.service.LoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private LoginService loginService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //强制转换成token类型
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //拿到账号密码
        String username = token.getUsername();
        //通过username查询用户是否存在
        TbUser tbUser= loginService.getUserByUsername(username);
        //判断返回是否为空
        if (tbUser == null) {
            return null;
        }
        //SimpleAuthenticationInfo sim = new SimpleAuthenticationInfo(tbUser, tbUser.getPassword(), getName());
        SimpleAuthenticationInfo sim =new SimpleAuthenticationInfo(tbUser,tbUser.getPassword(), ByteSource.Util.bytes(tbUser.getSalt()), "UserRealm");
        return sim;
    }
}
