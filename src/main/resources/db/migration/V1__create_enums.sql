-- Enum types for constraints
create type gender_enum as enum ('Мужской', 'Женский');      -- `gender` options
create type study_mode_enum as enum ('Очное', 'Заочное', 'Магистратура');    -- Study mode options
create type faculty_enum as enum ('ИСТ', 'Экономика', 'Менеджмент', 'Туризм', 'ЛД', 'УБ'); -- Faculties
CREATE TYPE academic_year_enum AS ENUM ('1', '2', '3', '4', '5');
create type question_type_enum as enum ('RATING', 'TEXT', 'SINGLE_CHOICE', 'MULTIPLE_CHOICE'); -- Question types