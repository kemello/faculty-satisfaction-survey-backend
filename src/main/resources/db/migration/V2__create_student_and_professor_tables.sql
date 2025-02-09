-- Table: student
create table student
(
    id             bigint not null,
    gender         gender_enum not null,                              -- Enum for gender
    academic_year  academic_year_enum not null,
    faculty        faculty_enum not null,
    mode_of_study  study_mode_enum not null,                          -- Enum for study mode
    created_at     timestamp not null,
    updated_at     timestamp not null,
    primary key (id)
);

-- Table: professor
create table professor
(
    id         bigint not null,
    name       varchar(100) not null,
    avatar_url varchar(255),
    created_at timestamp not null,
    updated_at timestamp not null,
    primary key (id)
);