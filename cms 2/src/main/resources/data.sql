

INSERT INTO foodtrucks (code, name, location, operating_hours, owner_id) VALUES ('TRUCK001' , 'Taco Truck', '123 College St', '9:00 AM - 5:00 PM', 1);
INSERT INTO foodtrucks (code, name, location, operating_hours, owner_id) VALUES ('TRUCK002', 'Burger Bus', '456 Bay st', '10:00 AM - 8:00 PM', 2);
INSERT INTO foodtrucks (code, name, location, operating_hours, owner_id) VALUES ('TRUCK003', 'Sushi Wagon', '789 University st', '11:00 AM - 7:00 PM', 3);
INSERT INTO foodtrucks (code, name, location, operating_hours, owner_id) VALUES ('TRUCK004', 'Pizza Van', '321 Maple st', '8:00 AM - 6:00 PM', 2);

INSERT INTO foodtruckowners (id, firstName, lastName, email) VALUES (1, 'Christy', 'Cui', 'christy.cui@mail.utoronto.ca');
INSERT INTO foodtruckowners (id, firstName, lastName, email) VALUES (2, 'Subing', 'Xiang', 'subing.xiang@mail.utoronto.ca');
INSERT INTO foodtruckowners (id, firstName, lastName, email) VALUES (3, 'Chu', 'Zhang', 'chu.zhang@mail.utoronto.ca');


INSERT INTO menu_items (code, name, description, price, isAvailable, truckCode) VALUES ('M001', 'Classic Burger', 8.99, TRUE, 'TRUCK001');
INSERT INTO menu_items (code, name, description, price, isAvailable, truckCode) VALUES ('M002', 'Spicy Chicken Sandwich', 7.49, TRUE, 'TRUCK002');
INSERT INTO menu_items (code, name, description, price, isAvailable, truckCode) VALUES ('M003', 'Vegetarian Wrap', 6.99, TRUE, 'TRUCK003');
INSERT INTO menu_items (code, name, description, price, isAvailable, truckCode) VALUES ('M004', 'BBQ Pulled Pork Sandwich', 9.99, TRUE, 'TRUCK004');
INSERT INTO menu_items (code, name, description, price, isAvailable, truckCode) VALUES ('M005', 'Grilled Cheese', 5.49, TRUE, 'TRUCK002');
INSERT INTO menu_items (code, name, description, price, isAvailable, truckCode) VALUES ('M006', 'Loaded Nachos', 7.99, TRUE, 'TRUCK003');
INSERT INTO menu_items (code, name, description, price, isAvailable, truckCode) VALUES ('M007', 'Fish Tacos', 10.99, TRUE, 'TRUCK004');
INSERT INTO menu_items (code, name, description, price, isAvailable, truckCode) VALUES ('M008', 'Loaded Nachos', 7.99, TRUE, 'TRUCK003');
INSERT INTO menu_items (code, name, description, price, isAvailable, truckCode) VALUES ('M009', 'Caesar Salad', 6.49, TRUE, 'TRUCK001');
INSERT INTO menu_items (code, name, description, price, isAvailable, truckCode) VALUES ('M010', 'Chocolate Milkshake', 4.99, TRUE, 'TRUCK002');