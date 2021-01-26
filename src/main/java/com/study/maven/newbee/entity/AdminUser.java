package com.study.maven.newbee.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/25 22:42:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_newbee_mall_admin_user")
public class AdminUser implements Serializable {

    private static final long serialVersionUID = 6915034159009175889L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminUserId;
    private String loginUserName;
    private String loginPassword;
    private String nickName;
    private Integer locked;

}
