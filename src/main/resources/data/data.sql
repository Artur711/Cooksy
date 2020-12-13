insert into USER_TYPE (USER_TYPE_NAME)
values
       ('ADMINISTRATOR'),
       ('CUSTOMER');

insert into USERS (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PHOTO_URL, USER_TYPE_ID)
values
       ('Patrick', 'Swayze', 'p.swayze@gmail.com', 'swayze52!', '', 1),
       ('Bob', 'Dylan', 'bob.dylan@gmail.com', 'dylan41', '', 2);

insert into PRODUCTS (NAME, PRICE, PRODUCT_TYPE_ID, MARKET_ID, GM_ID)
values
       ('PHONE', 100.00, 1, 1, 1);

insert into PRODUCTS (NAME, PRICE, PRODUCT_TYPE_ID, MARKET_ID, GM_ID)
values
       ('Pepper', 5.82, 1, 1, 1),
       ('Tomato', 3.25, 2, 1, 1);



insert into recipes (description, photo_Url, author)
values
       ('bla bla', 'dasdasdas', 'Atos'),
       ('bla', 'dasdasdas', 'Atos');

insert into favorites (user_id, recipe_id) values (1,2);
insert into favorites (user_id, recipe_id)
values
       (1, 1);
-- insert into favorites_recipes (favorite_favorite_id, recipes_recipe_id) values (1,1);
-- insert into favorites_recipes (favorite_favorite_id, recipes_recipe_id) values (1,2);