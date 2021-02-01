package com.study.maven.newbee.entity;

import com.study.maven.newbee.vo.UserAddressVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 13:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_newbee_mall_user_address")
public class UserAddress implements Serializable {
    private static final long serialVersionUID = 1114917619781884293L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long addressId;             // 收货地址id
    private Long userId;                // 用户id
    private String userName;            // 收货人姓名
    private String userPhone;             // 收货手机号
    private Boolean defaultFlag;        // 是否默认地址
    private String provinceName;        // 省
    private String cityName;            // 市
    private String regionName;          // 县
    private String detailAddress;       // 详细地址
    private Boolean isDeleted;          // 是否删除
    private Date createTime;            // 创建时间
    private Date updateTime;            // 更新时间

    public static UserAddress transform(UserAddressVO userAddressVO) {
        UserAddress userAddress = builder().build();
        BeanUtils.copyProperties(userAddressVO, userAddress);
        return userAddress;
    }
}
