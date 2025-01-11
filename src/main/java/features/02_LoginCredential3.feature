
Feature: Login functionality validation

  Scenario: Verify Login functionality
    #Given launch the browser and load the URL
    When Enter username as 'dilip@testleaf.com'
    And Enter the password as 'leaf@2024'
    And Click login button
    Then Verify whether logged in successfully
    When Click on AppLauncher toggle menu 
