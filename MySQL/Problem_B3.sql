SELECT p.*, c.CategoryName, c.Description
FROM products p, categories c
WHERE p.CategoryID=c.CategoryID AND c.CategoryName='Condiments';
