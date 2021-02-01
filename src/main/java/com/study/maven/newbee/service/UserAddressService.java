package com.study.maven.newbee.service;

import com.study.maven.newbee.vo.UserAddressVO;

import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 14:34
 */
public interface UserAddressService {

    /**
     * 根据用户id获取所有的收货地址
     * @param userId 用户id
     * @return 收货地址集合
     */
    List<UserAddressVO> getUserAddressByUserId(Long userId);

    /**
     * 修改收货地址
     * @param userAddressVO 要修改的收货地址参数
     * @param userId 用户id
     */
    void updateUserAddress(UserAddressVO userAddressVO, Long userId);

    /**
     * 添加收货地址
     * @param userAddressVO 要添加的收货地址
     * @param userId 用户id
     */
    void addUserAddress(UserAddressVO userAddressVO, Long userId);

    /**
     * 根据用户id和收货地址id删除用户地址
     * @param addressId 收货地址id
     * @param userId 用户id
     */
    void deleteUserAddress(Long addressId, Long userId);

    /**
     * 清空收货地址
     * @param userId 用户id
     */
    void clearUserAddress(Long userId);

    /**
     * 根据userId获取默认的收货地址
     * @param userId 用户id
     * @return 用户地址VO
     */
    UserAddressVO getDefaultAddressByUserId(Long userId);
}
