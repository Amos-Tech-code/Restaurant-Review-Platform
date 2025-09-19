# 🍽️ Restaurant Review Platform

A Spring Boot 3 application that allows users to discover restaurants, write reviews, and upload photos.
Built with a clean, layered architecture to demonstrate best practices in backend development.

### 🏗️ Project Structure

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
│       │   │   │   └── RestaurantDto.java
│       │   │   └── entities/          # Elasticsearch entities
│       │   │       ├── Restaurant.java
│       │   │       └── Review.java
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
