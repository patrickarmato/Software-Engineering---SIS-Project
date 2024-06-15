UPDATE `Student Information System`.`Student`
SET 
    major = CASE 
                WHEN idStudent <= 5 THEN 'Mathmatics'
				WHEN idStudent <= 7 THEN 'Business Finance'
                WHEN idStudent <= 10 THEN 'Computer Science'
				WHEN idStudent <= 13 THEN 'Business Managment'
				WHEN idStudent <= 15 THEN 'Biology'
                WHEN idStudent <= 20 THEN 'Psychology'
                ELSE 'English' 
            END,
    gpa = ROUND(RAND() * (4 - 2) + 2, 2)
WHERE idStudent <= 20;
