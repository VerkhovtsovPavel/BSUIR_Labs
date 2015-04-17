SELECT MAX(`cost`) FROM `rates`
UNION
SELECT MIN(`cost`) FROM `rates`