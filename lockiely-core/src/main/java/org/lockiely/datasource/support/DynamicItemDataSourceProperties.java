package org.lockiely.datasource.support;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;


public class DynamicItemDataSourceProperties extends DataSourceProperties {

    @NestedConfigurationProperty
    private DefaultDruidDataSourceProperties druid = new DefaultDruidDataSourceProperties();

    public DefaultDruidDataSourceProperties getDruid() {
        return druid;
    }

    public void setDruid(DefaultDruidDataSourceProperties druid) {
        this.druid = druid;
    }
}
