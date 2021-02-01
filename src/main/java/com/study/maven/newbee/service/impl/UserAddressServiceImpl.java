package com.study.maven.newbee.service.impl;

import com.study.maven.newbee.entity.UserAddress;
import com.study.maven.newbee.exception.SystemException;
import com.study.maven.newbee.mapper.UserAddressMapper;
import com.study.maven.newbee.service.UserAddressService;
import com.study.maven.newbee.vo.UserAddressVO;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 14:35
 */
@Service("userAddressService")
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
}
