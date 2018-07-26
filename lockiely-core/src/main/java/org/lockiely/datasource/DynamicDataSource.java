package org.lockiely.datasource;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.lockiely.datasource.support.DynamicDataSourceProperties;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDataSource {

    String dataSource() default DynamicDataSourceProperties.DYNAMIC_DATA_SOURCE_MASTER;

    String slaveNode() default "";
}
