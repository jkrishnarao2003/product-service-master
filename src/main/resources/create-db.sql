CREATE TABLE product (
    id   BIGINT PRIMARY KEY,
    name VARCHAR(50),
    category VARCHAR(30),
    cost DECIMAL
  );
  
create sequence IF NOT EXISTS product_q1 MINVALUE 1 INCREMENT 1 start with 1;