CREATE KEYSPACE IF NOT EXISTS twim_test WITH REPLICATION =
{ 'class' : 'SimpleStrategy', 'replication_factor' : 1};

USE twim_test;

CREATE TYPE IF NOT EXISTS user_subject (
    login text,
    id text,
    roles list<text>,
);

CREATE TABLE IF NOT EXISTS applications_by_client (
    client_id text,
    application_name text,
    application_id uuid,
    client_secret text,
    application_logo_uri text,
    application_description text,
    client_ip_address text,
    allowed_grant_types list<text>,
    registered_scopes list<text>,
    redirect_uris list<text>,
    properties map<text, text>,
    resource_owner_subject frozen<user_subject>,
    subject frozen<user_subject>,
    application_web_uri text,
    confidential boolean,
    creation_date timestamp,
    PRIMARY KEY ((client_id),application_name,application_id)
);
