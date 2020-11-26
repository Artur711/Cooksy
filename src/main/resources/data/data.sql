insert into USER_TYPE (USER_TYPE_NAME)
values
       ('CUSTOMER'),
       ('ADMINISTRATOR');

insert into USERS (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PHOTO_URL, USER_TYPE)
values
       ('Patrick', 'Swayze', 'p.swayze@gmail.com', 'swayze52!', '', 1),
       ('Bob', 'Dylan', 'bob.dylan@gmail.com', 'dylan41', '', 2);

insert into PRODUCTS (NAME, PRICE, PRODUCT_TYPE_ID, MARKET_ID, GM_ID)
values
       ('PHONE', 100.00, 1, 1, 1);

insert into USERS (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PHOTO_URL, USER_TYPE)
values
       ('Adam', 'Adamowski', 'example@gmail.com', 'example1234', '', 1),
       ('Artur', 'Dobry', 'example@gmail.com', 'example1234', '', 2),
       ('Adam', 'Sławny', 'example@gmail.com', 'example1234', '', 1),
       ('Bob', 'Dylan', 'example@gmail.com', 'example1234', '', 1),
       ('Barbara', 'Fiszek', 'example@gmail.com', 'example1234', '', 2),
       ('Alicja', 'Kowaniak', 'example@gmail.com', 'example1234', '', 1),
       ('Bartłmiej', 'Fiszek', 'example@gmail.com', 'example1234', '', 1),
       ('Anastazja', 'Bakcyl', 'example@gmail.com', 'example1234', '', 1),
       ('Antoni', 'Donat', 'example@gmail.com', 'example1234', '', 2),
       ('Beata', 'Chorman', 'example@gmail.com', 'example1234', '', 1);