# okrm-server

#### 介绍
#### 软件架构
软件架构说明


#### 安装教程

1. xxxx
2. xxxx
3. xxxx

#### 使用说明

安装IDE，修改IDE编码格式为UTF-8

安装mysql 8.x

运行redis

安装docker

docker pull redis

docker run --name okrm-redis -d redis

或者直接下载redis

make

make test

make install

make test

./src/redis-server

新建mysql用户okrm并给它赋予权限

安装lombok

# jenkins

## jenkins升级（一般不需要操作）

```shell
# 下载
wget -O /usr/lib/jenkins/jenkins_2.237.war http://mirror.serverion.com/jenkins/war/2.237/jenkins.war
#  curl http://updates.jenkins-ci.org/download/war/2.209/jenkins.war jenkins_2.209.war
# 停止服务
service jenkins stop

# 替换文件
cp /usr/lib/jenkins/jenkins_2.237.war /usr/lib/jenkins/jenkins.war

# 启动服务
service jenkins start
```

