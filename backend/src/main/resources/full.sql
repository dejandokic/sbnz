TRUNCATE TABLE users;

INSERT INTO `user_role` VALUES (1,'REGISTERED_USER'),(2,'ADMIN');
INSERT INTO `users` VALUES
('ADMIN',1,'admin@gmail.com','Admin','Admin','$2a$10$ObQo8pFNPlUgewUjIaBUju86eJcSnuSvQp1VGPgu3ri09tv2JMEmm','admin'),
('REGISTERED_USER',2,'doxa@gmail.com','The','Doxa','$2a$10$Rwa91/5rJdbEshaAmSCsuOvBsSIJNqtJWalT92i78rJzzI5DqeLHS','user');
