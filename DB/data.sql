# user_authorization table
INSERT INTO `periodicals_system`.`user_authorization` (`email`,`password`,`role`,`status`,`balance`) VALUES('admin@gmail.com','1','admin','active',120.25);
INSERT INTO `periodicals_system`.`user_authorization` (`email`,`password`,`role`,`status`,`balance`) VALUES('user@gmail.com','2','user','active',50.24);

# user_details table
INSERT INTO `periodicals_system`.`user_details` (`user_authorization_email`,`first_name`,`last_name`,`delivery_address`,`telephone`) VALUES ('admin@gmail.com','Petro','Petrenko','місто Київ проспект Лобоновського 50 квартира 22',80632458965);
INSERT INTO `periodicals_system`.`user_details` (`user_authorization_email`,`first_name`,`last_name`,`delivery_address`,`telephone`) VALUES ('user@gmail.com','Stepan','Stepanenko','місто Київ проспект Лобоновського 24 квартира 15',80985648218);

# topic table
INSERT INTO `periodicals_system`.`topic` (`name`) VALUES ('Бізнес');
INSERT INTO `periodicals_system`.`topic` (`name`) VALUES ('Дитяча література');
INSERT INTO `periodicals_system`.`topic` (`name`) VALUES ('Наукові видання');

# publication table
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('01154','Екологiчний вiсник','Екологiчний вiсник','укр.',90.66,'ЕКОЛОГIЧНИЙ ВIСНИК.png',3);
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('09732','Мистецтво та освiта','Мистецтво та освiта','укр.',108.66,'МИСТЕЦТВО ТА ОСВIТА.png',3);
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('48409','Нумізматика і фалеристика','Нумізматика і фалеристика','укр.',127.37,'Нумізматика і фалеристика.png',3);
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('90853','Проблеми програмування','Проблеми програмування. PROBLEMS IN PROGRAMMING','укр.,англ.',45.78,'Проблеми програмування. Problems in Programming.png',3);

INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('01203','Веселi уроки','ВЕСЕЛI УРОКИ  JOLLY LESSONS','укр.,англ.',44.00,'ВЕСЕЛI УРОКИ \' JOLLY LESSONS.png',2);
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('97799','Їжачок','Розважально-пізнавальне видання для розумних допитливих дітей. що полюбляють головоломки. кросворди.','укр.',20.52,'Їжачок.png',2);
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('89490','Кузя','Безліч головоломок. пізнавальних історій та іншої цікавої інформації українською мовою для дітей','укр.',28.00 ,'Кузя.png',2);
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('76048','Непосида','НЕПОСИДА','укр.',58.68,'Непосида.png',2);

INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('01158','Кадровик.юа','КАДРОВИК.ЮА','укр.',718.07,'КАДРОВИК.ЮА.png',1);
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('60724','Метрологія для підприємства','Метрологія для підприємства','укр.',795.78,'Метрологія для підприємства.png',1);
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('49609','Охорона праці і пожежна безпека','Охорона праці і пожежна безпека','укр.',599.07,'Охорона праці і пожежна безпека.png',1);
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('86555','Фдк. фінансовий директор компанії','ФДК. Фінансовий директор компанії','укр.',550.66,'ФДК. Фінансовий директор компанії.png',1);

# payment table
INSERT INTO `periodicals_system`.`payment`(`user_authorization_email`,`total_price`) VALUES('user@gmail.com',268.25);
INSERT INTO `periodicals_system`.`payment`(`user_authorization_email`,`total_price`) VALUES('user@gmail.com',890.25);

# subscription table
INSERT INTO `periodicals_system`.`subscription`(`user_authorization_email`,`publication_index`,`payment_id`,`status`, `expired_at`)
VALUES('user@gmail.com','09732',1,'active','2024-01-24 18:09:08');
INSERT INTO `periodicals_system`.`subscription`(`user_authorization_email`,`publication_index`,`payment_id`,`status`,`expired_at`)
VALUES('user@gmail.com','76048',2,'active','2024-01-24 18:09:08');