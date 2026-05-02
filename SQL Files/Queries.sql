--service booked by each guest 
SELECT
	Guest.Guest_Id,
    Guest.F_Name AS GuestFirstName,
    Guest.L_name AS GuestLastName,
    [Service] .service_Name
FROM
    Guest 
JOIN
    ServiceBooking ON  Guest.Guest_Id = ServiceBooking .Guest_Id
JOIN
    Offers  ON ServiceBooking .Booking_Id =  Offers .Booking_Id AND  Guest.Guest_Id =  Offers .Guest_Id 
JOIN
    [Service]  ON  Offers .service_Id = [Service] .service_Id;


--Guests and Their Bookings
SELECT
    Guest.F_Name AS GuestFirstName,
    Guest.L_name AS GuestLastName,
    ServiceBooking .Booking_Id
FROM
    Guest 
JOIN
    ServiceBooking ON Guest.Guest_Id = ServiceBooking.Guest_Id;

--Total Revenue per Booking
SELECT
    Booking_Id,
    SUM(BookingDetails .Total_Price) AS TotalRevenue
FROM
    BookingDetails 
GROUP BY
    BookingDetails .Booking_Id;

--Employees and the department they are working for
SELECT 
	Employee.Employee_id ,
	Employee.F_Name AS 'Employee First Name',
	Employee.L_Name AS 'Employee Last Name' ,
	Department.Department_id,
	Department.Department_Name
FROM 
	Employee
JOIN
	Department ON Employee.Department_id=Department.Department_Id;
--service bookings and the associated offers' details
SELECT
    ServiceBooking.Booking_Id,
    Offers.service_Id AS OfferServiceID,
    [Service] .service_Name AS OfferServiceName
FROM
    ServiceBooking 
JOIN
    Offers  ON ServiceBooking .Booking_Id = Offers.Booking_Id 
JOIN
    [Service]  ON Offers.service_Id = [Service] .service_Id;

--Guest and Total amount paid 
SELECT
    Guest.F_Name AS GuestFirstName,
    Guest.L_name AS GuestLastName,
    SUM(Payment.Amount) AS TotalAmountPaid
FROM
    Guest 
JOIN
    Payment  ON Guest .Guest_Id = Payment.Guest_Id
GROUP BY
     Guest.F_Name, Guest.L_name
ORDER BY
    TotalAmountPaid DESC;

--guests orderd by first name 
SELECT *
FROM 
	Guest
ORDER BY
	L_name;
	
--rooms with night price>200
SELECT 
	Room_Number,
	Room_Name,
	Night_Price
FROM 
	Room
JOIN 
	RoomType ON Room.Room_Type_Id= RoomType.Room_Type_Id
WHERE 
	Night_Price>300;

--booking made in 2025-04-28 
SELECT *
FROM
    BookingDetails
WHERE
    Date_time= '2025-04-28 10:00';

--rooms and their type and price
SELECT 
		Room.Room_Number,
		RoomType.Room_Name ,
		RoomType.Night_Price
FROM
	Room
JOIN
	RoomType ON Room.Room_Type_Id=RoomType.Room_Type_Id;

--payments and corresponding reservation details 
SELECT
	 Guest .F_Name AS GuestFirstName,
     Guest .L_name AS GuestLastName,
     Payment.Pay_Id,
     Payment.Pay_Stat,
     Payment.Pay_Date,
     Payment.Amount,
     Payment.Pay_Meth,
	 Reservation.Total_Price AS ReservationTotalPrice,
     Reservation.Reserve_Id,
     Reservation.Reserve_stat,
     Reservation.Check_IN,
     Reservation.Check_Out  
FROM
    Payment
JOIN
    Guest  ON  Payment.Guest_Id =  Guest .Guest_Id
LEFT JOIN
    Reservation ON  Payment.Reserve_Id = Reservation.Reserve_Id;
--rooms orderd by price ascending order 
SELECT
     Room.Room_Number,
    RoomType.Room_Name AS RoomType,
    RoomType.Night_Price
FROM
    Room
JOIN
    RoomType ON  Room.Room_Type_Id = RoomType.Room_Type_Id
ORDER BY
    RoomType.Night_Price;
--guest , number of nights stayed and total amount 
SELECT
     Guest.F_Name AS GuestFirstName,
     Guest.L_name AS GuestLastName,
     RoomType.Night_Price,
     DATEDIFF(day, Reservation.Check_IN, Reservation.Check_Out) AS NumberOfNightsReserved,
    (RoomType.Night_Price * DATEDIFF(day, Reservation.Check_IN, Reservation.Check_Out)) AS TotalReservationAmount
FROM
    Guest 
JOIN
    Reservation  ON Guest.Guest_Id = Reservation.Guest_Id
JOIN
    Room ON Reservation.Room_Id = Room.Room_Number
JOIN
    RoomType  ON Room.Room_Type_Id = RoomType .Room_Type_Id
ORDER BY
    GuestLastName, GuestFirstName;


--Occupied Rooms with Guest Names
SELECT 
    room.Room_Number,
    room_type.Room_Name,
    guest.F_Name + ' ' + guest.L_name AS Guest_Name
FROM 
    Room room
JOIN 
    RoomType room_type ON room.Room_Type_Id = room_type.Room_Type_Id
JOIN 
    Reservation reservation ON room.Room_Number = reservation.Room_Id
JOIN 
    Guest guest ON reservation.Guest_Id = guest.Guest_Id
WHERE 
    room.Room_Status = 'Occupied';


--Services Booked by Each Guest
SELECT 
    guest.F_Name + ' ' + guest.L_name AS Guest_Name,
    service.service_Name,
    service.Price,
    booking_details.Date_time
FROM 
    Guest guest
JOIN 
    BookingDetails booking_details ON guest.Guest_Id = booking_details.Guest_Id
JOIN 
    Offers offers ON booking_details.Booking_Id = offers.Booking_Id
JOIN 
    [Service] service ON offers.service_Id = service.service_Id;


--Room Types with Their Capacity
SELECT 
    Room_Name,
    Capacity,
    Night_Price
FROM 
    RoomType
ORDER BY 
    Capacity, Night_Price;


--Number of Bookings Handled by Each Employee
SELECT 
    employee.Employee_Id,
    employee.F_Name + ' ' + employee.L_Name AS Employee_Name,
    COUNT(reservation.Reserve_Id) AS Number_of_Bookings_Handled
FROM 
    Employee employee
LEFT JOIN 
    Reservation reservation ON employee.Employee_Id = reservation.Employee_Id
GROUP BY 
    employee.Employee_Id, employee.F_Name, employee.L_Name;


--Booking Details with Guest Information
SELECT 
    reservation.Reserve_Id,
    guest.F_Name + ' ' + guest.L_name AS Guest_Name,
    guest.Email,
    guest.Phone_Num,
    reservation.Check_IN,
    reservation.Check_Out,
    reservation.Total_Price,
    reservation.Reserve_stat AS Status
FROM 
    Reservation reservation
JOIN 
    Guest guest ON reservation.Guest_Id = guest.Guest_Id;


--Bookings with Employees Handling
SELECT 
    reservation.Reserve_Id,
    guest.F_Name + ' ' + guest.L_name AS Guest_Name,
    employee.F_Name + ' ' + employee.L_Name AS Handling_Employee,
    department.Department_Name
FROM 
    Reservation reservation
JOIN 
    Guest guest ON reservation.Guest_Id = guest.Guest_Id
JOIN 
    Employee employee ON reservation.Employee_Id = employee.Employee_Id
JOIN 
    Department department ON employee.Department_Id = department.Department_Id;

--Available Services 
SELECT 
    Service_Name,
    Price,
    Description
FROM 
    [Service]
WHERE 
    Availability = 'YES';


--Services with Price < 100 
SELECT 
    service_Name,
    Price
FROM 
    [Service]
WHERE 
    Price < 400;


--Employees Ordered by Salary (High to Low)
SELECT 
    F_Name + ' ' + L_Name AS Employee_Name,
    Job_Title,
    Salary
FROM 
    Employee
ORDER BY 
    Salary DESC;


--Employees Filtered by Job Title
SELECT 
    F_Name + ' ' + L_Name AS Employee_Name,
    Department_Id,
    Salary
FROM 
    Employee
WHERE 
    Job_Title = 'Front Desk Agent';  -- Replace with any job title


--Guests with Multiple Active Bookings
SELECT 
    guest.F_Name + ' ' + guest.L_name AS Guest_Name,
    COUNT(reservation.Reserve_Id) AS Active_Bookings
FROM 
    Guest guest
JOIN 
    Reservation reservation ON guest.Guest_Id = reservation.Guest_Id
WHERE 
    reservation.Reserve_stat IN ('Booked', 'CheckedIn')
GROUP BY 
    guest.Guest_Id, guest.F_Name, guest.L_name
HAVING 
    COUNT(reservation.Reserve_Id) > 1;


