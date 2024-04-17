Feature: Login Functionality

  Scenario Outline: Successful login with different user credentials
    Given the user set username "<username>"
    Given the user set password "<password>"
    When the user click Login button
    Then the user is redirected to products page

    Examples:
      | username         | password     |
      | standard_user    | secret_sauce |