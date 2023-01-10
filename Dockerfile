#
# Build stagesql\
#
FROM mysql:8.0.31
ENV MYSQL_ROOT_PASSWORD=root
WORKDIR /docker-entrypoint-initdb.d/

COPY init.sql init.sql