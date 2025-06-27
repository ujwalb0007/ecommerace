## Fast Selenium E-commerce Project
This is a comprehensive Selenium project in Java that automates login, product scraping, and cart interaction on the Sauce Demo test e-commerce site. It uses the Page Object Model (POM), TestNG for testing, SLF4J with Logback for logging, and generates a CSV report of scraped data. The project is optimized for performance with headless Chrome and explicit waits.
Project Structure
selenium-ecommerce-project/
├── src/main/java/com/example/selenium/
│   ├── config/
│   │   └── ConfigReader.java          # Reads configuration properties
│   ├── pages/
│   │   ├── LoginPage.java            # Page Object for login page
│   │   ├── InventoryPage.java        # Page Object for inventory page
│   │   ├── CartPage.java             # Page Object for cart page
│   ├── utils/
│   │   ├── WebDriverUtil.java        # WebDriver setup and management
│   │   └── ReportGenerator.java      # Generates CSV report
├── src/test/java/com/example/selenium/tests/
│   └── EcommerceTest.java            # TestNG test class
├── src/main/resources/
│   ├── config.properties             # Configuration file (credentials, URLs)
│   ├── logback.xml                   # Logback configuration for logging
├── pom.xml                           # Maven configuration
├── README.md                         # Project documentation

Features

Headless Chrome: Runs without a GUI for faster execution.
Page Object Model: Ensures maintainable and reusable code.
Explicit Waits: Minimizes delays using WebDriverWait.
TestNG Framework: Structured test execution with setup/teardown.
Logging: SLF4J with Logback for console and file logging.
CSV Reporting: Generates a report of scraped product data.
WebDriverManager: Automatically manages ChromeDriver versions.
Optimized Settings: Disables GPU, sandbox, and resource-heavy features.

Prerequisites

Java: Version 8 or higher
Maven: For dependency management
Chrome Browser: Required for WebDriver compatibility
IDE (optional): IntelliJ IDEA, Eclipse, or similar for easier development

Setup Instructions

Create the Project:

Create a directory named selenium-ecommerce-project.
Copy the project files into the appropriate directories as shown in the structure above.
Ensure config.properties and logback.xml are in src/main/resources/.


Install Dependencies:

Ensure Maven is installed (download from Apache Maven).
Navigate to the project root (selenium-ecommerce-project/) and run:mvn install


This downloads Selenium, TestNG, SLF4J, Logback, OpenCSV, and other dependencies.


Verify Chrome Installation:

Ensure Google Chrome is installed. WebDriverManager handles ChromeDriver setup.


Configure Logging:

Logs are written to logs/selenium.log (created automatically).
Modify logback.xml to adjust log levels or output formats if needed.



Usage

Run Tests:

From the project root, execute the TestNG tests:mvn clean test


Alternatively, use an IDE to run EcommerceTest.java or the testng.xml file (create one if needed for custom test suites).


Expected Output:

The program logs into Sauce Demo using credentials from config.properties:
Username: standard_user
Password: secret_sauce


It extracts product details (name, description, price), adds the first product to the cart, and verifies cart contents.
A CSV report is generated at reports/product_report.csv.
Logs are output to the console and logs/selenium.log.

Example console output:
2025-06-27 18:14:23 INFO  com.example.selenium.tests.EcommerceTest - Test setup completed
2025-06-27 18:14:24 INFO  com.example.selenium.pages.LoginPage - Login page loaded
2025-06-27 18:14:24 INFO  com.example.selenium.tests.EcommerceTest - Login test passed
2025-06-27 18:14:25 INFO  com.example.selenium.utils.ReportGenerator - Product report generated at: reports/product_report.csv
2025-06-27 18:14:25 INFO  com.example.selenium.tests.EcommerceTest - Extracted 6 products and generated report
2025-06-27 18:14:26 INFO  com.example.selenium.tests.EcommerceTest - Cart contains 1 items

Example CSV (reports/product_report.csv):
Name,Description,Price
Sauce Labs Backpack,carry.allTheThings() with the sleek...,$29.99
Sauce Labs Bike Light,A red light isn't the desired state...,$9.99
...


Customization:

Update config.properties to change the URL, credentials, or report path.
Modify LoginPage.java, InventoryPage.java, or CartPage.java to target different websites or elements.
Add new tests in src/test/java/com/example/selenium/tests/.
Extend page objects in the pages package for additional functionality.



Performance Optimizations

Headless Mode: Runs Chrome without a GUI, reducing resource usage.
Disabled GPU and Sandbox: Enhances speed in headless environments.
Explicit Waits: Waits only until elements are present, minimizing delays.
WebDriverManager: Ensures correct ChromeDriver version without manual updates.
POM Design: Improves code maintainability and scalability.
Logging: Provides detailed execution traces for debugging.
TestNG: Enables parallel testing and dependency management.

Extending the Project

Parallel Testing: Configure testng.xml to run tests in parallel for faster execution.
Additional Pages: Add new page objects in the pages package for other site sections.
Remote Grid: Integrate with a Selenium Grid for distributed testing.
Enhanced Reporting: Use ExtentReports or Allure for richer test reports.
Error Handling: Add retry logic or screenshots on failure in EcommerceTest.java.

Troubleshooting

ChromeDriver Issues: Ensure Chrome is updated and run mvn clean install to refresh dependencies.
Timeout Errors: Increase WebDriverWait timeouts in page objects if pages load slowly.
Dependencies: Verify pom.xml dependencies are resolved by Maven.
Login Failures: Confirm credentials in config.properties match the target site (standard_user, secret_sauce for Sauce Demo).
Report Issues: Ensure the reports/ directory exists or is writable.

Notes

The project uses Sauce Demo as a public test site to avoid real-world ethical/legal issues. Do not use real credentials in production code without security measures.
Ensure compliance with the terms of service for any website you automate.

License
This project is for educational purposes and provided as-is. Use responsibly and respect website terms of service.
