INSERT INTO "User" (login, password, list_of_created_rooms, list_of_socialized_rooms)
values ('user1', 'qwerty', '{2}', '{1, 2, 3, 4}'),
       ('user2', '12345', '{1, 3}', '{1, 3}'),
       ('user3', 'qwe', '{}', '{1, 2, 3, 4, 5}'),
       ('user4', 'password', '{5}', '{3, 4}'),
       ('user5', 'kqrh7w4!sf', '{4}', '{1, 2, 3, 4, 5}');

INSERT INTO "Chatroom" (name, owner, list_of_messages)
values ('chat1', '2', '{1, 2}'),
       ('chat2', '1', '{3, 4}'),
       ('chat3', '2', '{5, 6}'),
       ('chat4', '5', '{7, 8}'),
       ('chat5', '4', '{1, 2, 3, 4, 5, 6, 7, 8}');

INSERT INTO "Message" (author, room, text, date)
values ('2', '1', 'Warrior Struggling To remain Consequential', current_timestamp),
       ('2', '2', 'I must keep reminding myself of this.', current_timestamp),
       ('1', '3', 'See, I think drugs have done some good things for us, I really do', current_timestamp),
       ('1', '4', 'I''ve been crawling on my belly', current_timestamp),
       ('4', '4', 'Eleven and she was gone.', current_timestamp),
       ('5', '5', 'Liar, lawyer, mirror, show me what''s the difference?', current_timestamp),
       ('3', '1', 'Monkey killing monkey killing monkey over pieces of the ground', current_timestamp),
       ('3', '3', '10,000 days in the fire is long enough. You''re going home...', current_timestamp);

