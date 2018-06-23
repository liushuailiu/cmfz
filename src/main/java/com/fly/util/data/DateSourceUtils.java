package com.fly.util.data;

import com.fly.util.aop.TYPE;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author fly
 * 数据源切换工具类
 */

public class DateSourceUtils extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> dataSourceKey =
            new InheritableThreadLocal<String>();

    public static void setDataSourceKey(TYPE type){
        System.out.println("-------------->" + type.getType());
        dataSourceKey.set(type.getType());
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }

}
