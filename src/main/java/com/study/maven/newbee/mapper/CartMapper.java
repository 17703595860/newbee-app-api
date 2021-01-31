package com.study.maven.newbee.mapper;

import com.study.maven.newbee.entity.Cart;
import com.study.maven.newbee.vo.CartVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/31 15:41:41
 */
@Repository
public interface CartMapper extends Mapper<Cart> {

    /**
     * 根据用户id获取所属的购物车数据
     * @param userId 用户id
     * @return 购物车数据
     */
    List<CartVO> selectAllByUserId(@Param("userId") Long userId);

    /**
     * 根据用户id和商品id获取购物车数据
     * @param goodsId 商品id
     * @param userId 用户id
     * @return 购物车数据
     */
    Cart selectByGoodsIdAndUserId(@Param("goodsId") Long goodsId, @Param("userId") Long userId);

    /**
     * 查询改用户id对应的购物车数量
     * @param userId 用户id
     * @return 条数
     */
    Long selectCountByUserId(@Param("userId") Long userId);

    /**
     * 根据用户id和购物车id查找购物车数据
     * @param userId 用户id
     * @param cartItemId 购物车id
     * @return 购物车
     */
    Cart selectOneByUserIdAndCartId(@Param("userId") Long userId, @Param("cartItemId") Long cartItemId);

    /**
     * 删除购物车数据
     * @param id 购物车id
     * @param userId 用户id
     */
    void deleteCart(@Param("id") Long id, @Param("userId") Long userId);

    /**
     * 清空购物车
     * @param userId 用户id
     */
    void clearCart(@Param("userId") Long userId);
}
