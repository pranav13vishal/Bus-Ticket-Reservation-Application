# Bus-Ticket-Reservation-Application

> Bus Ticket Reservation Application is a full-stack web application designed to streamline the process of reserving bus tickets. It provides an intuitive interface for users to book, update, and view tickets while maintaining robust backend functionality for data management.It stores customers' personal data records, scheduled routes and other information.
> The application was developed at [GUVI](https://www.guvi.in/) as part of the Java Full Stack Developer Course.

# 👉 [Click here](https://drive.google.com/file/d/1wqEWr9GGw6WX81MjbW4P81o9WDlmlVFj/view?usp=sharing) to go through the working demo of this application

# Features

- User and Admin authentication and validation 
- Admin can provide details like Bus Name,Bus Number, Bus Route, seats, date, time, price
- Admin can provide details of route
- Admim has a sepeate dashboard to add buses, delete buses, edit buses, view registered users 
- User can register by giving all the details
- User can book ticket by selecting Source and destination and date
- User on succesfull booking will receive a pdf
- User has a sepeate dashboard to view his booking and edit his details 

# Technology used 

- Java
- MySQL
- Spring Boot
- Spring data JPA
- Spring Security
- Spring Validation
- thymleaf
- RESTful api
- Hibernate
- Swagger
- Lombok
- Maven
- Postman
- GitHub
- Junit

# Modules

- Login Logout Module
- Admin Module
- User Module
- Bus Route Module
- Reservation Module

# Lessons Learned

- Gain excessive knowledge on application of Java, MySQL and SpringBoot.
- Gain knowledge on creating RESTful API.
- Gain knowledge on creating thymleaf based frontend.
- Got to know how to decode the errors and testing.
- Enjoyed the process of learning and creating the application.


# Overview of Our work

## **Swagger Module** 
![Swagger Module*](https://github.com/pranav13vishal/Bus-Ticket-Reservation-Application/blob/b7d9dcafa264767b3ec6d5309fa8e9482e3aeefa/assets/swagger.png)

## **User Module**
![User Module](https://github.com/pranav13vishal/Bus-Ticket-Reservation-Application/blob/b7d9dcafa264767b3ec6d5309fa8e9482e3aeefa/assets/user.png)

## **Login Module** 
![Login Module](https://github.com/pranav13vishal/Bus-Ticket-Reservation-Application/blob/b7d9dcafa264767b3ec6d5309fa8e9482e3aeefa/assets/login.png)

## **Admin Module**
![Admin Module](https://github.com/pranav13vishal/Bus-Ticket-Reservation-Application/blob/b7d9dcafa264767b3ec6d5309fa8e9482e3aeefa/assets/admin.png)

## **Home Module** 
![Home Module*](https://github.com/pranav13vishal/Bus-Ticket-Reservation-Application/blob/b7d9dcafa264767b3ec6d5309fa8e9482e3aeefa/assets/home.png)

## **Reservation Module**
![Reservation Module](https://github.com/pranav13vishal/Bus-Ticket-Reservation-Application/blob/b7d9dcafa264767b3ec6d5309fa8e9482e3aeefa/assets/bookings.png)


# How To Run Our Project In Your Machine

1. Clone our Project into your system
    - Go to the folder location you want to clone
    - Open terminal 
    - Run command ``` git clone https://github.com/pranav13vishal/Bus-Ticket-Reservation-Application.git```
2. Open your preferred Integrated Development Environment (IDE) such as Spring Tool Suite (STS) or IntelliJ IDEA.
3. Import the cloned project into your IDE:
   - In IntelliJ IDEA, click on "File" in the top menu, then select "Open"
4. Select the project directory where you cloned the repository and click "OK" or "Open" to import the project.
5. You can run this project with MySQL db 
    -Update the username and password as per your local database config.
```
    spring.datasource.url=jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME
    spring.datasource.username=YOUR_DATABASE_USERNAME
    spring.datasource.password=YOUR_DATABASE_PASSWORD
```
6. Build the project and run 
7. To access database
    - For MySQL, you can use ```MySQL Workbench``` as shown in explanation video
8. Open Swagger UI ```http://localhost:8080/swagger-ui/#/``` and execute the end points as in explanation video
    - comment all the clases under the controller
9. To view the thymleaf pages run the application and go to ```http://localhost:8080/``` 
    - comment all the clases under the controllerRestApi       

# THANK YOU EVERYONE FOR VISITING OUR PROJECT




