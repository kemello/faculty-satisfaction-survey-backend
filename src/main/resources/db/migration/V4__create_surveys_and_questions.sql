create sequence survey_id_seq start with 1 increment by 1;
create sequence question_id_seq start with 1 increment by 1;

-- Table: survey
create table survey
(
    id           bigint not null default nextval('survey_id_seq'),
    name         varchar(255) not null,
    description  text,
    start_date   date not null,
    end_date     date not null,
    created_at   timestamp default current_timestamp,
    updated_at   timestamp default current_timestamp,
    primary key (id)
);

-- Table: survey_question
create table survey_question
(
    id           bigint not null default nextval('question_id_seq'),
    text         varchar(255) not null,
    question_type question_type_enum not null,                      -- Enum for question type
    metadata     text,
    is_active    boolean default true,
    created_at   timestamp default current_timestamp,
    primary key (id)
);

-- Table: survey_question_assignment
create table survey_question_assignment
(
    survey_id    bigint not null,
    question_id  bigint not null,
    order_number smallint,
    primary key (survey_id, question_id),
    foreign key (survey_id) references survey (id) on delete cascade,
    foreign key (question_id) references survey_question (id) on delete cascade
);

-- Table: survey_response
create table survey_response
(
    survey_id    bigint not null,
    anonymous_id varchar(255) not null,
    question_id  bigint not null,
    response     text not null,
    response_type question_type_enum not null,      -- Enum for response type
    response_at  timestamp default current_timestamp,
    primary key (anonymous_id, survey_id, question_id),
    foreign key (survey_id) references survey (id) on delete cascade,
    foreign key (question_id) references survey_question (id) on delete cascade
);

-- Adding performance indexes
create index idx_question_id_on_survey_response on survey_response (question_id);
create index idx_survey_id_on_survey_response on survey_response (survey_id);