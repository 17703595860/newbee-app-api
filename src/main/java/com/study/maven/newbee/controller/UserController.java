package com.study.maven.newbee.controller;

import com.study.maven.newbee.base.controller.ResultGenerator;
import com.study.maven.newbee.config.annotation.TokenToUser;
import com.study.maven.newbee.entity.User;
import com.study.maven.newbee.mapper.UserMapper;
import com.study.maven.newbee.mapper.UserTokenMapper;
import com.study.maven.newbee.service.UserService;
import com.study.maven.newbee.vo.Result;
import com.study.maven.newbee.vo.UserLoginParamVO;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/25 22:57:03
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
@Api(tags = "用户接口")
public class UserController implements ResultGenerator {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;

    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "")
//    @ApiImplicitParam(name = "userLoginParamVO", required = true, value = "登录用户", dataType = "UserLoginParamVO")
    private ResponseEntity<Result<?>> login(@RequestBody @Valid @ApiParam("要登录的用户信息") UserLoginParamVO userLoginParamVO) {
        String token = userService.login(userLoginParamVO.getUsername(), userLoginParamVO.getPassword());
        if (StringUtils.isBlank(token)) {
            return internalServererror("生成token失败");
        }
        return success(token);
    }

    @ApiOperation(value = "获取登录用户信息", notes = "")
    @GetMapping("/info")
    public ResponseEntity<Result<?>> findLoginUser(@TokenToUser @ApiIgnore User user) {
        // 已登录，直接返回，因为没有记录密码，所以直接返回即可
        return success(user);
    }

    @ApiOperation(value = "修改用户信息", notes = "")
    @PutMapping("/info")
    public ResponseEntity<Result<?>> updateUser(@RequestBody @ApiParam("要修改的用户信息") User updateUser, @TokenToUser @ApiIgnore User user) {
        // 手机端，个人中心，只能设置自己的信息
        updateUser.setUserId(user.getUserId());
        // 如果修改的事密码，加密
        if (updateUser.getPasswordMd5() != null){
            updateUser.setPasswordMd5(DigestUtils.md5Hex(updateUser.getPasswordMd5()));
        }
        // 去除不允许修改的项
        updateUser.setCreateTime(null);
        updateUser.setIsDeleted(null);
        updateUser.setLockedFlag(null);

        int flag = userMapper.updateByPrimaryKeySelective(updateUser);
        if (flag == 1) {
            return success();
        }
        return internalServererror("修改个人信息失败");
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出接口", notes = "清除token")
    public ResponseEntity<Result<?>> logout(@TokenToUser @ApiIgnore User user) {
        int flag = userTokenMapper.deleteByPrimaryKey(user.getUserId());
        if (flag == 1) {
            return success();
        }
        return internalServererror("退出错误");
    }

}
