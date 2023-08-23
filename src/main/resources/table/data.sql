-- -- 테스트 DML

INSERT INTO member (email, name, password)
VALUES
    ('user@test.com', '유저A', '$2a$10$wMXNvfMMkAmIaOHy7NbE4OTqKz0F12tNe1xulO06oHdcA40p4c8Te'),
    ('admin@test.com', '유저B', '$2a$10$wMXNvfMMkAmIaOHy7NbE4OTqKz0F12tNe1xulO06oHdcA40p4c8Te')
;