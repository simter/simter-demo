# [simter-demo-starter-rest-jaxrs](https://github.com/simter/simter-demo/tree/master/simter-demo-starter-rest-jaxrs)

A Simter Demo micro-service Starter base on JAX-RS.

## 1. Primary property configuration

| Name | Default | Description |
|---|---|---|
| server.port | 9010       | http server port
| db.host     | localhost  | the database server domain or ip 
| db.name     | demo       | the database name 
| db.username | demo_owner | the database user name
| db.password | owner      | the database user password

How to change the property value? such as change `db.password=888888` :

```shell
# maven
$ mvn spring-boot:run -D db.password=888888
  
# jar
$ java -jar xxx.jar --db.password=888888
```

## 2. Initial database

The default development environment is `tomcat+jersey+hsql`, no need to initial the database.  
But if you change to use a not memory database, such as `postgres` or `mysql` in production environment or force set `db.init=false` in 
development environment, you need to execute the following sql scripts in sequence.

1. Drop tables
  1. simter-demo-data-`{version}`.jar!/tech/simter/demo/sql/`{db-type}`/schema-drop.sql
  2. simter-meta-`{version}`.jar!/tech/simter/meta/sql/`{db-type}`/schema-drop.sql
2. Create tables
  1. simter-meta-`{version}`.jar!/tech/simter/meta/sql/`{db-type}`/schema-create.sql
  2. simter-demo-data-`{version}`.jar!/tech/simter/demo/sql/`{db-type}`/schema-create.sql
3. Initial data
  1. simter-meta-`{version}`.jar!/tech/simter/meta/sql/`{db-type}`/data.sql
  3. simter-demo-data-`{version}`.jar!/tech/simter/demo/sql/`{db-type}`/data.sql

> Note: `{version}` and `{db-type}` is markups, you need to change it with the actual value.
Such as if use `postgres` then `db-type=postgres`, if use `mysql` then `db-type=mysql`.

Follow below guides to create the demo database and account.

### 2.1 Postgres

```shell
# login as super user
$ psql -d postgres -U {account} -P
  
# create the database owner account
postgres=# create role demo_owner login password 'owner';
  
# create the database
postgres=# create database demo with encoding 'UTF8' owner demo_owner;
```

### 2.2 Mysql

```shell
# login as super user
$ mysql -u {account} -p
  
# create the database
mysql> create database demo default character set utf8 default collate utf8_general_ci;
  
# create the database owner account
mysql> grant usage on demo.* to demo_owner@localhost identified by 'owner';
mysql> grant all privileges on demo.* to demo_owner@localhost;
```

## 3. Development and debug

The default development configuration is use `tomcat+jersey+hsql` (by maven default active profile `dev-hsql` in `pom.xml` file).   
If you want difference, just add another maven profile, such as `dev-postgres` for `tomcat+jersey+postgres`.

Run it.

```shell
$ mvn spring-boot:run
```

The default http server port is 9010. You can change it follow belows:

```shell
$ mvn spring-boot:run -D server.port=8080
```

## 4. Production deployment

The default production configuration is use `tomcat+jersey+postgres` (by maven profile `prod` in `pom.xml` file).  
If you want difference, just change maven profile `prod`. We also provide mysql database production profile, 
it is `prod-mysql` profile to use `tomcat+jersey+mysql`。

```shell
$ mvn clean package -P prod
  
# run it
$ java -jar simter-demo-starter-rest-jaxrs-{version}.jar
  
# run in background
$ nohup java -jar simter-demo-starter-rest-jaxrs-{version}.jar > /dev/null &
```

If want to change the default http server port, follow belows:

```shell
$ java -jar simter-demo-starter-rest-jaxrs-{version}.jar --server.port=8080
```

> Note: After started, should auto generate the log file `app.log`.

