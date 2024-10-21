FROM mongo:latest

COPY init-scripts/01-create-database.js /docker-entrypoint-initdb.d/
COPY init-scripts/02-create-user.js /docker-entrypoint-initdb.d/
COPY init-scripts/03-create-tables.js /docker-entrypoint-initdb.d/
COPY insert-scripts/04-insert-records.js /docker-entrypoint-initdb.d/

ENV MONGO_INITDB_DATABASE=factory_db
ENV MONGO_INITDB_ROOT_USERNAME=factory_user
ENV MONGO_INITDB_ROOT_PASSWORD=admin

EXPOSE 27017