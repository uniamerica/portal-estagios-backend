insert into application_user
(application_user_role, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled,
 password)
values ('ROLE_STUDENT', 'alexandre@gmail.com', 'true', 'true', 'true', 'true',
        '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS');

insert into application_user
(application_user_role, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled,
 password)
values ('ROLE_STUDENT', 'bruno@gmail.com', 'true', 'true', 'true', 'true',
        '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS');

insert into application_user
(application_user_role, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled,
 password)
values ('ROLE_STUDENT', 'thaina@gmail.com', 'true', 'true', 'true', 'true',
        '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS');

insert into application_user
(application_user_role, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled,
 password)
values ('ROLE_STUDENT', 'vinicius@gmail.com', 'true', 'true', 'true', 'true',
        '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS');

insert into application_user
(application_user_role, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled,
 password)
values ('ROLE_COMPANY', 'company@gmail.com', 'true', 'true', 'true', 'true',
        '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS');

insert into application_user
(application_user_role, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled,
 password)
values ('ROLE_ADMIN', 'admin@gmail.com', 'true', 'true', 'true', 'true',
        '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS');

insert into application_user
(application_user_role, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled,
 password)
values ('ROLE_STUDENT', 'user@gmail.com', 'true', 'true', 'true', 'true',
        '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS');

insert into application_user
(application_user_role, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled,
 password)
values ('ROLE_EDUCATIONAL_INSTITUTION', 'uniamerica@gmail.com', 'true', 'true', 'true', 'true',
        '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS');


insert into student (age, application_user_id, first_name, last_name, photo)
values ('35', '1', 'Alexandre', 'Zanlorenzi', 'https://avatars.githubusercontent.com/u/63269650?v=4'),
       ('26', '2', 'Bruno', 'Segatto', 'https://avatars.githubusercontent.com/u/24920256?v=4'),
       ('25', '3', 'Thaina', 'Chagas', ' https://avatars.githubusercontent.com/u/65509827?v=4'),
       ('23', '4', 'Vinicius', 'Oliveira', 'https://avatars.githubusercontent.com/u/14930948?v=4');


insert into selection_process (id, company_name, title, status)
values (1, 'Itai','Processo Seletivo do ITAI', 'PENDENTE'),
       (2, 'PTI','Processo Seletivo do PTI', 'ABERTO'),
       (3, 'Uniamerica','Processo Seletivo da Uniamerica', 'ABERTO'),
       (4, 'Itaipu','Processo Seletivo da Itaipu', 'ABERTO');

insert into selection_process_student (selection_process_id, student_id)
values ('1', '1'),
       ('2', '1'),
       ('3', '1'),
       ('3', '2'),
       ('2', '4'),
       ('4', '3'),
       ('4', '4');