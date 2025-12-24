# Bus Route Management System

A Java-based Bus and Route Management System utilizing Hibernate for ORM and MySQL for data persistence. This application demonstrates basic CRUD operations on `Bus` and `Route` entities.

## Technologies Used
- **Java**: 19
- **Hibernate Core**: 5.6.15.Final
- **MySQL Connector**: 8.0.33
- **Maven**: Build and dependency management

## Prerequisites
- Java Development Kit (JDK) 19 or higher
- Maven 3.x
- MySQL Server

## Setup & Installation

1.  **Clone the repository**
    ```bash
    git clone https://github.com/Venkat5674/Bus_route_management_system.git
    cd Bus_route_management_system
    ```

2.  **Database Configuration**
    - Ensure your MySQL server is running.
    - Create a database (e.g., `bus_db`) if not already configured in `hibernate.cfg.xml`.
    - Update `src/main/resources/hibernate.cfg.xml` with your database credentials:
      ```xml
      <property name="connection.url">jdbc:mysql://localhost:3306/your_database_name</property>
      <property name="connection.username">your_username</property>
      <property name="connection.password">your_password</property>
      ```

3.  **Build the Project**
    ```bash
    mvn clean install
    ```

## Usage

The application's entry point is `com.busmgmt.Main`. You can run it via Maven or your IDE.

**Using Maven:**
```bash
mvn exec:java -Dexec.mainClass="com.busmgmt.Main"
```

**Functionality:**
The `Main` class demonstrates the following operations:
1.  **Insert Buses**: Adds sample bus records.
2.  **Insert Routes**: Adds sample route records.
3.  **Fetch Route**: Retrieves a route by its ID.
4.  **Update Bus**: Updates the ticket price of a specific bus.
5.  **Delete Route**: Deletes a route by its ID.
6.  **Create Route**: Adds a new route interactively (programmatic example).

## Project Structure
- `src/main/java/com/busmgmt/entity`: Contains `Bus` and `Route` POJO classes.
- `src/main/resources`: Contains Hibernate mapping files (`.hbm.xml`) and configuration (`hibernate.cfg.xml`).
- `src/main/java/com/busmgmt/Main.java`: Main executable class demonstrating CRUD operations.
