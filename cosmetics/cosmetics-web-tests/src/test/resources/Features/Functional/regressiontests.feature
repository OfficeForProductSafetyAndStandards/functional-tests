#Author: Nasir Khan
#Sample Feature Definition Template
@tag
Feature: Cosmetics system regression tests
  I want to run full system regression tests
  
  
Scenario: Verify forgot password - check email and password reset link
Scenario: Verify user registration - manual test 
Scenario: Verify create new RP and change RP
Scenario: Verify create new RP with existing name
Scenario: Verify invite new team member
Scenario: Verify invite and register user with same email 
Scenario: Verify register and invite user to a diff team with same email


  @tag1
  Scenario: Title of your scenario
    Given I want to write a step with precondition
    And some other precondition
    When I complete action
    And some other action
    And yet another action
    Then I validate the outcomes
    And check more outcomes

  @tag2
  Scenario Outline: Title of your scenario outline
    Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
