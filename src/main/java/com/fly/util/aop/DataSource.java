package com.fly.util.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author fly
 * 该注解声明在方法上,用于表示引用数据源类型
 */

@Target(ElementType.TYPE)
public @interface DataSource {
      String TYPE() default "mysql";
}
