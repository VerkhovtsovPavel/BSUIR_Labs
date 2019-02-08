--Variant 4
--a) Complete
ALTER TABLE [dbo].[StateProvince] ADD [CountryRegionName] nvarchar(50);
--b) Complete
DECLARE @tempStateProvince TABLE(
[StateProvinceID] [int] IDENTITY(1,1) NOT NULL,
	[StateProvinceCode] [nchar](3) NOT NULL,
	[CountryRegionCode] [nvarchar](3) NOT NULL,
	[IsOnlyStateProvinceFlag] [dbo].[Flag] NOT NULL,
	[Name] [dbo].[Name] NOT NULL,
	[TerritoryID] [int] NOT NULL,
	[ModifiedDate] [datetime] NOT NULL);

SELECT * INTO #tempStateProvince from [dbo].[StateProvince];
UPDATE #tempStateProvince SET [CountryRegionName] = (SELECT [Name] FROM Person.CountryRegion WHERE Person.CountryRegion.CountryRegionCode = #tempStateProvince.CountryRegionCode);
--c) Complete
UPDATE [dbo].[StateProvince] SET [CountryRegionName] = (SELECT [CountryRegionName] FROM [#tempStateProvince] WHERE [dbo].[StateProvince].CountryRegionCode = [#tempStateProvince].CountryRegionCode);
--d) Complete
DELETE FROM [dbo].[StateProvince] WHERE [dbo].[StateProvince].[StateProvinceID] NOT IN (SELECT [StateProvinceID] FROM Person.Address);
--e)
ALTER TABLE [dbo].[StateProvince] DROP COLUMN [CountryRegionName];

DECLARE @constraint varchar(255);
DECLARE constraint_cursor CURSOR FOR 
SELECT [CONSTRAINT_NAME] AS 'Constraint'
FROM AdventureWorks2012.INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE
WHERE TABLE_SCHEMA = 'dbo' AND TABLE_NAME = 'StateProvince'
UNION
SELECT [name] AS 'Constraint'
FROM sys.objects WHERE type = 'D' and parent_object_id = object_id('dbo.StateProvince')
OPEN constraint_cursor;
FETCH NEXT FROM constraint_cursor INTO @constraint;
WHILE @@FETCH_STATUS = 0
BEGIN
	EXEC('ALTER TABLE [dbo].[StateProvince] DROP CONSTRAINT '+ @constraint);
	FETCH NEXT FROM constraint_cursor INTO @constraint;
END
CLOSE constraint_cursor;
DEALLOCATE constraint_cursor;
--f) Complete
DROP TABLE [dbo].[StateProvince];




