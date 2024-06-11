# Face Cream Recommendation and Purchasing System

**Team Collaboration:**
- Anastasija Drašković SV67/2020
- Kristina Popov SV5/2020

**Introduction:**

Our face cream recommendation and purchasing system aims to provide personalized recommendations for face creams based on individual skin types and preferences. Additionally, it facilitates easy purchasing of the recommended products. The system is designed to enhance the skincare experience for users while ensuring efficiency and convenience.

# Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Angular
**Installing**

To install the necessary libraries, run:

```
npm install
npm install @angular/material @angular/cdk
npm install @angular/animations
npm install @angular/material-moment-adapter
npm install @angular/flex-layout
npm install ngx-material-timepicker
npm install @auth0/angular-jwt
npm install @angular/router
```

**Running**

To start the application, run:

```
ng serve
```

## Spring Boot

**Installing**

1. To install the necessary model dependencies, run:
   ```
   cd model
   npm ci
    ```
2. To install the necessary kjar dependencies, run:
     ```
   cd kjar
   npm ci
    ```
3. To install the necessary service dependencies, run:
    ```
   cd service
   npm ci
    ```
**Running**

To start the Spring Boot application, navigate to the root directory of the project and run:

```bash
mvn spring-boot:run
