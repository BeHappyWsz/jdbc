# jdbc
jdbc/c3p0/dbcp操作数据库

一、jdbc:
1.读取配置文件db.properties

2.调用存储过程：无参、有参、有参+返回

3.模拟事务操作

二、c3p0:

1.读取配置文件c3p0.properties，获取DataSource、Connection

2.结合common-dbutils中的QueryRunner进行数据操作：（批量）增删改查、分页

三、dbcp：

1.读取配置文件dbcp.properties,获取DataSource、Connection

2.数据库操作
