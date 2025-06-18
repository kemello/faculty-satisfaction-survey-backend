create sequence survey_token_id_seq start with 1 increment by 50;

-- Table: survey_access_token
create table survey_access_token (
    id bigint not null default nextval('survey_token_id_seq'),
    token varchar(255) not null,
    survey_id bigint not null,
    course_id bigint not null,
    professor_id bigint not null,
    hash varchar(255) not null,
    is_used boolean not null default false,
    created_at timestamp not null,
    expires_at timestamp not null,
    used_at timestamp,
    primary key (id),
    foreign key (survey_id) references survey (id) on delete cascade,
    foreign key (course_id) references course (id) on delete cascade,
    foreign key (professor_id) references professor (id) on delete cascade,
    constraint uk_survey_token unique (token)
);

-- Add index for faster token lookups
create index idx_survey_token on survey_access_token (token);
create index idx_survey_token_hash on survey_access_token (hash);