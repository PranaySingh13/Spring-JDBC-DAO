use springjdbcdaodb;
/*---------------------------------------*/
DROP PROCEDURE IF EXISTS `insertEmployee`;

DELIMITER $$
CREATE PROCEDURE insertEmployee(IN eid INTEGER(11),IN ename VARCHAR(255),IN esal FLOAT(10,2),IN eaddr VARCHAR(255))
BEGIN
insert into EMPLOYEE values(eid, ename, esal, eaddr);
END $$
DELIMITER ;

SET @eid=1;
SET @ename='abc';
SET @esal=1000;
SET @eaddr='xyz';

CALL insertEmployee(@eid,@ename,@esal,@eaddr);

/*---------------------------------------*/
DROP PROCEDURE IF EXISTS `getEmployee`;

DELIMITER $$
CREATE PROCEDURE getEmployee(IN no INTEGER(11),OUT eid INTEGER(11),OUT ename VARCHAR(255),OUT esal FLOAT(10,2),OUT eaddr VARCHAR(255))
BEGIN
select EMPID, EMPNAME, EMPSALARY, EMPADDRESS into eid, ename, esal, eaddr from EMPLOYEE where EMPID= no;
END $$
DELIMITER ;

/*---------------------------------------*/
DROP PROCEDURE IF EXISTS `updateEmployee`;

DELIMITER $$
CREATE PROCEDURE updateEmployee(IN eid INTEGER(11),IN ename VARCHAR(255),IN esal FLOAT(10,2),IN eaddr VARCHAR(255))
BEGIN
update EMPLOYEE set EMPNAME = ename, EMPSALARY = esal, EMPADDRESS = eaddr where EMPID = eid;
END $$
DELIMITER ;

/*---------------------------------------*/
DROP PROCEDURE IF EXISTS `deleteEmployee`;

DELIMITER $$
CREATE PROCEDURE deleteEmployee(IN eid INTEGER(11))
BEGIN
delete from EMPLOYEE where EMPID = eid;
END $$
DELIMITER ;

/*---------------------------------------*/
DROP PROCEDURE IF EXISTS `getSalary`;

DELIMITER $$
CREATE PROCEDURE getSalary(IN eid INTEGER(11),OUT esal FLOAT(10,2))
BEGIN
select EMPSALARY into esal from EMPLOYEE where EMPID = eid;
END $$
DELIMITER ;
  


