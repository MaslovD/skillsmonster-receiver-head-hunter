FROM openjdk

MAINTAINER Dmitry Maslov

ENV app_version @version@

COPY ./target/receiver-head-hunter-@version@.jar /jars/receiver-head-hunter-@version@.jar

COPY ./target/classes/main/resources/bootstrap.properties /bootstrap.properties

CMD ["sh", "-c", "java -jar -Dspring.profiles.active=$app_profile_name $java_opts /jars/receiver-head-hunter-@version@.jar"]