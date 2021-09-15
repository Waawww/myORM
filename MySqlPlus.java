package com.hjz.demeone.myorm;


import java.util.Map;

public class MySqlPlus<T> extends MySql {
    ObjReflect reflect = null;
    Class<T> clazz;
    String tableName;

    public MySqlPlus(Class clazz) {
        this.clazz = clazz;
        reflect = new ObjReflect(clazz);
        tableName = reflect.getTableName();
    }

    public Class<T> getCurrentClass() {
        return this.clazz;
    }

    public MySqlPlus select() {
        append("select");
        Map columnAll = reflect.getColumnAll();
        for (Object column : columnAll.keySet()) {
            sql.append(column).append(",");
        }
        delLastStr(",");
        append("FROM")
                .append(tableName)
//                .append("WITH(NOLOCK)")  mysql没有此语法
                .append(" where 1=1");
        return this;
    }


}
