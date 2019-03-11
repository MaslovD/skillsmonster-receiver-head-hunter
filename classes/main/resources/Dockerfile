FROM openjdk:8u171

MAINTAINER Dmitry Maslov

ENV app_version 0.0.35

RUN echo "Europe/Moscow" > /etc/timezone

RUN rm /etc/localtime

RUN dpkg-reconfigure -f noninteractive tzdata

COPY ./target/receiver-head-hunter-0.0.35.jar /jars/receiver-head-hunter-0.0.35.jar

COPY ./target/classes/main/resources/bootstrap.properties /bootstrap.properties

CMD ["sh", "-c", "java -jar -Dspring.profiles.active=$app_profile_name $java_opts /jars/receiver-head-hunter-0.0.35.jar"]