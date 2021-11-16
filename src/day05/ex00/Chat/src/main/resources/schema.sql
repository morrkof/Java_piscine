DROP table IF EXISTS "User", "Chatroom", "Message";
CREATE table "User"
(
    ID                       serial not null primary key,
    LOGIN                    VARCHAR(100)   not null,
    PASSWORD                 VARCHAR(50),
    LIST_OF_CREATED_ROOMS    int[],
    LIST_OF_SOCIALIZED_ROOMS int[]
);

CREATE table "Chatroom"
(
    ID               serial not null primary key,
    NAME             text   not null,
    OWNER            int references "User" (id),
    LIST_OF_MESSAGES int[]
);

CREATE table "Message"
(
    ID     serial not null primary key,
    AUTHOR int references "User" (id),
    ROOM   int references "Chatroom" (id),
    TEXT   text,
    DATE   timestamptz
);
