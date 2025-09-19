-- Customers
CREATE TABLE customers (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  status TEXT NOT NULL 
);

-- Accounts
CREATE TABLE accounts (
  id SERIAL PRIMARY KEY,
  customer_id INT NOT NULL,
  number TEXT NOT NULL,
  FOREIGN KEY (customer_id) REFERENCES customers(id)
);

INSERT INTO customers (name, status) VALUES
('Ana Torres', 'A'),
('Bruno Silva', 'ACTIVE'),
('Carla Perez', 'I'),
('Diego Ramos', 'INACTIVE');

INSERT INTO accounts (customer_id, number) VALUES
(1, '001-0001'),
(1, '001-0002'),
(2, '002-0001'),
(4, '004-0001');
