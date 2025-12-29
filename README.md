# Smart Parking Management System

## Overview
The **Smart Parking Management System** is a console-based Java application that manages vehicle parking operations for **bikes, cars, and trucks**. The system allows users to park vehicles, vacate parking spots, search bookings, view available spots, and access administrative functions such as monitoring occupancy and total earnings.

This application demonstrates core programming concepts such as collections, object management, menu-driven interaction, and basic business logic.

---

## Features
### User Operations
- Park a vehicle and automatically allocate an available spot
- Enter vehicle and owner details
- Vacate a spot and generate a parking charge
- Search bookings by vehicle number
- View available parking spots in real time
- Exit the system safely

### Administrator Operations
(Admin password required)
- View total earnings
- Monitor real-time occupancy
- Add or remove parking spots
- Exit admin mode

---

## Vehicle Types & Charges
| Vehicle Type | Parking Charge |
|--------------|----------------|
| Bike         | $5.00          |
| Car          | $10.00         |
| Truck        | $20.00         |

---

## Technologies Used
- **Language:** Java  
- **Collections:** List, Map  
- **Concepts:** OOP basics, menu-driven console input, dynamic data storage  

---

## How It Works
1. The user selects an option from the main menu.
2. Vehicle parking automatically assigns the next available spot.
3. Booking details are stored for tracking.
4. When a vehicle vacates a spot, a fixed charge is applied and earnings are updated.
5. Administrator tools provide reporting and management capabilities.

---

## Default Parking Capacity
| Vehicle Type | Spots |
|--------------|-------|
| Bikes        | 3     |
| Cars         | 4     |
| Trucks       | 2     |

``` 
Spots are labeled:
- Bike: `B-1`, `B-2`, `B-3`
- Car: `C-1`, `C-2`, `C-3`, `C-4`
- Truck: `T-1`, `T-2`
```
---

## Administrator Access
Default Password:  admin123

## Program Menu
1. Park Vehicle
2. Vacate Spot
3. Display Available Spots
4. Search for a Booking
5. Administrator Access
6. Exit


---

## Example Use Case
- A user parks a car → system assigns `C-1`
- Owner details are recorded
- Later the spot is vacated
- System charges $10 and adds to total earnings
- Spot becomes available again

---

## Current Constraints
- Fixed pricing (time is not calculated)
- Basic input validation
- Admin can remove active spots
- Data is stored in memory only (no database/file persistence)

---

## Future Enhancements
- Time-based billing
- Persistent storage (database or file)
- Web or GUI interface
- Improved validation
- Role-based authentication
- Reporting dashboard

> ## Project Motivation
> This project was developed based on the idea of creating a simple and effective parking lot management solution. The goal is to demonstrate project idea, logical design, Java programming fundamentals, and problem-solving using console-based interaction.
