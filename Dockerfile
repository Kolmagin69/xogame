FROM maven:3-openjdk-8
COPY . xogame
WORKDIR xogame
RUN mvn package spring-boot:repackage
ENTRYPOINT ["java", "-jar", "target/xogame-0.0.1.jar"]