# booking.com

+ ### JAVA 8
+ ### Selenuim WebDriver
+ ### JUnit
+ ### Maven
+ ### Allure
+ ### Log4j2

**Project structure:**
```
pom.xml - Maven configuration file;
src/main/resources/test.properties - property file with initial settings
src/main/resources/log4j2.xml - log4j2 configuration
src/main/drivers - storage for drivers;
src/main/java/pages - Page Object directory;
src/main/java/pages/BasePage.java - superclass for all pages;
src/main/java/utils/WebDriverUtils.java - additional methods for working with WebDriver
src/main/java/utils/DriverFactory.java - class for initialization of WebDriver instance
src/main/java/utils/PropertyConfig.java - class responsible for reading initial settings;
src/test/java - directory with tests;
src/test/java/BaseTest.java - superclass for all tests;
selenium.log - log collected during test execution
```
