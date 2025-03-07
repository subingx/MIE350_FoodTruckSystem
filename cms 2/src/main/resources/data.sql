
INSERT INTO foodtruckowners (id, firstName, lastName, email) VALUES (1, 'Christy', 'Cui', 'christy.cui@mail.utoronto.ca');
INSERT INTO foodtruckowners (id, firstName, lastName, email) VALUES (2, 'Subing', 'Xiang', 'subing.xiang@mail.utoronto.ca');
INSERT INTO foodtruckowners (id, firstName, lastName, email) VALUES (3, 'Chu', 'Zhang', 'chu.zhang@mail.utoronto.ca');


INSERT INTO foodtrucks (code, name, location, operatingHours, ownerId) VALUES ('TRUCK001', 'Taco Truck', '123 College St', '9:00 AM - 5:00 PM', 1);
INSERT INTO foodtrucks (code, name, location, operatingHours, ownerId) VALUES ('TRUCK002', 'Burger Bus', '456 Bay St', '10:00 AM - 8:00 PM', 2);
INSERT INTO foodtrucks (code, name, location, operatingHours, ownerId) VALUES ('TRUCK003', 'Sushi Wagon', '789 University St', '11:00 AM - 7:00 PM', 3);
INSERT INTO foodtrucks (code, name, location, operatingHours, ownerId) VALUES ('TRUCK004', 'Pizza Van', '321 Maple St', '8:00 AM - 6:00 PM', 2);


INSERT INTO menuitems (code, name, price, isAvailable, truckCode) VALUES ('TACO001', 'Beef Taco', 4.99, TRUE, 'TRUCK001');
INSERT INTO menuitems (code, name, price, isAvailable, truckCode) VALUES ('BURGER002', 'Classic Burger', 7.99, TRUE, 'TRUCK002');
INSERT INTO menuitems (code, name, price, isAvailable, truckCode) VALUES ('SUSHI003', 'Salmon Sushi', 12.99, TRUE, 'TRUCK003');
INSERT INTO menuitems (code, name, price, isAvailable, truckCode) VALUES ('PIZZA004', 'Margherita Pizza', 10.99, TRUE, 'TRUCK004');

