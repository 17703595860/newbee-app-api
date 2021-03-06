package com.study.maven.newbee.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 前台用户token表
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/25 22:47:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_newbee_mall_user_token")
public class UserToken implements Serializable {

    private static final long serialVersionUID = -7131683973118659472L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long UserId;            // 用户id
    private String token;           // 用户登录成功的token
    private Date updateTime;        // 更新时间
    private Date expireTime;        // 过期时间

}
