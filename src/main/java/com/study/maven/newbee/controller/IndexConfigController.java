package com.study.maven.newbee.controller;

import com.study.maven.newbee.base.controller.ResultGenerator;
import com.study.maven.newbee.service.IndexConfigService;
import com.study.maven.newbee.vo.IndexInfoVO;
import com.study.maven.newbee.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 10:41:07
 */
@RestController
@RequestMapping("/api/index-info")
@Api(tags = "首页信息接口")
public class IndexConfigController implements ResultGenerator {

    @Autowired
    private IndexConfigService indexConfigService;

    @GetMapping
    @ApiOperation("获取首页要展示的所有数据")
    public ResponseEntity<Result<?>> getIndexInfo() {
        IndexInfoVO indexInfo = indexConfigService.getIndexInfo();
        return success(indexInfo);
    }

}
