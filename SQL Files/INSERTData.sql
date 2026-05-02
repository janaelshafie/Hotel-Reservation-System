-- Inserting sample data into Guest table
INSERT INTO Guest (F_Name, L_name, Email, Gender, Nationality, SSN, Birth_Date, Phone_Num, G_Address) VALUES
('Ahmed', 'Ali', 'ahmed.ali2@example.com', 'Male', 'Egyptian', 12345, '1990-05-15', '01012345678', '10 Al Nasr St, Cairo'),
('Sara', 'Mohamed', 'sara.mohamed@example.com', 'Female', 'Egyptian', 9876, '1988-11-20', '01111223344', '5 El Haram, Giza'),
('John', 'Doe', 'john.doe@example.com', 'Male', 'American', 1122, '1985-03-01', '01234567890', '789 Main St, New York'),
('Fatima', 'Khaled', 'fatima.khaled@example.com', 'Female', 'Saudi', 5544, '1992-07-10', '01555667788', 'Al Olaya, Riyadh'),
('Jana', 'Hany', 'jana@example.com', 'Female', 'Egyptian', 3000, '2004-11-17', '0123456789', '5th Settlement');

-- Inserting sample data into [Service] table
INSERT INTO [Service] (service_Name, service_category, "Availability", Price, "Description") VALUES
('Standard Massage', 'Spa', 'YES', 500.00, 'Relaxing full body massage'),
('Facial Treatment', 'Spa', 'YES', 350.00, 'Deep cleansing facial'),
('Room Service - Breakfast', 'Dining', 'YES', 150.00, 'Continental breakfast'),
('Laundry Service', 'Other', 'YES', 100.00, 'Standard laundry service'),
('Airport Shuttle', 'Transportation', 'NO', 200.00, 'Transportation to/from airport');

-- Inserting sample data into Department table
INSERT INTO Department (Department_Description, Department_Name) VALUES
('Handles guest check-ins and check-outs', 'Front Desk'),
('Provides cleaning and maintenance of rooms', 'Housekeeping'),
('Offers relaxation and wellness services', 'Spa'),
('Manages employee records and HR functions', 'Human Resources');

-- Inserting sample data into [Room Type] table
INSERT INTO RoomType (Room_Name, Capacity, Night_Price) VALUES
('Single', 1, '300'),
('Double', 2, '500'),
('Double', 3, '500'),
('Suite', 4, '800');

-- Inserting sample data into Room table
INSERT INTO Room (Room_Number, Room_Status, Room_Type_Id) VALUES
('101', 'Available', 1),
('102', 'Occupied', 2),
('103', 'Available', 3),
('104', 'Available', 4);

-- Inserting sample data into Employee table
INSERT INTO Employee (F_Name, Department_Id, L_Name, Email, Phone_Number, Job_Title, Salary, Gender) VALUES
('Ahmed', 1, 'Hassan', 'ahmed.hassan@example.com', '01098765432', 'Front Desk Agent', 6000.00, 'Male'),
('Mona', 2, 'Ali', 'mona.ali@example.com', '01123456789', 'Housekeeping Supervisor', 7500.00, 'Female'),
('Youssef', 3, 'Mahmoud', 'youssef.mahmoud@example.com', '01211223344', 'Spa Therapist', 8000.00, 'Male'),
('Nadia', 4, 'Kamal', 'nadia.kamal@example.com', '01555443322', 'HR Manager', 10000.00, 'Female'),
('mohamed', 1, 'Mahmoud', 'mohamed.mahmoud@example.com', '01111223344', 'Spa Therapist', 8000.00, 'Male'),
('lila', 1, 'Kamal', 'laila.kamal@example.com', '01555444322', 'HR Senior Manager', 10000.00, 'Female');

-- Inserting  into Payment table
INSERT INTO Payment (Pay_Stat, Pay_Date, Amount, Pay_Meth, Guest_Id, Reserve_Id) VALUES
('Completed', '2025-04-28', 600.00, 'Credit Card', 1,NULL),
('Pending', '2025-04-29', 500.00, 'Cash', 2,NULL),  
('Completed', '2025-04-27', 1600.00, 'Online', 3,NULL),  
('Completed', '2025-04-29', 300.00, 'Debit Card', 4,NULL); 

-- Inserting  into Reservation table
INSERT INTO Reservation (Reserve_stat, Total_Price, Check_IN, Check_Out, Room_Id, Guest_Id, Pay_Id, Employee_Id) VALUES
('CheckedIn', 600.00, '2025-04-28', '2025-04-30', 101, 1, 1, 1),
('Booked', 500.00, '2025-05-05', '2025-05-06', 102, 2, 2, 2),
('CheckedOut', 1600.00, '2025-04-25', '2025-04-27', 103, 3, 3, 3),
('Booked', 300.00, '2025-05-10', '2025-05-11', 104, 4, 4, 4);

-- Updating Payment table with Reserve_Id
UPDATE Payment SET Reserve_Id = 1 WHERE Pay_Id = 1;
UPDATE Payment SET Reserve_Id = 2 WHERE Pay_Id = 2;
UPDATE Payment SET Reserve_Id = 3 WHERE Pay_Id = 3;
UPDATE Payment SET Reserve_Id = 4 WHERE Pay_Id = 4;

-- Inserting sample data into ServiceBooking table
INSERT INTO ServiceBooking (Guest_Id, Employee_Id) VALUES
(1, 1),
(2, 2), 
(3, 3), 
(4, 4); 

-- Inserting sample data into BookingDetails table
INSERT INTO BookingDetails (Booking_Id,Guest_Id, Date_time, Total_Price) VALUES
(1, 1, '2025-04-28 10:00', 500.00),
(2, 2, '2025-05-05 14:00', 150.00), 
( 3,3, '2025-04-26 16:00', 350.00), 
( 4,4, '2025-04-29 09:00', 100.00); 

-- Inserting sample data into Offers table (linking bookings to services)
INSERT INTO Offers (Booking_Id, service_Id, Guest_Id) VALUES
(1, 1, 1), 
(1, 2, 1),
(1 ,3, 1) ,
(2, 2, 2), 
(3, 3, 3), 
(4, 4, 4);


-- Selecting all data to verify insertion
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
