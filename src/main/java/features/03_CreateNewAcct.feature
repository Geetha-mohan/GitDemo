
Feature: Create Account functionality validation

Background:
    #Given launch the browser and load the URL
    When Enter username as 'dilip@testleaf.com'
    And Enter the password as 'leaf@2024'
    And Click login button
    Then Verify whether logged in successfully
    
  Scenario: Verify CreateAccount functionality
    #Given login into Salesforce application
    When Click on AppLauncher toggle menu 
    And Click on View All option
    And Click Accounts menu from App Launcher 
    And Click New button
    And Enter the Account name as 'Geetha'
    And Enter the Billing address as 'Chennai' or display a duplicate record warning
    And Select the customer priority as 'High' or display a duplicate record warning
    And Click Save button
    Then Verify Account created successfully
    
