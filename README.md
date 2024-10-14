# JSON to PDF Spring Boot Project

This project is a Spring Boot application that reads data from a JSON file and converts it to a PDF format. It includes a REST API that can be accessed via HTTP requests using tools like Postman.

## Features
- Reads data from JSON files.
- Converts JSON data into a PDF document.
- REST API for handling HTTP requests.

## Technologies Used
- Java
- Spring Boot
- JSON
- PDF generation libraries
- Maven (build tool)

## Getting Started

### Prerequisites
- Java 11 or later
- Maven 3.6+
- Postman (for testing API endpoints)

### Running the Application
1. Clone the repository:
   ```bash
   git clone https://github.com/CrawL0/json-to-pdf-spring-boot-.git
   cd json-to-pdf-spring-boot
2.  Build the project
   Use Maven to build the project and download dependencies:
  ```bash
  mvn clean install
3.  Run the application
  Start the Spring Boot application:
  ```bash

Created by Anıl Göğ
  mvn spring-boot:run
4.  Test with Postman
  You can use Postman to interact with the REST API endpoint:

  GET request: http://localhost:8080/api/read-trades
  This endpoint will read trade data from the JSON file and process it.
