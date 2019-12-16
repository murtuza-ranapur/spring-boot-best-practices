INSERT INTO course(
	id, created_date, last_modified_date, code, name)
	VALUES (nextval('hibernate_sequence'), now(), now(), 'CE-101', 'Computer Engineering');
INSERT INTO course(
	id, created_date, last_modified_date, code, name)
	VALUES (nextval('hibernate_sequence'), now(), now(), 'ME-100', 'Mechanical Engineering');
INSERT INTO course(
	id, created_date, last_modified_date, code, name)
	VALUES (nextval('hibernate_sequence'), now(), now(), 'EE-765', 'Electrical Engineering');

INSERT INTO public.student(
	id, created_date, last_modified_date, batch, class_section, name, semester, year, course_id)
	VALUES (nextval('hibernate_sequence'), now(), now(), 2017, 'A', 'Murtuza Ranapur', 4, 2, 1);
INSERT INTO public.student(
	id, created_date, last_modified_date, batch, class_section, name, semester, year, course_id)
	VALUES (nextval('hibernate_sequence'), now(), now(), 2018, 'B', 'John Doe', 2, 1, 2);
INSERT INTO public.student(
	id, created_date, last_modified_date, batch, class_section, name, semester, year, course_id)
	VALUES (nextval('hibernate_sequence'), now(), now(), 2018, 'A', 'Conney June', 2, 1, 2);
INSERT INTO public.student(
	id, created_date, last_modified_date, batch, class_section, name, semester, year, course_id)
	VALUES (nextval('hibernate_sequence'), now(), now(), 2018, 'B', 'Wayne Childress', 2, 1, 3);
INSERT INTO public.student(
	id, created_date, last_modified_date, batch, class_section, name, semester, year, course_id)
	VALUES (nextval('hibernate_sequence'), now(), now(), 2016, 'B', 'Honey Marry', 6, 3, 3);

drop table if exists student_course_view cascade;
create view student_course_view as select s.id as "student_id", s.name, c.code as "course_code", c.name as "course_name" from student s join course c on s.course_id = c.id;