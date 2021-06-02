
insert into application_user
(application_user_role, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password)
values
('ROLE_STUDENT', 'alexandre@gmail.com', 'true', 'true', 'true', 'true', '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS');

insert into application_user
(application_user_role, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password)
values
('ROLE_STUDENT', 'bruno@gmail.com', 'true', 'true', 'true', 'true', '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS');

insert into application_user
(application_user_role, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password)
values
('ROLE_STUDENT', 'thaina@gmail.com', 'true', 'true', 'true', 'true', '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS');

insert into application_user
(application_user_role, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password)
values
('ROLE_STUDENT', 'vinicius@gmail.com', 'true', 'true', 'true', 'true', '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS');

insert into application_user
(application_user_role, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password)
values
('ROLE_COMPANY', 'company@gmail.com', 'true', 'true', 'true', 'true', '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS');

insert into application_user
(application_user_role, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password)
values
('ROLE_ADMIN', 'admin@gmail.com', 'true', 'true', 'true', 'true', '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS');

insert into application_user
(application_user_role, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password)
values
('ROLE_EDUCATIONAL_INSTITUTION', 'uniamerica@gmail.com', 'true', 'true', 'true', 'true', '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS');


insert into student
(age, application_user_id, first_name, last_name)
values
('35', '1', 'Alexandre', 'Zanlorenzi');

insert into student
(application_user_id, first_name, last_name)
values
('2', 'Bruno', 'Segatto');

insert into student
(application_user_id, first_name, last_name)
values
('3', 'Thaina', 'Chagas');

insert into student
(application_user_id, first_name, last_name)
values
('4', 'Vinicius', 'Oliveira');

insert into selection_process
(title, status)
values
('Processo Seletivo do ITAI', 'PENDENTE');

insert into selection_process
(title, status)
values
('Processo Seletivo do PTI', 'PENDENTE');

insert into selection_process
(title, status)
values
('Processo Seletivo da Uniamerica', 'PENDENTE');

insert into selection_process
(title, status)
values
('Processo Seletivo da Itaipu', 'PENDENTE');

insert into selection_process_student
(selection_process_id, student_id)
values
('1', '1');

insert into selection_process_student
(selection_process_id, student_id)
values
('2', '1');

insert into selection_process_student
(selection_process_id, student_id)
values
('3', '1');

insert into selection_process_student
(selection_process_id, student_id)
values
('4', '1');

insert into selection_process_student
(selection_process_id, student_id)
values
('3', '2');

insert into selection_process_student
(selection_process_id, student_id)
values
('2', '4');

insert into selection_process_student
(selection_process_id, student_id)
values
('4', '3');

insert into selection_process_student
(selection_process_id, student_id)
values
('4', '4');