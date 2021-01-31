package com.study.maven.newbee.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
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
 * @date : Created in  2021/1/30 17:46:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("三级分类VO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryVO implements Serializable {
    private static final long serialVersionUID = -1864142053864231153L;

    @ApiModelProperty("分类id")
    private Long categoryId;            // 分类id
    @ApiModelProperty("分类等级，1,2,3级分类")
    private Integer categoryLevel;      // 分类等级，1,2,3级分类
    @ApiModelProperty("父级id")
    private Long parentId;              // 父级id
    @ApiModelProperty("分类名称")
    private String categoryName;        // 分类名称
    @ApiModelProperty("分类排序，序号越大，越靠前")
    private Integer categoryRank;       // 分类排序，序号越大，月靠前
    @ApiModelProperty("子集分类集合")
    private List<CategoryVO> children;

}
