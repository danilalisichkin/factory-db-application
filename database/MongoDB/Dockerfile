FROM mongo:latest

COPY init-scripts/01-create-database.js /docker-entrypoint-initdb.d/
COPY init-scripts/02-create-user.js /docker-entrypoint-initdb.d/
COPY init-scripts/03-create-collections.js /docker-entrypoint-initdb.d/
COPY insert-scripts/04-insert-records.js /docker-entrypoint-initdb.d/

EXPOSE 27017
