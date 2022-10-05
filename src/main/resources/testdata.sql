INSERT INTO Address (street, additional_info, city_info_id) VALUES ('Selmersvej','1A','619');
INSERT INTO Address (street, additional_info, city_info_id) VALUES ('Baunevang','7','645');
INSERT INTO Address (street, additional_info, city_info_id) VALUES ('Ålholmevej','54','977');
INSERT INTO Address (street, additional_info, city_info_id) VALUES ('Møllevænget','2','836');
INSERT INTO Hobby (name, description) VALUES ('Bagning','Generel Indendørs');
INSERT INTO Hobby (name, description) VALUES ('Kortspil','Generel Indendørs');
INSERT INTO Hobby (name, description) VALUES ('Tegning','Generel Indendørs');
INSERT INTO Hobby (name, description) VALUES ('Tennis','Generel Udendørs');
INSERT INTO Hobby (name, description) VALUES ('Parkour','Generel Udendørs');
INSERT INTO Person (firstName, lastName, email, address_id) VALUES ('Caroline','Lauritsen','CarolineLauritsen@email.com','1');
INSERT INTO Person (firstName, lastName, email, address_id) VALUES ('Jeppe','Mathiesen','JeppeMathiesen@email.com','3');
INSERT INTO Person (firstName, lastName, email, address_id) VALUES ('Benjamin','Schultz','BenjaminSchultz@email.com','4');
INSERT INTO Person (firstName, lastName, email, address_id) VALUES ('Jonas','Dam','JonasDamn@email.com','4');
INSERT INTO Person (firstName, lastName, email, address_id) VALUES ('Nicklas','Larsen','NicklasLarsen@email.com','1');
INSERT INTO Phone (number, description, person_id) VALUES ('29936116','Mobil','1');
INSERT INTO Phone (number, description, person_id) VALUES ('41527312','Mobil','2');
INSERT INTO Phone (number, description, person_id) VALUES ('20853039','Hjemme tlf.','2');
INSERT INTO Phone (number, description, person_id) VALUES ('61690741','Mobil','4');
INSERT INTO Phone (number, description, person_id) VALUES ('30845335','Mobil','5');
INSERT INTO Person_Hobby (person_id, hobby_id) VALUES ('1','2');
INSERT INTO Person_Hobby (person_id, hobby_id) VALUES ('1','4');
INSERT INTO Person_Hobby (person_id, hobby_id) VALUES ('2','1');
INSERT INTO Person_Hobby (person_id, hobby_id) VALUES ('2','5');
INSERT INTO Person_Hobby (person_id, hobby_id) VALUES ('3','4');
INSERT INTO Person_Hobby (person_id, hobby_id) VALUES ('3','2');
INSERT INTO Person_Hobby (person_id, hobby_id) VALUES ('4','4');
INSERT INTO Person_Hobby (person_id, hobby_id) VALUES ('5','1');
INSERT INTO Person_Hobby (person_id, hobby_id) VALUES ('5','2');
INSERT INTO Person_Hobby (person_id, hobby_id) VALUES ('5','3');
INSERT INTO Person_Hobby (person_id, hobby_id) VALUES ('5','4');
INSERT INTO Person_Hobby (person_id, hobby_id) VALUES ('5','5');