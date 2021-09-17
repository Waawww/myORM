package com.hjz.demeone.myorm;


import java.util.Map;

public class MySqlPlus<T> extends MySql {
    ObjReflect reflect = null;
    Class<T> clazz;
    String tableName;

    Boolean isParenRange = false;
    Boolean isAddAndOr = false;

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

    public MySqlPlus eq(String column,String value){
        addOrAnd("and").append(column).append("=?").addValues(value);
        return this;
    }


    public MySqlPlus or() {
        addOrAnd("or");
        isAddAndOr = true;
        return this;
    }

    public MySqlPlus addOrAnd(String andOror){
        if (isAddAndOr){
            isAddAndOr = false;
            return this;
        }
        append(andOror);
        return this;
    }

    public MySqlPlus parenStart() {
        isParenRange = true;
        addOrAnd("and").append("(");
        isAddAndOr = true;
        return this;
    }

    public MySqlPlus parenEnd() {
        append(")");
        isParenRange = false;
        return this;
    }



    public MySqlPlus like(String column, String value) {
        addOrAnd("and").append(column).append("like ?").addValues("%"+value.trim()+"%");
        return this;
    }



}
