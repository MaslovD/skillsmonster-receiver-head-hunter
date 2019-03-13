FROM openjdk:8u171

MAINTAINER Dmitry Maslov

ENV app_version @version@

RUN echo "Europe/Moscow" > /etc/timezone

RUN rm /etc/localtime

RUN dpkg-reconfigure -f noninteractive tzdata

COPY ./target/receiver-head-hunter-@version@.jar /jars/receiver-head-hunter-@version@.jar

COPY ./target/classes/bootstrap.yml /jars/bootstrap.yml

CMD ["sh", "-c", "java -jar -Dspring.profiles.active=$app_profile_name $java_opts /jars/receiver-head-hunter-@version@.jar"]