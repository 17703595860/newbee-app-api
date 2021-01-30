package com.study.maven.newbee.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * 前台用户表
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/26 21:52:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_newbee_mall_user")
public class User implements Serializable {

    private static final long serialVersionUID = 8622935789737728500L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long userId;            // 用户id
    private String nickName;        // 用户昵称
    private String loginName;       // 登录名
    private String passwordMd5;     // 密码
    private String introduceSign;   // 个性签名
    private Boolean isDeleted;      // 是否删除
    private Boolean lockedFlag;     // 是否锁定
    private Date createTime;        // 创建时间


}
