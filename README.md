# 🚂 Train Booking System

A comprehensive Java-based train booking application that allows users to search trains, book seats, and manage their bookings through an interactive command-line interface.

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Usage](#-usage)
- [Configuration](#-configuration)
- [API Documentation](#-api-documentation)
- [Testing](#-testing)
- [Contributing](#-contributing)
- [License](#-license)

## ✨ Features

### Core Functionality
- **User Authentication**: Secure signup and login with password hashing
- **Train Search**: Find trains between source and destination stations
- **Seat Booking**: Interactive seat selection and booking
- **Booking Management**: View and manage user bookings
- **Real-time Availability**: Check seat availability in real-time

### Technical Features
- **JSON Data Persistence**: Local data storage using JSON files
- **Password Security**: BCrypt password hashing
- **Input Validation**: Comprehensive input validation and error handling
- **Clean Architecture**: Well-structured codebase with separation of concerns

## 🛠 Tech Stack

- **Language**: Java 21+
- **Build Tool**: Gradle 8.14.2
- **Libraries**:
  - Jackson Databind (JSON processing)
  - BCrypt (Password hashing)
  - Lombok (Code generation)
  - JUnit (Testing)
  - Guava (Utilities)

## 📁 Project Structure

```
IRTC/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── org/example/
│   │   │   │       ├── entities/
│   │   │   │       │   ├── Ticket.java
│   │   │   │       │   ├── Train.java
│   │   │   │       │   └── User.java
│   │   │   │       ├── services/
│   │   │   │       │   ├── TrainService.java
│   │   │   │       │   └── UserBookingService.java
│   │   │   │       ├── util/
│   │   │   │       │   └── UserServiceUtil.java
│   │   │   │       ├── localDb/
│   │   │   │       │   ├── trains.json
│   │   │   │       │   └── users.json
│   │   │   │       └── App.java
│   │   │   └── resources/
│   │   └── test/
│   │       └── java/
│   ├── build.gradle
│   └── gradle/
├── gradle/
├── gradlew
├── gradlew.bat
├── settings.gradle
└── README.md
```

## 📋 Prerequisites

Before running this application, make sure you have:

- **Java Development Kit (JDK) 21 or higher**
  - Check version: `java -version`
  - Download: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)

- **Gradle 8.14.2 or higher** (Optional - project includes Gradle wrapper)
  - Check version: `gradle -version`
  - Download: [Gradle](https://gradle.org/install/)

- **IDE** (Optional but recommended)
  - IntelliJ IDEA
  - Eclipse
  - VS Code with Java Extension Pack

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/train-booking-system.git
cd train-booking-system
```

### 2. Using Gradle Wrapper (Recommended)

```bash
# On Windows
gradlew.bat build

# On Linux/Mac
./gradlew build
```

### 3. Using System Gradle

```bash
gradle build
```

### 4. Run the Application

```bash
# Using Gradle wrapper
./gradlew run

# Or using system Gradle
gradle run

# Or run the main class directly
java -cp build/libs/app.jar org.example.App
```

## 💻 Usage

### Starting the Application

1. **Run the application** using one of the methods above
2. **Choose from the menu options**:

```
=== Train Booking System ===
Choose option:
1. Sign up
2. Login
3. Fetch Bookings
4. Search Trains
5. Book a Seat
6. Cancel my Booking
7. Exit the App
```

### Step-by-Step Guide

#### 1. Create Account
```
Enter your choice: 1
Enter the username to signup: john_doe
Enter the password to signup: secure123
User signed up successfully!
```

#### 2. Login
```
Enter your choice: 2
Enter the username to Login: john_doe
Enter the password to login: secure123
Login successful!
```

#### 3. Search Trains
```
Enter your choice: 4
Type your source station: Delhi
Type your destination station: Mumbai

Available trains:
1. Train ID: DEL001
   Schedule:
   Station: Delhi - Time: 08:00
   Station: Mumbai - Time: 20:00

Select a train by typing 1, 2, 3... (or 0 to go back): 1
Train selected: DEL001
```

#### 4. Book a Seat
```
Enter your choice: 5
Available seats for train: DEL001
Seat map (0 = available, 1 = booked):
Row 1: 0 0 1 0 
Row 2: 0 0 0 0 
Row 3: 1 0 0 0 

Enter the row number (1-3): 2
Enter the column number (1-4): 1
Booking your seat...
Seat booked successfully! Enjoy your journey!
```

## ⚙️ Configuration

### Data Files

The application uses JSON files for data persistence:

- **users.json**: Stores user accounts and bookings
- **trains.json**: Contains train schedules and seat availability

### Sample Data Format

#### trains.json
```json
[
  {
    "trainId": "DEL001",
    "stationTimes": {
      "Delhi": "08:00",
      "Agra": "12:00",
      "Mumbai": "20:00"
    },
    "seats": [
      [0, 0, 1, 0],
      [0, 0, 0, 0],
      [1, 0, 0, 0]
    ]
  }
]
```

#### users.json
```json
[
  {
    "name": "john_doe",
    "password": "secure123",
    "hashedPassword": "$2a$10$...",
    "bookings": [],
    "userId": "uuid-string"
  }
]
```

## 🔧 Development

### Building from Source

```bash
# Clean build
./gradlew clean build

# Run tests
./gradlew test

# Generate test reports
./gradlew test --info
```

### IDE Setup

#### IntelliJ IDEA
1. Open the project folder
2. IDEA will automatically detect it as a Gradle project
3. Wait for Gradle sync to complete
4. Run configuration should be automatically created

#### Eclipse
1. Import as "Existing Gradle Project"
2. Select the project root directory
3. Complete the import wizard

### Code Style

- Follow Java naming conventions
- Use meaningful variable and method names
- Add comments for complex logic
- Maintain consistent indentation (4 spaces)

## 🧪 Testing

### Running Tests

```bash
# Run all tests
./gradlew test

# Run specific test class
./gradlew test --tests "org.example.UserServiceTest"

# Run with verbose output
./gradlew test --info
```

### Test Coverage

Generate test coverage reports:

```bash
./gradlew jacocoTestReport
```

View reports at: `build/reports/jacoco/test/html/index.html`

## 📚 API Documentation

### Core Classes

#### User
- `String name`: Username
- `String password`: Plain text password (for login)
- `String hashedPassword`: BCrypt hashed password
- `List<String> bookings`: User's booking IDs
- `String userId`: Unique user identifier

#### Train
- `String trainId`: Unique train identifier
- `Map<String, String> stationTimes`: Station to time mapping
- `List<List<Integer>> seats`: 2D seat availability matrix

#### UserBookingService
- `signUp(User user)`: Register new user
- `login(User user)`: Authenticate user
- `getTrains(String source, String destination)`: Search trains
- `bookTrainSeat(Train train, int row, int col)`: Book a seat
- `fetchBookings()`: Get user bookings

## 🔮 Future Enhancements

- [ ] **Database Integration**: Replace JSON with proper database
- [ ] **REST API**: Add REST endpoints for web/mobile clients
- [ ] **Payment Integration**: Add payment processing
- [ ] **Seat Types**: Different seat categories (AC, Non-AC, Sleeper)
- [ ] **Booking History**: Detailed booking history with timestamps
- [ ] **Admin Panel**: Administrative functions for managing trains
- [ ] **Notifications**: Email/SMS notifications for bookings
- [ ] **Multi-language Support**: Internationalization

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. **Fork the repository**
2. **Create a feature branch**: `git checkout -b feature/amazing-feature`
3. **Make your changes**
4. **Write tests** for new functionality
5. **Ensure all tests pass**: `./gradlew test`
6. **Commit your changes**: `git commit -m 'Add amazing feature'`
7. **Push to the branch**: `git push origin feature/amazing-feature`
8. **Open a Pull Request**

### Code Guidelines

- Write clear, descriptive commit messages
- Add unit tests for new features
- Follow existing code style and conventions
- Update documentation for any API changes
- Ensure backward compatibility

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2025 Your Name

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## 🆘 Troubleshooting

### Common Issues

#### Build Fails with Lombok Error
```bash
# Update Lombok version in build.gradle
compileOnly 'org.projectlombok:lombok:1.18.34'
annotationProcessor 'org.projectlombok:lombok:1.18.34'
```

#### File Not Found Error
- Ensure JSON files are in the correct location
- Check file permissions
- Verify working directory is set correctly

#### Java Version Issues
```bash
# Check Java version
java -version

# Set JAVA_HOME if needed
export JAVA_HOME=/path/to/java
```

### Getting Help

- Issues: Report bugs on [GitHub Issues](https://github.com/yourusername/train-booking-system/issues)
- Discussions: Join discussions on [GitHub Discussions](https://github.com/yourusername/train-booking-system/discussions)
- Email: contact@yourproject.com

## 📞 Contact

- Author: Aashish Adhikari
- Email: aashishad67@gmail.com


---

⭐ **If you found this project helpful, please give it a star!** ⭐

---

*Last updated: June 2025*
