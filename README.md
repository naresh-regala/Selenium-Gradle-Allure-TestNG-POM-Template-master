# Selenium-Gradle-Allure-TestNG-POM-Template
- template for Selenium3 + Gradle + Allure + TestNG + POM

## Gradle command parameters to configure threadcount for parallel execution
- -Dthreadcount (You can configure number of parallel threads for methods )
- -Dtag=regression (valid values are: regression, smoke)
- -DbrowserName=chrome (valid values are: chrome, firefox, edge)
- -DbrowserVersion=latest (any valid version. to run on browser latest version you can provide <latest>. else you can provide specifice verison ex:91.0.4472.123) 
- -Dheadless=true (valid values are: true or false)
- -DproxyEnabled=false (valid values are: true or false)

## Gradle command to run test cases in chrome without proxy
- gradlew clean run -Dthreadcount=3 -Dtag=regression -DbrowserName=chrome -DbrowserVersion=latest -Dheadless=false -DproxyEnabled=false
 
## Gradle command to run test cases in chrome headless mode without proxy
- gradlew clean run -Dthreadcount=3 -Dtag=regression -DbrowserName=chrome -DbrowserVersion=latest -Dheadless=true -DproxyEnabled=false

## Install allure ro generate allure report
 ###  Mac OS X
 - install allure using below command
  ```bash
   brew install allure
 ```
 ###  Windows
 - For Windows, Allure is available from the Scoop commandline-installer.
 - To install Allure, download and install Scoop and then execute in the Powershell:
  ```bash
   scoop install allure
 ```
## command to generate the report
   ```bash
   allure serve
 ```
 
 ## You can run the scripts on github using GitHub Actions. You can find the .yml file in current project .github/workflows

