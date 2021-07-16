FROM maven:jdk-8-alpine

COPY ./ /
RUN mvn clean package -DskipTests eureka-server
CMD java $JAVA_OPTS -jar target/eureka-server.jar