CREATE TABLE TODO(
	TODO_ID INT AUTO_INCREMENT PRIMARY KEY,
	TODO_NAME VARCHAR(20),
	TODO_CONTENT VARCHAR(2000),
	TODO_STATUS CHAR(1),
	TODO_CREATED_DATE DATE,
	TODO_START_DATE DATE,
	TODO_END_DATE DATE
);