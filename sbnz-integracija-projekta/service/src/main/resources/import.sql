INSERT INTO PUBLIC.ROLE (ID, NAME) VALUES ('637da2e8-30f6-4bff-8bff-16e22e75e2be', 'CLIENT');
INSERT INTO PUBLIC.ROLE (ID, NAME) VALUES ('390f4e9f-d6d7-44af-963e-bd31c0f483b7', 'DELIVERER');
INSERT INTO PUBLIC.ROLE (ID, NAME) VALUES ('44fe25eb-5607-4a5c-8f71-fbe9353c2f22', 'EMPLOYEE');
INSERT INTO PUBLIC.ROLE (ID, NAME) VALUES ('4d4a60fa-5f0a-4adb-a599-5e63c982460d', 'OWNER');
INSERT INTO PUBLIC.ROLE (ID, NAME) VALUES ('2ba68df8-4ae3-4bd3-9d5b-bbfe1104ef40', 'ADMIN');
INSERT INTO PUBLIC.ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) VALUES ('2ba68df8-4ae3-4bd3-9d5b-bbfe1104ef40', 'USER_CRUD');
INSERT INTO PUBLIC.ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) VALUES ('2ba68df8-4ae3-4bd3-9d5b-bbfe1104ef40', 'RESTAURANT_CRUD');
INSERT INTO PUBLIC.ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) VALUES ('2ba68df8-4ae3-4bd3-9d5b-bbfe1104ef40', 'REPORT_MANAGEMENT');

INSERT INTO PUBLIC.ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) VALUES ('4d4a60fa-5f0a-4adb-a599-5e63c982460d', 'CREATE_EMPLOYEE');
INSERT INTO PUBLIC.ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) VALUES ('4d4a60fa-5f0a-4adb-a599-5e63c982460d', 'ORDER_STATUS_UPDATE');
INSERT INTO PUBLIC.ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) VALUES ('4d4a60fa-5f0a-4adb-a599-5e63c982460d', 'READ_RESTAURANT_ORDERS');

INSERT INTO PUBLIC.ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) VALUES ('44fe25eb-5607-4a5c-8f71-fbe9353c2f22', 'ORDER_STATUS_UPDATE');
INSERT INTO PUBLIC.ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) VALUES ('44fe25eb-5607-4a5c-8f71-fbe9353c2f22', 'READ_RESTAURANT_ORDERS');

INSERT INTO PUBLIC.ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) VALUES ('390f4e9f-d6d7-44af-963e-bd31c0f483b7', 'READ_DELIVERER_ORDERS');
INSERT INTO PUBLIC.ROLE_PERMISSIONS (ROLE_ID, PERMISSIONS) VALUES ('390f4e9f-d6d7-44af-963e-bd31c0f483b7', 'DELIVERY_ORDER_STATUS_UPDATE');

INSERT INTO PUBLIC.ADMIN (ID, EMAIL, PASSWORD_HASH, NAME, SURNAME, PHONE_NUMBER, PROFILE_PICTURE, ACTIVE, BLOCKED, DISCOUNT, ROLE_ID) VALUES ('201ab15f-3b22-4659-b839-235b9e6a728e', 'admin@admin.com', '$2a$10$5tykbOhp3Uo2QrY2t3/uCOmsuwvEZl4KgEgZgN3At6JB3HQX.Z75y', 'Uros', 'Prijovic', '066430250', NULL, true, false, 0.5, '2ba68df8-4ae3-4bd3-9d5b-bbfe1104ef40');
INSERT INTO ADDRESS (ID, LATITUDE, LONGITUDE, NAME, USER_ID) VALUES ('503ee93e-f3de-4d72-b37b-0ef402f110b9', 45.2671, 19.8335, 'Trg Slobode 1', '201ab15f-3b22-4659-b839-235b9e6a728e');
INSERT INTO ADDRESS (ID, LATITUDE, LONGITUDE, NAME, USER_ID) VALUES ('8fcd1700-6678-4ac0-a9fe-52cba1845a3f', 45.2529, 19.8283, 'Bulevar Cara Lazara 12', '201ab15f-3b22-4659-b839-235b9e6a728e');

INSERT INTO PUBLIC.DELIVERER (ID, EMAIL, PASSWORD_HASH, NAME, SURNAME, PHONE_NUMBER, PROFILE_PICTURE, ACTIVE, BLOCKED, DISCOUNT, ROLE_ID, STATUS, TYPE) VALUES ('e5b192c1-f385-413f-9093-ce4706ebf12d', 'deliver@deliver.com', '$2a$10$5tykbOhp3Uo2QrY2t3/uCOmsuwvEZl4KgEgZgN3At6JB3HQX.Z75y', 'Mirko', 'Mirkovic', '063433432', NULL, true, false, 0, '390f4e9f-d6d7-44af-963e-bd31c0f483b7', 'AVAILABLE', 'BICYCLE');
INSERT INTO ADDRESS (ID, LATITUDE, LONGITUDE, NAME, USER_ID) VALUES ('471b22e9-0a26-4943-a3b1-c619bbe27808', 45.2422, 19.8429, 'Futoška 1', 'e5b192c1-f385-413f-9093-ce4706ebf12d');

INSERT INTO PUBLIC.RESTAURANT_OWNER (ID, EMAIL, PASSWORD_HASH, NAME, SURNAME, PHONE_NUMBER, PROFILE_PICTURE, ACTIVE, BLOCKED, DISCOUNT, ROLE_ID) VALUES ('8f92847e-953d-4126-a844-2990de5c8d17', 'owner@owner.com', '$2a$10$5tykbOhp3Uo2QrY2t3/uCOmsuwvEZl4KgEgZgN3At6JB3HQX.Z75y', 'Petar', 'Petrovic', '06444320250', NULL, true, false, 0, '4d4a60fa-5f0a-4adb-a599-5e63c982460d');
INSERT INTO ADDRESS (ID, LATITUDE, LONGITUDE, NAME, USER_ID) VALUES ('e5b8d7e9-e9ec-4c1f-95ce-fc441aadcc62', 45.2550, 19.8457, 'Bulevar Oslobođenja 18', '4d4a60fa-5f0a-4adb-a599-5e63c982460d');

INSERT INTO ADDRESS (ID, LATITUDE, LONGITUDE, NAME, USER_ID) VALUES ('ca05829c-dd6a-4ecc-8d93-fa18a382be8a', 45.2524, 19.8477, 'Zmaj Jovina 2', NULL);
INSERT INTO RESTAURANT (ID, CLOSED, CREATED_ON, DESCRIPTION, MAX_PREP, MIN_ORDER, MIN_PREP, NAME, PICTURE, CLOSES_AT, OPENS_AT, ADDRESS_ID, OWNER_ID) VALUES ('1a3c7b8f-ef9d-4c68-9a37-678b2c3d9e1e', false, current_timestamp, 'Delicious Italian cuisine', 30, 10.0, 15, 'Pizza Heaven', 'italian.jpg', '22:00:00', '23:00:00', 'ca05829c-dd6a-4ecc-8d93-fa18a382be8a', '8f92847e-953d-4126-a844-2990de5c8d17');
INSERT INTO MENU_ITEM (ID, AVAILABLE, CREATED_ON, DESCRIPTION, NAME, PICTURE, PRICE, TYPE, RESTAURANT_ID) VALUES ('1b5d792f-d63b-4d10-a2e0-365a407afded', true, CURRENT_TIMESTAMP, 'Delicious pepperoni pizza', 'Pepperoni Pizza', 'pepperoni_pizza.jpg', 12.99, 'PIZZA', '1a3c7b8f-ef9d-4c68-9a37-678b2c3d9e1e');
INSERT INTO MENU_ITEM (ID, AVAILABLE, CREATED_ON, DESCRIPTION, NAME, PICTURE, PRICE, TYPE, RESTAURANT_ID) VALUES ('b01102aa-4bca-4f4c-b69b-ed0d0cde3e1a', true, CURRENT_TIMESTAMP, 'Creamy Alfredo pasta', 'Alfredo Pasta', 'alfredo_pasta.jpg', 10.99, 'PASTA', '1a3c7b8f-ef9d-4c68-9a37-678b2c3d9e1e');
INSERT INTO MENU_ITEM (ID, AVAILABLE, CREATED_ON, DESCRIPTION, NAME, PICTURE, PRICE, TYPE, RESTAURANT_ID) VALUES ('4c1430d5-a993-4e03-b597-97eea537e0ef', true, CURRENT_TIMESTAMP, 'Grilled lemon chicken', 'Lemon Chicken', 'lemon_chicken.jpg', 14.99, 'CHICKEN', '1a3c7b8f-ef9d-4c68-9a37-678b2c3d9e1e');
INSERT INTO MENU_ITEM (ID, AVAILABLE, CREATED_ON, DESCRIPTION, NAME, PICTURE, PRICE, TYPE, RESTAURANT_ID) VALUES ('65f32dcc-a1b9-4e80-b87c-6e82feff5a49', true, CURRENT_TIMESTAMP, 'Classic Margherita pizza', 'Margherita Pizza', 'margherita_pizza.jpg', 11.99, 'ITALIAN', '1a3c7b8f-ef9d-4c68-9a37-678b2c3d9e1e');
INSERT INTO MENU_ITEM (ID, AVAILABLE, CREATED_ON, DESCRIPTION, NAME, PICTURE, PRICE, TYPE, RESTAURANT_ID) VALUES ('267e7844-a7b2-4308-86b8-35e6fb90598f', true, CURRENT_TIMESTAMP, 'Veggie lovers pizza', 'Veggie Pizza', 'veggie_pizza.jpg', 13.99, 'PIZZA', '1a3c7b8f-ef9d-4c68-9a37-678b2c3d9e1e');
INSERT INTO MENU_ITEM (ID, AVAILABLE, CREATED_ON, DESCRIPTION, NAME, PICTURE, PRICE, TYPE, RESTAURANT_ID) VALUES ('ebe7aef0-84e3-444b-a4d1-a448c38f1bfa', true, CURRENT_TIMESTAMP, 'Spicy Arrabiata pasta', 'Arrabiata Pasta', 'arrabiata_pasta.jpg', 11.99, 'PASTA', '1a3c7b8f-ef9d-4c68-9a37-678b2c3d9e1e');
INSERT INTO MENU_ITEM (ID, AVAILABLE, CREATED_ON, DESCRIPTION, NAME, PICTURE, PRICE, TYPE, RESTAURANT_ID) VALUES ('2c8af432-02b6-4639-8dc0-faeec3af30d0', true, CURRENT_TIMESTAMP, 'Crispy fried chicken', 'Fried Chicken', 'fried_chicken.jpg', 12.99, 'CHICKEN', '1a3c7b8f-ef9d-4c68-9a37-678b2c3d9e1e');
INSERT INTO MENU_ITEM (ID, AVAILABLE, CREATED_ON, DESCRIPTION, NAME, PICTURE, PRICE, TYPE, RESTAURANT_ID) VALUES ('41469ba6-34e4-49be-bd4e-a71087643318', true, CURRENT_TIMESTAMP, 'Tiramisu', 'Tiramisu', 'tiramisu.jpg', 6.99, 'ITALIAN', '1a3c7b8f-ef9d-4c68-9a37-678b2c3d9e1e');
INSERT INTO MENU_ITEM (ID, AVAILABLE, CREATED_ON, DESCRIPTION, NAME, PICTURE, PRICE, TYPE, RESTAURANT_ID) VALUES ('21186a6c-594c-4a0a-ad89-905c666466c2', true, CURRENT_TIMESTAMP, 'Meat lovers pizza', 'Meat Lovers Pizza', 'meat_lovers_pizza.jpg', 15.99, 'PIZZA', '1a3c7b8f-ef9d-4c68-9a37-678b2c3d9e1e');
INSERT INTO MENU_ITEM (ID, AVAILABLE, CREATED_ON, DESCRIPTION, NAME, PICTURE, PRICE, TYPE, RESTAURANT_ID) VALUES ('973d0136-4d1f-4130-bfb7-6854b27e86f0', true, CURRENT_TIMESTAMP, 'Spaghetti Bolognese', 'Spaghetti Bolognese', 'spaghetti_bolognese.jpg', 9.99, 'PASTA', '1a3c7b8f-ef9d-4c68-9a37-678b2c3d9e1e');

INSERT INTO ADDRESS (ID, LATITUDE, LONGITUDE, NAME, USER_ID) VALUES ('e2a215b6-b006-4fed-a27a-24d1661b6caf', 45.2471, 19.8369, 'Dunavska 10', NULL);
INSERT INTO RESTAURANT (ID, CLOSED, CREATED_ON, DESCRIPTION, MAX_PREP, MIN_ORDER, MIN_PREP, NAME, PICTURE, CLOSES_AT, OPENS_AT, ADDRESS_ID, OWNER_ID) VALUES ('2b685cff-1877-4e42-a839-6f920c8922e1', false, current_timestamp, 'Traditional Indian dishes', 45, 15.0, 20, 'Spice Bazaar', 'indian.jpg', '23:00:00', '09:00:00', 'e2a215b6-b006-4fed-a27a-24d1661b6caf', '8f92847e-953d-4126-a844-2990de5c8d17');

INSERT INTO ADDRESS (ID, LATITUDE, LONGITUDE, NAME, USER_ID) VALUES ('1172fd27-51a3-4155-b994-29e2fb988e8a', 45.2547, 19.8349, 'Bulevar Evrope 35', NULL);
INSERT INTO RESTAURANT (ID, CLOSED, CREATED_ON, DESCRIPTION, MAX_PREP, MIN_ORDER, MIN_PREP, NAME, PICTURE, CLOSES_AT, OPENS_AT, ADDRESS_ID, OWNER_ID) VALUES ('3b9e41e6-897b-4285-9a2e-2b60df4ab47a', true, current_timestamp, 'Gourmet seafood specialties', 60, 20.0, 30, 'Ocean Delight', 'seafood.jpg', '21:30:00', '12:00:00', '1172fd27-51a3-4155-b994-29e2fb988e8a', '8f92847e-953d-4126-a844-2990de5c8d17');

INSERT INTO ADDRESS (ID, LATITUDE, LONGITUDE, NAME, USER_ID) VALUES ('e9563b16-bb16-4631-a8c0-34e9de4c8c6c', 45.2542, 19.8412, 'Pap Pavla 14', NULL);
INSERT INTO RESTAURANT (ID, CLOSED, CREATED_ON, DESCRIPTION, MAX_PREP, MIN_ORDER, MIN_PREP, NAME, PICTURE, CLOSES_AT, OPENS_AT, ADDRESS_ID, OWNER_ID) VALUES ('4f345c3e-993e-4b1c-85f3-02a76b17d153', false, current_timestamp, 'Authentic Mexican flavors', 40, 12.0, 25, 'Taco Fiesta', 'mexican.jpg', '23:00:00', '11:30:00', 'e9563b16-bb16-4631-a8c0-34e9de4c8c6c', '8f92847e-953d-4126-a844-2990de5c8d17');

INSERT INTO ADDRESS (ID, LATITUDE, LONGITUDE, NAME, USER_ID) VALUES ('ae98c4ce-896e-4779-b02c-931eb16c0e7e', 45.2637, 19.8328, 'Kisačka 2', NULL);
INSERT INTO RESTAURANT (ID, CLOSED, CREATED_ON, DESCRIPTION, MAX_PREP, MIN_ORDER, MIN_PREP, NAME, PICTURE, CLOSES_AT, OPENS_AT, ADDRESS_ID, OWNER_ID) VALUES ('8bce6833-dbe6-4d44-9c8a-faf2b2122421', false, current_timestamp, 'Sushi and Japanese specialties', 40, 15.0, 25, 'Tokyo Sushi', 'sushi.jpg', '23:00:00', '12:00:00', 'ae98c4ce-896e-4779-b02c-931eb16c0e7e', '8f92847e-953d-4126-a844-2990de5c8d17');
INSERT INTO MENU_ITEM (ID, AVAILABLE, CREATED_ON, DESCRIPTION, NAME, PICTURE, PRICE, TYPE, RESTAURANT_ID) VALUES ('e6f8c79e-64e1-4e39-9875-7872386f4969', true, CURRENT_TIMESTAMP, 'Salmon Nigiri', 'Salmon Nigiri', 'salmon_nigiri.jpg', 5.99, 'SUSHI', '8bce6833-dbe6-4d44-9c8a-faf2b2122421');
INSERT INTO MENU_ITEM (ID, AVAILABLE, CREATED_ON, DESCRIPTION, NAME, PICTURE, PRICE, TYPE, RESTAURANT_ID) VALUES ('2f267b19-0bda-4c6d-9eaa-3c2bea248e09', true, CURRENT_TIMESTAMP, 'California Roll', 'California Roll', 'california_roll.jpg', 8.99, 'SUSHI', '8bce6833-dbe6-4d44-9c8a-faf2b2122421');
INSERT INTO MENU_ITEM (ID, AVAILABLE, CREATED_ON, DESCRIPTION, NAME, PICTURE, PRICE, TYPE, RESTAURANT_ID) VALUES ('3e8b1c0f-84e3-4a8a-9af4-74304f7c4e45', true, CURRENT_TIMESTAMP, 'Tuna Sashimi', 'Tuna Sashimi', 'tuna_sashimi.jpg', 12.99, 'SUSHI', '8bce6833-dbe6-4d44-9c8a-faf2b2122421');
INSERT INTO MENU_ITEM (ID, AVAILABLE, CREATED_ON, DESCRIPTION, NAME, PICTURE, PRICE, TYPE, RESTAURANT_ID) VALUES ('cd8b789d-bb9f-4f67-a5c8-845fcba045ed', true, CURRENT_TIMESTAMP, 'Miso Soup', 'Miso Soup', 'miso_soup.jpg', 3.99, 'ASIAN', '8bce6833-dbe6-4d44-9c8a-faf2b2122421');
INSERT INTO MENU_ITEM (ID, AVAILABLE, CREATED_ON, DESCRIPTION, NAME, PICTURE, PRICE, TYPE, RESTAURANT_ID) VALUES ('9a2d245f-8c38-4c8f-9cb2-28e4177b0d5c', true, CURRENT_TIMESTAMP, 'Pad Thai', 'Pad Thai', 'pad_thai.jpg', 11.99, 'ASIAN', '8bce6833-dbe6-4d44-9c8a-faf2b2122421');
INSERT INTO MENU_ITEM (ID, AVAILABLE, CREATED_ON, DESCRIPTION, NAME, PICTURE, PRICE, TYPE, RESTAURANT_ID) VALUES ('18bdaac2-c7e5-487a-8801-3352d75f6925', true, CURRENT_TIMESTAMP, 'General Tso Chicken', 'General Tso Chicken', 'general_tso_chicken.jpg', 13.99, 'ASIAN', '8bce6833-dbe6-4d44-9c8a-faf2b2122421');
INSERT INTO MENU_ITEM (ID, AVAILABLE, CREATED_ON, DESCRIPTION, NAME, PICTURE, PRICE, TYPE, RESTAURANT_ID) VALUES ('e7c3e8f5-3fe9-4c54-8d47-67eab6231b64', true, CURRENT_TIMESTAMP, 'Rainbow Roll', 'Rainbow Roll', 'rainbow_roll.jpg', 10.99, 'SUSHI', '8bce6833-dbe6-4d44-9c8a-faf2b2122421');
INSERT INTO MENU_ITEM (ID, AVAILABLE, CREATED_ON, DESCRIPTION, NAME, PICTURE, PRICE, TYPE, RESTAURANT_ID) VALUES ('49d9b42d-2496-4d24-b497-45f2e3b8f926', true, CURRENT_TIMESTAMP, 'Sashimi Platter', 'Sashimi Platter', 'sashimi_platter.jpg', 35.99, 'SUSHI', '8bce6833-dbe6-4d44-9c8a-faf2b2122421');

INSERT INTO ADDRESS (ID, LATITUDE, LONGITUDE, NAME, USER_ID) VALUES ('9a879680-6f29-4c27-9b7f-d68ad049e575', 45.2620, 19.8352, 'Miletićeva 5', NULL);
INSERT INTO RESTAURANT (ID, CLOSED, CREATED_ON, DESCRIPTION, MAX_PREP, MIN_ORDER, MIN_PREP, NAME, PICTURE, CLOSES_AT, OPENS_AT, ADDRESS_ID, OWNER_ID) VALUES ('7a6d4091-1dd5-46d7-8f88-1c9db73c97e0', true, current_timestamp, 'Delightful French pastries and desserts', 45, 10.0, 20, 'La Pâtisserie', 'french.jpg', '20:30:00', '08:30:00', '9a879680-6f29-4c27-9b7f-d68ad049e575', '8f92847e-953d-4126-a844-2990de5c8d17');
