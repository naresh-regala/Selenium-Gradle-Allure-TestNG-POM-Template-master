# Selenium-Gradle-Allure-TestNG-POM-Template
- template for Selenium3 + Gradle + Allure + TestNG + POM

## Gradle command parameters
- -Dtag=regression (valid values are: regression, smoke)
- -DbrowserName=chrome (valid values are: chrome, firefox, edge)
- -DbrowserVersion=latest (any valid version. to run on browser latest version you can provide <latest>. else you can provide specifice verison ex:91.0.4472.123) 
- -Dheadless=true (valid values are: true or false)
- -DproxyEnabled=false (valid values are: true or false)

## Gradle command to run test cases in chrome headless mode without proxy
- gradlew clean run -Dtag=regression -DbrowserName=chrome -DbrowserVersion=latest -Dheadless=true -DproxyEnabled=false

## Command to generate Allure Report
- allure serve

