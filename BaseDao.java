package com.hjz.demeone.myorm;

import com.hjz.demeone.test.MyUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public abstract class BaseDao {

    @Resource
    DataMapper dataMapper;

    protected MySqlPlus getSQL(Class clz){
        return new MySqlPlus(clz);
    }

//    protected List selectList(String sql,Class cls){
//        //Q:为什么查出来的，结果是List<Map<String,Object>>
//        List<Map<String,Object>> maps = dataMapper.select(sql);
//        List list = MyUtil.MapToObj(maps, cls);
//        return list;
//    }

    protected <T> List<T> selectList(MySqlPlus sql,Class<T> cls){
        List<Map<String,Object>> maps = dataMapper.select(sql.getMaps(),sql.toSql());
        return  MyUtil.MapToObj(maps, cls);
    }

    protected <T> List<T> selectList(MySqlPlus sql){
        return selectList(sql,sql.getCurrentClass());
    }


    protected <T> T select(MySqlPlus sql,Class<T> clz) {
        List<T> ts = selectList(sql, clz);
        if (ts != null && ts.size() > 1) {
            throw new RuntimeException("查询出多个结果");
        }
        if (ts != null && ts.size() > 0) {
            return ts.get(0);
        }
        return null;
    }

    protected <T> T select(MySqlPlus sql) {
        return (T) select(sql, sql.getCurrentClass());
    }





}
