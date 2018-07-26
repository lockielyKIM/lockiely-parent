package org.lockiely;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusAutoConfiguration;
import org.lockiely.datasource.DynamicDataSourceAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.autoconfigure.mail.MailSenderValidatorAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpEncodingAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */
@EnableTransactionManagement(order = 2)
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
//    exclude = {
//        DruidDataSourceAutoConfigure.class, MessageSourceAutoConfiguration.class,
//        JacksonAutoConfiguration.class, DynamicDataSourceAutoConfiguration.class,
//        PropertyPlaceholderAutoConfiguration.class, WebSocketAutoConfiguration.class,
//        ValidationAutoConfiguration.class, ErrorMvcAutoConfiguration.class, JmxAutoConfiguration.class,
//        SpringApplicationAdminJmxAutoConfiguration.class, CacheAutoConfiguration.class,
//        ProjectInfoAutoConfiguration.class, MailSenderAutoConfiguration.class,
//        MailSenderValidatorAutoConfiguration.class, HttpEncodingAutoConfiguration.class,
//        MultipartAutoConfiguration.class, WebClientAutoConfiguration.class,
//        MybatisPlusAutoConfiguration.class, DataSourceAutoConfiguration.class
//    }

public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }
}
