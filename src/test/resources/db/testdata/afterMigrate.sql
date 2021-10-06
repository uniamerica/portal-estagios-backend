delete from selection_process_student;

delete from selection_process;

delete from student;

delete from application_user;

insert into application_user
(id, application_user_role, email, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled,
 password)
values (1, 'ROLE_STUDENT', 'alexandre@gmail.com', 'true', 'true', 'true', 'true',
        '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS'),

       (2, 'ROLE_STUDENT', 'bruno@gmail.com', 'true', 'true', 'true', 'true',
        '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS'),

       (3, 'ROLE_STUDENT', 'thaina@gmail.com', 'true', 'true', 'true', 'true',
        '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS'),

       (4, 'ROLE_STUDENT', 'vinicius@gmail.com', 'true', 'true', 'true', 'true',
        '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS'),

       (5, 'ROLE_COMPANY', 'company@gmail.com', 'true', 'true', 'true', 'true',
        '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS'),

       (6, 'ROLE_ADMIN', 'admin@gmail.com', 'true', 'true', 'true', 'true',
        '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS'),

       (7, 'ROLE_STUDENT', 'teste@teste.com', 'true', 'true', 'true', 'true',
        '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS'),

       (8, 'ROLE_EDUCATIONAL_INSTITUTION', 'uniamerica@gmail.com', 'true', 'true', 'true', 'true',
        '$2a$10$0j5hRwkkXuzQ0.H1sT42GOzJMZT66tvCisohRFR7T3eyGFvAM2.yS');


insert into student (id, age, application_user_id, first_name, last_name, photo)
values (1, '35', '1', 'Alexandre', 'Zanlorenzi', 'https://avatars.githubusercontent.com/u/63269650?v=4'),
       (2, '26', '2', 'Bruno', 'Segatto', 'https://avatars.githubusercontent.com/u/24920256?v=4'),
       (3, '25', '3', 'Thaina', 'Chagas', ' https://avatars.githubusercontent.com/u/65509827?v=4'),
       (4, '23', '4', 'Vinicius', 'Oliveira', 'https://avatars.githubusercontent.com/u/14930948?v=4'),
       (5, '18', '7', null, null, null);


insert into selection_process (id, company_name, title, status)
values (1, 'Itai', 'Processo Seletivo do ITAI', 'PENDENTE'),
       (2, 'PTI', 'Processo Seletivo do PTI', 'ABERTO'),
       (3, 'Uniamerica', 'Processo Seletivo da Uniamerica', 'ABERTO'),
       (4, 'Itaipu', 'Processo Seletivo da Itaipu', 'ABERTO');

insert into selection_process_student (selection_process_id, student_id)
values ('2', '1'),
       ('3', '1'),
       ('3', '2'),
       ('2', '4'),
       ('4', '3'),
       ('4', '4');