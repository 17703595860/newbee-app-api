package com.study.maven.newbee.controller;

import com.study.maven.newbee.base.controller.ResultGenerator;
import com.study.maven.newbee.config.annotation.TokenToUser;
import com.study.maven.newbee.entity.User;
import com.study.maven.newbee.service.UserAddressService;
import com.study.maven.newbee.vo.Result;
import com.study.maven.newbee.vo.UserAddressVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 14:36
 */
@RestController
@RequestMapping("/api/user-address")
@Api(tags = "用户收货地址接口")
public class UserAddressController implements ResultGenerator {

    @Autowired
    private UserAddressService userAddressService;

    @GetMapping("/{id}")
    @ApiOperation("根据收货地址id，获取当前登录用户的对应的收货地址")
    public ResponseEntity<Result<?>> getUserAddressById(@ApiParam("收货地址id") @PathVariable Long id, @TokenToUser @ApiIgnore User user) {
        UserAddressVO userAddressVO = userAddressService.getUserAddressByUserIdAndAddressId(user.getUserId(), id);
        return success(userAddressVO);
    }

    @GetMapping("/default")
    @ApiOperation("获取当前登录用户的默认的收货地址")
    public ResponseEntity<Result<?>> getDefaultUserAddress(@TokenToUser @ApiIgnore User user) {
        UserAddressVO userAddressVO = userAddressService.getDefaultUserAddressByUserId(user.getUserId());
        return success(userAddressVO);
    }

    @GetMapping
    @ApiOperation("获取当前登录用户的所有收货地址")
    public ResponseEntity<Result<?>> getUserAddress(@TokenToUser @ApiIgnore User user) {
        List<UserAddressVO> userAddressVOS = userAddressService.getUserAddressByUserId(user.getUserId());
        if (CollectionUtils.isEmpty(userAddressVOS)) {
            return notFound();
        }
        return success(userAddressVOS);
    }

    @PutMapping
    @ApiOperation("修改收货地址")
    public ResponseEntity<Result<?>> updateUserAddress(@ApiParam("要修改的收货地址信息") @RequestBody UserAddressVO userAddressVO, @TokenToUser @ApiIgnore User user) {
        userAddressService.updateUserAddress(userAddressVO, user.getUserId());
        return success();
    }

    @PostMapping
    @ApiOperation("新增收货地址")
    public ResponseEntity<Result<?>> addUserAddress(@ApiParam("要新增的收货地址信息") @RequestBody @Valid UserAddressVO userAddressVO, @TokenToUser @ApiIgnore User user) {
        userAddressService.addUserAddress(userAddressVO, user.getUserId());
        return success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除收货地址")
    public ResponseEntity<Result<?>> deleteUserAddress(@ApiParam("要删除的收货地址id") @PathVariable Long id, @TokenToUser @ApiIgnore User user) {
        userAddressService.deleteUserAddress(id, user.getUserId());
        return success();
    }

    @DeleteMapping("/clear")
    @ApiOperation("情空收货地址")
    public ResponseEntity<Result<?>> clearUserAddress(@TokenToUser @ApiIgnore User user) {
        userAddressService.clearUserAddress(user.getUserId());
        return success();
    }

}
