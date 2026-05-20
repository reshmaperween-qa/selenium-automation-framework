# 🧪 Selenium Automation Framework (OpenCart)

## 📌 Project Overview
This project is a Selenium-based automation testing framework developed for testing the OpenCart web application. It follows the Page Object Model (POM) design pattern and supports data-driven testing.

The framework is designed to automate key user workflows such as login, registration, account validation, and data-driven login scenarios.

---

## 🚀 Tech Stack
- Java
- Selenium WebDriver
- TestNG
- Maven
- Apache POI (Excel Handling)
- Extent Reports
- Log4j2
- Page Object Model (POM)
- Jenkins

---

## 📂 Project Structure
src/
├── test/java
│   ├── pageObjects
│   ├── testCases
│   ├── utilities
│   └── TestBase
├── test/resources
testData/
reports/
pom.xml
grouping.xml

---

## ✅ Features
- Automated Login & Registration Test Cases
- Data-Driven Testing using Excel
- Page Object Model (POM) Design
- Cross-browser testing support
- Test execution using TestNG XML
- Extent Report generation with timestamp
- Screenshot capture for failed test cases
- Log4j2 logging implementation
- Jenkins integration for CI execution

---

## 🧪 Test Scenarios Covered
- User Login with valid credentials
- User Login with invalid credentials
- User Registration
- Account Validation
- Data-driven Login Testing using Excel

---

## ▶️ How to Run the Project
1. Clone the repository:
git clone https://github.com/reshmaperween-qa/selenium-automation-framework

2. Open the project in Eclipse or IntelliJ IDE

3. Install dependencies using Maven:
Right-click on project → Maven → Update Project

4. Run test suite:
Right-click on grouping.xml → Run As → TestNG Suite

---

## 🔄 Jenkins CI Integration
- Jenkins is integrated with GitHub repository
- Poll SCM is configured to check new commits
- Maven is used to execute TestNG test suites
- Jenkins runs automation tests after code push
- Console Output is used to debug build failures
- Test results are recorded in Jenkins
- Extent Report is published in Jenkins after execution

Jenkins Maven command:
clean test -Dsurefire.suiteXmlFiles=grouping.xml

---

## 📊 Reporting
- Extent Reports are generated with timestamp
- Reports are stored inside the reports folder
- Screenshots are captured for failed test cases
- Jenkins HTML Publisher plugin is used to view Extent Reports

---

## 🛠️ Tools Used
- Eclipse IDE
- GitHub
- Jenkins
- Maven
- Chrome Browser

---

## 👩‍💻 Author
Reshma Perween  
QA Test Engineer | Manual & Automation Testing  

Dubai, UAE  
Email: reshmaperween0250@gmail.com  

---

## ⭐ Note
This project demonstrates hands-on experience in automation testing using Selenium WebDriver, TestNG, Maven, POM framework design, data-driven testing, Extent Reports, and Jenkins CI integration.
