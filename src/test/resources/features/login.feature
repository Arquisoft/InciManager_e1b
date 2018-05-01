
Feature: Being able to login
Scenario: Login
  Login with some username

    Given a list of users:
      | name    | password |
      | 12345678P    | 123456   |
      | sensor1    | 123456     |
      | 12345678A    | 123456   |
    When I login with name "12345678P" and password "123456"
    Then I am redirected to the incident form