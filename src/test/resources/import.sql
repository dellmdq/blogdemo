INSERT INTO `user` (user_name , password, first_name, last_name, email, delete_user_at)
VALUES
('dellmdq', ' $2a$12$dFOMkHfn4oSvA2kVlAZPnOcNa6uJjokuV7i3JUaFTg3hJaXYdoQce ', 'Erik', 'Dell', 'erikdell@gmail.com', null),
('federiver', ' $2a$12$zLNJg21.bW8kFlDH1GrPi.YkGJbDPBZDfrf3PrDjBhwoY.qPuXgdu ', 'Fede', 'Guevara', 'fedeguevara@gmail.com', '2021-11-23 16:10:33'),
('florgomez', ' $2a$12$ekYoFJlejrY4pPq7RuUzIe9Pa8qo5vtZkABPylVkqb/yD1.4ADAN. ', 'Flor', 'Gomez', 'florgomez@gmail.com',null);

INSERT INTO `category` (title)
VALUES
('Software'),
('Deportes'),
('Espectaculos');

INSERT INTO `post` (title, message, image, creation_date, delete_at, category_id, user_id)
VALUES
('Blogdemo Alkemy 3', 'Comienza una nueva aceleración de Java', 'https://previews.123rf.com/images/4zevar/4zevar1904/4zevar190400078/120512684-email-or-sms-email-and-incoming-messages-concept-vector.jpg', '2021-11-02 12:15:23', null, 1, 1),
('Scrum Alkemy Course', 'Introducción a las metodologias agiles', 'https://previews.123rf.com/images/4zevar/4zevar1904/4zevar190400078/120512684-email-or-sms-email-and-incoming-messages-concept-vector.jpg', '2021-11-22 17:30:00', null, 1, 3),
('Super clásico del Domigo', 'Juega Riiiiiiveeeeerrrr', 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/CA_river_plate_logo.svg/192px-CA_river_plate_logo.svg.png', '2020-10-03 16:15:30', null, 2, 2);
