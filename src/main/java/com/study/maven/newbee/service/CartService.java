package com.study.maven.newbee.service;

import com.study.maven.newbee.entity.User;
import com.study.maven.newbee.vo.CartParamVO;
import com.study.maven.newbee.vo.CartVO;
import com.study.maven.newbee.vo.PageResult;
import com.study.maven.newbee.vo.UpdateCartParamVO;

import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/31 15:43:09
 */
public interface CartService {

    /**
     * 获取用户下的购物车数据
     * @param userId 用户id
     * @return 用户id对应的购物车数据
     */
    PageResult<CartVO> getOwnCart(Long userId, Integer pageSize, Integer currentPage);

    /**
     * 添加用户的购物车数据，无返回值，如果有错误，抛出异常，走统一异常处理器
     * @param cartParamVO 要 添加的购物车数据
     * @param userId 登录的用户的id
     */
    void addCart(CartParamVO cartParamVO, Long userId);

    /**
     * 更新购物车数量
     * @param updateCartParamVO 要更新的购物车参数VO
     * @param userId 用户id
     */
    void updateCartNum(UpdateCartParamVO updateCartParamVO, Long userId);

    /**
     * 删除一条购物车对象
     * @param id 购物车id
     * @param userId 用户id
     */
    void deleteCart(Long id, Long userId);

    /**
     * 清空购物车
     * @param userId 用户id
     */
    void clearCart(Long userId);

    /**
     * 根据购物车id集合查询购物车信息
     * @param cartIds 购物车id
     * @param userId 用户id
     * @return 查询到的数据
     */
    List<CartVO> getCartVOListByCartIds(List<Long> cartIds, Long userId);
}
