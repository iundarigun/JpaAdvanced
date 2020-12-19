# MySQL

Create a new docker instance of mysql with name local-mysql, listen in 3306 port (once)

```
$ docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --name local-mysql -d mysql
```

Connect and create the database

```
$ docker exec -it local-mysql mysql -uroot -proot
> create database jpaadvanced;
```

Start container next time:
```
$ docker start local-mysql
```

