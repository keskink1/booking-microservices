# Booking & Notification Microservices

This repository contains a microservices-based application for booking management and notifications, built using Spring Boot and cloud-native patterns.

---

## Features

- **Spring Boot 3.x** for service development
- **JPA / Hibernate** for ORM with **PostgreSQL**
- **Event-driven architecture** using **RabbitMQ**
- **Eureka Server** for service discovery
- **Spring Cloud Config Server** for centralized configuration
- **API Gateway** for routing requests between microservices
- **Swagger / OpenAPI** documentation for all endpoints
- **Resilience patterns** using **Resilience4j**:
    - Circuit Breaker
    - Retry Pattern
    - Rate Limiter
- **Docker** support for containerization
- **Redis** for caching and rate limiting

---
## Architecture

- API Gateway: Central layer that routes all incoming requests between services.

- BookingService: Manages bookings and reservations.

- Technologies used: Spring Boot, JPA/Hibernate, PostgreSQL, RabbitMQ

- NotificationService: Handles notifications for bookings.

- Technologies used: Spring Boot, JPA/Hibernate, PostgreSQL, RabbitMQ

- Service Discovery: Services register and discover each other via Eureka Server.

- Configuration: Centralized configuration managed with Spring Cloud Config Server.

- Event-driven communication: Services communicate asynchronously using RabbitMQ.

- Resilience: Circuit breaker, retry pattern, and rate limiting implemented using Resilience4j.

- Containerization: Services are containerized using Docker.

- Caching / Rate Limiting: Redis is used for caching and controlling request rates.

---

## Endpoints

### Booking Service

| Method | URL                         | Description                    |
|--------|-----------------------------|--------------------------------|
| GET    | /api/bookings/test           | Test booking service           |
| POST   | /api/bookings/create         | Create a new booking           |
| GET    | /api/bookings/{id}           | Get booking by ID              |
| GET    | /api/bookings/all            | Get all bookings               |
| PUT    | /api/bookings/update/{id}    | Update a booking               |
| DELETE | /api/bookings/delete/{id}    | Delete a booking               |
| GET    | /api/bookings/contact-info   | Get booking service information|

### Notification Service

| Method | URL                              | Description                    |
|--------|----------------------------------|--------------------------------|
| GET    | /api/notifications/test           | Test notification service       |
| GET    | /api/notifications/{id}           | Get notification by ID          |
| GET    | /api/notifications/all            | Get all notifications           |
| DELETE | /api/notifications/delete/{id}    | Delete a notification           |
| GET    | /api/notifications/contact-info   | Get notification service info   |

---

## Example Requests (Postman)

### Create Booking

POST /api/bookings/create

Content-Type: application/json

```json
{
  "userName": "John Doe",
  "email": "john.doe@example.com",
  "appointmentTime": "2025-12-01T14:30:00"
}
```
---

### Update Booking

PUT /api/bookings/update

Content-Type: application/json
```json
{
  "appointmentTime": "2025-12-02T15:00:00"
}
```