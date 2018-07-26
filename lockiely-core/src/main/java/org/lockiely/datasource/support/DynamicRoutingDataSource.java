package org.lockiely.datasource.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.lockiely.datasource.DynamicDataSourceContextHolder;
import org.lockiely.datasource.DynamicDataSourceProvider;
import org.lockiely.datasource.DynamicDataSourceStrategy;
import org.lockiely.utils.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    private List<String> dataSourceLookupKeys;

    private DynamicDataSourceStrategy dynamicDataSourceStrategy;

    private DynamicDataSourceProvider  dynamicDataSourceProvider;

    public DynamicRoutingDataSource(){
        this(null, null);
    }

    public DynamicRoutingDataSource(DynamicDataSourceStrategy dynamicDataSourceStrategy){
        this(dynamicDataSourceStrategy, null);
    }

    public DynamicRoutingDataSource(DynamicDataSourceProvider dynamicDataSourceProvider){
        this(null, dynamicDataSourceProvider);
    }

    public DynamicRoutingDataSource(DynamicDataSourceStrategy dynamicDataSourceStrategy,
        DynamicDataSourceProvider dynamicDataSourceProvider) {
        this.dynamicDataSourceStrategy = dynamicDataSourceStrategy==null ? new LoadBalanceDynamicDataSourceStrategy(): dynamicDataSourceStrategy;
        this.dynamicDataSourceProvider = dynamicDataSourceProvider;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String currentDataSourceLookupKey = DynamicDataSourceContextHolder.getDataSourceLookupKey();
        if(!StringUtils.hasText(currentDataSourceLookupKey)
            || DynamicDataSourceProperties.DYNAMIC_DATA_SOURCE_MASTER.equals(currentDataSourceLookupKey)){
            return DynamicDataSourceProperties.DYNAMIC_DATA_SOURCE_MASTER;
        }else{
            if(DynamicDataSourceProperties.DYNAMIC_DATA_SOURCE_SLAVE.equals(currentDataSourceLookupKey)
                || !this.dataSourceLookupKeys.contains(currentDataSourceLookupKey)){
                //选择备用数据源、或者手工选择的备用数据源名称不存在的话， 使用默认的策略选择备用数据源
                return dynamicDataSourceStrategy.determineCurrentDataSource(this.dataSourceLookupKeys);
            }else{  //根据手动自己选择备用数据源
                return currentDataSourceLookupKey;
            }
        }
    }

    @Override
    public void afterPropertiesSet() {
        DataSource masterDataSource = dynamicDataSourceProvider.loadMasterDataSource();
        Map<String, DataSource> slavesDataSource = dynamicDataSourceProvider.loadSlaveDataSource();
        this.dataSourceLookupKeys = new ArrayList<>(slavesDataSource.keySet());
        Map<Object, Object> targetDataSource = new HashMap<>(slavesDataSource.size() + 1);
        targetDataSource.put(DynamicDataSourceProperties.DYNAMIC_DATA_SOURCE_MASTER, masterDataSource);
        targetDataSource.putAll(slavesDataSource);
        super.setDefaultTargetDataSource(masterDataSource);
        super.setTargetDataSources(targetDataSource);
        super.afterPropertiesSet();
    }
}
