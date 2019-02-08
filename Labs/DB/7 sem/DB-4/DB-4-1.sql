--Variant 2
--a) Complete
CREATE TABLE Production.LocationHst(
	[ID] [int] IDENTITY(1,1) PRIMARY KEY,
	[Action] [nchar](6) NOT NULL CHECK(Action IN('insert', 'update', 'delete')),
	[ModifiedDate] [datetime] NOT NULL,
	[SourceID] [int] NOT NULL,
	[UserName] [nvarchar](100))
GO
---DROP TABLE Production.LocationHst
--b) Complete
CREATE TRIGGER logger ON Production.Location
AFTER INSERT, UPDATE, DELETE
AS
DECLARE @action as nchar(6);
DECLARE @row_id INT
SET @action = 'insert';
SET @row_id = (SELECT MAX([LocationID]) FROM Production.Location);
    IF EXISTS(SELECT * FROM DELETED)
    BEGIN
        SET @action = 
            CASE
                WHEN EXISTS(SELECT * FROM INSERTED) THEN 'update'
                ELSE 'delete'       
            END
		set @row_id = 
            CASE
                WHEN EXISTS(SELECT * FROM INSERTED) THEN (SELECT [LocationID] FROM inserted)
                ELSE (SELECT [LocationID] FROM deleted)   
            END
    END
INSERT INTO Production.LocationHst ([Action], [ModifiedDate],[SourceID], [UserName]) VALUES(@action,SYSDATETIME(),@row_id,USER_NAME());
GO
--DROP TRIGGER [Production].[logger]
--c) Complete
CREATE VIEW location_view
AS 
SELECT *
FROM [Production].[Location];
GO
--d) Complete
SET IDENTITY_INSERT [Production].[Location] OFF
INSERT INTO [location_view]([Name],[CostRate],[Availability],[ModifiedDate]) VALUES('Paints Club', 15.00, 0.0, SYSDATETIME());
DELETE FROM [location_view] WHERE [LocationID] = 76;
UPDATE location_view SET [CostRate] = 50.00 WHERE [LocationID] = 1;

