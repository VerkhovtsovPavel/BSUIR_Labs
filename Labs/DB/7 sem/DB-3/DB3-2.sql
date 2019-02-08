--Variant 4
--a) Complite
SET NOCOUNT ON  
EXEC master.dbo.sp_configure 'show advanced options', 1 
RECONFIGURE 
EXEC master.dbo.sp_configure 'xp_cmdshell', 1 
RECONFIGURE 

DECLARE @FilePathName varchar(255), @Command varchar(255);
SET @FilePathName = '"D:\University\DB\7 sem\DB-2\DB2-2.sql"';
SET @Command = 'sqlcmd -S ' + @@SERVERNAME + ' -d  ' + 'AdventureWorks2012' + ' -i ' + @FilePathName;

EXEC xp_cmdshell @Command;

EXEC master.dbo.sp_configure 'xp_cmdshell', 0 
RECONFIGURE 
EXEC master.dbo.sp_configure 'show advanced options', 0 
RECONFIGURE  
SET NOCOUNT OFF 
---
ALTER TABLE [dbo].[StateProvince] ADD [SalesYTD]  MONEY NULL;
ALTER TABLE [dbo].[StateProvince] ADD [SumSales]  MONEY NULL;
ALTER TABLE [dbo].[StateProvince] ADD [SalesPercent] AS ([SumSales] / [SalesYTD] * 100);
SELECT * FROM [dbo].[StateProvince];
--b) Complite
DECLARE @StateProvince TABLE(
[StateProvinceID] [int] PRIMARY KEY NOT NULL,
	[StateProvinceCode] [nchar](3) NOT NULL,
	[CountryRegionCode] [nvarchar](3) NOT NULL,
	[Name] [dbo].[Name] NOT NULL,
	[TerritoryID] [int] NOT NULL,
	[ModifiedDate] [datetime] NOT NULL,
	[SalesYTD] [Money] NULL,
    [SumSales] [Money] NULL);
--c) Complite
INSERT INTO  @StateProvince ([StateProvinceID], [StateProvinceCode], [CountryRegionCode], [Name], [TerritoryID], [ModifiedDate])
 SELECT [StateProvinceID], [StateProvinceCode], [CountryRegionCode], [Name], [TerritoryID], [ModifiedDate]
 FROM [dbo].[StateProvince];

UPDATE @StateProvince  SET [SalesYTD] = (SELECT [SalesYTD] FROM [Sales].[SalesTerritory] WHERE [@StateProvince].TerritoryID = [Sales].[SalesTerritory].TerritoryID);

WITH Sales_CTE (TerritoryID, SumOfSales)
AS
(
    SELECT DISTINCT TerritoryID, SUM([SalesYTD]) OVER (PARTITION BY [TerritoryID])  AS 'SumOfSales'
    FROM Sales.SalesPerson
)
UPDATE @StateProvince SET [SumSales] = (SELECT [SumOfSales] FROM [Sales_CTE] WHERE Sales_CTE.TerritoryID = [@StateProvince].TerritoryID);

--d) Complite
DELETE TOP (1) FROM [dbo].[StateProvince] WHERE [dbo].[StateProvince].[StateProvinceID] = 5;
--e) Complite
SET IDENTITY_INSERT [dbo].[StateProvince] ON;
MERGE dbo.StateProvince AS target USING @StateProvince AS source ON target.StateProvinceID = source.StateProvinceID
WHEN MATCHED THEN
	UPDATE SET [SalesYTD] = source.SalesYTD, [SumSales] = source.SumSales
WHEN NOT MATCHED BY TARGET THEN
	INSERT ([StateProvinceID], [StateProvinceCode], [CountryRegionCode], [Name], [TerritoryID], [ModifiedDate], [SalesYTD], [SumSales])
 VALUES (source.[StateProvinceID], source.[StateProvinceCode], source.[CountryRegionCode], source.[Name], source.[TerritoryID], source.[ModifiedDate], [SalesYTD], [SumSales])
 WHEN NOT MATCHED BY SOURCE
	THEN DELETE;


	

 



