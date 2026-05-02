EXEC GetReservationsByDateRange 
    @StartDate = '2025-05-01', 
    @EndDate = '2025-05-31';

DECLARE @Result DECIMAL(10,3);
EXEC  CalculateTotalReservationPrice
    @Check_IN ='2025-04-28',
	@Check_Out ='2025-04-30',
	@TotalPrice=@Result OUTPUT; 
PRINT @Result;

EXEC GetRoomTypeByRoomNumber 
    @RoomType = 'single';

DECLARE @Total DECIMAL(10,3);
EXEC BookingTotalPrice 
	 @Booking_Id =1,
	 @Guest_Id =1,
	 @result = @Total OUTPUT;
print @Total;


EXEC GetReservationDetails
    @ReservationId = 1; 


EXEC GetGuestStayHistory
    @GuestId = 1; 

EXEC UpdateRoomStatus
    @RoomId = 101,
    @NewStatus = 'Available';
SELECT Room_Status ,Room_Number  FROM Room WHERE Room_Number =101;