CREATE TABLE T_EMPLOYEE (ID NUMBER PRIMARY KEY , FILE_NAME VARCHAR2(100) NOT NULL , FILE_LOCATION VARCHAR2(250) , FILE_STATUS VARCHAR2(20) , ERROR_MESSAGE VARCHAR2(500), PROGRESS_PERCENTAGE NUMBER, START_TIME DATE, END_TIME DATE, CREATED_TIME DATE);
CREATE TABLE T_FILE_STATUS(ID NUMBER,FILE_NAME VARCHAR2(50), FILE_LOCATION VARCHAR2(200), FILE_STATUS VARCHAR2(20),
  ERROR_MESSAGE VARCHAR2(300), START_TIME DATE, END_TIME DATE, CREATED_TIME DATE, PRIMARY KEY (ID));