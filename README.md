# EmployeeREST
REST API that allows to manage employees and roles. Allows create, edit search and delete employees and roles.

# Technology
*JVM: Java 8, Spring (Core, Boot, Data JPA, Web), Hibernate.
*Database: H2.
*Tools: Maven, Lombok.

# Development
### Run project
To run project you should run Maven command:
`$ mvn spring-boot:run`

### Run methods
To run methods in API you can use many tools, for example Postman. You should select HTTP Metod and specify the operation url. Below is an example table with methods and queries for selected operations.

|HTTP Method   | Simply request |Description |
| ------------- | ------------- | ------------- |
| GET  | http://localhost:8080/api/employee  | To retrieve all employees or roles |  
| GET  | http://localhost:8080/api/employee?search=name&value=Ryszard  | To search employee(s) or role(s) based on atribute |
| POST  | http://localhost:8080/api/employee  | To create new employee or role |
| PUT  | http://localhost:8080/api/employee/3  | To edit employee or role |
| DELETE  | http://localhost:8080/api/employee/3  | To delete employee or role |
