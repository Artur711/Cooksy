insert into USER_TYPE (USER_TYPE_NAME)
values
       ('ADMINISTRATOR'),
       ('CUSTOMER');

insert into USERS (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PHOTO_URL, USER_TYPE_ID)
values
       ('Patrick', 'Swayze', 'p.swayze@gmail.com', 'swayze52!', '', 1),
       ('Bob', 'Dylan', 'bob.dylan@gmail.com', 'dylan41', '', 2);

insert into Grammage (grammage_id, quantity, grammage)
values
        (1, 1, 'KG'),
        (2, 1, 'L'),
        (3, 1, 'PCS');

insert into PRODUCTS (PRODUCT_ID, NAME, PRICE, PRODUCT_TYPE_ID, MARKET_ID, GRAMMAGE_ID)
values
       (1, 'Pepper', 5.82, 1, 1, 1),
       (2, 'Tomato', 3.25, 2, 1, 1);

-- add spaghetti ingredients
insert into products (product_id, market_id, name, price, product_type_id, grammage_id)
VALUES
    (3, 1, 'Onion', 5, 1,1),
    (4, 1, 'Olive Oil', 20, 4, 2),
    (5, 1, 'Garlic', 34, 1, 1),
    (6, 1, 'Beef', 35, 2, 1),
    (7, 1, 'Mushrooms', 70, 1, 1),
    (8, 1, 'Oregano', 22, 4, 1),
    (9, 1, 'Salt', 4.61, 4, 1),
    (10, 1, 'Pasta Spaghetti', 12, 5, 1),
    (11, 1, 'Cheese Parmesan', 88, 5, 1),
    (12, 1, 'Egg', 0.75, 5, 3),
    (13, 1, 'Butter', 6, 5, 3);

-- wrapper
insert into recipe_product
values
        (2, 0.5, 1),
        (3, 0.2, 3),
        (4, 0.02, 4),
        (5, 0.05, 5),
        (6, 0.5, 6),
        (7,0.09, 7),
        (8,0.01,8),
        (9,0.4,2),
        (10,0.01,9),
        (11,0.03,1),
        (12,0.35,10),
        (13,0.2,11),
        (14,2,12),
        (15,0.2,13);

insert into recipes (recipe_id, author, description, name, photo_url)
VALUES
    (1, 'MD', 'Put the onion and oil in a large pan and fry over a fairly high heat for 3-4 mins. Add the garlic and mince and fry until they both brown. Add the mushrooms and herbs, and cook for another couple of mins.

    Stir in the tomatoes, beef stock, tomato ketchup or purée, Worcestershire sauce and seasoning. Bring to the boil, then reduce the heat, cover and simmer, stirring occasionally, for 30 mins.

    Meanwhile, cook the spaghetti in a large pan of boiling, salted water, according to packet instructions. Drain well, run hot water through it, put it back in the pan and add a dash of olive oil, if you like, then stir in the meat sauce. Serve in hot bowls and hand round Parmesan cheese, for sprinkling on top.
', 'Spaghetti Bolognese', 'photo.url'),
(2, 'MD', 'STEP 1

    Lightly whisk 2 large eggs, 6 tbsp single cream or full cream milk and a pinch of salt together until the mixture has just one consistency.
    STEP 2

    Heat a small non-stick frying pan for a minute or so, then add a knob of butter and let it melt. Don’t allow the butter to brown or it will discolour the eggs.
    STEP 3

    Pour in the egg mixture and let it sit, without stirring, for 20 seconds. Stir with a wooden spoon, lifting and folding it over from the bottom of the pan.
    STEP 4

    Let it sit for another 10 seconds then stir and fold again.
    STEP 5

    Repeat until the eggs are softly set and slightly runny in places. Remove from the heat and leave for a moment to finish cooking.
    STEP 6

    Give a final stir and serve the velvety scramble without delay.','Perfect scrambled eggs recipe', 'photo.url');


insert into recipe_composition
values

        (1,3),
        (1,4),
        (1,5),
        (1,6),
        (1,7),
        (1,8),
        (1,9),
        (1,10),
        (1,11),
        (1,12),
        (1,13),
        (2,14),
        (2,15);

insert into favorites (user_id, recipe_id)
values
       (1,1),
       (1,2);
