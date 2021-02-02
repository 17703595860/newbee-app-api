package com.study.maven.newbee.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.maven.newbee.config.entity.Constants;
import com.study.maven.newbee.entity.Cart;
import com.study.maven.newbee.entity.GoodsInfo;
import com.study.maven.newbee.exception.SystemException;
import com.study.maven.newbee.mapper.CartMapper;
import com.study.maven.newbee.mapper.GoodsInfoMapper;
import com.study.maven.newbee.service.CartService;
import com.study.maven.newbee.vo.CartParamVO;
import com.study.maven.newbee.vo.CartVO;
import com.study.maven.newbee.vo.PageResult;
import com.study.maven.newbee.vo.UpdateCartParamVO;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/31 15:43:22
 */
@Service("cartService")
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @Autowired
    private Constants constants;

    private final static Long CART_NUM = 30L;

    @Override
    public PageResult<CartVO> getOwnCart(Long userId, Integer pageSize, Integer currentPage) {
        // 不分页的情况
        if (pageSize == -1 && currentPage == -1) {
            List<CartVO> cartVOS = cartMapper.selectAllByUserId(userId);
            return PageResult.<CartVO>builder().list(cartVOS).build();
        }
        // 分页情况
        pageSize = pageSize < 1 ? constants.getCartPageSize() : pageSize;
        currentPage = currentPage < 1 ? constants.getCartCurrentPage() : currentPage;
        PageHelper.startPage(currentPage, pageSize);
        List<CartVO> cartVOS = cartMapper.selectAllByUserId(userId);
        PageInfo<CartVO> pageInfo = new PageInfo<>(cartVOS);
        return new PageResult<>(pageInfo);
    }

    @Override
    public void addCart(CartParamVO cartParamVO, Long userId) {
        // 查看当前用户有几条购物车数据
        Long count = cartMapper.selectCountByUserId(userId);
        if (count >= CART_NUM) {
            throw new SystemException("您的购物车已经超出限制");
        }
        // 根据商品id获取商品信息
        Example example = new Example(GoodsInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodsId", cartParamVO.getGoodsId());
        criteria.andEqualTo("goodsSellStatus", true);
        GoodsInfo goodsInfo = goodsInfoMapper.selectOneByExample(example);
        if (goodsInfo == null) {
            throw new SystemException("商品不存在或者已经被删除");
        }
        // 判断数量
        if (cartParamVO.getGoodsCount() < 1) {
            throw new SystemException("商品数量不能小于1");
        }
        if (cartParamVO.getGoodsCount() > goodsInfo.getStockNum()) {
            throw new SystemException("库存不足");
        }
        // 添加购物车
        Cart cart = cartMapper.selectByGoodsIdAndUserId(cartParamVO.getGoodsId(), userId);
        DateTime now = DateTime.now();
        if (cart == null) {
            // 添加
            Cart saveCart = new Cart();
            BeanUtils.copyProperties(cartParamVO, saveCart);
            saveCart.setIsDeleted(false);
            saveCart.setUserId(userId);
            saveCart.setCreateTime(now.toDate());
            saveCart.setUpdateTime(now.toDate());
            // 插入
            cartMapper.insertSelective(saveCart);
        } else {
            // 已存在数据
            // 覆盖
            if (cart.getIsDeleted()) {
                // 如果有删除标记的
                cart.setGoodsCount(cartParamVO.getGoodsCount());
                cart.setIsDeleted(false);
            } else {
                int goodsCount = cart.getGoodsCount() + cartParamVO.getGoodsCount();
                if (goodsCount > goodsInfo.getStockNum()) {
                    throw new SystemException("库存不足");
                }
                cart.setGoodsCount(goodsCount);
            }
            // 更新
            cart.setUpdateTime(now.toDate());
            cartMapper.updateByPrimaryKeySelective(cart);
        }

    }

    @Override
    public void updateCartNum(UpdateCartParamVO updateCartParamVO, Long userId) {
        Cart cart = cartMapper.selectOneByUserIdAndCartId(userId, updateCartParamVO.getCartItemId());
        if (cart == null) {
            throw new SystemException("购物车商品不存在");
        }
        if (cart.getIsDeleted()) {
            throw new SystemException("购物车商品已经被删除");
        }
        GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(cart.getGoodsId());
        if (goodsInfo == null || !goodsInfo.getGoodsSellStatus()) {
            throw new SystemException("商品不存在或者已被下架");
        }
        int count = cart.getGoodsCount() + updateCartParamVO.getGoodsCount();
        if (count < 0) {
            throw new SystemException("不能改为负");
        }
        if (count > goodsInfo.getStockNum()) {
            throw new SystemException("商品库存不足");
        }
        cart.setGoodsCount(count);
        int flag = cartMapper.updateByPrimaryKeySelective(cart);
        if (flag != 1) {
            throw new SystemException("更新失败");
        }
    }

    @Override
    public void deleteCart(Long id, Long userId) {
        cartMapper.deleteCart(id, userId);
    }

    @Override
    public void clearCart(Long userId) {
        cartMapper.clearCart(userId);
    }

}
