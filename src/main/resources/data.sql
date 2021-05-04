use partycipate;
INSERT INTO role VALUES (1,'ROLE_USER'), (69, 'ROLE_ADMIN');
INSERT INTO user (id,email, password, username) VALUES (1, 'cipate@gmail.com', '940mIzYc', 'TheChosenOne1');
INSERT INTO user (id,email, password, username) VALUES (2, 'bruno@gmail.com', 'IzYc940m', 'Sunny2');
INSERT INTO user (id,email, password, username) VALUES (3, 'luna@gmail.com', 'Ic940zYm', 'Moon3');

INSERT INTO participant (id,  cookie,  region) VALUES (1,  'kEk5', 'NAM');
INSERT INTO participant (id,  cookie,region) VALUES (2,  'kEk5',  'EUW');
INSERT INTO participant (id,  cookie,  region) VALUES (3, 'kEk5', 'APC');

INSERT INTO survey (id,  creation_date, title, user_id) VALUES (1,  '2021-03-11', 'Ice Cream', 1);
INSERT INTO survey (id,  creation_date, title, user_id) VALUES (2,  '2021-03-12', 'Car brand', 2);

INSERT INTO survey_element (id, may_skip, position, question, type, survey_id) VALUES (1, true, 1, 'What is your favourite ice cream?', 'singlechoice', 1);
INSERT INTO survey_element (id, may_skip, position, question, type, survey_id) VALUES (2, false, 1, 'What is your favourite car brand?', 'singlechoice', 1);
INSERT INTO survey_element (id, may_skip, position, question, type, survey_id) VALUES (3, true, 1, 'What is your shoes size?', 'singlechoice', 2);

INSERT INTO answer_possibility (id, answer, position, survey_element_id) VALUES (1, 'Strawberry', 1, 1);
INSERT INTO answer_possibility (id, answer, position, survey_element_id) VALUES (2, 'Chocolate',2, 1);
INSERT INTO answer_possibility (id, answer, position, survey_element_id) VALUES (3, 'VW', 1, 2);
INSERT INTO answer_possibility (id, answer, position, survey_element_id) VALUES (4, 'BMW',2, 2);
INSERT INTO answer_possibility (id, answer, position, survey_element_id) VALUES (5, '42',1, 3);
INSERT INTO answer_possibility (id, answer, position, survey_element_id) VALUES (6, '43',2, 3);

INSERT INTO answer (id, participant_id, survey_element_id) VALUES (1, 1, 1);
INSERT INTO answer (id, participant_id, survey_element_id) VALUES (2, 1, 2);
INSERT INTO answer (id, participant_id, survey_element_id) VALUES (3, 2, 1);
INSERT INTO answer (id, participant_id, survey_element_id) VALUES (4, 2, 2);
INSERT INTO answer (id, participant_id, survey_element_id) VALUES (5, 3, 1);

INSERT INTO mcanswer_content (id, answer_id, answer_possibility_id) VALUES (1, 1, 1);
INSERT INTO mcanswer_content (id, answer_id, answer_possibility_id) VALUES (2, 2, 3);
INSERT INTO mcanswer_content (id, answer_id, answer_possibility_id) VALUES (3, 3, 1);
INSERT INTO mcanswer_content (id, answer_id, answer_possibility_id) VALUES (4, 4, 4);
INSERT INTO mcanswer_content (id, answer_id, answer_possibility_id) VALUES (5, 5, 2);



