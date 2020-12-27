CREATE TABLE IF NOT EXISTS "user"
(
     id    SERIAL PRIMARY KEY,

    login      VARCHAR(100) NOT NULL UNIQUE,

    password   CHAR(144)      NULL,
    type_user BOOLEAN NOT NULL
 );
