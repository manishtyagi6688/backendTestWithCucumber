Feature: Verify the user's actions such as insert and multiple insert

  @regression
  Scenario Outline: Verify the user inserted successfully with multiple data
    When User sends a POST request to add with user endpoint
    Then User must get back a valid status code 200 as per the data: "<name>",  "<email>",  "<gender>",  "<status>"
    Examples:
      | name                  |  email                                      | gender | status   |
      | Ekdant Embranthiri    | embranthiri_ekdant@adams-douglas.example    | male   | inactive |
      | Kalyani Marar         | kalyani_marar@hane.test                     | female | inactive |
      | Chandi Asan           | chandi_asan@mclaughlin.test                 | female | active   |


  @regression
  Scenario: Verify the user get successfully with correct data
    When Verify the user's name, email, gender, status
    Then Verify the status code of request