--Variant 2
CREATE PROCEDURE dbo.SubCategoriesByColor
@colors nvarchar(255)
AS 

exec('SELECT [Name],'+
@colors+'
 FROM
(  SELECT PS.[Name], [Color], [Weight] FROM [Production].[ProductSubcategory] AS PS
   INNER JOIN [Production].[Product] AS PP
    ON PS.[ProductSubCategoryID] = PP.[ProductSubCategoryID]) AS SourceTable
PIVOT
(
Max([Weight])
FOR [Color] IN ('+@colors+')
) AS PivotTable;');
GO
--DROP PROCEDURE  dbo.SubCategoriesByColor;
EXECUTE dbo.SubCategoriesByColor '[Black],[Silver],[Yellow]';


