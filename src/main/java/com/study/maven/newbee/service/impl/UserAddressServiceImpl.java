package com.study.maven.newbee.service.impl;

import com.study.maven.newbee.entity.UserAddress;
import com.study.maven.newbee.exception.SystemException;
import com.study.maven.newbee.mapper.UserAddressMapper;
import com.study.maven.newbee.service.UserAddressService;
import com.study.maven.newbee.vo.UserAddressVO;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 14:35
 */
@Service("userAddressService")
@Transactional
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddressVO> getUserAddressByUserId(Long userId) {
        return userAddressMapper.selectAllByUserId(userId);
    }

    @Override
    public void updateUserAddress(UserAddressVO userAddressVO, Long userId) {
        if (userAddressVO.getAddressId() == null) {
            throw new SystemException("参数错误，修改失败");
        }
        Long count = userAddressMapper.selectCountByUserIdAndAddressId(userId, userAddressVO.getAddressId());
        if (count != 1) {
            throw new SystemException("改用户下没有此地址，请联系管理员");
        }
        // 如果是默认地址，吧已经存在的都改为不默认
        if (userAddressVO.getDefaultFlag()) {
            userAddressMapper.updateNotDefaultByUserId(userId);
        }
        UserAddress userAddress = UserAddress.transform(userAddressVO);
        userAddress.setUserId(userId);
        DateTime now = DateTime.now();
        userAddress.setUpdateTime(now.toDate());
        userAddressMapper.updateByPrimaryKeySelective(userAddress);
    }

    @Override
    public void addUserAddress(UserAddressVO userAddressVO, Long userId) {
        DateTime now = DateTime.now();
        UserAddress userAddress = UserAddress.transform(userAddressVO);
        userAddress.setUserId(userId);
        // 如果是默认地址，吧已经存在的都改为不默认
        if (userAddress.getDefaultFlag()) {
            userAddressMapper.updateNotDefaultByUserId(userId);
        }
        // 如果没有地址，第一次添加自动默认
        int count = userAddressMapper.selectCount(new UserAddress() {{
            setUserId(userId);
        }});
        if (count == 0) {
            userAddress.setDefaultFlag(true);
        }
        UserAddress tempData = userAddressMapper.selectOne(userAddress);
        if (tempData == null) {
            userAddress.setCreateTime(now.toDate());
            userAddress.setUpdateTime(now.toDate());
            userAddress.setIsDeleted(false);
            userAddressMapper.insertSelective(userAddress);
        } else {
            if (tempData.getIsDeleted()) {
                tempData.setIsDeleted(false);
                tempData.setUpdateTime(now.toDate());
                userAddressMapper.updateByPrimaryKeySelective(tempData);
            } else {
                throw new SystemException("收货地址已经存在");
            }
        }
    }

    @Override
    public void deleteUserAddress(Long addressId, Long userId) {
        UserAddress userAddress = userAddressMapper.selectOneByUserIdAndAddressId(userId, addressId);
        if (userAddress == null) {
            throw new SystemException("收货地址不存在");
        }
        if (userAddress.getDefaultFlag()) {
            throw new SystemException("默认地址不能删除，请先修改默认收货地址");
        }
        userAddressMapper.deleteByUserIdAndAddressId(userId, addressId);
    }

    @Override
    public void clearUserAddress(Long userId) {
        userAddressMapper.clearByUserIdAndAddressId(userId);
    }

    @Override
    public UserAddressVO getDefaultAddressByUserId(Long userId) {
        return userAddressMapper.getDefaultAddressByUserId(userId);
    }

    @Override
    public UserAddressVO getUserAddressByUserIdAndAddressId(Long userId, Long addressId) {
        UserAddress userAddress = userAddressMapper.selectOneByUserIdAndAddressId(userId, addressId);
        UserAddressVO userAddressVO = UserAddressVO.transform(userAddress);
        return userAddressVO;
    }

    @Override
    public UserAddressVO getDefaultUserAddressByUserId(Long userId) {
        List<UserAddressVO> userAddressVOList = this.getUserAddressByUserId(userId);
        List<UserAddressVO> defaultAddressList = userAddressVOList.stream().filter(address -> address.getDefaultFlag()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(defaultAddressList)) {
            throw new SystemException("无默认的收货地址，请设置默认收货地址");
        }
        return defaultAddressList.get(0);
    }
}
