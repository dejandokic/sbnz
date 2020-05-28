TRUNCATE TABLE users;

INSERT INTO users
    (dtype, id, email, fullname, password, username)
VALUES
    ('Admin', 1, 'admin@gmail.com', 'Admin Admin', '$2a$10$eRK7zIEQo1aMk9E8D3hXqOwXANtp0yU0QLVnJoAYCBp/Qgjt12A2q', 'admin'),
    ('RegisteredUser', 2, 'user@gmail.com', 'User User', '$2a$10$eRK7zIEQo1aMk9E8D3hXqOwXANtp0yU0QLVnJoAYCBp/Qgjt12A2q', 'user');

INSERT INTO user_role
VALUES
    (1, 'ADMIN'),
    (2, 'REGISTERED_USER');