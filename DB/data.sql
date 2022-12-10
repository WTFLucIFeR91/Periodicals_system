# user_authorization table
INSERT INTO `periodicals_system`.`user_authorization` (`email`,`password`,`role`,`status`,`balance`) VALUES('admin@gmail.com','1','admin','active',120.25);
INSERT INTO `periodicals_system`.`user_authorization` (`email`,`password`,`role`,`status`,`balance`) VALUES('user@gmail.com','2','user','active',50.24);

# user_details table
INSERT INTO `periodicals_system`.`user_details` (`user_authorization_email`,`first_name`,`last_name`,`delivery_address`,`telephone`) VALUES ('admin@gmail.com','Petro','Petrenko','місто Київ проспект Лобоновського 50 квартира 22',80632458965);
INSERT INTO `periodicals_system`.`user_details` (`user_authorization_email`,`first_name`,`last_name`,`delivery_address`,`telephone`) VALUES ('user@gmail.com','Stepan','Stepanenko','місто Київ проспект Лобоновського 24 квартира 15',80985648218);

# topic table
INSERT INTO `periodicals_system`.`topic` (`name`) VALUES ('business');
INSERT INTO `periodicals_system`.`topic` (`name`) VALUES ('Children publications');
INSERT INTO `periodicals_system`.`topic` (`name`) VALUES ('Scientifically publications');

# publication table
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('01154','ЕКОЛОГIЧНИЙ ВIСНИК','ЕКОЛОГIЧНИЙ ВIСНИК','укр.',90.66,'',3);
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('09732','МИСТЕЦТВО ТА ОСВIТА','МИСТЕЦТВО ТА ОСВIТА','укр.',108.66,'',3);
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('48409','Нумізматика і фалеристика','НУМIЗМАТИКА I ФАЛЕРИСТИКА','укр.',127.37,'',3);
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('90853','Проблеми програмування','ПРОБЛЕМИ ПРОГРАМУВАННЯ. PROBLEMS IN PROGRAMMING','укр.,англ.',45.78,'',3);

INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('01203','ВЕСЕЛI УРОКИ','ВЕСЕЛI УРОКИ  JOLLY LESSONS','укр.,англ.',44.00,'',2);
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('97799','Їжачок','Розважально-пізнавальне видання для розумних допитливих дітей. що полюбляють головоломки. кросворди.','укр.',20.52,'',2);
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('89490','Кузя','Безліч головоломок. пізнавальних історій та іншої цікавої інформації українською мовою для дітей','укр.',28.00 ,'',2);
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('76048','Непосида','НЕПОСИДА','укр.',58.68,'',2);

INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('01158','КАДРОВИК.ЮА','КАДРОВИК.ЮА','укр.',718.07,'',1);
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('60724','Метрологія для підприємства','Метрологія для підприємства','укр.',795.78,'',1);
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('49609','Охорона праці і пожежна безпека','Охорона праці і пожежна безпека','укр.',599.07,'',1);
INSERT INTO `periodicals_system`.`publication` (`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES
('86555','ФДК. Фінансовий директор компанії','ФДК. Фінансовий директор компанії','укр.',550.66,'',1);

# payment table
INSERT INTO `periodicals_system`.`payment`(`user_authorization_email`,`total_price`) VALUES('user@gmail.com',268.25);
INSERT INTO `periodicals_system`.`payment`(`user_authorization_email`,`total_price`) VALUES('user@gmail.com',890.25);

# subscription table
INSERT INTO `periodicals_system`.`subscription`(`user_authorization_email`,`publication_index`,`payment_id`,`status`)
VALUES('user@gmail.com','09732',1,'active');
INSERT INTO `periodicals_system`.`subscription`(`user_authorization_email`,`publication_index`,`payment_id`,`status`)
VALUES('user@gmail.com','76048',2,'active');