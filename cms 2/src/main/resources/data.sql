

INSERT INTO foodtrucks (code, name, location, operating_hours, owner_id) VALUES ('TRUCK001' , 'Taco Truck', '123 College St', '9:00 AM - 5:00 PM', 1);
INSERT INTO foodtrucks (code, name, location, operating_hours, owner_id) VALUES ('TRUCK002', 'Burger Bus', '456 Bay st', '10:00 AM - 8:00 PM', 2);
INSERT INTO foodtrucks (code, name, location, operating_hours, owner_id) VALUES ('TRUCK003', 'Sushi Wagon', '789 University st', '11:00 AM - 7:00 PM', 3);
INSERT INTO foodtrucks (code, name, location, operating_hours, owner_id) VALUES ('TRUCK004', 'Pizza Van', '321 Maple st', '8:00 AM - 6:00 PM', 2);

INSERT INTO foodtruckowners (id, firstName, lastName, email) VALUES (1, 'Christy', 'Cui', 'christy.cui@mail.utoronto.ca');
INSERT INTO foodtruckowners (id, firstName, lastName, email) VALUES (2, 'Subing', 'Xiang', 'subing.xiang@mail.utoronto.ca');
INSERT INTO foodtruckowners (id, firstName, lastName, email) VALUES (3, 'Chu', 'Zhang', 'chu.zhang@mail.utoronto.ca');


INSERT INTO menu_items (name, description, price, isAvailable, truckCode) VALUES
                                                                                     ('Beef Taco', 'Spicy beef taco with salsa', 4.99, TRUE, 'TRUCK001'),
                                                                                     ('Classic Burger', 'Beef patty with lettuce and tomato', 7.99, TRUE, 'TRUCK002'),
                                                                                     ('Salmon Sushi', 'Fresh salmon nigiri', 12.99, TRUE, 'TRUCK003'),
                                                                                     ('Margherita Pizza', 'Tomato, mozzarella, basil', 10.99, TRUE, 'TRUCK004'),
                                                                                     ('Ramen Bowl', 'Pork broth with noodles', 9.99, TRUE, 'TRUCK005');