CREATE DATABASE inventory;
USE inventory;

-- Suppliers table
CREATE TABLE inventory.suppliers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL
);
-- Inventory table
CREATE TABLE inventory.inventory (
    id INT AUTO_INCREMENT PRIMARY KEY,
    supplier_id INT NOT NULL,
    product_name VARCHAR(100) NOT NULL,
    quantity INT CHECK (quantity >= 0),
    price DOUBLE CHECK (price > 0),
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
);
-- Return all inventory grouped by supplier, sorted by total inventory value (quantity × price)
SELECT
    s.id,
    s.name,
    SUM(i.quantity * i.price) AS total_inventory_value
FROM suppliers s
JOIN inventory i ON s.id = i.supplier_id
GROUP BY s.id, s.name
ORDER BY total_inventory_value DESC;
