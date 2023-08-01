FROM java:8
ADD *.jar app.jar

# 声明后端服务运行在9886端口
EXPOSE 9886
ENTRYPOINT  ["java","-Xmx512m","-Xms512m","-Dspring.profiles.active=prod","-jar","/app.jar"]
RUN echo "Asia/shanghai" > /etc/timezone;
ENV LANG C.UTF-8

# 指定维护者的名字
MAINTAINER zjut