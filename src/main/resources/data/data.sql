insert into USER_TYPE (USER_TYPE_NAME)
values
       ('ADMINISTRATOR'),
       ('CUSTOMER');

insert into USERS (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PHOTO_URL, USER_TYPE_ID)
values
       ('Patrick', 'Swayze', 'p.swayze@gmail.com', 'swayze52!', '', 1),
       ('Bob', 'Dylan', 'bob.dylan@gmail.com', 'dylan41', '', 2);

insert into Grammage (grammage_id, quantity, grammage) values (1, 1, 'KG');

-- insert into PRODUCTS (NAME, PRICE, PRODUCT_TYPE_ID, MARKET_ID, GRAMMAGE_ID)
-- values
--        ('PHONE', 100.00, 1, 1, 1);
--
--
insert into PRODUCTS (PRODUCT_ID, NAME, PRICE, PRODUCT_TYPE_ID, MARKET_ID, GRAMMAGE_ID)
values
       (1, 'Pepper', 5.82, 1, 1, 1),
       (2, 'Tomato', 3.25, 2, 1, 1);


-- wrapper
insert into recipe_product
values
        (1, 0.5, 2),
        (2, 0.5, 1);

insert into recipes (recipe_id, name, description, photo_Url, author)
values
       (1, 'Pepper & Tomato', 'bla bla', 'dasdasdas.url', 'Atos'),
       (2, 'chicken', 'bla', 'dasdasdas', 'Atos');

insert into recipe_composition
values
        (1,1),
        (1,2);

insert into favorites (user_id, recipe_id)
values
       (1, 1),
        (1,2);
