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