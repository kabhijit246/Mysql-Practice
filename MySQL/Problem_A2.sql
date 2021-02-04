SELECT Ename FROM employee
WHERE (Pid = (SELECT Pid FROM employee WHERE Ename ='ABC') AND Ename !='ABC');
