FROM openjdk:21

EXPOSE 8888

WORKDIR /bld/store-app

ADD /target/config-server-0.0.1-SNAPSHOT.jar config-server.jar

ENTRYPOINT ["java", "-jar", "config-server.jar"]

CMD [""]