package com.hjz.demeone.myorm;

import org.apache.ibatis.jdbc.SQL;

public class ReturnSql {

    //定义方法，返回查询的sql语句
    public String getSelectAll(){
        SQL sql = new SQL() {
            {
                SELECT("*");
                FROM("user");
            }
        };
        return sql.toString();
    }

    public String select(String sql){
        return sql;
    }
    public String insert(String sql){
        return sql;
    }
    public String update(String sql){
        return sql;
    }
    public String delete(String sql){
        return sql;
    }

}
