--Variant 4
SELECT DISTINCT [Name],[JobTitle], SUM(COUNT(Name)) OVER (PARTITION By [Name])  AS 'Count' FROM HumanResources.EmployeeDepartmentHistory
	INNER JOIN  HumanResources.Department ON HumanResources.EmployeeDepartmentHistory.DepartmentID = Department.DepartmentID
	INNER JOIN HumanResources.Employee ON Employee.BusinessEntityID = EmployeeDepartmentHistory.BusinessEntityID  GROUP BY Name, JobTitle ORDER BY Name;
--
SELECT e.BusinessEntityID, [JobTitle], [Name], [StartTime], [EndTime] FROM HumanResources.EmployeeDepartmentHistory AS edh
 INNER JOIN HumanResources.Shift ON  Shift.ShiftID = edh.ShiftID
 INNER JOIN HumanResources.Employee AS e ON edh.BusinessEntityID = e.BusinessEntityID
 WHERE Shift.Name='Night';
--- Complete
SELECT e.BusinessEntityID, [JobTitle], [Rate],
 CASE 
	When LAG(e.BusinessEntityID) OVER (ORDER BY e.BusinessEntityID) = e.BusinessEntityID
	 THEN (LAG(EmployeePayHistory.Rate) OVER (ORDER BY e.BusinessEntityID))
	ELSE 0.0
 End AS 'PrevRate',
CASE 
	When LAG(e.BusinessEntityID) OVER (ORDER BY e.BusinessEntityID) = e.BusinessEntityID
	 THEN (Rate - LAG(EmployeePayHistory.Rate) OVER (ORDER BY e.BusinessEntityID))
	ELSE Rate
 End AS 'Increased'
	 FROM HumanResources.Employee AS e INNER JOIN HumanResources.EmployeePayHistory ON e.BusinessEntityID = EmployeePayHistory.BusinessEntityID;
--- Complete
