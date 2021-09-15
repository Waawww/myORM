package com.hjz.demeone.myorm;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjReflect<T> {
    Class<T> clazz;
    T t;
    String tableName;

    //字段名集合
    Map<String, Field> columnAll = new HashMap<>();

    public ObjReflect(T t) {
        this.t = t;
        clazz = (Class<T>) t.getClass();
        priseClassAnnotates(clazz);
        tableName = priseTableName(clazz);
    }

    public ObjReflect(Class<T> clz) {
        clazz = clz;
        priseClassAnnotates(clazz);
        tableName = priseTableName(clazz);
    }

    /**
     * 获取实体类对应的表名
     *
     * @param clazz
     * @return
     */
    private String priseTableName(Class<T> clazz) {
        TableName annotation = clazz.getAnnotation(TableName.class);
        String tableName = null;
        if (annotation != null) {
            tableName = annotation.value();
        } else if (tableName == null) {
            String simpleName = clazz.getSimpleName();
            tableName = simpleName.toLowerCase();
        }
        return tableName;
    }

    /**
     * 获取实体类对应的字段名
     *
     * @param clazz
     */
    private void priseClassAnnotates(Class<T> clazz) {
        for (Field fid : clazz.getDeclaredFields()) {
            String key = null;
            TableId tableId = fid.getAnnotation(TableId.class);
            if (tableId != null) {
                key = tableId.value();
            }
            TableField tableField = fid.getAnnotation(TableField.class);
            if (tableField != null) {
                //不为数据库字段，跳过
                if (!tableField.exist()) {
                    continue;
                }
                key = tableField.value();
            }
            if (StringUtils.isBlank(key)){
                key = getColumnByField(fid);
            }
            columnAll.put(key, fid);
        }
    }

    private String getColumnByField(Field fid) {
        return fid.getName().toUpperCase();
    }

    public String getTableName() {
        return tableName;
    }

    public Map<String, Field> getColumnAll() {
        return columnAll;
    }
}
