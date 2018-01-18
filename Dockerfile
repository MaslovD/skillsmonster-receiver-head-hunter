FROM openjdk
MAINTAINER Dmitry Maslov
COPY maven /
CMD java -Dspring.profiles.active=prod -jar /jars/receiver-head-hunter-0.0.29.jar
