
INSERT INTO foodtruckowners (id, firstName, lastName, email, password) VALUES (1, 'Christy', 'Cui', 'christy.cui@mail.utoronto.ca', 'nihao666');
INSERT INTO foodtruckowners (id, firstName, lastName, email, password) VALUES (2, 'Subing', 'Xiang', 'subing.xiang@mail.utoronto.ca','wanshanghao123');
INSERT INTO foodtruckowners (id, firstName, lastName, email, password) VALUES (3, 'Chu', 'Zhang', 'chu.zhang@mail.utoronto.ca', 'xiawuhao789');
INSERT INTO foodtruckowners (id, firstName, lastName, email, password) VALUES (4, 'Linda', 'Wells', 'linda.wells@mail.utoronto.ca', 'zaoshanghao456');
INSERT INTO foodtruckowners (id, firstName, lastName, email, password) VALUES (5, 'Mike', 'Tian', 'mike.tian@mail.utoronto.ca', 'wanan999');

INSERT INTO foodtrucks (code, name, location, operatingHours, ownerId) VALUES ('TRUCK001', 'Taco Truck', '123 College St', '9:00 AM - 5:00 PM', 1);
INSERT INTO foodtrucks (code, name, location, operatingHours, ownerId) VALUES ('TRUCK002', 'Burger Bus', '456 Bay St', '10:00 AM - 8:00 PM', 2);
INSERT INTO foodtrucks (code, name, location, operatingHours, ownerId) VALUES ('TRUCK003', 'Sushi Wagon', '789 University St', '11:00 AM - 7:00 PM', 3);
INSERT INTO foodtrucks (code, name, location, operatingHours, ownerId) VALUES ('TRUCK004', 'Pizza Van', '321 Maple St', '8:00 AM - 6:00 PM', 2);

INSERT INTO foodtrucks (code, name, location, operatingHours, ownerId) VALUES
('TRUCK005', 'Noodle Express', '101 Dundas St', '11:00 AM - 9:00 PM', 4),
('TRUCK006', 'Kebab King', '77 King St', '10:00 AM - 7:00 PM', 5),
('TRUCK007', 'Crepe Corner', '88 Bloor St', '8:00 AM - 4:00 PM', 1),
('TRUCK008', 'Vegan Vibes', '66 Queen St', '12:00 PM - 6:00 PM', 3);


INSERT INTO menuItems (code, name, price, isAvailable, truckCode) VALUES
    ('TACO001', 'Beef Taco', 4.99, TRUE, 'TRUCK001'),
    ('BURGER002', 'Classic Burger', 7.99, TRUE, 'TRUCK002'),
    ('SUSHI003', 'Salmon Sushi', 12.99, TRUE, 'TRUCK003'),
    ('PIZZA004', 'Margherita Pizza', 10.99, TRUE, 'TRUCK004'),
    ('NOODLE005', 'Spicy Ramen', 9.49, TRUE, 'TRUCK005'),
    ('KEBAB006', 'Chicken Kebab', 8.99, TRUE, 'TRUCK006'),
    ('CREPE007', 'Strawberry Crepe', 6.49, TRUE, 'TRUCK007'),
    ('VEGAN008', 'Tofu Bowl', 7.99, TRUE, 'TRUCK008'),
    ('BURGER003', 'Bacon Cheeseburger', 9.99, TRUE, 'TRUCK002'),
    ('TACO002', 'Veggie Taco', 4.49, TRUE, 'TRUCK001'),
    ('SUSHI004', 'Tuna Roll', 11.49, TRUE, 'TRUCK003'),
    ('PIZZA005', 'Pepperoni Pizza', 11.99, TRUE, 'TRUCK004'),
    ('NOODLE006', 'Udon Noodles', 8.79, TRUE, 'TRUCK005'),
    ('CREPE008', 'Nutella Banana Crepe', 6.99, TRUE, 'TRUCK007'),
    ('VEGAN009', 'Quinoa Salad', 7.49, TRUE, 'TRUCK008');

INSERT INTO customers (id, firstName, lastName, email, password) VALUES
(101, 'Alice', 'Wong', 'alice.wong@example.com', 'pass123'),
(102, 'Brian', 'Nguyen', 'brian.nguyen@example.com', 'secret456'),
(103, 'Carla', 'Singh', 'carla.singh@example.com', 'mypassword'),
(104, 'Daniel', 'Kim', 'daniel.kim@example.com', 'qwerty789'),
(105, 'Eva', 'Chen', 'eva.chen@example.com', 'letmein123'),
(106, 'Frank', 'Li', 'frank.li@example.com', 'abc123'),
(107, 'Grace', 'Zhao', 'grace.zhao@example.com', 'gracepass'),
(108, 'Henry', 'Park', 'henry.park@example.com', 'hpark888'),
(109, 'Ivy', 'Mo', 'ivy.mo@example.com', 'ivymo321'),
(110, 'Jack', 'Wu', 'jack.wu@example.com', 'password10');


INSERT INTO favorites (customerId, truckCode) VALUES (101, 'TRUCK001');
INSERT INTO favorites (customerId, truckCode) VALUES (102, 'TRUCK002');
INSERT INTO favorites (customerId, truckCode) VALUES (103, 'TRUCK003');
INSERT INTO favorites (customerId, truckCode) VALUES (104, 'TRUCK001');
INSERT INTO favorites (customerId, truckCode) VALUES (105, 'TRUCK004');

INSERT INTO favorites (customerId, truckCode) VALUES
(106, 'TRUCK001'),
(107, 'TRUCK003'),
(108, 'TRUCK004'),
(109, 'TRUCK005'),
(110, 'TRUCK006'),
(101, 'TRUCK007'),
(102, 'TRUCK008'),
(103, 'TRUCK005');
