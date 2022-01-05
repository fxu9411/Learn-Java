DROP TABLE IF EXISTS taco_order;
DROP TABLE IF EXISTS taco;
DROP TABLE IF EXISTS ingredient_ref;
DROP TABLE IF EXISTS ingredient;

CREATE TABLE IF NOT EXISTS taco_order
(
    id              IDENTITY,
    delivery_name   VARCHAR (50) NOT NULL,
    delivery_street VARCHAR (50) NOT NULL,
    delivery_city   VARCHAR (50) NOT NULL,
    delivery_state  VARCHAR (2) NOT NULL,
    delivery_zip    VARCHAR (10) NOT NULL,
    cc_number       VARCHAR (16) NOT NULL,
    cc_expiration   VARCHAR (5) NOT NULL,
    cc_cvv          VARCHAR (3) NOT NULL,
    placed_at       TIMESTAMP NOT NULL
    );

CREATE TABLE IF NOT EXISTS taco
(
    id             IDENTITY,
    name           VARCHAR (50) NOT NULL,
    taco_order     BIGINT NOT NULL,
    taco_order_key BIGINT NOT NULL,
    created_at     TIMESTAMP NOT NULL
    );

CREATE TABLE IF NOT EXISTS ingredient_ref
(
    ingredient VARCHAR (4) NOT NULL,
    taco       BIGINT NOT NULL,
    taco_key   BIGINT NOT NULL
    );

CREATE TABLE IF NOT EXISTS ingredient
(
    id   VARCHAR (4) NOT NULL,
    name VARCHAR (25) NOT NULL,
    type VARCHAR (10) NOT NULL
    );

ALTER TABLE taco
    ADD FOREIGN KEY (taco_order) REFERENCES taco_order (id);

ALTER TABLE ingredient_ref
    ADD FOREIGN KEY (ingredient) REFERENCES ingredient (id);