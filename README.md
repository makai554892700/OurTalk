# 粗制聊天工具服务器代码
# 粗制Android端代码[参考](https://github.com/makai554892700/OurTalkAndroid.git)
# 聊天核心采用[netty](https://netty.io/)
# OurTalk服务器端
# 运行命令
        
        java -Dfile.encoding=utf-8 -Dserver.port=8080 -jar OurTalk.jar


#### docker 常用命令
* docker实例查看

        docker ps -a
        
* docker复制文件

        docker cp <local path> <applicationID>:<docker path>
        
* docker登录 1

        docker exec -i -t <applicationID> /bin/sh
        
* docker登录 2

        docker exec -it <iamge name> bash

* 拉取镜像

        docker pull zookeeper

* 运行项目 
    * 端口映射(-p 本地端口:docker端口)
    * 指定项目名称(-d 项目名称)
    
            docker run -p 8080:8080 -d zookeeper

* 查看docker日志

        docker logs -f --tail=100 CONTAINER

## docker 集成
* 在项目根目录编写Dockerfile文件 示例仅供参考:

        # 基础镜像使用java
        FROM java:8
        # 作者 可写可不写
        # MAINTAINER makai <makai554892700@sina.com>
        # VOLUME 指定了临时文件目录为/tmp
        # 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
        VOLUME /tmp
        # 将jar包添加到容器中并更名为app.jar
        ADD OurTalk-0.0.1-SNAPSHOT.jar app.jar
        # 运行jar包
        RUN bash -c 'touch /app.jar'
        # 指定对外端口
        EXPOSE 8080
        # 执行参数，自定义执行 例：java -Dfile.encoding=utf-8 -Dserver.port=8080 -jar app.jar 则对应如下：
        ENTRYPOINT ["java","-Dfile.encoding=utf-8","-Dserver.port=8080","-jar","/app.jar"]

* build完项目之后将 Dockerfile 文件与 打包的jar包 放至同一目录下
* 将jar打包为镜像(docker build -t 项目名 镜像路径)，示例仅供参考:

        docker build -t ourtalk .

* 此时再查看docker的镜像会看到多了一个刚刚打包镜像的选项

        docker images

* 运行镜像(-p 对外端口:docker端口 --name 创建镜像实例名称 -d 运行的镜像名称),示例仅供参考
    
        docker run -p 8080:8080 --name ourtalk -d ourtalk 

* 服务更新
    * 方案一：
        * 在服务器找到容器jar包名(登录docker后操作 docker exec -i -t <applicationID> /bin/sh)：
        
                find / -name app.jar
     
        * 替换jar包(docker容器外操作)，示例仅供参考
    
                docker cp <local path> <applicationID>:<docker path>            

        * 重启docker镜像
        
                docker restart ourtalk
            
    * 方案二：
        * 在运行docker镜像时关联，示例仅供参考：

                 docker run --name ourtalk -it -v /service/ourtalk/workplace:/data -d -p 8080:8080 ourtalk

* 因为我们的服务用到了mysql和reids，且用的都是本地服务，所以可能需要进行docker的外链
    * 创建mysql容器并启动
    
            docker pull mysql:5.7
            docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7

    * 创建redis容器并启动
    
            docker pull redis
            docker run --name redis -d redis

    * 登录mysql并创建相应数据库
    
            docker exec -it mysql bash
            mysql -uroot -p
            $create database ourtalk;
    
    * 链接运行命令
        
            docker run -d --name ourtalk -p 8080:8080 --link=mysql:mysql --link=redis:redis ourtalk
            
            
           