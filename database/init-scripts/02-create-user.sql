CREATE USER factory_user WITH PASSWORD 'admin';

GRANT ALL PRIVILEGES ON DATABASE factory_db TO factory_user;

\c factory_db;

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO factory_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO factory_user;

ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON TABLES TO factory_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON SEQUENCES TO factory_user;