SELECT p.*, c.CategoryName, c.Description
FROM products p
INNER JOIN categories c
ON p.CategoryID=c.CategoryID
WHERE ProductName LIKE '%ch%';