SELECT p.Pid, p.Pname 
FROM project p, employee e
WHERE (strcmp(p.Pid, e.Pid)=0)
GROUP BY e.Pid, e.Ename
HAVING count(e.Pid) >=2 AND count(e.Ename) >=2;