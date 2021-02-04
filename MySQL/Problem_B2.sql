SELECT p.*, c.CategoryName, c.Description
FROM products p, categories c
WHERE p.CategoryID=c.CategoryID
GROUP BY p.ProductID
HAVING (p.Price > (SELECT avg(Price) FROM products));
