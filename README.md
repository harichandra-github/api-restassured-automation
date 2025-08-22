# REST Assured API Automation Framework

A robust and scalable API testing framework built with REST Assured, TestNG, and Java. This framework provides comprehensive testing capabilities for REST APIs with features like authentication, CRUD operations, logging, and test reporting.

##  Features

- **REST API Testing**: Complete CRUD operations testing using REST Assured
- **Authentication Support**: Built-in login functionality with token management
- **Data-Driven Testing**: TestNG data providers for parameterized testing
- **Comprehensive Logging**: Log4j2 integration for detailed request/response logging
- **Advanced Reporting**: Extent Reports integration for beautiful HTML test reports
- **Test Listeners**: Custom test listeners for enhanced reporting and logging
- **Request/Response Filtering**: Custom filters for logging and debugging
- **Fake Data Generation**: Java Faker integration for realistic test data
- **Configuration Management**: Externalized configuration properties
- **POJO Models**: Clean separation of request/response models using Lombok

##  Prerequisites

- Java 11 or higher
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

##  Tech Stack

- **Java 11**: Core programming language
- **REST Assured 5.5.5**: API testing library
- **TestNG 7.11.0**: Testing framework
- **Log4j2 2.24.3**: Logging framework
- **Extent Reports 5.1.2**: Advanced HTML test reporting
- **Lombok 1.18.32**: Reduces boilerplate code
- **Jackson 2.19.2**: JSON serialization/deserialization
- **Java Faker 1.0.2**: Test data generation

##  Project Structure

```
restassured-api-automation/
├── src/
│   ├── main/java/
│   │   ├── filters/
│   │   │   └── LoggingFilters.java          # Request/Response logging filters
│   │   ├── listeners/
│   │   │   └── TestListeners.java           # Test execution listeners
│   │   ├── models/
│   │   │   ├── request/
│   │   │   │   ├── LoginRequest.java        # Login request POJO
│   │   │   │   └── ProductRequest.java      # Product request POJO
│   │   │   └── response/
│   │   │       ├── LoginResponse.java       # Login response POJO
│   │   │       └── ProductResponse.java     # Product response POJO
│   │   ├── reporting/
│   │   │   └── ExtentReportManager.java     # Extent Reports configuration
│   │   ├── services/
│   │   │   ├── base/
│   │   │   │   └── BaseService.java         # Base service with common configuration
│   │   │   ├── AuthService.java             # Authentication service
│   │   │   └── ProductService.java          # Product CRUD operations
│   │   └── utils/
│   │       ├── CommonFunctions.java         # Common utility functions
│   │       ├── ConfigReader.java            # Configuration property reader
│   │       ├── LoginDataProvider.java       # Test data provider for login
│   │       └── ProductRequestGenerator.java # Fake data generator
│   └── test/
│       ├── java/tests/
│       │   ├── EndToEndTest.java            # End-to-end test scenarios
│       │   ├── LoginTest.java               # Login test cases
│       │   └── Sample.java                  # Sample test class
│       └── resources/
│           ├── configuration/
│           │   └── config.properties        # Configuration properties
│           └── log4j2.xml                   # Logging configuration
├── logs/
│   └── app.log                              # Application logs
├── reports/                                 # Extent Reports output directory
├── pom.xml                                  # Maven dependencies
└── README.md                                # Readme file
```

## ⚙ Configuration

### Environment Setup

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd restassured-api-automation
   ```

2. **Update configuration** in `src/test/resources/configuration/config.properties`:
   ```properties
   baseUrl=https://dummyjson.com
   username=your_username
   password=your_password
   ```

3. **Install dependencies**:
   ```bash
   mvn clean install
   ```

##  Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=EndToEndTest
```

### Run Specific Test Method
```bash
mvn test -Dtest=EndToEndTest#testCreateProduct
```

### Run Tests with TestNG XML
```bash
mvn test -DsuiteXmlFile=testng.xml
```

##  Test Scenarios

### Authentication Tests
- Login with valid credentials
- Token verification
- Data-driven login testing

### Product Management Tests
- **Create Product**: Generate fake product data and create new products
- **Read Product**: Retrieve product details by ID
- **Update Product**: Modify existing product information
- **Delete Product**: Remove products from the system

### End-to-End Workflow
The framework includes a comprehensive end-to-end test that:
1. Authenticates the user
2. Creates a new product
3. Reads the created product
4. Updates the product details
5. Deletes the product

##  Key Components

### Base Service
The `BaseService` class provides common configuration for all API requests:
- Base URL configuration
- Request specification setup
- Logging filters integration

### Data Providers
- `LoginDataProvider`: Provides test data for login scenarios
- `ProductRequestGenerator`: Generates realistic product data using Java Faker

### Logging
- **Log4j2 Configuration**: Structured logging with different levels
- **Request/Response Logging**: Detailed logging of all API interactions
- **Test Execution Logging**: Comprehensive test execution tracking

### Reporting
- **ExtentReportManager**: Manages Extent Reports configuration and instance
- **CommonFunctions**: Utility functions for timestamp generation and configuration loading
- **Test Listeners**: Integrates with Extent Reports for automatic test reporting

### Test Listeners
Custom test listeners provide:
- Test execution start/finish notifications
- Success/failure logging
- Performance metrics
- Extent Reports integration for detailed test reporting

##  Important Notes

1. **API Endpoints**: The framework currently uses the DummyJSON API for testing. Update the `baseUrl` in configuration for your target API.

2. **Authentication**: The current implementation uses basic authentication. Modify `AuthService` for different authentication methods (OAuth, JWT, etc.).

3. **Test Data**: Product creation and updates use generated fake data. In production, you may want to use predefined test data.

4. **Dependencies**: Some tests have dependencies (e.g., `testReadProduct` depends on `testCreateProduct`). This ensures proper test flow.

##  Test Reports

Test execution generates:
- Console output with detailed logs
- Log files in the `logs/` directory
- **Extent Reports**: Beautiful HTML reports with detailed test information
  - Interactive dashboard with test statistics
  - Detailed test execution logs with request/response data
  - Timeline view of test execution
  - System information and environment details
  - Searchable test results
- TestNG HTML reports 


##  Author

**Harichandra Chaudhari**
- GitHub: [@harichandra-github](https://github.com/harichandra-github)
- Linkedin: [@harichandra-chaudhari](https://www.linkedin.com/in/harichandra-chaudhari/)
- Email: [harichandrachaudhari21@gmail.com](mailto:harichandrachaudhari21@gmail.com)
- Phone: +91-866-900-9231

##  Acknowledgments

- [REST Assured](https://rest-assured.io/) for excellent API testing capabilities
- [TestNG](https://testng.org/) for robust testing framework
- [Extent Reports](https://extentreports.com/) for beautiful HTML test reporting
- [Java Faker](https://github.com/DiUS/java-faker) for realistic test data generation
- [DummyJSON](https://dummyjson.com/) for providing a test API

---

**Happy Testing! **
