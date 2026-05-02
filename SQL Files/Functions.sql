
CREATE FUNCTION dbo.GetCustomerServices(@GuestId NVARCHAR(100))
RETURNS TABLE
AS 
RETURN
(
	SELECT Guest.Guest_Id,
	Guest.F_Name+''+Guest.L_name As GuestName,
	[Service].service_Id,
	[Service].service_Name
	FROM Guest
	JOIN
	ServiceBooking on Guest.Guest_Id= ServiceBooking.Guest_Id
	JOIN 
	Offers ON ServiceBooking.Booking_Id=Offers.Booking_Id
	JOIN
	[Service] ON Offers.Service_Id=[Service].service_Id
	where Guest.Guest_Id=@GuestId
);
--------------------------------------------------------------------------
SELECT *FROM dbo.GetCustomerServices(1);
---------------------------------------------------------------------------
CREATE FUNCTION dbo.DiscountedPrice(
    @NightPrice DECIMAL(10, 2),
    @NumberOfNights INT
)
RETURNS DECIMAL(10, 2)
AS
BEGIN
    DECLARE @DiscountedPrice DECIMAL(10, 2);

    IF @NumberOfNights >= 7
        SET @DiscountedPrice = @NightPrice * 0.9;
    ELSE IF @NumberOfNights >= 1
        SET @DiscountedPrice = @NightPrice * 0.95;
    ELSE
        SET @DiscountedPrice = @NightPrice;

    RETURN @DiscountedPrice;
END;

SELECT
    r.Reserve_Id, 
    rt.Night_Price,
    DATEDIFF(day, r.Check_IN, r.Check_Out) AS NumberOfNights,
    dbo.DiscountedPrice(rt.Night_Price, DATEDIFF(day, r.Check_IN, r.Check_Out)) AS DiscountedTotal
FROM
    Reservation AS r 
JOIN
    Room AS rm ON r.Room_Id = rm.Room_Number
JOIN
    [RoomType] AS rt ON rm.Room_Type_Id = rt.Room_Type_Id;

SELECT dbo.DiscountedPrice(100.00, 7) AS DiscountedTotal; 
------------------------------------------------------------------------------------------------------
CREATE FUNCTION GetNumberOfEmployeesOfDepartment
(@DEP_ID int) 
RETURNS INT
as
begin
DECLARE @return_val int
select @return_val = COUNT(Employee.Department_Id)
FROM Employee
WHERE Department_Id = @DEP_ID
RETURN @return_val
END;

SELECT dbo.GetNumberOfEmployeesOfDepartment(1) AS "number of emloyees "