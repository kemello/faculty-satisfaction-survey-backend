create sequence student_id_seq start with 1 increment by 50;

create table student
(
    id            bigint       not null default nextval('student_id_seq'),
    gender        varchar(10)  not null,
    academic_year int          not null,
    faculty       varchar(100) not null,
    mode_of_study varchar(50)  not null,
    primary key (id)
);


create sequence professor_id_seq start with 1 increment by 50;

create table professor
(
    id         bigint       not null default nextval('professor_id_seq'),
    name       varchar(50)  not null,
    avatar_url varchar(255) not null,
    primary key (id)
);

create sequence course_id_seq start with 1 increment by 50;

create table course
(
    id            bigint       not null default nextval('course_id_seq'),
    name          varchar(255) not null,
    faculty       varchar(255) not null,
    academic_year int          not null,
    mode_of_study varchar(255) not null,
    professor_id  bigint       not null,
    primary key (id),
    foreign key (professor_id) references professor (id)
);

create table student_course
(
    student_id bigint not null,
    course_id  bigint not null,
    primary key (student_id, course_id),
    foreign key (student_id) references student (id),
    foreign key (course_id) references course (id)
);
