create sequence student_id_seq start 1 increment by 1;
create sequence professor_id_seq start 1 increment by 1;

-- Table: student
create table student
(
    id             bigint not null default nextval('student_id_seq'),
    gender         gender_enum not null,                              -- Enum for gender
    academic_year  academy_year_enum not null,
    faculty        faculty_enum not null,
    mode_of_study  study_mode_enum not null,                          -- Enum for study mode
    created_at     timestamp default current_timestamp,
    updated_at     timestamp default current_timestamp,
    primary key (id)
);

-- Table: professor
create table professor
(
    id         bigint not null default nextval('professor_id_seq'),
    name       varchar(100) not null,
    department varchar(100) not null,
    avatar_url varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (id)
);