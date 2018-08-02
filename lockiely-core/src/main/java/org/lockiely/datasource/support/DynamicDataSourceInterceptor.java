package org.lockiely.datasource.support;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.lockiely.datasource.DynamicDataSource;
import org.lockiely.datasource.DynamicDataSourceContextHolder;
import org.lockiely.exception.BusinessException;
import org.lockiely.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class DynamicDataSourceInterceptor {

    private static final Logger LOGGER = LoggerFactory
        .getLogger(DynamicDataSourceInterceptor.class);

    //within 在类上设置
    //annotation 在方法上设置
    @Pointcut("@within(org.lockiely.datasource.DynamicDataSource) || @annotation(org.lockiely.datasource.DynamicDataSource)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object switchDataSource(ProceedingJoinPoint joinPoint){
        Class<?> className = joinPoint.getTarget().getClass();
        DynamicDataSource dynamicDataSource = null;
        if(className.isAnnotationPresent(DynamicDataSource.class)){
            dynamicDataSource = className.getAnnotation(DynamicDataSource.class);
        }
        if(joinPoint.getSignature() instanceof MethodSignature){
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            try {
                Method m = className.getMethod(method.getName(), method.getParameterTypes());
                if(m.isAnnotationPresent(DynamicDataSource.class)){
                    dynamicDataSource = m.getAnnotation(DynamicDataSource.class);
                }
            } catch (NoSuchMethodException e) {
                LOGGER.error("switch dynamic datasource aop interceptor error " + e.toString());
            }
        }
        setDynamicDataSource(dynamicDataSource, joinPoint);
        try {
            return joinPoint.proceed();
        }catch (Throwable e){
            throw new BusinessException(e.toString(), e);
        }finally {
            DynamicDataSourceContextHolder.clearDataSourceLookupKey();
        }
    }

    public void setDynamicDataSource(DynamicDataSource dynamicDataSource, JoinPoint joinPoint){
        if(dynamicDataSource == null || DynamicDataSourceProperties.DYNAMIC_DATA_SOURCE_MASTER.equals(dynamicDataSource.dataSource())){
            //使用master数据源
            DynamicDataSourceContextHolder.setDataSourceLookupKey(DynamicDataSourceProperties.DYNAMIC_DATA_SOURCE_MASTER);
        }else{
            //使用slave数据源
            //指定slave节点
            if(StringUtils.hasText(dynamicDataSource.slaveNode())){
                DynamicDataSourceContextHolder.setDataSourceLookupKey(dynamicDataSource.slaveNode());
            }else {
                DynamicDataSourceContextHolder.setDataSourceLookupKey(DynamicDataSourceProperties.DYNAMIC_DATA_SOURCE_SLAVE);
            }
        }
    }
}
