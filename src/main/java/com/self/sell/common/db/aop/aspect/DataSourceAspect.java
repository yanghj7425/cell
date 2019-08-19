package com.self.sell.common.db.aop.aspect;

import com.self.sell.common.db.MultiDataSource;
import com.self.sell.common.db.aop.annotation.DynamicRoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("@annotation(com.self.sell.common.db.aop.annotation.DynamicRoutingDataSource)")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class clazz = signature.getDeclaringType();
        Class[] parameterTypes = signature.getParameterTypes();
        try {
            Method method = clazz.getMethod(methodName, parameterTypes);
            if (method != null && method.isAnnotationPresent(DynamicRoutingDataSource.class)) {
                DynamicRoutingDataSource dataSource = method.getAnnotation(DynamicRoutingDataSource.class);
                MultiDataSource.setDataSourceKey(dataSource.name());
            }
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage(), e.getCause());
        }
    }


}
