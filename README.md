# SportBookingApp

Overview of Login & Register Feature

The aim is to create a secured environment for users to login and/or register.
MVC dataflow:

        VIEW --UserDetail--> CONTROLLER --UserDTO--> Service --UserEntity--> Database

Instructions on connecting to db via Docker Desktop and MySQL Workbench:

Download Docker Desktop here: https://docs.docker.com/desktop/install/windows-install/
Download MySQL Workbench: https://dev.mysql.com/downloads/workbench/ (v8.0.36)

Create a Docker container:
1. Move to designated directory
2. Git clone this repo
3. cd to "SportBookingApp"
4. docker-compose up
--> Checkpoint: Making sure the container shows up on Docker Desktop, continuously running.

Create a connection to MySQL Workbench:
1. Add a new connection on Home page
![image](https://github.com/jasienlt/SportBookingApp/assets/48340091/dab69615-43ed-448b-9faf-02a130d5da8c)
2. Fill in the detail to setup:
![image](https://github.com/jasienlt/SportBookingApp/assets/48340091/5233e324-91a7-474c-b402-cacf8788aaea)
  Connection name: booking/sport_app
  Hostname: 127.0.0.1
  Port: 3306
  Username: root
  Password: userroot
--> Click test connection to make sure the connection is valid. Ignore/Accept all warnings for incompatible version of MySQL.

Initiate the project
1. Run [text](src/main/java/com/developer/sportbooking/SportBookingApplication.java)
