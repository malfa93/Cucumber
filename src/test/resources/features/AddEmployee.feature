Feature: Add Employees

  Background:
    When user enters valid admin username and password
    And user clicks on login button
    Then admin user is successfully logged in
    When user clicks on PIM option
    And user clicks on Add employee button


  Scenario: Adding one employee from feature file
    When user enters valid admin username and password
    And user clicks on login button
    Then admin user is successfully logged in
    When user clicks on PIM option
    And user clicks on Add employee button
    And user enters firstname and lastname
    And user clicks on save button
    Then employee added successfully


    @ddt
    Scenario Outline: Add employee
      When user enters "<firstName>" "<middleName>" and "<lastName>"
      And user clicks on save button
      Then employee added successfully
      Examples:
      |firstName|middleName|lastName|
      |test123  |MS        |test456 |
      |testabc  |MS        |testxyz |
      |test987  |MS        |test321 |


    @cucumber
    Scenario: Adding one employee using cucumber feature
      When user enters valid admin username and password
      And user clicks on login button
      Then admin user is successfully logged in
      When user clicks on PIM option
      And user clicks on Add employee button
      And user enters direct data "Moe" "Deena" and "Arwa"
      And user clicks on save button
      Then employee added successfully



  @dataTable
  Scenario: Add employee
    When user add multiple employees and verify they are added
      |firstName|middleName|lastName|
      |test123  |MS        |test456 |
      |testabc  |MS        |testxyz |
      |test987  |MS        |test321 |




    @excel
    Scenario: Adding employees from excel file
      When user adds multiple employyes from excel file using "EmployeeData" sheet and verify the added employee


  @db
  Scenario: Front end and backend Testing
    When user enters direct data "Bla" "Olga" and "Bla"
    And capture the employee id
    And user clicks on save button
    And employee added successfully
    And query the information from backend
    Then verify the results from UI and Backend



  @employee_Id
  Scenario: Testing the employee Id's are being stored inside the Employee_id in hs_hr_employees Table
    When user enters direct data "Blayi" "Olgaa" and "Blad"
    And capture the employee id
    And user clicks on save button
    And employee added successfully
    And query the information from backend
    Then verify the result from Front end and Back end
