insert into USER_TYPE (USER_TYPE_NAME)
values
       ('CUSTOMER'),
       ('ADMINISTRATOR');

insert into USERS (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PHOTO_URL, USER_TYPE_ID)
values
       ('Patrick', 'Swayze', 'p.swayze@gmail.com', 'swayze52!', '', 1),
       ('Bob', 'Dylan', 'bob.dylan@gmail.com', 'dylan41', '', 2);

insert into PRODUCTS (NAME, PRICE, PRODUCT_TYPE_ID, MARKET_ID, GM_ID)
values
       ('PHONE', 100.00, 1, 1, 1);