package org.lockiely.datasource.support;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.lockiely.datasource.DynamicDataSourceStrategy;

public class LoadBalanceDynamicDataSourceStrategy implements DynamicDataSourceStrategy {

    private AtomicInteger count = new AtomicInteger(0);
    @Override
    public String determineCurrentDataSource(List<String> dataSourceLookupKeys) {
        int number = count.getAndAdd(1);
        return dataSourceLookupKeys.get(number % dataSourceLookupKeys.size());
    }
}
