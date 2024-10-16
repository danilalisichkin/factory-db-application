FROM postgres:14-alpine

WORKDIR /docker-entrypoint-initdb.d

COPY init-scripts/01-create-database.sql /docker-entrypoint-initdb.d/
COPY init-scripts/02-create-user.sql /docker-entrypoint-initdb.d/
COPY init-scripts/03-create-tables.sql /docker-entrypoint-initdb.d/
COPY insert-scripts/04-insert-records.sql /docker-entrypoint-initdb.d/