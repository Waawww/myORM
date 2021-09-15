package com.hjz.demeone.myorm;

import com.hjz.demeone.test.MyUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public abstract class BaseDao {

    @Resource
    DataMapper dataMapper;

    protected List selectList(String sql,Class cls){
        //Q:为什么查出来的，结果是List<Map<String,Object>>
        List<Map<String,Object>> maps = dataMapper.select(sql);
        List list = MyUtil.MapToObj(maps, cls);
        return list;
    }

    protected <T> List<T> selectList(MySqlPlus sql,Class<T> cls){
        List<Map<String,Object>> maps = dataMapper.select(sql.toSql());
        return  MyUtil.MapToObj(maps, cls);
    }




}
