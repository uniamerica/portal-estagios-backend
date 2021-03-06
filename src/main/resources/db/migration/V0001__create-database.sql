drop table if exists application_user CASCADE;

drop table if exists selection_process CASCADE;

drop table if exists selection_process_student CASCADE;

drop table if exists student CASCADE;

create table application_user (
                                  id bigint generated by default as identity,
                                  application_user_role varchar(255),
                                  email varchar(255),
                                  is_account_non_expired boolean not null,
                                  is_account_non_locked boolean not null,
                                  is_credentials_non_expired boolean not null,
                                  is_enabled boolean not null,
                                  password varchar(60),
                                  primary key (id)
);

create table selection_process (
                                   id bigint generated by default as identity,
                                   company_name varchar(255),
                                   status varchar(255),
                                   title varchar(255),
                                   primary key (id)
);

create table selection_process_student (
                                           selection_process_id bigint not null,
                                           student_id bigint not null,
                                           primary key (selection_process_id, student_id)
);

create table student (
                         id bigint generated by default as identity,
                         age tinyint,
                         first_name varchar(255),
                         last_name varchar(255),
                         photo varchar(255),
                         application_user_id bigint,
                         primary key (id)
);

alter table application_user
    add constraint UK_cb61p28hanadv7k0nx1ec0n5l unique (email);

alter table student
    add constraint UK_p4owi12tb5wdypate6t0vyoqi unique (application_user_id);

alter table selection_process_student
    add constraint FK3yx21o9d50avlmf3n7m9kav37
        foreign key (student_id)
            references student;

alter table selection_process_student
    add constraint FK49dn2exjjyoqv86b1renpna71
        foreign key (selection_process_id)
            references selection_process;

alter table student
    add constraint FKkk5yli3vhkgsn1pf7usywbkh0
        foreign key (application_user_id)
            references application_user;