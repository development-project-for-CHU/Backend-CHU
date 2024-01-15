# Patient Management System - API Development Assignment

## Project Description:

In our ongoing efforts to enhance our patient management system, we're focusing on an API-first development approach. This project aims to extend our system to include various patient-related categories such as known diagnostics, current medication, pregnancy tracking (for female patients), and other specific conditions. This complements our existing implementation of the allergies category.

## Business Requirements:

Your task is to develop RESTful API endpoints for the following patient management categories:

1. **Known Diagnostics API**:
    - Develop endpoints to manage patient diagnostic records.
    - Feature Details:
        - Endpoint: `/api/v1/categories/partieCommune/diagnostics`
        - Methods: `GET`, `POST`, `PUT`, `DELETE`
        - Support CRUD operations for patient diagnostic records.
        - Include search functionality by diagnostic name or date.
        - Task: Implement search functionality for diagnostic records.

2. **Current Medication API**:
    - Create endpoints for handling patient medication details.
    - Feature Details:
        - Endpoint: `/api/v1/categories/partieCommune/medication`
        - Methods: `GET`, `POST`, `PUT`, `DELETE`
        - Enable CRUD operations for managing current medications of patients.
        - Provide search by medication name or prescribed date.
        - Task: Implement search functionality for medication records. (`GET` ==> `/search`)

3. **Pregnancy Tracking API** (For Female Patients):
    - Implement endpoints to track pregnancy-related information.
    - Feature Details:
        - Endpoint: `/api/v1/categories/partieCommune/pregnancy`
        - Methods: `GET`, `POST`, `PUT`
        - Manage pregnancy details and updates for female patients.
      - Task: Implement search functionality for medication records. (`GET` ==> `/search`)

4. **Specific Conditions API**:
    - Develop APIs to handle unique medical conditions of patients.
    - Feature Details:
        - Endpoint: `/api/v1/categories/partieCommune/conditions`
        - Methods: `GET`, `POST`, `PUT`, `DELETE`
        - Support CRUD operations and allow filtering by condition name or severity.
        - Task: Implement search functionality for medication records. (`GET` ==> `/search`)

## Assignment:

- Implement the above API endpoints following the RESTful design principles.
- Ensure robust error handling, particularly for edge cases.
- Write comprehensive unit tests for each endpoint to validate functionality.
- Document each endpoint using Swagger or Postman. Include detailed information on request/response formats, headers, and possible error codes.

## Technical Guidelines:

- Use Java with Spring Boot for backend development.
- Organize code according to MVC patterns and structure it in clearly defined packages.
- Utilize Spring Data JPA for database interactions.
- Apply validation and exception handling where necessary to ensure data integrity and proper response handling.

## Development Process:

1. **Initial Setup**:
     - Clone the project repository and set up your local development environment.
     - Familiarize yourself with the existing codebase, especially the `AllergieController` , `AllergieService` ...

2. **API Development**:
    - Develop the APIs as per the specifications.
    - Make sure to encapsulate business logic in the service layer.
    - Follow RESTful practices and ensure consistency with the existing code structure.

3. **Testing and Documentation**:
    - Perform unit testing for all the endpoints.
    - Use Swagger or Postman for API documentation.
    - Ensure that all tests pass and cover a wide range of scenarios.
