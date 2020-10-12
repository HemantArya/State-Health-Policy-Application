drop database if exists shpa;

create database shpa;

use shpa;

drop Table if exists userDetails;

Drop Table If Exists records;

create table userDetails (
	ID int primary key NOT NULL AUTO_INCREMENT,
    NAME varchar(30) NOT NULL,
    EMAIL VARCHAR(30) NOT NULL unique,
    PASSWORD VARCHAR(30) NOT NULL,
    PHONE VARCHAR(10) NOT NULL,
    ISADMIN INT NOT NULL
);

CREATE TABLE RECORDS (
	PID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    UID INT NOT NULL REFERENCES userDetails(ID),
    POLICYNAME varchar(80) NOT NULL,
    DESCRIPTION varchar(80) NOT NULL,
    STATE varchar(80) NOT NULL,
    STATUS varchar(80) NOT NULL,
    TYPE varchar(80) NOT NULL,
    JURISDICTION varchar(80) NOT NULL,
    SOURCE varchar(80) NOT NULL,
    YEAR INT(4) NOT NULL
);

INSERT INTO userDetails (ID,NAME,EMAIL,PASSWORD,PHONE,ISADMIN) values
(1,'Hemant Arya','hemant@shpa.com','hemant123','8765434567',1),
(2,'Prince Arya','prince@shpa.com','prince123','8724344563',0),
(3,'Shubham Gupta','shubham@shpa.com','shubham123','8724344343',0);

INSERT INTO RECORDS (UID,PID,POLICYNAME,DESCRIPTION,STATE,STATUS,TYPE,JURISDICTION,SOURCE,YEAR) VALUES
(1,1,'Medical Policy','Medicines storage related','Madhya Pradesh','In force','Regulatory','City/Municipal','IEA Database',2019),
(1,2,'Energy Policy','Energy storage related','Utter Pradesh','In force','Regulatory','State','IEA Database',2021),
(1,3,'Road Safety Policy','Accident storage related','Punjab','Enabled','Regulatory','Provincial','HPE Database',2020)
;
