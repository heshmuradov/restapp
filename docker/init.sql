CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    balance bigint  DEFAULT 0
);

CREATE TABLE user_transactions (
    id SERIAL PRIMARY KEY,
    user_id integer NOT NULL,
    trans_amount bigint NOT NULL,
    trans_time  timestamp DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (name, email, balance)
VALUES ('user1', 'user1@mail.org', 1000);
