--------------------------------------------------------
CREATE PROCEDURE GetReservationsByDateRange
    @StartDate DATE,
    @EndDate DATE
AS
BEGIN
    SELECT 
        r.Reserve_Id,
        g.F_Name + ' ' + g.L_name AS Guest_Name,
        r.Check_IN,
        r.Check_Out,
        r.Total_Price,
        r.Reserve_stat AS Status
    FROM 
        Reservation r
    JOIN 
        Guest g ON r.Guest_Id = g.Guest_Id
    WHERE 
        r.Check_IN BETWEEN @StartDate AND @EndDate;
END;
-----------------------------------------------------

CREATE PROCEDURE CalculateTotalReservationPrice
	@Check_IN DATE,
	@Check_Out DATE,
	@TotalPrice DECIMAL(10,3) OUTPUT
AS
BEGIN
	SELECT
    @TotalPrice=SUM((RoomType.Night_Price * DATEDIFF(day, Reservation.Check_IN, Reservation.Check_Out)))
	FROM
		Reservation
	JOIN
		Room ON Reservation.Room_Id = Room.Room_Number 
	JOIN
		[RoomType]  ON Room.Room_Type_Id = RoomType.Room_Type_Id
	WHERE
		Reservation.Check_IN = @Check_IN AND Reservation.Check_Out = @Check_Out;
END;

-----------------------------------------------------------------------------------

CREATE PROCEDURE BookingTotalPrice
	@Booking_Id int, @Guest_Id int, 
	@result DECIMAL(10,3) OUTPUT
AS BEGIN 
 select @result = ISNULL( sum(Service.Price) ,0)
 from [Service]
 INNER JOIN Offers
 on [Service].service_Id = Offers.service_Id
 where
 Offers.Booking_Id = @Booking_Id and   Offers.Guest_Id = @Guest_Id

END;
----------------------------------------------------------------------------------
CREATE PROCEDURE GetRoomTypeByRoomNumber
    @RoomType VARCHAR(100)
AS
BEGIN
    SELECT 
        room.Room_Number,
        roomtype.Room_Name,
        roomtype.Capacity,
        roomtype.Night_Price
    FROM 
        Room room
    JOIN 
        RoomType roomtype ON room.Room_Type_Id = roomtype.Room_Type_Id
    WHERE 
        roomtype.Room_Name = @RoomType AND room.Room_Status = 'Available';
END;

----------------------------------------------------------------------------------

CREATE PROCEDURE GetReservationDetails (
    @ReservationId INT
)
AS
BEGIN
    SELECT
        r.Reserve_Id,
        g.F_Name AS GuestFirstName,
        g.L_name AS GuestLastName,
        rm.Room_Number,
        rt.Room_Name AS RoomType,
        r.Check_IN,
        r.Check_Out,
        r.Total_Price
    FROM
        Reservation AS r
    JOIN
        Guest AS g ON r.Guest_Id = g.Guest_Id
    JOIN
        Room AS rm ON r.Room_Id = rm.Room_Number
    JOIN
        [RoomType] AS rt ON rm.Room_Type_Id = rt.Room_Type_Id
    WHERE
        r.Reserve_Id = @ReservationId;
END;

--------------------------------------------------------------------------------------
CREATE PROCEDURE GetGuestStayHistory (
    @GuestId INT
)
AS
BEGIN
    SELECT
        r.Reserve_Id,
        rm.Room_Number,
        rt.Room_Name AS RoomType,
        r.Check_IN,
        r.Check_Out,
        r.Total_Price
    FROM
        Guest AS g
    JOIN
        Reservation AS r ON g.Guest_Id = r.Guest_Id
    JOIN
        Room AS rm ON r.Room_Id = rm.Room_Number
    JOIN
        [RoomType] AS rt ON rm.Room_Type_Id = rt.Room_Type_Id
    WHERE
        g.Guest_Id = @GuestId
        AND r.Check_Out < GETDATE() 
    ORDER BY
        r.Check_Out DESC; 
END;
--------------------------------------------------------------------------------------
CREATE PROCEDURE UpdateRoomStatus (
    @RoomId INT,
    @NewStatus VARCHAR(100)
)
AS
BEGIN
    UPDATE Room
    SET Room_Status = @NewStatus
    WHERE Room_Number = @RoomId; 
END;
