package com.hjz.demeone.myorm;

import java.util.*;

public class MySql {

    /**
     *  sql语句
     */
    StringBuffer sql;
    List<Object> values = new ArrayList<>();

    public MySql() {
        sql = new StringBuffer();
    }

    public MySql(String sql) {
        this.sql = new StringBuffer(sql);
        this.sql.append(" ");
    }

    /**
     * 在sql后面继续添加
     * @param aSql
     * @return
     */
    public MySql append(String aSql) {
        sql.append(" ").append(aSql).append(" ");
        return this;
    }

    /**
     * 添加多个条件值
     * @param value
     * @return
     */
    public MySql addValues(Object... value) {
        values.addAll(Arrays.asList(value));
        return this;
    }

    /**
     * 获取条件值,array类型
     * @return Object[]
     */
    public Object[] getValues() {
        return values.toArray();
    }

    /**
     * 获取条件值,map类型
     * @return map
     */
    public Map<String, Object> getMaps() {
        Map<String, Object> map = new HashMap<>(values.size());
        for (int i = 0; i < values.size(); i++) {
            map.put(i + "", values.get(i));
        }
        return map;
    }

    /**
     * 删除sql最后一段str字符串
     * @param str
     */
    public void delLastStr(String str) {
        int i = sql.lastIndexOf(str);
        if (i != -1) {
            sql.delete(i, i + str.length());
        }
    }

    /**
     * 生成最后的SQL
     * @return string
     */
    public String toSql() {
        String str = sql.toString();
        Object[] values = getValues();
        for (int i = 0; i < values.length; i++) {
            str = str.replaceFirst("\\?", "#{map." + i + "}");
        }
        return str.trim();
    }


}
