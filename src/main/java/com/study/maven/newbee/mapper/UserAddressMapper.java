package com.study.maven.newbee.mapper;

import com.study.maven.newbee.entity.UserAddress;
import com.study.maven.newbee.vo.UserAddressVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 14:29
 */
@Repository
public interface UserAddressMapper extends Mapper<UserAddress> {

    /**
     * 根据用户id查询所有的收货地址
     * @param userId 用户id
     * @return 所有的收货地址
     */
    List<UserAddressVO> selectAllByUserId(@Param("userId") Long userId);

    /**
     * 根据用户id和收货地址id查询存在不存在
     * @param userId 用户id
     * @param addressId 收货地址id
     * @return Long 条数
     */
    Long selectCountByUserIdAndAddressId(@Param("userId") Long userId, @Param("addressId") Long addressId);

    /**
     * 根据用户id和收货地址id删除用户地址
     * @param addressId 收货地址id
     * @param userId 用户id
     */
    void deleteByUserIdAndAddressId(@Param("userId") Long userId, @Param("addressId") Long addressId);

    /**
     * 清空收货地址
     * @param userId 用户id
     */
    void clearByUserIdAndAddressId(@Param("userId") Long userId);

    /**
     * 根据userId获取默认的收货地址
     * @param userId 用户id
     * @return 用户地址VO
     */
    UserAddressVO getDefaultAddressByUserId(@Param("userId") Long userId);

    /**
     * 根据用户id和收货地址id获取用户地址
     * @param addressId 收货地址id
     * @param userId 用户id
     */
    UserAddress selectOneByUserIdAndAddressId(@Param("userId") Long userId, @Param("addressId") Long addressId);
}
