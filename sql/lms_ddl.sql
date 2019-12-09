-- Create lmsdb.sql
-- Create DDL for companydb
drop database lmsdb;
create database lmsdb;
use lmsdb;

-- CREATE TABLE scripts
create table Student(
    name varchar(30),
    studentID smallint,
    constraint pk_Student PRIMARY KEY (studentID)
);

create table Professor(
    name varchar(30),
    profID smallint,
    constraint pk_Professor PRIMARY KEY (profID)
);

create table Course(
    courseID smallint,
    profID smallint,
    courseName varchar(30),
    slides varchar(30),
    constraint pk_Course PRIMARY KEY (courseID)
);

create table Enrollment(
    enrID smallint,
    studentID  smallint,
    courseID smallint,
    constraint pk_Enrollment PRIMARY KEY (enrID)
);

create table Exam(
    examID smallint NOT NULL AUTO_INCREMENT,
    examName varchar(30),
    courseID smallint,
    constraint pk_Exam PRIMARY KEY (examID)
);

create table Exam_Response(
    responseID smallint NOT NULL AUTO_INCREMENT,
    response varchar(30),
    enrID smallint,
    examID smallint,
    constraint pk_Exam_Response PRIMARY KEY (responseID)
);

-- ALTER TABLE scripts
alter table Course
    add constraint fk_profID FOREIGN KEY(profID) references Professor(profID);

alter table Enrollment
    add constraint fk_studentID FOREIGN KEY(studentID) references Student(studentID),
    add constraint fk_courseID1 FOREIGN KEY(courseID) references Course(courseID);

alter table Exam
    add constraint fk_courseID FOREIGN KEY(courseID) references Course(courseID);

alter table Exam_Response
    add constraint fk_enrID FOREIGN KEY(enrID) references Enrollment(enrID),
    add constraint fk_examID FOREIGN KEY(examID) references Exam(examID);

