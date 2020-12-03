delete from FAVORITES;
delete from USERS;
delete from RECIPES;
delete from PRODUCTS;

insert into USERS (USER_ID, FIRST_NAME, LAST_NAME, USER_TYPE_ID)
values
       (1, 'Adam', 'Adamowski', 2),
       (2, 'Artur', 'Dobry', 1),
       (3,'Adam', 'Sławny', 2),
       (4, 'Bob', 'Dylan', 1),
       (5, 'Barbara', 'Fiszek', 1),
       (6, 'Alicja', 'Kowniniak', 2),
       (7, 'Bartłomiej', 'Fiszek', 2),
       (8, 'Anastazja', 'Bakcyl', 2),
       (9, 'Antoni', 'Donat', 1),
       (10, 'Beata', 'Chorman', 2);

insert into PRODUCTS (PRODUCT_ID, NAME, PRICE, PRODUCT_TYPE_ID, MARKET_ID, GM_ID)
values
       (1, 'Pepper', 5.82, 1, 1, 1),
       (2, 'Tomato', 3.25, 2, 1, 1);

insert into RECIPES (RECIPE_ID, PRODUCT_ID, description, photo_Url, author)
values
       (1, 1, 'Mexican chicken', 'dasdasdas', 'Atos'),
       (2, 2, 'Tomato chicken', 'dasdasdas', 'Atos');

insert into FAVORITES (FAVORITE_ID, USER_ID, RECIPE_ID)
values
       (1, 1, 1),
       (2, 1, 2),
       (3, 2, 1),
       (4, 2, 2),
       (5, 3, 1),
       (6, 3, 2),
       (7, 4, 1),
       (8, 4, 2),
       (9, 5, 1),
       (10, 5, 2);