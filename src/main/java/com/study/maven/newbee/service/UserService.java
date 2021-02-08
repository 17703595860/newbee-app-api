package com.study.maven.newbee.service;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/25 22:51:49
 */
public interface UserService {

    /**
     * 登录，根据用户名密码登录，返回token
     * @param username 用户名
     * @param password 密码
     * @return String 返回Token
     */
    String login(String username, String password);

    /**
     * 注册用户
     * @param username 用户名
     * @param password 密码
     */
    void register(String username, String password);
}
