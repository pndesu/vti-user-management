USE user_management;

-- -- 1. Create the departments table first (The Parent)
-- CREATE TABLE IF NOT EXISTS departments (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     name VARCHAR(255) NOT NULL,
--     created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
-- );
--
-- -- 2. Create the users table (The Child)
-- CREATE TABLE IF NOT EXISTS users (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     username VARCHAR(50) NOT NULL UNIQUE,
--     password VARCHAR(255) NOT NULL,
--     first_name VARCHAR(50),
--     last_name VARCHAR(50),
--     role ENUM('ADMIN', 'EMPLOYEE', 'MANAGER') DEFAULT 'EMPLOYEE',
--     -- You MUST define the column before you can make it a Foreign Key
--     department_id INT, 
--     
--     CONSTRAINT fk_department 
--         FOREIGN KEY (department_id) 
--         REFERENCES departments(id)
--         ON DELETE SET NULL -- Good practice: if a dept is deleted, user stays but dept becomes null
-- );
INSERT INTO departments (id, name) VALUES (1, 'IT Department');
-- Your data insertion
INSERT INTO users (username, password, first_name, last_name, role, department_id) VALUES
('alice_w', 'pass123', 'Alice', 'Wong', 'ADMIN', 1),
('bob_m', 'secure456', 'Bob', 'Miller', 'EMPLOYEE', 1),
('charlie_d', 'charlie789', 'Charlie', 'Davis', 'MANAGER', 1),
('diana_p', 'diana_pass', 'Diana', 'Prince', 'EMPLOYEE', 1),
('ethan_h', 'hunt_mission', 'Ethan', 'Hunt', 'ADMIN', 1),
('fiona_g', 'green_shrek', 'Fiona', 'Gallagher', 'EMPLOYEE', 1),
('george_c', 'curious_geo', 'George', 'Clooney', 'MANAGER', 1),
('hannah_b', 'banana_h', 'Hannah', 'Baker', 'EMPLOYEE', 1),
('ian_m', 'magneto_x', 'Ian', 'McKellen', 'EMPLOYEE', 1),
('jenny_l', 'forest_g', 'Jenny', 'Lawson', 'ADMIN', 1),
('kevin_h', 'home_alone', 'Kevin', 'Hart', 'EMPLOYEE', 1),
('laura_v', 'v_craft', 'Laura', 'Vander', 'MANAGER', 1),
('mike_t', 'iron_mike', 'Mike', 'Tyson', 'EMPLOYEE', 1),
('nina_s', 'ballet_9', 'Nina', 'Simone', 'EMPLOYEE', 1),
('oscar_i', 'isaac_moon', 'Oscar', 'Isaac', 'ADMIN', 1),
('paul_r', 'ant_man', 'Paul', 'Rudd', 'EMPLOYEE', 1),
('quinn_f', 'fast_q', 'Quinn', 'Fabray', 'EMPLOYEE', 1),
('riley_k', 'king_r', 'Riley', 'Keough', 'MANAGER', 1),
('sam_w', 'falcon_s', 'Sam', 'Wilson', 'EMPLOYEE', 1),
('tina_f', 'rock_30', 'Tina', 'Fey', 'ADMIN', 1);
