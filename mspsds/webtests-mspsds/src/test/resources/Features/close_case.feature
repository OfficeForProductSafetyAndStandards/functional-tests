#Author: nasirkhan.beis@gmail.com
#Keywords Summary :
#Feature: Close a case.
@tag
Feature: Close a case


@regression
Scenario: When a case is closed, products on the case will become timestamp version
Given I login as Trading standard user
And I open case "QA-Autognerated test"
When I click "Close case" on case summary page
And I click Close case on confirmation
And I go to "Products" in left nav
Then I should see product becomes timestamped version

@closecase @regression
Scenario: when a case is closed,cannot add a product
Given I login as Trading standard user
When I open the closed case "QA-Autognerated test"
And I go to "Products" in left nav
Then the link "Add a product to case" is not displayed

Scenario: Verify when I close a case which has a product owned by me and also linked to various other cases then the product will become un-owned

Scenario: Verify product owner is unchanged when I close one of my two cases which is linked to a product and is owned by my team.

Scenario: Verify product owner is unchanged when I close a case which is owned by another team

Scenario: Verify when I close a case which has product records owned by my team and another team, the products owned by another team will not change

Scenarios: Verify when I reopen a closed case, products on the case the will be time stamped one and product ownership will not change

 Scenario: Verify a product record cannot be added to a closed case.
