CREATE TABLE IF NOT EXISTS "user"
(
     id    SERIAL PRIMARY KEY,

    login      VARCHAR(100) NOT NULL UNIQUE,

    password   CHAR(300)      NULL,
    type BOOLEAN NOT NULL
 );
