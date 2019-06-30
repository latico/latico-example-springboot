package com.latico.example.springboot.interceptor;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * <PRE>
 1：intercept 拦截器，它将直接覆盖掉你真实拦截对象的方法。

 2：plugin方法它是一个生成动态代理对象的方法

 3：setProperties它是允许你在使用插件的时候设置参数值。
 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-03-29 9:05
 * @Version: 1.0
 */
@Intercepts(
        {
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        }
)
public class TestMybatisInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("调用了Mybatis测试拦截器");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("调用了Mybatis测试拦截器");
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("调用了Mybatis测试拦截器");

    }
}
