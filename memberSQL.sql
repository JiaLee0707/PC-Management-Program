CREATE DATABASE pcmanagement;
USE pcmanagement;
CREATE TABLE member 
(NAME VARCHAR(10) NOT NULL, id VARCHAR(20) NOT NULL PRIMARY KEY, pw VARCHAR(20) NOT NULL, 
TIME TIME NOT NULL, pay INT(255) NOT NULL, how VARCHAR(10), pcLOG INT(20) NOT NULL);