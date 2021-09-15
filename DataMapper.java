package com.hjz.demeone.myorm;

import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface DataMapper {

    @SelectProvider(type = ReturnSql.class,method = "select")
    List<Map<String,Object>> select(String sql);

    @SelectProvider(type = ReturnSql.class,method = "update")
    Long update(String sql);

    @SelectProvider(type = ReturnSql.class,method = "insert")
    Long insert(String sql);

    @SelectProvider(type = ReturnSql.class,method = "delete")
    Long delete(String sql);

    class ReturnSql {
        public String select(String sql){ return sql;}
        public String insert(String sql){ return sql;}
        public String update(String sql){ return sql;}
        public String delete(String sql){ return sql;}
    }

}
