FROM openjdk:21

EXPOSE 8761

WORKDIR /bld/store-app

ADD /target/discovery-server-0.0.1-SNAPSHOT.jar discovery-server.jar

ENTRYPOINT ["java", "-jar", "discovery-server.jar"]

CMD [""]