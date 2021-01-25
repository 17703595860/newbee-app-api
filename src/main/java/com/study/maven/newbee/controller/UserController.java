package com.study.maven.newbee.controller;

import com.study.maven.newbee.service.UserService;
import com.study.maven.newbee.vo.Result;
import com.study.maven.newbee.vo.UserLoginParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/25 22:57:03
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
@Api(value = "用户接口", tags = "用户接口1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "")
    private ResponseEntity<Result<String>> login(@RequestParam @Valid UserLoginParam userLoginParam) {
        String token = userService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
        if (StringUtils.isBlank(token)) {
            return ResponseEntity.status(500).body(Result.<String>builder().resultCode(500).message("生成token失败").build());
        }
        return ResponseEntity.ok(Result.<String>builder().data(token).build());
    }

}
