-- INSERT scripts for sample data
-- Student id 0, course id 1, prof id 2, enr id 3
insert into Student VALUES("Thomas", 01), ("Toni", 02);
insert into Professor VALUES("Kevin", 21), ("Luke", 22);
insert into Course VALUES(11, 21, "Database",NULL), (12, 22, "ML",NULL), (13, 21, "Automata",NULL), (14, 21, "Maths",NULL), (15, 22, "Programming",NULL);
insert into Enrollment VALUES(31, 01, 11),(32, 01, 13), (33, 01, 14), (34, 02, 13), (35, 02, 14), (36, 02, 15);
