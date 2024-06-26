# Train Ticket Booking API

This project is a Java application developed using Spring Boot framework to facilitate booking train tickets from London to France.

## Overview
The application provides RESTful APIs to perform various operations related to ticket booking, user management, and seat allocation on a train journey from London to France.

## Features

- **Ticket Booking**: Users can submit a purchase for a ticket specifying the journey details.
- **Receipt Generation**: Upon successful booking, a receipt is generated containing journey details and the price paid.
- **Seat Allocation**: Users are allocated a seat in one of the two sections - Section A or Section B.
- **User Management**: APIs to view users, their allocated seats, remove users, and modify their seats.
- **Swagger Documentation**: The APIs are documented using Swagger for easy reference and testing.
- **Unit Testing**: The project includes unit tests to ensure code reliability.

## APIs

1. **Ticket Purchase**:
   - Endpoint: �/ticket/purchase�
   - Method: POST
   - Request Body: 
     {
       "from": "London",
       "to": "France",
       "user": {
         "firstName": "firstName",
         "lastName": "lastname",
         "email": "example@example.com"
       }
     }
      - Response: Returns receipt details.

2. **View Receipt Details**:
   - Endpoint: `/ticket/receipt/{email}`
   - Method: GET
   - Path Variable: `email`
   - Response: Returns receipt details for the specified user.

3. **View Users and Seats by Section**:
   - Endpoint: `/user/section/{section}`
   - Method: GET
   - Path Variable: `section` (A or B)
   - Response: Returns list of users and their allocated seats in the specified section.

4. **Remove User from Train**:
   - Endpoint: `/user/remove/{email}`
   - Method: DELETE
   - Path Variable: `email`
   - Response: Returns success message upon removal of the user.

5. **Modify User's Seat**:
   - Endpoint: `/user/modify/{email}`
   - Method: PUT
   - Path Variable: `email`
   - Request Body:
     
   - Response: Returns success message upon modification of user's seat.

## Usage

1. Clone the repository.
2. Build the project using Maven.
3. Run the application.
4. Access Swagger documentation to explore and test APIs.

## Dependencies

- Spring Boot
- Spring Web
- Springfox Swagger (for API documentation)
- JUnit (for unit testing)

## Getting Started

To get started with the project, follow these steps:

1. Clone this repository.
2. Import the project into your preferred IDE.
3. Build the project using Maven.
4. Run the application.
5. Access Swagger UI at `http://localhost:9090/swagger-ui.html` to explore and test the APIs.
6. Find Postman collection requests and URLs in the `postman_collection` folder for easy testing and integration.
