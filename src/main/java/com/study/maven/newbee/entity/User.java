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
    private Long userId;
    private String nickName;
    private String loginName;
    private String passwordMd5;
    private String introduceSign;
    private Boolean isDeleted;
    private Boolean lockedFlag;
    private Date createTime;


}
