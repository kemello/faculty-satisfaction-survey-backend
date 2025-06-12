-- Insert survey
INSERT INTO survey (name, start_date, end_date, status, created_at)
VALUES ('2025 Faculty Survey', now(), now() + interval '1 month', 'DRAFT', now());

-- Вставка вопросов
INSERT INTO survey_question (id, survey_id, text, question_type, question_category, question_order, created_at)
VALUES (1, 1, 'Ясное и доступное изложение материала', 'RATING', 'FOR_PROFESSOR', 1, now()),
       (2, 1, 'Умение вызвать и поддержать интерес к предмету', 'RATING', 'FOR_PROFESSOR', 2, now()),
       (3, 1,
        'Умение вести диалог со студенческой аудиторией (возможность задавать вопросы, дискутировать), способность профессионально отвечать на вопросы студентов',
        'RATING', 'FOR_PROFESSOR', 3, now()),
       (4, 1, 'Преподаватель приводит примеры из реальной практики профессиональной деятельности', 'RATING',
        'FOR_PROFESSOR', 4, now()),
       (5, 1, 'Мне понятны задания, которые дает преподаватель для самостоятельного выполнения', 'RATING',
        'FOR_PROFESSOR', 5, now()),
       (6, 1, 'Преподаватель комментирует результаты самостоятельной работы, тестов, проверочных работ', 'RATING',
        'FOR_PROFESSOR', 6, now()),
       (7, 1, 'Объективность в оценке знаний студентов', 'RATING', 'FOR_PROFESSOR', 7, now()),
       (8, 1, 'Доброжелательность и тактичность в отношении со студентами', 'RATING', 'FOR_PROFESSOR', 8, now()),
       (9, 1, 'Свободно ориентируется в своем предмете и в смежных областях знаний', 'RATING', 'FOR_PROFESSOR', 9,
        now()),
       (10, 1, 'Соразмерность требований на экзаменах и зачетах изученному программному материалу', 'RATING',
        'FOR_PROFESSOR', 10, now()),
       (11, 1,
        'Привлечение студентов к научной деятельности (к написанию статей, научных работ; к участию в научных конференциях, олимпиадах, грантовых конкурсах)',
        'RATING', 'FOR_PROFESSOR', 11, now()),
       (12, 1,
        'Преподаватель точно соблюдает учебное расписание (вовремя начинает и заканчивает занятие, делает перерыв)',
        'RATING', 'FOR_PROFESSOR', 12, now()),
       (13, 1, 'Степень соответствия учебной информации современным требованиям профессии', 'RATING', 'FOR_PROFESSOR',
        13, now()),
       (14, 1, 'Дополнительные комментарии о преподавателе и предложения', 'TEXT', 'FOR_PROFESSOR', 14, now());

-- Вставка опций для всех вопросов (одинаковые)
INSERT INTO question_option (question_id, text, value, option_order)
VALUES
-- Вопрос 1
(1, 'Very Satisfied', '5', 1),
(1, 'Satisfied', '4', 2),
(1, 'Neutral', '3', 3),
(1, 'Dissatisfied', '2', 4),
(1, 'Very Dissatisfied', '1', 5),
-- Вопрос 2
(2, 'Very Satisfied', '5', 1),
(2, 'Satisfied', '4', 2),
(2, 'Neutral', '3', 3),
(2, 'Dissatisfied', '2', 4),
(2, 'Very Dissatisfied', '1', 5),
-- Вопрос 3
(3, 'Very Satisfied', '5', 1),
(3, 'Satisfied', '4', 2),
(3, 'Neutral', '3', 3),
(3, 'Dissatisfied', '2', 4),
(3, 'Very Dissatisfied', '1', 5),
-- Вопрос 4
(4, 'Very Satisfied', '5', 1),
(4, 'Satisfied', '4', 2),
(4, 'Neutral', '3', 3),
(4, 'Dissatisfied', '2', 4),
(4, 'Very Dissatisfied', '1', 5),
-- Вопрос 5
(5, 'Very Satisfied', '5', 1),
(5, 'Satisfied', '4', 2),
(5, 'Neutral', '3', 3),
(5, 'Dissatisfied', '2', 4),
(5, 'Very Dissatisfied', '1', 5),
-- Вопрос 6
(6, 'Very Satisfied', '5', 1),
(6, 'Satisfied', '4', 2),
(6, 'Neutral', '3', 3),
(6, 'Dissatisfied', '2', 4),
(6, 'Very Dissatisfied', '1', 5),
-- Вопрос 7
(7, 'Very Satisfied', '5', 1),
(7, 'Satisfied', '4', 2),
(7, 'Neutral', '3', 3),
(7, 'Dissatisfied', '2', 4),
(7, 'Very Dissatisfied', '1', 5),
-- Вопрос 8
(8, 'Very Satisfied', '5', 1),
(8, 'Satisfied', '4', 2),
(8, 'Neutral', '3', 3),
(8, 'Dissatisfied', '2', 4),
(8, 'Very Dissatisfied', '1', 5),
-- Вопрос 9
(9, 'Very Satisfied', '5', 1),
(9, 'Satisfied', '4', 2),
(9, 'Neutral', '3', 3),
(9, 'Dissatisfied', '2', 4),
(9, 'Very Dissatisfied', '1', 5),
-- Вопрос 10
(10, 'Very Satisfied', '5', 1),
(10, 'Satisfied', '4', 2),
(10, 'Neutral', '3', 3),
(10, 'Dissatisfied', '2', 4),
(10, 'Very Dissatisfied', '1', 5),
-- Вопрос 11
(11, 'Very Satisfied', '5', 1),
(11, 'Satisfied', '4', 2),
(11, 'Neutral', '3', 3),
(11, 'Dissatisfied', '2', 4),
(11, 'Very Dissatisfied', '1', 5),
-- Вопрос 12
(12, 'Very Satisfied', '5', 1),
(12, 'Satisfied', '4', 2),
(12, 'Neutral', '3', 3),
(12, 'Dissatisfied', '2', 4),
(12, 'Very Dissatisfied', '1', 5),
-- Вопрос 13
(13, 'Very Satisfied', '5', 1),
(13, 'Satisfied', '4', 2),
(13, 'Neutral', '3', 3),
(13, 'Dissatisfied', '2', 4),
(13, 'Very Dissatisfied', '1', 5);