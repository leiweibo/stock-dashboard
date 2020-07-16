# stockdashboard
Stock Dashboard

在 docker-compose中，如果要想数据库重新初始化，需要执行下面命令：
```
docker system prune --force --volumes
```
但是这个命令应该是将整个本地volume给删除，这不是一种很绿色的做法，其他解决方案再继续赵