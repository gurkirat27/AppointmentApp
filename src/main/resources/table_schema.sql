CREATE TABLE IF NOT EXISTS appointment(
id LONG PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255),
email VARCHAR(255)
);
INSERT INTO appointment(name, email) VALUES
('Frank', 'frank@frank.com'),
('Hao', 'hao@gmail.com'),
('Sue', 'sue@yahoo.ca'),
('Jaspreet', 'jaspreet@outlook.com');