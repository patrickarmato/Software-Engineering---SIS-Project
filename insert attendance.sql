ALTER TABLE enrollments
ADD COLUMN attendance INT;


UPDATE enrollments
SET attendance = FLOOR(RAND() * 6);
