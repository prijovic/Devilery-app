insert into PUBLIC.ROLE (ID, NAME) values ('637da2e8-30f6-4bff-8bff-16e22e75e2be', 'CLIENT');
insert into PUBLIC.ROLE (ID, NAME) values ('390f4e9f-d6d7-44af-963e-bd31c0f483b7', 'DELIVERER');
insert into PUBLIC.ROLE (ID, NAME) values ('44fe25eb-5607-4a5c-8f71-fbe9353c2f22', 'EMPLOYEE');
insert into PUBLIC.ROLE (ID, NAME) values ('4d4a60fa-5f0a-4adb-a599-5e63c982460d', 'OWNER');
insert into PUBLIC.ROLE (ID, NAME) values ('2ba68df8-4ae3-4bd3-9d5b-bbfe1104ef40', 'ADMIN');
insert into PUBLIC.ADMIN (ID, EMAIL, PASSWORD_HASH, NAME, SURNAME, PHONE_NUMBER, PROFILE_PICTURE, CUSTOMER_ID, ACTIVE, BLOCKED, DISCOUNT, ROLE_ID) values ('201ab15f-3b22-4659-b839-235b9e6a728e', 'prijovic.uros13@gmail.com', '$2a$10$5tykbOhp3Uo2QrY2t3/uCOmsuwvEZl4KgEgZgN3At6JB3HQX.Z75y', 'Uros', 'Prijovic', '066430250', null, 'cus_NExYsC84fHA5qt', true, false, 50, '2ba68df8-4ae3-4bd3-9d5b-bbfe1104ef40');