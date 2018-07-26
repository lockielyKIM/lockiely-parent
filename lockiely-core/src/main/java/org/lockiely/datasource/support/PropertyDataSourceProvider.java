package org.lockiely.datasource.support;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.lockiely.datasource.AbstractDataSourceProvider;

public class PropertyDataSourceProvider extends AbstractDataSourceProvider {

    private final DynamicDataSourceProperties dynamicDataSourceProperties;

    public PropertyDataSourceProvider(DynamicDataSourceProperties dynamicDataSourceProperties){
        this.dynamicDataSourceProperties = dynamicDataSourceProperties;
    }

    @Override
    public DataSource loadMasterDataSource() {
        return createDataSource(dynamicDataSourceProperties.getMaster());
    }

    @Override
    public Map<String, DataSource> loadSlaveDataSource() {
        Map<String, DynamicItemDataSourceProperties> slaves = dynamicDataSourceProperties.getSlaves();
        Map<String, DataSource> dataSourceMap = new HashMap<>(slaves.size());
        slaves.forEach((k, v) -> dataSourceMap.put(k, createDataSource(v)));
        return dataSourceMap;
    }
}
