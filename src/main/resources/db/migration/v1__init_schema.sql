CREATE TABLE tennant (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    tenant_key VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255),
    role VARCHAR(50),
    tenant_id INTEGER REFERENCES tenant(id),
    enabled BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE task (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    due_date DATE,
    status VARCHAR(50),
    assigned_to_id INTEGER REFERENCES users(id),
    tenant_id INTEGER REFERENCES tenant(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);