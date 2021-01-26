package com.study.maven.newbee.utils;

import com.study.maven.newbee.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/26 22:08:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayLoad implements Serializable {

    private static final long serialVersionUID = -5358094024123744359L;

    private int expire;
    private User user;

}
