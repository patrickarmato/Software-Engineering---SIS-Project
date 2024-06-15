CREATE TABLE `Student Information System`.`teachers` (
  `idTeacher` INT NOT NULL AUTO_INCREMENT,
  `teacherName` VARCHAR(100) NOT NULL,
  `course_id` INT NOT NULL,
  PRIMARY KEY (`idTeacher`),
  FOREIGN KEY (`course_id`) REFERENCES `Student Information System`.`courses`(`idcourses`)
);
