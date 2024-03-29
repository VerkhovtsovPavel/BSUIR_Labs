--Variant 2
--1) Complete
CREATE FUNCTION dbo.count_of_employees
(@id int)
RETURNS int

BEGIN
    RETURN (SELECT Count(*) FROM [HumanResources].[EmployeeDepartmentHistory] WHERE [HumanResources].[EmployeeDepartmentHistory].DepartmentID = @id)
END
GO

SELECT dbo.count_of_employees(3) AS 'CountOFEmployees';
GO
--2) Complete  DROP FUNCTION dbo.long_work_employess;
CREATE FUNCTION dbo.long_work_employess
(@id int)
RETURNS table
AS
RETURN (
        SELECT [HumanResources].[Employee].[BusinessEntityID]
      ,[JobTitle]
      ,[Gender]
      ,[HireDate]
        FROM
		 [HumanResources].[Employee]
		 INNER JOIN  [HumanResources].[EmployeeDepartmentHistory]
		  ON [HumanResources].[Employee].[BusinessEntityID] = [HumanResources].[EmployeeDepartmentHistory].BusinessEntityID
		WHERE [DepartmentID] = @id
		AND
		YEAR(SYSDATETIME()) -YEAR(HireDate) > 11
       );
GO

SELECT *
FROM dbo.long_work_employess(5)
GO
--3) Complete
SELECT * FROM [HumanResources].[Department] D
CROSS APPLY
dbo.long_work_employess(D.DepartmentID) ORDER BY DepartmentID;

SELECT * FROM [HumanResources].[Department] D
OUTER APPLY
dbo.long_work_employess(D.DepartmentID) ORDER BY DepartmentID;
GO;
--4)
CREATE FUNCTION  dbo.multiStatement_long_work_employess
(@id int)
RETURNS
@long_work_employee table (
[BusinessEntityID] int,
[JobTitle] nvarchar(50),
[Gender] nchar(1),
[HireDate] date
)
AS
BEGIN
 
INSERT INTO @long_work_employee
SELECT [HumanResources].[Employee].[BusinessEntityID]
      ,[JobTitle]
      ,[Gender]
      ,[HireDate]
        FROM
		 [HumanResources].[Employee]
		 INNER JOIN  [HumanResources].[EmployeeDepartmentHistory]
		  ON [HumanResources].[Employee].[BusinessEntityID] = [HumanResources].[EmployeeDepartmentHistory].BusinessEntityID
		WHERE [DepartmentID] = @id
		AND
		YEAR(SYSDATETIME()) -YEAR(HireDate) > 11;
RETURN
END
GO

SELECT * FROM dbo.multiStatement_long_work_employess(5);