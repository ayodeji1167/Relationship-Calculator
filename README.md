# Reletionship Prediction Web App


I developed an web application that predicts your relationship with another user using Spring MVC 

### Functionalities:

1. User Registeration
* Registration To the web app


2. **Email Verification**

* After Registration, User has to verify their account through the email given in the registration form
* Link Will be sent to the emial of the  user


3. **Security Implementation**

* Method Levle Security and http ant-matchers security method
* There are two different endpoint for  registration (one for USER and the other for ADMIN)
* USERS can only access the Relationship Prediction Features
* ADMIN can acess everything USERS can access PLUS another section on EMPLOYEE MANAGEMENT in the same application




4. **CRUD Operations for EMPLOYEE**

* ADMIN can perform all CRUD operations on EMPLOYEE


5. Configure your database configuration in **application.properties**.

   * Database properties:

 
                      spring.jpa.database-platform=YOUR DB DIALECT
                      spring.datasource.driver-class-name=YOUR DB DRIVER CLASS NAME


                      spring.datasource.username= YOUR DB USERNAME
                      spring.datasource.password= YOUR DB PASSWORD
                      spring.jpa.hibernate.ddl-auto = create

### Tools and Technologies:

* **Technology** : Bootstrap, Java, Spring MVC, Hibernate, JSP, Maven.
* **Application Servicer**: Apache Tomcat Server.
* **Database** : MySQL
Contributors are most welcome.



