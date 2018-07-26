package org.lockiely.datasource.support;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "spring.datasource.dynamic")
public class DynamicDataSourceProperties {

    @NestedConfigurationProperty
    private DynamicItemDataSourceProperties master = new DynamicItemDataSourceProperties();

    @NestedConfigurationProperty
    private Map<String, DynamicItemDataSourceProperties> slaves = new HashMap<>();

    public DynamicItemDataSourceProperties getMaster() {
        return master;
    }

    public void setMaster(DynamicItemDataSourceProperties master) {
        this.master = master;
    }

    public Map<String, DynamicItemDataSourceProperties> getSlaves() {
        return slaves;
    }

    public void setSlaves(Map<String, DynamicItemDataSourceProperties> slaves) {
        this.slaves = slaves;
    }

    public static final String DYNAMIC_DATA_SOURCE_MASTER = "master";

    public static final String DYNAMIC_DATA_SOURCE_SLAVE = "slave";
}
