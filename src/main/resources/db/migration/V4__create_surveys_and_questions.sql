-- Table: survey
create table survey
(
    id           bigint not null,
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
    id           bigint not null,
    text         varchar(255) not null,
    question_type question_type_enum not null,                      -- Enum for question type
    metadata     text,
    is_active    boolean not null default true,
    created_at   timestamp not null,
    primary key (id)
);

-- Table: survey_question_assignment
create table survey_question_assignment
(
    id           uuid not null default gen_random_uuid(),
    survey_id    bigint not null,
    question_id  bigint not null,
    order_number integer not null,
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
    response_at  timestamp not null,
    primary key (anonymous_id, survey_id, question_id),
    foreign key (survey_id) references survey (id) on delete cascade,
    foreign key (question_id) references survey_question (id) on delete cascade
);

-- Adding performance indexes
create index idx_question_id_on_survey_response on survey_response (question_id);
create index idx_survey_id_on_survey_response on survey_response (survey_id);