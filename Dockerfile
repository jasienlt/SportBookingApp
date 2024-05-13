# cat Dockerfile
FROM mysql:latest

RUN chown -R mysql:root /var/lib/mysql/

ARG MYSQL_DATABASE
ARG MYSQL_USER
ARG MYSQL_PASSWORD
ARG MYSQL_ROOT_PASSWORD

ENV MYSQL_DATABASE=$MYSQL_DATABASE
ENV MYSQL_USER=$MYSQL_USER
ENV MYSQL_PASSWORD=$MYSQL_PASSWORD
ENV MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD

ADD database_group.sql ./backend-projects/database_group.sql

RUN sed -i 's/MYSQL_DATABASE/'$MYSQL_DATABASE'/g' ./backend-projects/database_group.sql
RUN cp ./backend-projects/database_group.sql /docker-entrypoint-initdb.d

EXPOSE 3306