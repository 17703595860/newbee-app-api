package com.study.maven.newbee.base;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 9:45:59
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback(true)
@WebAppConfiguration
public class BaseTest {
}
