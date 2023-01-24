CREATE SCHEMA IF NOT EXISTS my_store;

CREATE SEQUENCE IF NOT EXISTS my_store.my_store_id_seq;


CREATE TABLE IF NOT EXISTS my_store.order
(
    id            integer NOT NULL DEFAULT nextval('my_store.my_store_id_seq'),
    creation_date date,
    cost          float   NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_order ON my_store.order USING hash (creation_date);



CREATE TABLE IF NOT EXISTS my_store.product
(
    id   integer NOT NULL DEFAULT nextval('my_store.my_store_id_seq'),
    name text    NOT NULL,
    cost float   NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_product ON my_store.product USING hash (name);


ALTER TABLE  my_store."order" ADD CONSTRAINT pk_my_store_order PRIMARY KEY (id);

ALTER TABLE  my_store."product" ADD CONSTRAINT pk_my_store_product PRIMARY KEY (id);

CREATE TABLE IF NOT EXISTS my_store.order_product
(
    id            integer NOT NULL DEFAULT nextval('my_store.my_store_id_seq'),
    fk_order_id   integer
        constraint data_source_fk_connection_id_fkey references my_store.order,
    fk_product_id integer
        constraint data_source_fk_product_id_fkey references my_store.product
);

CREATE INDEX IF NOT EXISTS idx_product ON my_store.order_product USING hash (fk_order_id);