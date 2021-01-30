package com.study.maven.newbee.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品分类表，三级分类
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 9:37:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_newbee_mall_goods_category")
public class Category implements Serializable {
    private static final long serialVersionUID = 7385790216032340351L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long categoryId;            // 分类id
    private Integer categoryLevel;      // 分类等级，1,2,3级分类
    private Long parentId;              // 父级id
    private String categoryName;        // 分类名称
    private Integer categoryRank;       // 分类排序，序号越大，月靠前
    private Boolean isDeleted;          // 是否删除
    private Date createTime;        // 创建时间
    private Integer createUser;         // 创建用户
    private Date updateTime;        // 更新时间
    private Integer updateUser;         // 更新用户

}
