package com.study.maven.newbee.entity;

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
 * 首页配置表
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 10:28:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_newbee_mall_index_config")
public class IndexConfig implements Serializable {
    private static final long serialVersionUID = -5328170697255823346L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long configId;          // 配置id
    private String configName;      // 显示字符(配置搜索时不可为空，其他可为空)
    private Integer configType;     // 配置类型，1搜索框热搜，2搜索下拉框热搜，3-(首页)热销商品 4-(首页)新品上线 5-(首页)为你推荐
    private Long goodsId;           // 商品id，默认为0
    private String redirectUrl;     // 点击后的额跳转地址
    private Integer configRank;     // 配置排序，越大越靠前
    private Boolean isDeleted;      // 是否删除
    private Integer createUser;         // 创建人
    private Date createTime;            // 创建时间
    private Integer updateUser;         // 更新人
    private Integer updateTime;         // 更新时间

}
