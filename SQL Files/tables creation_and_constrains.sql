CREATE TABLE Guest(
Guest_Id INT IDENTITY(1,1) PRIMARY KEY,
F_Name VARCHAR(100) NOT NULL,
L_name VARCHAR(100) NOT NULL,
Email VARCHAR(100) NOT NULL UNIQUE,
Gender VARCHAR(100) NOT NULL check (Gender IN('Male','Female')),
Nationality VARCHAR(100),
SSN INT NOT NULL UNIQUE,
Birth_Date DATE NOT NULL,
Phone_Num VARCHAR(100),
G_Address VARCHAR(100),
);

CREATE TABLE [Service](
service_Id INT IDENTITY(1,1) PRIMARY KEY,
service_Name VARCHAR(100) NOT NULL UNIQUE,
service_category VARCHAR(100) NOT NULL ,
"Availability" VARCHAR(10) check("Availability" IN('YES','NO')),
Price DECIMAL(10,3) NOT NULL CHECK (Price >= 0),
"Description" VARCHAR(100)
);

CREATE TABLE Department(
Department_Id INT IDENTITY(1,1) PRIMARY KEY,
Department_Description VARCHAR(100),
Department_Name VARCHAR(100) NOT NULL UNIQUE,
);

CREATE TABLE Employee(
Employee_Id INT IDENTITY(1,1) PRIMARY KEY,
F_Name VARCHAR(100),
Department_Id INT NOT NULL,
FOREIGN KEY (Department_Id ) REFERENCES Department (Department_Id),
L_Name VARCHAR(100),
Email VARCHAR(100) UNIQUE,
Phone_Number VARCHAR(100),
Job_Title VARCHAR(100) NOT NULL,
Salary DECIMAL(10,3) NOT NULL CHECK(Salary >=0),
Gender VARCHAR(100) NOT NULL CHECK( Gender IN ('Female','Male'))
);

CREATE TABLE ServiceBooking(
Booking_Id INT IDENTITY(1,1) NOT NULL UNIQUE,-- partail
Guest_Id INT NOT NULL ,
Employee_Id INT NULL,
PRIMARY KEY (Booking_Id ,Guest_Id),
FOREIGN KEY (Guest_Id) REFERENCES Guest (Guest_Id),
FOREIGN KEY (Employee_Id) REFERENCES Employee (Employee_Id)--
);

CREATE TABLE BookingDetails(
Booking_Id INT NOT NULL,
Guest_Id INT NOT NULL,
PRIMARY KEY (Booking_Id , Guest_Id),
Date_time DATETIME2 NOT NULL ,
Total_Price DECIMAL(10,3) NOT NULL CHECK (Total_Price >= 0),
FOREIGN KEY (Booking_Id,Guest_Id) REFERENCES ServiceBooking (Booking_Id,Guest_Id) 
);

CREATE TABLE Offers(
Booking_Id INT  NOT NULL,
Service_Id INT NOT NULL,
Guest_Id  INT  NOT NULL,
PRIMARY KEY(Booking_Id, Service_Id, Guest_Id ),
FOREIGN KEY (service_Id) REFERENCES [Service] (service_Id),
FOREIGN KEY (Booking_Id,Guest_Id) REFERENCES ServiceBooking (Booking_Id ,Guest_Id)
);

CREATE TABLE RoomType(
Room_Type_Id INT IDENTITY(1,1) PRIMARY KEY,
Room_Name VARCHAR(100) NOT NULL,
Capacity INT NOT NULL CHECK (Capacity > 0),
Night_Price VARCHAR(100) NOT NULL CHECK (Night_Price >= 0)
);

CREATE TABLE Room(
Room_Number INT PRIMARY KEY,
Room_Status VARCHAR(100) NOT NULL CHECK (Room_Status IN ('Available','Occupied','Maintenance','Reserved')),
Room_Type_Id INT NULL,
FOREIGN KEY  (Room_Type_Id) REFERENCES "RoomType" (Room_Type_Id)
);

CREATE TABLE Payment(
Pay_Id INT IDENTITY(1,1) PRIMARY KEY,
Pay_Stat VARCHAR(100)  NOT NULL CHECK (Pay_Stat IN ('Pending','Completed','Failed','Refunded')),
Pay_Date Date NOT NULL ,
Amount DECIMAL(10,3) NOT NULL CHECK (Amount >=0),
Pay_Meth VARCHAR(100) NOT NULL CHECK( Pay_Meth IN ('Cash','Credit Card','Debit Card','Online')),
Guest_Id INT NOT NULL,
FOREIGN KEY  (Guest_Id) REFERENCES Guest (Guest_Id),
Reserve_Id INT NULL
);

CREATE TABLE Reservation(
Reserve_Id INT IDENTITY(1,1) PRIMARY KEY,
Reserve_stat VARCHAR(100) NOT NULL CHECK (Reserve_Stat IN ('Booked','CheckedIn','CheckedOut','Cancelled')),
Total_Price DECIMAL(10,3) NOT NULL CHECK (Total_Price >= 0),
Check_IN DATE NOT NULL,
Check_Out DATE NOT NULL,
Room_Id INT NOT NULL,
Guest_Id INT NOT NULL,
Pay_Id INT NULL,
Employee_Id INT NULL,
FOREIGN KEY  (Room_Id) REFERENCES Room (Room_Number),
FOREIGN KEY  (Guest_Id) REFERENCES Guest (Guest_Id),
FOREIGN KEY  (Pay_Id) REFERENCES Payment (Pay_Id),
FOREIGN KEY  (Employee_Id) REFERENCES Employee (Employee_ID)
);
ALTER TABLE Payment
	ADD FOREIGN KEY  (Reserve_Id) REFERENCES Reservation (Reserve_Id);
---------------------------------------------------------------------------
--deletetion constrains
--syntax:Child Table (FK Column)

--Employee(Department_Id) 
-- we do not want to drop a department  with employees still assigned
ALTER TABLE Employee
  ADD CONSTRAINT c_department_emp_fk_
    FOREIGN KEY (Department_Id) REFERENCES Department(Department_Id)
    ON DELETE NO ACTION

--for ServiceBooking--(Guest_Id)
-- we prevent deleting a guest who’s got service-booking records
ALTER TABLE ServiceBooking
  ADD CONSTRAINT c_servicebooking_guest_fk
    FOREIGN KEY (Guest_Id)
    REFERENCES Guest(Guest_Id)
    ON DELETE NO ACTION
-- ServiceBooking (Employee_Id)
--when we delete an emp their bookings handelings remain but fk =null
ALTER TABLE ServiceBooking
	ADD CONSTRAINT c_employee_servicebooking_fk
	FOREIGN KEY (Employee_Id) REFERENCES Employee(Employee_Id) 
	ON DELETE SET NULL

-- BookingDetails(Booking_Id, Guest_Id)
-- when we delete a service booking then delete its details too
ALTER TABLE BookingDetails
  ADD CONSTRAINT c_bookingdetails_servicebooking_fk
    FOREIGN KEY (Booking_Id, Guest_Id) REFERENCES ServiceBooking(Booking_Id, Guest_Id)
    ON DELETE CASCADE

-- Offers(service_Id)
-- we prevent deleting a service that’s been offered in past bookings
ALTER TABLE Offers
  ADD CONSTRAINT c_offers_service_fk
    FOREIGN KEY (service_Id) REFERENCES [Service](service_Id)
    ON DELETE NO ACTION;
-- Offers(Booking_Id, Guest_Id)
-- when we delete a service booking then delete its offers too
ALTER TABLE Offers
  ADD CONSTRAINT c_offers_servicebooking_fk
    FOREIGN KEY (Booking_Id, Guest_Id) REFERENCES ServiceBooking(Booking_Id, Guest_Id)
    ON DELETE CASCADE;

--Room(Room_Type_Id)
-- we do not want to delete a room type while rooms still reference it
ALTER TABLE Room
  ADD CONSTRAINT c_room_roomtype_fk 
  FOREIGN KEY (Room_Type_Id) REFERENCES RoomType (Room_Type_Id)
    ON DELETE NO ACTION;

-- Reservation(Room_Id)
-- we prevent deleting a room that still has reservations
ALTER TABLE Reservation
  ADD CONSTRAINT c_reservation_room_fk
    FOREIGN KEY (Room_Id) REFERENCES Room(Room_Number)
    ON DELETE NO ACTION;
-- Reservation(Guest_Id)
-- we prevent deleting a guest who has reservations
ALTER TABLE Reservation
  ADD CONSTRAINT c_reservation_guest_fk
    FOREIGN KEY (Guest_Id) REFERENCES Guest(Guest_Id)
    ON DELETE NO ACTION
-- Reservation(Pay_Id)
-- cannot delete a payment that have reservations//bec will pick other direction deleting cascade
ALTER TABLE Reservation
  ADD CONSTRAINT c_pay_fk
    FOREIGN KEY (Pay_Id) REFERENCES Payment(Pay_Id)
    ON DELETE NO ACTION
--Reservation(Employee_Id)
-- when we delete a empoyee the reservation not deleted but employee set to null
ALTER TABLE Reservation
	ADD CONSTRAINT C_employee_res_fk_
	FOREIGN KEY (Employee_Id) REFERENCES Employee(Employee_Id)
    ON DELETE SET NULL

-- Payment(Guest_Id)
-- we prevent deleting a guest who has payment history
ALTER TABLE Payment
  ADD CONSTRAINT c_payment_guest_fk
    FOREIGN KEY (Guest_Id)
    REFERENCES Guest(Guest_Id)
    ON DELETE NO ACTION

--Payment(Reserve_Id)
--when delete the reservation then delete its payment too
ALTER TABLE Payment
    ADD CONSTRAINT c_payment_reservation_fk
    FOREIGN KEY (Reserve_Id) REFERENCES Reservation(Reserve_Id)
    ON DELETE CASCADE