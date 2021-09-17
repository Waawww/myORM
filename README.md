# myORM
自己练习写的封装orm

## 方便在日常快速开发

主要使用到了mybatis-plus
pom:

```java
<dependency>
   <groupId>com.baomidou</groupId>
   <artifactId>mybatis-plus-boot-starter</artifactId>
   <version>3.4.1</version>
</dependency>
```

### 使用示例：

```java
@Repository
public class UserDao extends BaseDao {
    public List<User> getUserById(String id){
        MySqlPlus sql = getSQL(User.class);
        sql.select().eq("id",id);
        return select(sql);
    }
}
```
