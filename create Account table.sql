CREATE TABLE `Account` (
  `username` INT NOT NULL,
  `password` VARCHAR(100) NOT NULL DEFAULT '1234',
  `emergency_contact` VARCHAR(100),
  `gpa` DECIMAL(3, 2) CHECK (gpa >= 0 AND gpa <= 4),
  PRIMARY KEY (`username`),
  FOREIGN KEY (`username`) REFERENCES `Student Information System`.`Student`(`idStudent`)
);

INSERT INTO `Student Information System`.`Account` (`username`, `password`, `emergency_contact`, `gpa`) VALUES
('30', '1234', '347-778-9022', '3.4');



