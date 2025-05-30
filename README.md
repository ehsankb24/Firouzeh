# Firouzeh News App

A modern Android news application that aggregates and displays news from major tech companies (Microsoft, Apple, Google, and Tesla) using clean architecture principles.

## Project Structure

```
├── app/                  # Application module
├── data/                 # Data module
│   ├── dataSource/       # Data sources (local and remote)
│   ├── model/           # Data models and DTOs
│   └── repository/      # Repository implementations
├── domain/              # Domain module
│   ├── model/          # Domain models
│   ├── repository/     # Repository interfaces
│   └── usecase/        # Use cases
├── di/                  # Dependency injection module
├── ui/                  # UI components module
└── news/               # News feature module
```

## Architecture

### Clean Architecture Implementation

1. **Domain module** (`domain/`)
    - Business logic and use cases
    - Repository interfaces
    - Domain models
    - Independent of Android framework

2. **Data module** (`data/`)
    - Repository implementations
    - Local data source (Room Database)
    - Remote data source (News API)
    - Data mapping between layers

3. **Presentation module** (`news/`)
    - Jetpack Compose UI components
    - ViewModels
    - UI state management

### Key Components

1. **News Repository**
    - Handles data operations for news articles
    - Manages local caching and remote fetching
    - Implements sequential news arrangement

2. **Use Cases**
    - `GetNewsUseCase`: Orchestrates news fetching and arrangement
    - Handles business logic for news display

3. **Data Sources**
    - Room Database for local storage
    - News API for remote data
    - Efficient caching mechanism

## Features

### News Aggregation
- Real-time news from tech companies
- Sequential arrangement (Microsoft → Apple → Google → Tesla)
- Automatic refresh mechanism
- Offline support

### Technical Implementation
- Kotlin Coroutines for async operations
- Flow for reactive programming
- Room Database for persistence
- Retrofit for API calls
- Hilt for dependency injection
- Jetpack Compose UI
- Material Design 3

## Setup

### Prerequisites
- Android Studio Hedgehog or newer
- Kotlin 1.9.0 or newer
- Android SDK 34 or newer
- Gradle 8.0 or newer

### Configuration
1. Clone the repository
2. Configure your API key:
   - Open `di/src/main/java/kb24/ehsan/di/RemoteDataSourceModule.kt`
   - Locate the `provideRemoteToken()` function
   - Replace the existing token with your NewsAPI API key:
     ```kotlin
     @RemoteToken
     @Provides
     fun provideRemoteToken(): String = "YOUR_NEWSAPI_API_KEY"
     ```
3. Sync project with Gradle files
4. Build and run the project

## License
- [NewsAPI](https://newsapi.org/docs) for news content
This project is licensed under the MIT License - see the LICENSE file for details 