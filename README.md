# Hotel Reservation System

Hotel Reservation System is a JavaFX desktop application for managing hotel operations through a dashboard-driven interface. The app follows an MVC-style structure and provides separate management screens for guests, employees, departments, payments, bookings, services, rooms, and reservations.

## Features

- Dashboard home screen for navigating between modules
- Guest management
- Employee management
- Department management
- Payment management
- Booking management
- Service booking management
- Room management
- Reservation management
- FXML-based UI with controller classes for each module

## Tech Stack

- Java 21
- JavaFX 21
- Maven
- JDBC
- Microsoft SQL Server

## Project Structure

- `src/main/java/org/example/demo/App.java` - JavaFX application entry point
- `src/main/java/org/example/demo/Controller/` - FXML controllers for each module
- `src/main/java/org/example/demo/Model/` - Database access and business logic
- `src/main/resources/org/example/demo/` - FXML views
- `SQL Files/` - Database scripts, queries, procedures, inserts, and updates

## Requirements

- JDK 21
- Maven 3.9+ recommended
- JavaFX 21 dependencies are managed through Maven
- A running Microsoft SQL Server instance

## Database Setup

The model classes connect to a SQL Server database named `HotelReservation` using a JDBC URL that currently uses a specific server name on port `1433` with integrated security enabled. You can change the server name in the connection string to match your own SQL Server setup.

Before running the application, make sure:

1. The SQL Server database has been created.
2. The tables and constraints script has been executed.
3. Any required insert, function, procedure, query, or update scripts have been applied as needed.
4. The connection string in the model classes matches your local SQL Server setup.

## Run the Application

From the `demo` folder, run:

```bash
mvn clean javafx:run
```

If you prefer to use the Maven wrapper, run:

```bash
./mvnw clean javafx:run
```

On Windows PowerShell, you can also use:

```powershell
.\mvnw.cmd clean javafx:run
```

## Notes

- The app opens the dashboard first.
- Navigation buttons load the corresponding management FXML screens.
- If the UI starts but data actions fail, check the SQL Server connection and the database scripts first.
