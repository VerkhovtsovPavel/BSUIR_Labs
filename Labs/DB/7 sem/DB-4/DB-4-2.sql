--Variant 2
--a) Complete
CREATE VIEW location_product_productInventory_view WITH ENCRYPTION,
SCHEMABINDING
AS 
SELECT [P].[ProductID], [L].[LocationID], P.[Name] As ProductName, [L].[Name] as LocationName, L.CostRate, L.Availability, PrI.Shelf, PrI.Bin, PrI.Quantity
FROM [Production].[Location] L
INNER JOIN [Production].[ProductInventory] PrI
ON L.LocationID = PrI.LocationID
INNER JOIN [Production].[Product] P
ON PrI.ProductID = P.ProductID;
GO
--DROP VIEW [dbo].[location_product_productInventory_view];
CREATE UNIQUE CLUSTERED INDEX IDX_LP
    ON location_product_productInventory_view (LocationID,ProductID);
GO

SELECT * FROM location_product_productInventory_view;
UPDATE location_product_productInventory_view SET [CostRate] = 45 WHERE [ProductName] = 'Adjustable Race';
GO
--b) 
CREATE TRIGGER DeletedTrigger on location_product_productInventory_view
INSTEAD OF DELETE
AS
BEGIN
  DECLARE @locationID int;
  DECLARE @productID int;

  SET @locationID = (SELECT [LocationID] FROM location_product_productInventory_view WHERE
  [ProductName] = (SELECT [ProductName] FROM deleted));
  SET @productID = (SELECT [ProductID] FROM location_product_productInventory_view WHERE
  [ProductName] = (SELECT [ProductName] FROM deleted));
  	
  DELETE FROM [Production].[Location] WHERE 
  [LocationID] IN (SELECT [LocationID] FROM location_product_productInventory_view WHERE
  [ProductName] = (SELECT [ProductName] FROM deleted));

  DELETE FROM [Production].[ProductInventory] WHERE 
  [ProductID] = (SELECT [ProductID] FROM location_product_productInventory_view WHERE
  [ProductName] = (SELECT [ProductName] FROM deleted));
END;
GO

CREATE TRIGGER UpdatedTrigger on location_product_productInventory_view
INSTEAD OF UPDATE
AS
BEGIN
	DECLARE @locationID int;
	DECLARE @productID int;

	SET @locationID = (SELECT [LocationID] FROM inserted);
	SET @productID = (SELECT [ProductID] FROM location_product_productInventory_view WHERE
	[ProductName] = (SELECT [ProductName] FROM inserted));

  UPDATE
  [Production].[Location]
  SET 
	[Name] = New.[LocationName],
	[CostRate] = New.[CostRate],
	[Availability] = New.Availability,
	[ModifiedDate] = GETDATE()
 FROM
  (SELECT [LocationName], [CostRate], [Availability]
  FROM inserted WHERE
  [LocationID] = @locationID) AS New;


  UPDATE
  [Production].[ProductInventory]
  SET 
	[LocationID] = New.[LocationID],
	[Shelf] = New.[Shelf],
	[Bin] = New.Bin,
	[Quantity] = New.Quantity,
	[ModifiedDate] = GETDATE()
 FROM
  (SELECT [LocationID], [Shelf], [Bin], [Quantity]
  FROM inserted) AS New;
END;
GO
-- DROP TRIGGER [dbo].[UpdatedTrigger]
--Complete
CREATE TRIGGER InsteadTrigger on location_product_productInventory_view
INSTEAD OF Insert
AS
BEGIN
  DECLARE @locationID int;
  DECLARE @productID int;

  IF NOT EXISTS (SELECT * FROM [Production].[Product] WHERE [Name] = (SELECT [ProductName] FROM inserted))
  BEGIN
	RAISERROR('Invalid product name', 16, 1)
  END
  ELSE
  BEGIN	
	SET IDENTITY_INSERT [Production].[Location]  ON	
  	INSERT INTO [Production].[Location] ([LocationID]
      ,[Name]
      ,[CostRate]
      ,[Availability]
      ,[ModifiedDate]) SELECT [LocationID], [LocationName], [CostRate], [Availability], GETDATE() FROM inserted;
	INSERT INTO [Production].[ProductInventory]([ProductID]
      ,[LocationID]
      ,[Shelf]
      ,[Bin]
      ,[Quantity]
      ,[rowguid]
      ,[ModifiedDate]) SELECT [ProductID], [LocationID], [Shelf], [Bin], [Quantity], NEWID(), GETDATE() FROM inserted;
  END	
END;
GO
-- DROP TRIGGER [dbo].[InsteadTrigger]
--c)
INSERT INTO location_product_productInventory_view VALUES (3,	12, 'BB Ball Bearing', 'Toolq Crib',50.00,	0.00,'A','7',585)
UPDATE location_product_productInventory_view SET CostRate = 34 WHERE [ProductName] = 'BB Ball Bearing';

