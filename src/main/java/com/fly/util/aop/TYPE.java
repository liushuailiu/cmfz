package com.fly.util.aop;

import java.lang.annotation.*;

/**
 * @author fly
 * 该注解声明在方法上,用于表示引用数据源类型
 */
public enum TYPE {

      ORACLE("oracle"),
      MYSQL("mysql"),
      SQL_SERVER("sql_server");

      private String type = "mysql";

      private TYPE(String type){
            this.type = type;
      }

      public String getType() {
            return type;
      }

      public void setType(String type) {
            this.type = type;
      }
}
