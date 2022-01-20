Feature: Login feature

  Background:
    #Given user is navigated to HRMS application

  @smoke @regression @sprint2
  Scenario Outline: Valid admin login
    When user enters different "<username>" and "<password>"
    And user clicks on login button
    Then "<admin>" user is successfully logged in
    Examples:
      |username|password|admin|
      |Admin   |Hum@nhrm123|Admin  |




    @regression
    Scenario: Valid ess login
      When user enters valid ess username and password
      And user clicks on login button



  @login
  Scenario Outline: negative login Test
    When user enters different "<username>" and "<password>" and verify the "<error>" for all combinations
    Examples:
    |username|password|error|
    |moe     |Hum@nhrm123|Invalid credentials|
    |        |Hum@nhrm123|Username cannot be empty|
    |Admin   |           |Password cannot be empty|



