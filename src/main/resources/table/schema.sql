DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS chat_room;
DROP TABLE IF EXISTS chat_message;
DROP TABLE IF EXISTS connected_chat_room;

CREATE TABLE member
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    email      VARCHAR(255) NOT NULL UNIQUE,
    name       VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL
);

CREATE TABLE chat_room
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE chat_message
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    chat_room_id BIGINT,
    member_id    BIGINT,
    message      TEXT NOT NULL,
    created_at   DATETIME,
    updated_at   DATETIME,
    FOREIGN KEY (chat_room_id) REFERENCES chat_room (id),
    FOREIGN KEY (member_id) REFERENCES member (id)
);

CREATE TABLE connected_chat_room
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    member_id    BIGINT,
    chat_room_id BIGINT,
    FOREIGN KEY (member_id) REFERENCES member (id),
    FOREIGN KEY (chat_room_id) REFERENCES chat_room (id)
);