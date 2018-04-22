FROM java:8-jre
MAINTAINER Erwin Alberto

ENV SERVER_PORT 8998
EXPOSE 8998

RUN apt-get update
RUN mkdir /app
COPY ./build/libs/healthcare-openpayment-data-1.0.jar /app/
COPY ./datafiles/OP_DTL_GNRL_PGYR2016_P06302017_small.csv /datafiles/

ENTRYPOINT ["java", "-jar", "/app/healthcare-openpayment-data-1.0.jar"]