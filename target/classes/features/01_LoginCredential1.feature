
Feature: Login functionality validation

  Scenario: Verify Login functionality
    #Given launch the browser and load the URL
    When Enter username as 'vidyar@testleaf.com'
    And Enter the password as 'Sales@123'
    And Click login button
    Then Verify whether logged in successfully
    When Click on AppLauncher toggle menu 
