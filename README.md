Technologies Used
Frontend

Angular - Modern web framework
TypeScript - Type-safe JavaScript
CSS - Styling

Backend

Java Spring Boot - REST API framework
Maven - Build and dependency management


Prerequisites
Before running the application, make sure you have installed:

Node.js (v14 or higher) - Download here
npm (comes with Node.js)
Java JDK (v11 or higher) - Download here
Maven (v3.6 or higher) - Download here


Installation
1. Clone the repository
bashgit clone https://github.com/TheodorosIoannou/handball-manager.git
cd handball-manager
2. Install Frontend Dependencies
bashcd frontend
npm install
cd ..
3. Install Backend Dependencies
Maven will automatically download dependencies when you run the application.

Running the Application
Step 1: Start the Frontend
Open a terminal and run:
bashcd frontend
npm start
You should see output similar to:
> handball-team-app@0.0.0 start
> ng serve

Initial chunk files | Names         | Raw size
main.js             | main          | 48.32 kB | 
styles.css          | styles        | 95 bytes | 
                    | Initial total | 48.42 kB

Application bundle generation complete. [3.532 seconds]
Watch mode enabled. Watching for file changes...

  ➜  Local:   http://localhost:4200/
  ➜  press h + enter to show help
Step 2: Start the Backend
Open a NEW terminal (keep the first one running!) and run:
bashcd backend
mvn spring-boot:run
Wait for the Spring Boot application to start. You should see:
Started HandballManagerApplication in X.XXX seconds
Step 3: Access the Application
Open your browser and navigate to:
http://localhost:4200/

API Endpoints
Players

GET /api/players - Get all players
GET /api/players/{id} - Get player by ID
POST /api/players - Create new player
PUT /api/players/{id} - Update player
DELETE /api/players/{id} - Delete player

Events

GET /api/events - Get all events
GET /api/events/{id} - Get event by ID
POST /api/events - Create new event
PUT /api/events/{id} - Update event
DELETE /api/events/{id} - Delete event

Attendance

GET /api/attendance - Get all attendance records
POST /api/attendance - Record attendance
PUT /api/attendance/{id} - Update attendance

Statistics

GET /api/statistics/player/{id} - Get player statistics
GET /api/statistics/team - Get team statistics

Author
Theodoros Ioannou