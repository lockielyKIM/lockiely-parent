package org.lockiely.datasource;

import org.springframework.core.NamedThreadLocal;

public final class DynamicDataSourceContextHolder {


    private static final ThreadLocal<String> LOOKUP_KEY_HOLDER =  new NamedThreadLocal("current dynamic datasource");

    private DynamicDataSourceContextHolder() {
    }

    public static String getDataSourceLookupKey() {
        return LOOKUP_KEY_HOLDER.get();
    }

    public static void setDataSourceLookupKey(String dataSourceLookupKey) {
        LOOKUP_KEY_HOLDER.set(dataSourceLookupKey);
    }

    public static void clearDataSourceLookupKey() {
        LOOKUP_KEY_HOLDER.remove();
    }
}
