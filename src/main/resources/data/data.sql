insert into USER_TYPE (USER_TYPE_NAME) values ('ADMINISTRATOR');
insert into USER_TYPE (USER_TYPE_NAME) values ('CUSTOMER');

insert into USERS (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PHOTO_URL, USER_TYPE) values ('Patrick', 'Swayze', 'p.swayze@gmail.com', 'swayze52!', '', 1);
insert into USERS (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PHOTO_URL, USER_TYPE) values ('Bob', 'Dylan', 'bob.dylan@gmail.com', 'dylan41', '', 2);

insert into PRODUCTS (NAME, PRICE, PRODUCT_TYPE_ID, MARKET_ID, GM_ID) values ('PHONE', 100.00, 1, 1, 1);

insert into recipes (product_Id, description, photo_Url, author) values (1,'bla bla', 'dasdasdas', 'Atos');
insert into recipes (product_Id, description, photo_Url, author) values (2,'bla', 'dasdasdas', 'Atos');

-- insert into favorites (user_id, recipe_id) values (1,2);
insert into favorites (user_id, recipe_id) values (1,1);
-- insert into favorites_recipes (favorite_favorite_id, recipes_recipe_id) values (1,1);
-- insert into favorites_recipes (favorite_favorite_id, recipes_recipe_id) values (1,2);