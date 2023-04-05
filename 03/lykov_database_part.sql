-- создать базу данных “Друзья человека”
CREATE DATABASE human_friends;

USE human_friends;

/*
Создать и заполнить низкоуровневые таблицы именами(животных), командами
которые они выполняют и датами рождения
*/
CREATE TABLE animal_types 
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    animal_type VARCHAR(16)
);

INSERT INTO animal_types(animal_type) VALUES
('dog'), ('cat'), ('hamster'), ('horse'), ('donkey'), ('camel');

CREATE TABLE commands
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    command VARCHAR(32)
);

INSERT INTO commands(command) VALUES
('run'), ('voice'), ('play'), ('serve'), ('carry the load');

CREATE TABLE dogs
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    animal_type INT NOT NULL DEFAULT 1,
    animal_name VARCHAR(32) NOT NULL,
    date_of_birth DATE NOT NULL
    /* выполняемые команды в отдельной таблице can_perform со связью many-to-many */
);

INSERT INTO dogs(animal_name, date_of_birth) VALUES
('Рекс', '2019-01-02'), ('Тузик', '2020-02-03'), ('Шарик', '2021-03-04');

CREATE TABLE cats
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    animal_type INT NOT NULL DEFAULT 2,
    animal_name VARCHAR(32) NOT NULL,
    date_of_birth DATE NOT NULL
    /* выполняемые команды в отдельной таблице can_perform со связью many-to-many */
);

INSERT INTO cats(animal_name, date_of_birth) VALUES
('Васька', '2021-03-04'), ('Мурзик', '2020-04-05'), ('Дуся', '2022-05-06');

CREATE TABLE hamsters
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    animal_type INT NOT NULL DEFAULT 3,
    animal_name VARCHAR(32) NOT NULL,
    date_of_birth DATE NOT NULL
    /* выполняемые команды в отдельной таблице can_perform со связью many-to-many */
);

INSERT INTO hamsters(animal_name, date_of_birth) VALUES
('Грызь', '2020-03-04'), ('Соня', '2021-04-05'), ('Комочек', '2022-05-06');

CREATE TABLE horses
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    animal_type INT NOT NULL DEFAULT 4,
    animal_name VARCHAR(32) NOT NULL,
    date_of_birth DATE NOT NULL
    /* выполняемые команды в отдельной таблице can_perform со связью many-to-many */
);

INSERT INTO horses(animal_name, date_of_birth) VALUES
('Звездочка', '2019-03-04'), ('Боливар', '2018-04-05'), ('Буцефал', '2020-05-06');

CREATE TABLE donkeys
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    animal_type INT NOT NULL DEFAULT 5,
    animal_name VARCHAR(32) NOT NULL,
    date_of_birth DATE NOT NULL
    /* выполняемые команды в отдельной таблице can_perform со связью many-to-many */
);

INSERT INTO donkeys(animal_name, date_of_birth) VALUES
('Шанель', '2021-03-04'), ('Юлий', '2020-04-05'), ('Трейси', '2019-05-06');

CREATE TABLE camels
(
	id INT PRIMARY KEY AUTO_INCREMENT,
    animal_type INT NOT NULL DEFAULT 6,
    animal_name VARCHAR(32) NOT NULL,
    date_of_birth DATE NOT NULL
    /* выполняемые команды в отдельной таблице can_perform со связью many-to-many */
);

INSERT INTO camels(animal_name, date_of_birth) VALUES
('Али', '2020-03-04'), ('Шехерезада', '2019-04-05'), ('Султан', '2018-05-06');

CREATE TABLE can_perform
(
	type_id INT NOT NULL,
    animal_id INT NOT NULL,
    command_id INT NOT NULL,
    CONSTRAINT fk_type_perform
	FOREIGN KEY (type_id) REFERENCES animal_types(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    CONSTRAINT fk_command_perform
	FOREIGN KEY (command_id) REFERENCES commands(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    PRIMARY KEY(type_id, animal_id, command_id)
);

/*
Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.
*/
CREATE TABLE pack_animals
SELECT animal_type, animal_name, date_of_birth FROM horses
UNION
SELECT animal_type, animal_name, date_of_birth FROM donkeys
UNION
SELECT animal_type, animal_name, date_of_birth FROM camels; 

ALTER TABLE pack_animals
ADD COLUMN id INT PRIMARY KEY AUTO_INCREMENT FIRST;

DELETE FROM pack_animals
WHERE animal_type = 6; /*верблюды*/

/*
Создать новую таблицу “молодые животные” в которую попадут все
животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
до месяца подсчитать возраст животных в новой таблице
*/
CREATE TABLE young_animals
SELECT animal_type, animal_name, (YEAR(CURRENT_DATE()) - YEAR(date_of_birth)) AS age FROM dogs
HAVING age < 3 AND age > 1
UNION
SELECT animal_type, animal_name, (YEAR(CURRENT_DATE()) - YEAR(date_of_birth)) AS age FROM cats
HAVING age < 3 AND age > 1
UNION
SELECT animal_type, animal_name, (YEAR(CURRENT_DATE()) - YEAR(date_of_birth)) AS age FROM hamsters
HAVING age < 3 AND age > 1
UNION
SELECT animal_type, animal_name, (YEAR(CURRENT_DATE()) - YEAR(date_of_birth)) AS age FROM pack_animals
HAVING age < 3 AND age > 1;

/*
Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
прошлую принадлежность к старым таблицам.
*/
CREATE TABLE all_animals
SELECT animal_type, animal_name, date_of_birth FROM dogs
UNION
SELECT animal_type, animal_name, date_of_birth FROM cats
UNION
SELECT animal_type, animal_name, date_of_birth FROM hamsters
UNION
SELECT animal_type, animal_name, date_of_birth FROM pack_animals
UNION
SELECT animal_type, animal_name, date_of_birth FROM camels;

ALTER TABLE all_animals
ADD COLUMN id INT PRIMARY KEY AUTO_INCREMENT FIRST; 









