package com.xinchen.base;

import com.xinchen.base.config.AppConfig;
import com.xinchen.base.config.DataSourceConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author xinchen
 * @version 1.0
 * @date 04/03/2019 13:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration(classes = {AppConfig.class, DataSourceConfig.class})
})
@WebAppConfiguration
public class ApplicationContextTests {
}
