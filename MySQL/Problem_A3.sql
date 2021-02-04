SELECT p.Pname, count(e.Pid) AS Number_Of_Employees
FROM project p
LEFT JOIN employee e
ON (strcmp(p.Pid, e.Pid)=0)
GROUP BY p.Pid;




