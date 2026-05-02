-- Update Guest table
UPDATE Guest
SET
    F_Name = 'Somaya',
    L_name = 'Ahmed',
    Email = 'somaya.email@example.com',
    Gender = 'Female',
	Nationality = 'Egyptian',
    SSN = 987654321,
    Birth_Date = '2004-01-15',
    Phone_Num = '01155748596',
    G_Address = '5 EL Haram,Giza' 
WHERE
    Guest_Id = 1; 

-- Update Service table
UPDATE [Service]
SET
    [service_Name] = 'Aromatherapy Massage',
    service_category = 'Massage Therapy',
    "Availability" = 'YES',
    Price = 150.000,
    "Description" = 'Gentle massage with essential oils'
WHERE
    service_Id = 1; 

-- Update Department table
UPDATE Department
SET
    Department_Description = 'Responsible for the preparation and cooking of food.',
    Department_Name = 'Kitchen'
WHERE
    Department_Id = 1; 

-- Update Employee table
UPDATE Employee
SET
    F_Name = 'Sara',
    L_Name = 'Ahmed',
    Email = 'saraemp@example.com',
    Phone_Number = '111-222-3333',
    Job_Title = 'Front Desk Agent',
    Salary = 6000.000,
    Gender = 'Female' 
WHERE
    Employee_Id = 1; 

-- Update ServiceBooking table
UPDATE ServiceBooking
SET
    Employee_Id = 5 
WHERE
    Booking_Id = 1 AND Guest_Id = 1; 

-- Update BookingDetails table
UPDATE BookingDetails
SET
    Date_time = GETDATE(), 
    Total_Price = 750.500
WHERE
    Booking_Id = 1 AND Guest_Id = 1; 

-- Update RoomType table
UPDATE RoomType
SET
    Room_Name = 'Single',
    Capacity = 1,
    Night_Price = '300'
WHERE
    Room_Type_Id = 1; 

-- Update Room table
UPDATE Room
SET
    Room_Status = 'Available', 
    Room_Type_Id = 2 
WHERE
    Room_Number = 101; 

-- Update Payment table
UPDATE Payment
SET
    Pay_Stat = 'Completed', 
    Pay_Date = GETDATE(),
    Amount = 650.000,
    Pay_Meth = 'Online'
WHERE
    Pay_Id = 1; 

-- Update Reservation table
UPDATE Reservation
SET
    Reserve_stat = 'CheckedOut', 
    Total_Price = 620.000,
    Check_IN = '2025-05-04',
    Check_Out = '2025-05-06',
    Room_Id = 102,
    Employee_Id = 6 
WHERE
    Reserve_Id = 1; 


SELECT * FROM Guest;
SELECT * FROM [Service];
SELECT * FROM Department;
SELECT * FROM RoomType;
SELECT * FROM Room;
SELECT * FROM Employee;
SELECT * FROM Payment;
SELECT * FROM Reservation;
SELECT * FROM ServiceBooking;
SELECT * FROM BookingDetails;
SELECT * FROM Offers;