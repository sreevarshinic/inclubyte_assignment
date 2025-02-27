# inclubyte_assignment
# Automation Assignment for Incubyte

This repository contains my solution for the Automation Assessment Task for the Software Developer in Testing role at Incubyte. The project automates the sign-up and sign-in flow on the [Magento Testing Board](https://magento.softwaretestingboard.com/) website using Selenium, TestNG, and Java. It follows BDD (Behavior-Driven Development) principles and uses the Page Object Model (POM) design pattern for maintainability and clarity.

## Project Structure

assignment_incubyte/
├── pom.xml 
# Maven configuration file
├── README.md # This file 
└── src/ ├── main/java/ 
└── test/ ├── java/ │ ├── pages/ # Page Object classes (HomePage, RegistrationPage, LoginPage) │ 
└── runners/ # Test classes (SignUpTest)

## Technologies Used

- **Java**
- **Selenium WebDriver**
- **TestNG**
- **Maven** for dependency management
- **EGit** (if using Eclipse for Git integration)
- **BDD and POM** design patterns

## Setup and Execution

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/sreevarshinic/inclubyte_assignment.git
   cd inclubyte_assignment
mvn clean install
mvn test
