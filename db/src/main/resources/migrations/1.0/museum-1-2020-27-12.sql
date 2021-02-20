

CREATE TABLE IF NOT EXISTS "users_table"
(
    user_id    SERIAL PRIMARY KEY,
    email      VARCHAR(100) NOT NULL UNIQUE,
    password   CHAR(144)      NULL,
    type Boolean NOT NULL
);


