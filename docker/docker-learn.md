# dorcker-learn

## 安装

## docker 镜像常用命令

* 搜索镜像 `docker search`
* 下载镜像 `docker pull`; 指定要下载的镜像标签及Docker Register地址: `docker pull reg.itmuch/java:7`(指定从 Docker Register中下载标签为7的Java镜像)
* 列出镜像 `docker image`
* 删除本地镜像 `docker rmi`

Docker命令：<https://docs.docker.com/engine/reference/commandline/docker/>

## Docker 容器常用命令

* 新建并启动容器，`docker run`，以下是一些常用的选项
  * `-d`选项: 表示后台运行
  * `-P`选项: 随机端口映射
  * `-p`选项: 指定端口映射，有四种格式(`ip:hostPort:containerPort`; `ip::containerPort`; `hostPort:containerPort`; `containerPort`)
  * `-network` 选项: 指定网络模式，可选参数如下
    * `--network=bridge`: 默认选项，表示连接到默认的网桥
    * `--network=host`: 容器使用宿主的网络
    * `--network=container:NAME_or_ID`: 新容器使用已有容器的网络配置

示例#1:

`docker run java /bin/echo 'Hello World'` 终端会打印`Hello World`，跟本地直接执行`/bin/echo 'Hello World'`一样。

示例 #2:

`docker run -d -p 91:80 nignx` 启动一个Nginx容器(后端运行、宿主端口为91、容器端口为80)

*注：docker run创建容器时，会先检查本地是否存在指定镜像，若不存在，会先自动从Docker Hub下载镜像，再启动一个容器。*

* 列出容器，`docker ps`；使用`-a`参数，列出所有容器(包括已停止的)。7列含义:
  * `CONTAINER_ID`: 容器ID
  * `IMAGE`: 镜像名称
  * `COMMAND`: 启动容器时执行的命令
  * `CREATED`: 容器的创建时间
  * `STATUS`: 容器的运行状态
  * `PORTS`: 容器对外的端口
  * `NAMES`: 容器的名称。默认由Docker自动生成，也可以使用 `docker run`命令的 `--name` 选项指定

* 停止容器 `docker stop`
* 强制停止容器 `docker kill`
* 启动已停止的容器 `docker start`
* 重启容器 `docker restart`

* 进入容器 `docker attach`/`nsenter`
  * `docker attach`命令并不方便，会阻塞其他的窗口
  * 使用 `nsenter` 需要找到容器第一个进程的PID，获取命令 `docker inspect --format "{{.State.Pid}}" $CONTAINER_ID`

一个完整的例子:

todo

* 删除容器 `docker rm`; 删除所有的容器 `docker rm -f $(docker ps -a -q)`

## 运行微服务

* 使用 Dockerfile 构建 Docker镜像

一个简单的 Dockerfile:

```txt
FROM nginx
RUN echo '<h1>Hello Dockerfile.</h1>' > /usr/share/ngnix/html/index.html
```

执行以下命令构建镜像: `docker build -t nginx:my`

启动镜像，`docker run -p 92:80 nginx:my`

### Dockerfile 常用命令

#### ADD 复制文件

ADD 指令用于复制文件，格式为:

* `ADD <src>... <dest>`
* `ADD ["<src>",... "<dest>"]`

从 src 目录复制文件到容器的 dest。其中 src 可以是 Dockerfile 所在目录的相对路径，也可以是一个URL，还可以是一个压缩包。

注意:

* src 必须在构建的上下文内
* 如 src 是一个 URL，而 dest 不以斜杠结尾，dest将被视为文件
* 如 src 是一个 URL，而 dest 以斜杠结尾，dest将被视为目录
* 如 sec 是一个目录，那整个目录下的内容将会被复制
* 如果文件是可识别的压缩包格式，docker会自动解压

#### ARG 设置构建参数

ARG 指令用于设置构建参数，格式为 `ARG <name>[=<default value>]`，示例: `ARG user1=someuser`。

#### CMD 容器启动命令

CMD 指令用于为执行容器提供默认值，每个 Dockerfile 只有一个 CMD命令，格式为:

* CMD ["executable", "param1", "param2"] (推荐)
* CMD ["param1", "param2"] (为ENTRYPOINT指令提供预设参数)
* CMD command param1 param2 (在 shell 中执行)

示例: `CMD echo "This is a test" | wc -`

#### COPY 复制文件

复制文件命令格式为:

* `COPY <src>... <dest>`
* `COPY ["<src>",... "<dest>"]`

复制本地端的src 到容器的 dest。

#### ENTRYPOINT 入口点

ENTRYPOINT 和 CMD 指令的目的一样，指定 Docker 容器启动时执行的命令，格式为:

* ENTRYPOINT ["executable", "param1", "param2"]
* ENTRYPOINT command param1 param2

#### ENV 设置环境变量

ENV 指令用于设置环境变量，格式为:

* `ENV <key> <value>`
* `ENV <key>=<value>...`

示例:

`ENV JAVA_HOME /path/to/java`

#### EXPOSE 声明暴露的端口

EXPOSE 指令用于声明在运行时容器提供服务的端口，格式为: `EXPOSE <port> [<port>...]`

注意，这只是一个声明，运行时并不会打开相应端口，指令作用主要是帮助镜像使用者理解该镜像服务的守护端口；其实，当运行时使用随机映射时，会自动映射到EXPORT端口。

#### FROM 指定基础镜像

使用 FROM 指令指定基础镜像，有点像Java里的extend关键字。

支持的格式:

* `FROM <image>`
* `FROM <image>:<tag>`
* `FROM <image>@<digest>`

#### LABEL 为镜像添加元数据

LABEL 指令用于为镜像添加元数据。

格式为: `LABEL <key>=<value> <key>=<value> <key>=<value>...`

示例:

```sh
LABEL "me.icro.vendor"="ACME Incor"
LABEL me.icro.lable-with-value="foo"
LABEL version="1.0"
LABEL descripition="This text illustrates \
that lable-values can span miltiple lines."
```

#### MAINTAINER 指定维护者的信息

MAINTAINER 指令用于指定维护者的信息，用于为Dockerfile署名。

格式为: `MAINTAINER <name>`

#### RUN 执行命令

格式为:

* `RUN <command>`
* `RUN ["executable", "param1", "param2"]`

示例: `RUN ["/bin/bash", "-c", "echo hello"]`

#### USER 设置用户

USER 指令用于设置启动镜像时的用户或者UID。

格式: `USER 用户名`

#### VOLUME 指定挂载点

该指令使容器中的一个目录具有持久化存储的功能，该目录可被容器本身使用，也可共享给其他容器。

格式为: `VOLUME ["/data"]`

#### WORKDIR 指定工作目录

格式为 `WORKDIR /path/to/workdir`。类似于 cd 命令。

#### 其他命令参考

更多的其他命令 请前往 <https://docs.docker.com/engine/reference/builder/>。

Dockfile 最佳实践: [Best practices for writing Dockerfiles]<https://docs.docker.com/develop/develop-images/dockerfile_best-practices/>

### 使用 Dockerfile 构建镜像
