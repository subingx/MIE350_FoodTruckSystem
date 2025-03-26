
INSERT INTO foodtruckowners (id, firstName, lastName, email) VALUES (1, 'Christy', 'Cui', 'christy.cui@mail.utoronto.ca');
INSERT INTO foodtruckowners (id, firstName, lastName, email) VALUES (2, 'Subing', 'Xiang', 'subing.xiang@mail.utoronto.ca');
INSERT INTO foodtruckowners (id, firstName, lastName, email) VALUES (3, 'Chu', 'Zhang', 'chu.zhang@mail.utoronto.ca');


INSERT INTO foodtrucks (code, name, location, operatingHours, ownerId) VALUES ('TRUCK001', 'Taco Truck', '123 College St', '9:00 AM - 5:00 PM', 1);
INSERT INTO foodtrucks (code, name, location, operatingHours, ownerId) VALUES ('TRUCK002', 'Burger Bus', '456 Bay St', '10:00 AM - 8:00 PM', 2);
INSERT INTO foodtrucks (code, name, location, operatingHours, ownerId) VALUES ('TRUCK003', 'Sushi Wagon', '789 University St', '11:00 AM - 7:00 PM', 3);
INSERT INTO foodtrucks (code, name, location, operatingHours, ownerId) VALUES ('TRUCK004', 'Pizza Van', '321 Maple St', '8:00 AM - 6:00 PM', 2);


INSERT INTO menuItems (code, name, price, isAvailable, truckCode) VALUES
    ('TACO001', 'Beef Taco', 4.99, TRUE, 'TRUCK001'),
    ('BURGER002', 'Classic Burger', 7.99, TRUE, 'TRUCK002'),
    ('SUSHI003', 'Salmon Sushi', 12.99, TRUE, 'TRUCK003'),
    ('PIZZA004', 'Margherita Pizza', 10.99, TRUE, 'TRUCK004');

INSERT INTO customers (id, firstName, lastName, email, password) VALUES
(101, 'Alice', 'Wong', 'alice.wong@example.com', 'pass123'),
(102, 'Brian', 'Nguyen', 'brian.nguyen@example.com', 'secret456'),
(103, 'Carla', 'Singh', 'carla.singh@example.com', 'mypassword'),
(104, 'Daniel', 'Kim', 'daniel.kim@example.com', 'qwerty789'),
(105, 'Eva', 'Chen', 'eva.chen@example.com', 'letmein123');