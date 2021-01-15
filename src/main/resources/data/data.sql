insert into USER_TYPE (USER_TYPE_NAME)
values
       ('ADMINISTRATOR'),
       ('CUSTOMER');

insert into USERS (NICK, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, PHOTO_URL, USER_TYPE_ID, ENABLED, LOCKED)
values
       ('patrick52', 'Patrick', 'Swayze', 'p.swayze@gmail.com', '$2a$10$J/KcJT8LYIz1B2ieLBy7hOARoZBxNnkQRgcxl/1mCTbJsPwmaG70q', '', 1, true, false),
       ('bob41', 'Bob', 'Dylan', 'bob.dylan@gmail.com', '$2a$10$4X0PT6XEid0wV2bseeuSieEipQsPMnV8WdN4X6AG4q1lK3lDonqsS', '', 2, true, false);
