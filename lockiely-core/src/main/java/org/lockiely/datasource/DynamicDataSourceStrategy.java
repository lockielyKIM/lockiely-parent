package org.lockiely.datasource;

import java.util.List;

@FunctionalInterface
public interface DynamicDataSourceStrategy {

    String determineCurrentDataSource(List<String> dataSourceLookupKeys);
}
