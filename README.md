# Car Rental Microservices

A modern, scalable microservices-based car rental system built with Spring Boot and cloud-native principles.

## 🚀 Features

### User Service
- User registration and management
- Role-based access control (Customer, Admin, Manager)
- JWT-based authentication (Coming Soon)
- User profile management

### Vehicle Service
- Vehicle catalog management
- Vehicle availability tracking
- Advanced search and filtering
- Vehicle status management

### Booking Service
- Reservation management
- Booking lifecycle (create, update, cancel)
- Availability validation
- Booking history

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **H2 Database** (Development)
- **MapStruct** for DTO mapping
- **Lombok** for reducing boilerplate
- **Swagger/OpenAPI** for API documentation
- **Maven** for dependency management

## 📦 Project Structure

```
car-rental-microservices/
├── userservice/         # User management service
├── vehicleservice/      # Vehicle catalog service
├── bookingservice/      # Booking management service
└── commonlibrary/       # Shared components and utilities
```

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher

### Running the Services

1. **Clone the repository**
   ```bash
   git clone https://github.com/Jimmylawson/car-rental-microservice.git
   cd car-rental-microservice
   ```

2. **Build all services**
   ```bash
   mvn clean install
   ```

3. **Run services individually**
   ```bash
   # User Service
   cd userservice
   mvn spring-boot:run
   
   # Vehicle Service
   cd ../vehicleservice
   mvn spring-boot:run
   
   # Booking Service
   cd ../bookingservice
   mvn spring-boot:run
   ```

## 🔧 API Documentation

Each service comes with Swagger UI for API documentation:

- **User Service**: http://localhost:8081/swagger-ui.html
- **Vehicle Service**: http://localhost:8082/swagger-ui.html
- **Booking Service**: http://localhost:8083/swagger-ui.html

## 🧪 Testing

Run tests for all services:
```bash
mvn test
```

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📫 Contact

Project Link: [https://github.com/Jimmylawson/car-rental-microservice](https://github.com/Jimmylawson/car-rental-microservice)
