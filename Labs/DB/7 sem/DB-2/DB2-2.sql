-- 1) Complete DROP TABLE [dbo].[StateProvince];
CREATE TABLE [dbo].[StateProvince](
	[StateProvinceID] [int] IDENTITY(1,1) NOT NULL,
	[StateProvinceCode] [nchar](3) NOT NULL,
	[CountryRegionCode] [nvarchar](3) NOT NULL,
	[IsOnlyStateProvinceFlag] [dbo].[Flag] NOT NULL,
	[Name] [dbo].[Name] NOT NULL,
	[TerritoryID] [int] NOT NULL,
	[ModifiedDate] [datetime] NOT NULL)
	GO
-- 2) Complete
ALTER TABLE [dbo].[StateProvince] ADD CONSTRAINT uniqueName UNIQUE ([Name]);
-- 3) Complete
ALTER TABLE [dbo].[StateProvince] ADD CONSTRAINT countryRegionCodePattern CHECK
 (CountryRegionCode LIKE 
   '[^0-9][^0-9]');
-- 4) Complete
ALTER TABLE [dbo].[StateProvince] ADD DEFAULT getdate() FOR [ModifiedDate];
-- 5) Complete
SET IDENTITY_INSERT [dbo].[StateProvince] ON;
INSERT INTO [dbo].[StateProvince]([StateProvinceID] ,[StateProvinceCode], [CountryRegionCode], [IsOnlyStateProvinceFlag], [Name] , [TerritoryID], [ModifiedDate])
SELECT [Person].[StateProvince].[StateProvinceID] ,[Person].[StateProvince].[StateProvinceCode], [Person].[StateProvince].[CountryRegionCode],[Person].[StateProvince].[IsOnlyStateProvinceFlag],[Person].[StateProvince].[Name] ,[Person].[StateProvince].[TerritoryID],[Person].[StateProvince].[ModifiedDate]
FROM [Person].[StateProvince] WHERE [Person].[StateProvince].[Name] = (SELECT [Name] FROM [Person].[CountryRegion] WHERE [Person].[CountryRegion].CountryRegionCode = [Person].[StateProvince].CountryRegionCode);
--6) Complete
ALTER TABLE [dbo].[StateProvince] DROP COLUMN [IsOnlyStateProvinceFlag];
ALTER TABLE [dbo].[StateProvince] ADD [CountryNum] INT NULL;
