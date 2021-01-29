package com.study.maven.newbee.utils;

import com.study.maven.newbee.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/26 22:43:43
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtUtilsTest {

    @Autowired
    private JwtProperties jwtProperties;

    @Test
    public void generateToken() throws Exception {
        User user = new User(1L, "aa", "aa", "sdf", "sdf", true, true, new Date());
        String token = JwtUtils.generateToken(PayLoad.builder().user(user).build(), jwtProperties.getPrivateKey(), jwtProperties.getExpire());
        System.out.println(token);
    }

    @Test
    public void getInfoFromToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJ1c2VyIjoie1wiZXhwaXJlXCI6MCxcInVzZXJcIjp7XCJ1c2VySWRcIjoxLFwibmlja05hbWVcIjpcImFhXCIsXCJsb2dpbk5hbWVcIjpcImFhXCIsXCJwYXNzd29yZE1kNVwiOlwic2RmXCIsXCJpbnRyb2R1Y2VTaWduXCI6XCJzZGZcIixcImxvY2tlZEZsYWdcIjp0cnVlLFwiY3JlYXRlVGltZVwiOjE2MTE2NzI2NTE3NzksXCJkZWxldGVkXCI6dHJ1ZX19IiwiZXhwIjoxNjExNjc0NDUxfQ.JfpqfapRnjPH68Zj3JBazfEVg9KPSSEE2I_OM4d97KU__SPXwBHQPxUUPUdypg_7jHWhmRILSbTm62HTUO0sRjOSb5-bDugJhLYYm93VDfJyt-_Q85ySB3UgPQKg3ojUg-AgmsQbBn_kF_WYrRkMhtOD9qdhEvrWU5mtm-vpx_NHw8dZdF2SaSB8NjzZrCj_ZYmlXlZ8WXP2R76IdQnBp-jhWObF4uXMCb0N1mDk1j2yOHZbvCXUwJq8k9p5qR4xUEkchd9eLIVJXNVKSLhmSiWjpbeEAwvTq5aAQRok967U-tAA4FHt2jyY1PmT_aRvXSROLy8o3K5623Gv0Z7tsQ";
        PayLoad payLoad = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        System.out.println(payLoad.getUser());
    }

    @Test
    public void md5Password() {
        String md5Pwd = DigestUtils.md5Hex("123");
        System.out.println(md5Pwd);
    }

}