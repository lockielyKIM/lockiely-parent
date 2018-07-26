package org.lockiely.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import java.util.Map;
import javax.sql.DataSource;
import org.lockiely.datasource.support.DefaultDruidDataSourceProperties;
import org.lockiely.datasource.support.DynamicItemDataSourceProperties;
import org.lockiely.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

public abstract class AbstractDataSourceProvider implements DynamicDataSourceProvider {

    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractDataSourceProvider.class);

    @Override
    public DataSource loadMasterDataSource() {
        throw new BusinessException("abstract class AbstractDataSourceProvider not find masterDataSource");
    }

    @Override
    public Map<String, DataSource> loadSlaveDataSource() {
        throw new BusinessException("abstract class AbstractDataSourceProvider not find slaveDataSource");
    }

    protected DataSource createDataSource(DynamicItemDataSourceProperties dataSourceProperties){
        //FIXME: 后期扩展为通用的type
        Class<? extends DataSource> type = dataSourceProperties.getType();
        if(type == null) {

            if(ClassUtils.isPresent("com.alibaba.druid.pool.DruidDataSource", this.getClass().getClassLoader())){
                return createDruidDataSource(dataSourceProperties);
            }
            LOGGER.info("dynamic datasource not find datasource by DruidDataSource");
            if(ClassUtils.isPresent("com.zaxxer.hikari.HikariDataSource", this.getClass().getClassLoader())){
                return createHikariDataSource(dataSourceProperties);
            }
            LOGGER.info("dynamic datasource not find datasource by HikariDataSource");
            throw new BusinessException("please set master and slave type like spring.datasource.dynamic.master.type");
        }else{
            if("com.alibaba.druid.pool.DruidDataSource".equals(type.getName())){
                return createDruidDataSource(dataSourceProperties);
            }else{
                return dataSourceProperties.initializeDataSourceBuilder().build();
            }
        }
    }

    private DataSource createDruidDataSource(DynamicItemDataSourceProperties properties){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(properties.getUrl());
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setPassword(properties.getPassword());
        druidDataSource.setDriverClassName(properties.getDriverClassName());

        DefaultDruidDataSourceProperties druidProperties = properties.getDruid();

        druidDataSource.setInitialSize(druidProperties.getInitialSize());
        druidDataSource.setMaxActive(druidProperties.getMaxActive());
        druidDataSource.setMinIdle(druidProperties.getMinIdle());
        druidDataSource.setMaxWait(druidProperties.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
        druidDataSource.setMaxEvictableIdleTimeMillis(druidProperties.getMaxEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(druidProperties.getValidationQuery());
        druidDataSource.setValidationQueryTimeout(druidProperties.getValidationQueryTimeout());
        druidDataSource.setTestOnBorrow(druidProperties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(druidProperties.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(druidProperties.isPoolPreparedStatements());
        druidDataSource.setMaxOpenPreparedStatements(druidProperties.getMaxOpenPreparedStatements());
        druidDataSource.setSharePreparedStatements(druidProperties.isSharePreparedStatements());
        druidDataSource.setConnectProperties(druidProperties.getConnectionProperties());
        try {
            druidDataSource.setFilters(druidProperties.getFilters());
            druidDataSource.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }

    private DataSource createHikariDataSource(DynamicItemDataSourceProperties properties){
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

}
