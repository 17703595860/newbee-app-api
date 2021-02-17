package com.study.maven.newbee.vo;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 19:34:19
 */
@ApiModel("统一的分页结果对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 778085959303124383L;

    @Builder.Default
    @ApiModelProperty("总条数")
    private Integer totalCount = 0;
    @Builder.Default
    @ApiModelProperty("总页数")
    private Integer totalPage = 0;
    @Builder.Default
    @ApiModelProperty("页面条数")
    private Integer pageSize = 0;
    @Builder.Default
    @ApiModelProperty("当前页数")
    private Integer currentPage = 1;
    private List<T> list;

    public PageResult(PageInfo<T> pageInfo) {
        this.totalCount = Long.valueOf(pageInfo.getTotal()).intValue();
        this.totalPage = pageInfo.getPages();
        this.pageSize = pageInfo.getPageSize();
        this.currentPage = pageInfo.getPageNum();
        this.list = pageInfo.getList();
    }

    public PageResult(Integer totalCount, Integer totalPage, Integer pageSize, Integer currentPage) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }
}
