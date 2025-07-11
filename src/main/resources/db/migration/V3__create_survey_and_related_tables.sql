create sequence survey_id_seq start with 1 increment by 50;
create sequence question_id_seq start with 1 increment by 50;


-- Table: survey
create table survey
(
    id          bigint       not null default nextval('survey_id_seq'),
    name        varchar(255) not null,
    description text,
    start_date  date         not null,
    end_date    date         not null,
    status      text         not null,
    created_at  timestamp    not null,
    updated_at  timestamp,
    primary key (id)
);

-- Table: survey_question
create table survey_question
(
    id                bigint       not null default nextval('question_id_seq'),
    survey_id         bigint       not null,
    text              varchar(255) not null,
    question_type     text         not null,
    question_category text         not null,
    question_order    integer      not null,
    created_at        timestamp    not null,
    primary key (id),
    foreign key (survey_id) references survey (id) on delete cascade
);

-- Table: question_option
create table question_option
(
    question_id  bigint       not null,
    text         varchar(255) not null,
    value        varchar(255) not null,
    option_order integer      not null,
    primary key (question_id, option_order),
    foreign key (question_id) references survey_question (id) on delete cascade
);

-- Table: survey_response
create table survey_response
(
    anonymous_id varchar(255) not null,
    question_id  bigint       not null,
    survey_id    bigint       not null,
    professor_id bigint       not null,
    content      text         not null,
    response_at  timestamp    not null,
    primary key (anonymous_id, question_id, survey_id),
    foreign key (question_id) references survey_question (id) on delete cascade,
    foreign key (survey_id) references survey (id) on delete cascade
);

CREATE UNIQUE INDEX only_one_active_survey ON survey (status)
    WHERE status = 'ACTIVE'