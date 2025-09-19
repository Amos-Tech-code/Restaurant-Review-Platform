# 🍽️ Restaurant Review Platform

A **Spring Boot 3** application that allows users to discover restaurants, write reviews, and upload photos.  
Built with a clean, layered architecture to demonstrate **best practices in backend development**.

---

## 🚀 Features
- 🔐 Secure API endpoints with **Spring Security**
- 🗄️ Data persistence using **Spring Data Elasticsearch**
- 📝 Domain-driven design with **DTOs & Entities**
- ⚡ Mapper layer with **MapStruct**
- 📷 Photo upload & storage (filesystem-based implementation)
- ✅ Comprehensive exception handling for robust APIs

---

## 🏗️ Project Structure

```bash
restaurant/
├── src/
│   └── main/
│       ├── java/com/amos_tech_code/restaurant/
│       │   ├── config/                # Security & application configs
│       │   │   └── SecurityConfig.java
│       │   │
│       │   ├── controllers/           # REST API controllers
│       │   │   ├── ErrorController.java
│       │   │   ├── PhotoController.java
│       │   │   ├── RestaurantController.java
│       │   │   └── ReviewController.java
│       │   │
│       │   ├── domain/                # Core domain layer
│       │   │   ├── dtos/              # Data Transfer Objects
│       │   │   └── entities/          # Elasticsearch entities
│       │   │
│       │   ├── exceptions/            # Custom exceptions
│       │   │   ├── BaseException.java
│       │   │   ├── RestaurantNotFoundException.java
│       │   │   ├── ReviewNotAllowedException.java
│       │   │   └── StorageException.java
│       │   │
│       │   ├── mappers/               # MapStruct mappers
│       │   │   ├── PhotoMapper.java
│       │   │   ├── RestaurantMapper.java
│       │   │   └── ReviewMapper.java
│       │   │
│       │   ├── repositories/          # Spring Data repositories
│       │   │   └── RestaurantRepository.java
│       │   │
│       │   ├── services/              # Service layer
│       │   │   ├── GeoLocationService.java
│       │   │   ├── PhotoService.java
│       │   │   ├── RestaurantService.java
│       │   │   ├── ReviewService.java
│       │   │   ├── StorageService.java
│       │   │   └── impl/              # Implementations
│       │   │       ├── FileSystemStorageService.java
│       │   │       ├── PhotoServiceImpl.java
│       │   │       ├── RandomLondonGeoLocationService.java
│       │   │       ├── RestaurantServiceImpl.java
│       │   │       └── ReviewServiceImpl.java
│       │   │
│       │   └── RestaurantApplication.java   # Main Spring Boot entrypoint
│       │
│       └── resources/
│           ├── static/                # Static assets
│           ├── templates/             # Thymeleaf templates
│           └── application.properties # App config
│
├── pom.xml                            # Maven dependencies & build
└── README.md                          # Project documentation
