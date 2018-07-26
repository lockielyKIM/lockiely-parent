package org.lockiely.datasource;

import javax.sql.DataSource;
import org.lockiely.datasource.support.DynamicDataSourceProperties;
import org.lockiely.datasource.support.DynamicRoutingDataSource;
import org.lockiely.datasource.support.LoadBalanceDynamicDataSourceStrategy;
import org.lockiely.datasource.support.PropertyDataSourceProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@Import(DruidDynamicDataSourceConfiguration.class)
public class DynamicDataSourceAutoConfiguration {

    private final DynamicDataSourceProperties properties;

    public DynamicDataSourceAutoConfiguration(DynamicDataSourceProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public DynamicDataSourceProvider dynamicDataSourceProvider(){
        return new PropertyDataSourceProvider(properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public DynamicDataSourceStrategy dynamicDataSourceStrategy(){
        return new LoadBalanceDynamicDataSourceStrategy();
    }


    @Bean
    @ConditionalOnMissingBean(DataSource.class)
    public DynamicRoutingDataSource dynamicRoutingDataSource(
        DynamicDataSourceStrategy dynamicDataSourceStrategy, DynamicDataSourceProvider  dynamicDataSourceProvider){
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource(
            dynamicDataSourceStrategy, dynamicDataSourceProvider);
        return dynamicRoutingDataSource;
    }
}
