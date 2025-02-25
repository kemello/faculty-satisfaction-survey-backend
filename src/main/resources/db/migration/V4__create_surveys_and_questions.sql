create sequence survey_id_seq start with 1 increment by 50;
create sequence question_id_seq start with 1 increment by 50;
create sequence response_id_seq start with 1 increment by 50;


-- Table: survey
create table survey
(
    id           bigint not null default nextval('survey_id_seq'),
    name         varchar(255) not null,
    description  text,
    start_date   date not null,
    end_date     date not null,
    created_at   timestamp not null,
    updated_at   timestamp not null,
    primary key (id)
);

-- Table: survey_question
create table survey_question
(
    id           bigint not null default nextval('question_id_seq'),
    survey_id    bigint not null,
    text         varchar(255) not null,
    question_type text not null,                      -- Enum for question type
    question_category text not null,
    question_order integer not null,
    created_at   timestamp not null,
    primary key (id),
    foreign key (survey_id) references survey (id) on delete cascade  -- ADD FOREIGN KEY
);


-- Table: survey_response
create table survey_response
(
    survey_id    bigint not null default nextval('response_id_seq'),
    anonymous_id varchar(255) not null,
    question_id  bigint not null,
    response     text not null,
    response_type text not null,      -- Enum for response type
    response_at  timestamp not null,
    primary key (anonymous_id, survey_id, question_id),
    foreign key (survey_id) references survey (id) on delete cascade,
    foreign key (question_id) references survey_question (id) on delete cascade
);

-- Adding performance indexes
create index idx_question_id_on_survey_response on survey_response (question_id);
create index idx_survey_id_on_survey_response on survey_response (survey_id);