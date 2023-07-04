# Backend-Application-Internshala


## Installation

1. Clone the repository: `git clone https://github.com/your-username/project.git`
2. Navigate to the project directory: `cd project`
3. Install the required dependencies:
   - If you're using Maven: `mvn install`
   - If you're using Gradle: `gradle build`
4. Configure the application properties:
   - Open the `application.properties` file.
   - Set the necessary database connection properties.
   - Configure JWT properties such as secret key and expiration time.
   - Save the file.

## Setup

1. Run the application:
   - If you're using Maven: `mvn spring-boot:run`
   - If you're using Gradle: `gradle bootRun`
2. The application should now be running locally at `http://localhost:8080`.
3. Access the API documentation at `http://localhost:8080/api-docs` for details on available endpoints and how to use them.

## Usage

1. To access the API endpoints that require authentication, you need to obtain a JWT token.
2. Make POST request to `/register` to register student(for eg)
3. Make a POST request to the `/login` endpoint with valid credentials to receive a token.
4. Include the token in the `Authorization` header of subsequent requests as a Bearer token.
5. You can now access the protected endpoints by sending authenticated requests.
6. This project also has endponits for forgot password and mail OTP.

## Testing

1. Unit tests: Run the following command:
   - If you're using Maven: `mvn test`
   - If you're using Gradle: `gradle test`
2. Integration tests: Run the following command:
   - If you're using Maven: `mvn integration-test`
   - If you're using Gradle: `gradle integrationTest`

## Deployment

1. Prepare the application for deployment:
   - Build an executable JAR file:
     - If you're using Maven: `mvn package`
     - If you're using Gradle: `gradle bootJar`
   - Alternatively, build a WAR file for deployment to a servlet container.
2. Deploy the generated JAR/WAR file to your preferred deployment environment.
3. Configure the necessary environment variables or system properties, such as database connection details and JWT properties.
4. Start the application in the deployment environment.
5. The application should now be accessible at the specified deployment URL.


