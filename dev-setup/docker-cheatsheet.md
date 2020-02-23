# docker-cheatsheet

[Docker](https://docs.docker.com/)是开发人员和系统管理员开发、发布和运行应用程序的平台。如果你也使用`Oh My Zsh`，推荐使用[Docker插件](https://github.com/ohmyzsh/ohmyzsh/wiki/Plugins#docker)。

## 镜像常用命令

搜索镜像:

```sh
docker search java
```

下载镜像:

```sh
docker pull java:8
```

如何查找镜像支持的版本:

> 由于docker search命令只能查找出是否有该镜像，不能找到该镜像支持的版本，所以我们需要通过docker hub来搜索支持的版本。

* 进入docker hub的官网，地址：<hub.docker.com>
* 然后搜索需要的镜像
* 查看镜像支持的版本
* 进行镜像的下载操作：`docker pull nginx:1.17.0`

列出镜像:

```sh
docker images
```

删除镜像:

```sh
# 指定名称删除镜像
docker rmi java:8

# 指定名称删除镜像（强制）
docker rmi -f java:8

# 强制删除所有镜像
docker rmi -f $(docker images)
```

## 容器常用命令

新建并启动容器:

```sh
docker run -p 80:80 --name nginx -d nginx:1.17.0
# -d选项：表示后台运行
# --name选项：指定运行后容器的名字为nginx,之后可以通过名字来操作容器
# -p选项：指定端口映射，格式为：hostPort:containerPort
```

列出容器:

```sh
# 列出运行中的容器
docker ps
# 列出所有容器
docker ps -a
```

停止容器:

```sh
# $ContainerName及$ContainerId可以用docker ps命令查询出来
docker stop $ContainerName(或者$ContainerId)
```

强制停止容器:

```sh
docker kill $ContainerName(或者$ContainerId)
```

启动已停止的容器:

```sh
docker start $ContainerName(或者$ContainerId)
```

进入容器:

```sh
# 先查询出容器的pid
docker inspect --format "{{.State.Pid}}" $ContainerName(或者$ContainerId)
# 根据容器的pid进入容器：
nsenter --target "$pid" --mount --uts --ipc --net --pid
```

删除容器:

```sh
# 删除指定容器
docker rm $ContainerName(或者$ContainerId)

# 强制删除所有容器
docker rm -f $(docker ps -a -q)
```

查看容器的日志:

```sh
docker logs $ContainerName(或者$ContainerId)
```

查看容器的IP地址:

```sh
docker inspect --format '{{ .NetworkSettings.IPAddress }}' $ContainerName(或者$ContainerId)
```

同步宿主机时间到容器:

```sh
docker cp /etc/localtime $ContainerName(或者$ContainerId):/etc/
```

在宿主机查看docker使用cpu、内存、网络、io情况:

```sh
# 查看指定容器情况
docker stats $ContainerName(或者$ContainerId)

# 查看所有容器情况：
docker stats -a
```

进入Docker容器内部的bash:

```sh
docker exec -it $ContainerName /bin/bash
```

修改Docker镜像的存放位置:

```sh
# 查看Docker镜像的存放位置：
docker info | grep "Docker Root Dir"
# 关闭Docker服务：
systemctl stop docker
# 移动目录到目标路径：
mv /var/lib/docker /mydata/docker
# 建立软连接：
ln -s /mydata/docker /var/lib/docker
```

## 一些小技巧

```sh
# remove ALL your containers
docker container prune

# Delete all untagged containers
docker image prune

# See all space Docker take up
docker system df

# Get IP address of running container
docker inspect [CONTAINER ID] | grep -wm1 IPAddress | cut -d '"' -f 4

# Kill all running containers
docker kill $(docker ps -q)

```
