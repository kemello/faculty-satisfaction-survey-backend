create sequence course_id_seq start with 1 increment by 50;

-- Table: course
create table course
(
    id           bigint       not null default nextval('course_id_seq'),
    name         varchar(255) not null unique,
    professor_id bigint       not null,
    created_at   timestamp    not null,
    primary key (id),
    foreign key (professor_id) references professor (id) on delete cascade
);

-- Consolidated course relationships
create table course_assignment
(
    course_id     bigint not null,
    faculty       text   not null,
    academic_year text   not null,
    study_mode    text   not null,
    primary key (course_id, faculty, academic_year, study_mode),
    foreign key (course_id) references course (id) on delete cascade
);

-- Student-course enrollment table
create table student_course
(
    student_id  bigint    not null,
    course_id   bigint    not null,
    enrolled_at timestamp not null,
    primary key (student_id, course_id),
    foreign key (student_id) references student (id) on delete cascade,
    foreign key (course_id) references course (id) on delete cascade
);

-- Adding performance indexes
create index idx_professor_id_on_course on course (professor_id);
create index idx_student_id_on_student_course on student_course (student_id);
create index idx_course_id_on_student_course on student_course (course_id);