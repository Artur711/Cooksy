insert into USER_TYPE (USER_TYPE_NAME)
values
       ('ADMINISTRATOR'),
       ('CUSTOMER');

insert into USERS (NICK, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PHOTO_URL, USER_TYPE_ID, ENABLED, LOCKED)
values
       ('patrick52', 'Patrick', 'Swayze', 'p.swayze@gmail.com', '$2a$10$J/KcJT8LYIz1B2ieLBy7hOARoZBxNnkQRgcxl/1mCTbJsPwmaG70q', '', 1, true, false),
       ('bob41', 'Bob', 'Dylan', 'bob.dylan@gmail.com', '$2a$10$4X0PT6XEid0wV2bseeuSieEipQsPMnV8WdN4X6AG4q1lK3lDonqsS', '', 2, true, false);

insert into Grammage (grammage_id, quantity, grammage)
values
       (1, 1, 'KG'),
       (2, 1, 'L'),
       (3, 1, 'PCS'),
       (4, 100, 'Gramme'),
       (5, 100, 'ml');

insert into PRODUCTS (PRODUCT_ID, NAME, PRICE, PRODUCT_TYPE_ID, MARKET_ID, GRAMMAGE_ID)
values
       (1, 'Paprika', 0.45, 1, 1, 3),
       (2, 'Tomato', 2.38, 2, 1, 1);

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

-- add classic lasagne
insert into products (product_id, market_id, name, price, product_type_id, grammage_id)
VALUES
       (14, 1, 'Smoked bacon', 2, 2 , 1),
       (15, 1, 'Celery', 0.43, 1, 3),
       (16, 1, 'Carrot', 0.04, 1, 3),
       (17, 1, 'Tomato purée', 1.98, 5, 1),
       (18, 1, 'Chopped tomatoes', 0.35, 6, 3),
       (19, 1, 'Clear honey', 0.30, 5, 4),
       (20, 1, 'Pack fresh egg lasagne sheets', 4.80, 5, 1),
       (21, 1, 'Crème', 0.37, 5, 5),
       (22, 1, 'Ball mozzarella', 12, 5, 1);

--add Thai fried prawn & pineapple rice
insert into products (product_id, market_id, name, price, product_type_id, grammage_id)
VALUES
       (23, 1, 'Sunflower oil', 1.10 , 1 , 1),
       (24, 1, 'Spring onions', 3.7, 1, 1),
       (25, 1, 'Pineapple', 5.20, 1, 1),
       (26, 1, 'Thai green curry paste', 0.75, 5, 4),
       (27, 1, 'Light soy sauce', 0.43, 5, 5),
       (28, 1, 'Rice', 1.45, 5, 1),
       (29, 1, 'Frozen peas', 2.5, 1, 1),
       (30, 1, 'Bamboo shoots', 9.17, 1, 1),
       (31, 1, 'Frozen prawns', 13.34, 6, 1),
       (32, 1, 'Lime', 0.3, 1, 3);

--add Polish dumplings
insert into products (product_id, market_id, name, price, product_type_id, grammage_id)
VALUES
       (33,1,' floury potatoes', 5, 5, 1),
       (34,1,'olive oil', 4, 5, 2),
       (35,1,'half-fat cottage cheese', 2, 5, 1),
       (36,1,'self-raising flour', 1, 5, 1),
       (37,1,'vegetable oil', 4, 5, 2),
       (38,1,'onion',1,1,3);

--add Vegetable stock
insert into products (product_id, market_id, name, price, product_type_id, grammage_id)
VALUES
       (39,1,'celery stalks',2,1,3),
       (40,1,'sprig fresh thyme',1,1,3),
       (41,1,'bay leaf',0.5,4,3),
       (42,1,'bunch fresh parsley',1,1,3),
       (43,1,'black peppercorns',2,4,1);

--wrapper polish dumplings
insert into recipe_product
values
       (37,0.25,33),
       (38,0.06,34),
       (39,1,38),
       (40,0.25,35),
       (41,0.25,36),
       (42,0.015,9),
       (43,0.015,37);

--wrapper vegetable stock
insert into recipe_product
values
       (44,2,38),
       (45,3,16),
       (46,4,39),
       (47,5,40),
       (48,1,41),
       (49,1,42),
       (50,0.015,43);


-- wrapper
insert into recipe_product
values
        (2, 0.5, 1),
        (3, 0.2, 3),
        (4, 0.02, 4),
        (5, 0.05, 5),
        (6, 0.5, 6),
        (7, 0.09, 7),
        (8, 0.01, 8),
        (9, 0.4, 2),
        (10, 0.01, 9),
        (11, 0.03, 1),
        (12, 0.35, 10),
        (13, 0.2, 11),
        (14, 2, 12),
        (15, 0.2, 13);

-- wraper classic lasange
insert into recipe_product
values
       (16, 0.05, 14),
       (17, 1, 15),
       (18, 1, 16),
       (19, 0.005, 17),
       (20, 2, 18),
       (21, 0.05, 19),
       (22, 0.05, 20),
       (23, 4, 21),
       (24, 0.125, 22),
       (25, 0.05, 11);

-- wraper Thai fried prawn & pineapple rice
insert into recipe_product
values
       (26, 0.02, 23),
       (27, 0.1, 24),
       (28, 1, 1),
       (29, 0.14, 25),
       (30, 0.1, 26),
       (31, 0.2, 27),
       (32, 0.3, 28),
       (33, 0.14, 29),
       (34, 0.225, 30),
       (35, 0.25, 31),
       (36, 3, 32);


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

    Give a final stir and serve the velvety scramble without delay.','Perfect scrambled eggs recipe', 'photo.url'),
    (3, 'BBC good food - Angela Boggiano', 'STEP 1
Heat the oil in a large saucepan. Use kitchen scissors to snip the bacon into small pieces, or use a sharp knife to chop it on a chopping board. Add the bacon to the pan and cook for just a few mins until starting to turn golden. Add the onion, celery and carrot, and cook over a medium heat for 5 mins, stirring occasionally, until softened.

STEP 2
Add the garlic and cook for 1 min, then tip in the mince and cook, stirring and breaking it up with a wooden spoon, for about 6 mins until browned all over.

STEP 3
Stir in the tomato purée and cook for 1 min, mixing in well with the beef and vegetables. Tip in the chopped tomatoes. Fill each can half full with water to rinse out any tomatoes left in the can, and add to the pan. Add the honey and season to taste. Simmer for 20 mins.

STEP 4
Heat oven to 200C/180C fan/gas 6. To assemble the lasagne, ladle a little of the ragu sauce into the bottom of the roasting tin or casserole dish, spreading the sauce all over the base. Place 2 sheets of lasagne on top of the sauce overlapping to make it fit, then repeat with more sauce and another layer of pasta. Repeat with a further 2 layers of sauce and pasta, finishing with a layer of pasta.

STEP 5
Put the crème fraîche in a bowl and mix with 2 tbsp water to loosen it and make a smooth pourable sauce. Pour this over the top of the pasta, then top with the mozzarella. Sprinkle Parmesan over the top and bake for 25–30 mins until golden and bubbling. Serve scattered with basil, if you like.',
     'Easy classic lasagne', '/image/Recipes img/Easy classic lasagne.webp'),
       (4, 'BBC good food- Good Food team', 'STEP 1
Heat the oil in a wok or non-stick frying pan and fry the spring onion whites for 2 mins until softened. Stir in the pepper for 1 min, followed by the pineapple for 1 min more, then stir in the green curry paste and soy sauce.

STEP 2
Add the rice, stir-frying until piping hot, then push the rice to one side of the pan and scramble the eggs on the other side. Stir the peas, bamboo shoots and prawns into the rice and eggs, then heat through for 2 mins until the prawns are hot and the peas tender. Finally, stir in the spring onion greens, lime juice and coriander, if using. Spoon into bowls and serve with extra lime wedges and soy sauce.',
        'Thai fried prawn & pineapple rice', '/image/Recipes img/Thai fried prawn & pineapple rice.webp'),
       (5,'Rafael Paszenda','For the cottage cheese and potato filling, boil the potatoes in a pan of salted water until soft enough to mash (about 20 minutes). Drain well and set aside to cool.

Heat the oil in a frying pan over a medium heat and fry the onions for 4-5 minutes, or until crisp and browned. Reserve a tablespoonful of the onions for the garnish.

        For the dumpling dough, sift the flour into a large mixing bowl and make a well in the centre. Fill the well with the salt, oil and water. Using your fingers, gradually stir the flour into the wet ingredients, until the mixture comes together as a soft dough.

Turn the dough out onto a lightly floured work surface and knead for 5-8 minutes, or until it is smooth and glossy. Wrap the dough in a clean tea towel and set aside to rest in a cool room for at least 20 minutes.

        When the potatoes have cooled, transfer them to a large bowl and crumble over the cottage cheese. Mash until smooth, then stir in the fried onions until well combined. Set aside.

        To shape the pierogi, roll out the dough onto a lightly floured surface to a thickness of 3mm. Cut 10cm/4in rounds from it using a pastry cutter.

Place one teaspoonful of the cottage cheese and potato filling into half of the pastry rounds, and one teaspoonsful of the mushroom and sauerkraut filling into the remaining pastry rounds. Brush a little water around the edge of each pastry round, then fold the edges together to create a bulging semi-circular dumpling, pressing the edges together to seal.

Poach the pierogi, in batches if necessary, in a deep-sided pan of boiling water for 3-4 minutes, or until they float to the surface.

        To serve, pile the pierogi onto serving plates and serve the soured cream in small bowls alongside. Sprinkle with the reserved fried onions and the dill.','Pierogi','photo.url'),
       (6,'Emma Christensen','Gather some vegetables and herbs. Onions, carrots, and celery give stock a great base flavor, and you can round these out with any of the other vegetables listed above. You can also make stock using any amount of vegetables that you happen to have on-hand, but it''s good to have a roughly equal portion of each so the resulting stock will have a balanced flavor.
Coarsely chop all the vegetables. Wash any visible dirt off the vegetables and give them a rough chop. You don''t even need to peel them first unless you really want to. (Some people even advocate leaving on the onion skins!) Throw all the vegetables in a pot big enough to hold them plus a few extra inches of water.
Cover with water and bring to a simmer. Cover the vegetables with enough water that you can easily stir them in the pot. Less water means that your stock will be more concentrated; more water makes a lighter-flavored stock. Set the pot over medium-high heat and bring it to just under a boil. Once you start to see some bubbling around the edges of the pot and a few wisps of steam on the surface, turn the heat down to medium-low.
Simmer for about 1 hour. This isn''t an exact science, but one hour is generally enough time to infuse the water with vegetable goodness. If you need to take it off the heat a little early or don''t get to it until a little later, it will be fine. Give it a stir every now and again to circulate the vegetables.
Strain and store. Take the pot off the stove and remove all the vegetables with a slotted spoon. Set a colander or strainer over a big bowl and line it with cheesecloth or coffee filters. Pour the stock through. If not using immediately, divide the stock into storage containers, cool completely, and then freeze.','Vegetable stock','photo.url');


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
        (2,15),
        (3, 4),
        (3, 14),
        (3, 3),
        (3, 15),
        (3, 16),
        (3, 5),
        (3, 6),
        (3, 17),
        (3, 18),
        (3, 19),
        (3, 20),
        (3, 21),
        (3, 22),
        (3, 11),
        (4, 26),
        (4, 27),
        (4, 28),
        (4, 29),
        (4, 30),
        (4, 31),
        (4, 32),
        (4, 14),
        (4, 33),
        (4, 34),
        (4, 35),
        (4, 36),
        (5, 37),
        (5, 38),
        (5, 39),
        (5, 40),
        (5, 41),
        (5, 42),
        (5, 43),
        (6, 44),
        (6, 45),
        (6, 46),
        (6, 47),
        (6, 48),
        (6, 49),
        (6, 50);


insert into favorites (user_id, recipe_id)
values
       (1,1),
       (1,2);
