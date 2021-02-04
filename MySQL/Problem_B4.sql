SELECT c.CategoryName, count(p.CategoryID) AS Number_Of_Products
FROM categories c
LEFT JOIN products p
ON c.CategoryID=p.CategoryID
GROUP BY p.CategoryID;