-- Base Table for Users
CREATE TABLE IF NOT EXISTS _user (
    id VARCHAR(255) PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    account_locked BOOLEAN DEFAULT FALSE,
    enabled BOOLEAN DEFAULT TRUE,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    date_of_birth DATE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMPTZ DEFAULT NULL
);

-- Base Table for Roles
CREATE TABLE IF NOT EXISTS role (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMPTZ DEFAULT NULL
);

-- Join Table for User and Role Many-to-Many Relationship
CREATE TABLE IF NOT EXISTS _user_roles (
    user_id VARCHAR(255) REFERENCES _user(id) ON DELETE CASCADE,
    role_id VARCHAR(255) REFERENCES role(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);
