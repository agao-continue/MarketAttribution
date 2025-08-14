package com.hy.result;

public interface ContentResult {
    public static final String SUCCESS_CODE="1";
    public static final String SUCCESS_MESSAGE="操作成功";
    public static final String SUCCESS_Login_CODE="2";
    public static final String SUCCESS_Login_MESSAGE="登录成功";
    public static final String SUCCESS_Login_CODE1="3";
    public static final String SUCCESS_Login_MESSAGE1="登录失败";
    public static final String TOKEN_NOT_NULL_CODE="4";
    public static final String TOKEN_NOT_NULL_MESSAGE="token无效";
    public static final String FAIL_CODE="5";
    public static final String FAIL_MESSAGE="查询失败，用户ID有误或用户不存在";
}
