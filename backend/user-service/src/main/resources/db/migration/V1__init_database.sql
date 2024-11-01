
CREATE TABLE IF NOT EXISTS role (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    created_date TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMPTZ DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS _user (
    id BIGSERIAL PRIMARY KEY,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    date_of_birth DATE,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    account_locked BOOLEAN DEFAULT FALSE,
    enabled BOOLEAN DEFAULT TRUE,
    created_date TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMPTZ DEFAULT NULL
);

-- Create join table for User and Role (many-to-many relationship)
CREATE TABLE IF NOT EXISTS _user_roles (
    user_id BIGINT REFERENCES _user(id) ON DELETE CASCADE,
    role_id BIGINT REFERENCES role(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS token (
    id BIGSERIAL PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expired_at TIMESTAMPTZ,
    validated_at TIMESTAMPTZ,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES _user(id) ON DELETE CASCADE
);
