-- Modify survey_access_token table
ALTER TABLE survey_access_token 
    DROP COLUMN course_id,
    DROP COLUMN professor_id,
    DROP COLUMN hash,
    ADD COLUMN submission_hash varchar(255);

-- Update index
DROP INDEX IF EXISTS idx_survey_token_hash;
CREATE INDEX idx_submission_hash ON survey_access_token (submission_hash);