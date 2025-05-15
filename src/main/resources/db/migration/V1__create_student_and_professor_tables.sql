create sequence student_id_seq start with 1 increment by 50;
create sequence professor_id_seq start with 1 increment by 50;


-- Table: student
create table student
(
    id             bigint not null default nextval('student_id_seq'),
    gender         text not null,                              -- Enum for gender
    faculty        text not null,
    academic_year  text not null,
    mode_of_study  text not null,                          -- Enum for study mode
    created_at     timestamp not null,
    primary key (id)
);


-- Table: professor
create table professor
(
    id         bigint not null default nextval('professor_id_seq'),
    name       varchar(100) not null,
    avatar_url varchar(255),
    created_at timestamp not null,
    updated_at timestamp,
    primary key (id)
);