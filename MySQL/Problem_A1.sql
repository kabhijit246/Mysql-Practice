SELECT * FROM project 
WHERE Pid NOT IN (SELECT Pid FROM employee WHERE Pid != 'null');
