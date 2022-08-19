Feature: Opss user
	As an opss user
	In order to create a case
	I should have expected permissions
	

@regression @smoke
Scenario: Login as opss user
Given I login as OPSS user
Then I should see "Cases" tab
And I should see "Businesses" tab
And I should see "Products" tab


@regression
Scenario: Verify each header displays correct respective information
Given I login as OPSS user
When I click on "Businesses" tab
Then I should see "Businesses" list page
When I click on "Products" tab
Then I should see "Products" list page


Scenario: As OPSSuser, I should be able to create a case
Given I login as OPSS user
When I click button "Create a case"
And I select case type "Product safety allegation"
And I select reporter as "Local authority (Trading Standards)"
And I enter contact details
And I enter allegation details
Then I should see "Allegation was successfully created"

Scenario: As OPSSuser, I should be able to create a case with covid flag
Given I login as OPSS user
When I click button "Create a case"
And I select case type "Product safety allegation"
And I select reporter as "Local authority (Trading Standards)"
And I enter contact details
And I enter allegation details
Then I should see "Allegation was successfully created"

@regression 
Scenario Outline: As OPSSuser, I should be able to create a case
Given I login as OPSS user
When I click button "Create a case"
And I select case type "Product safety allegation"
And I select reporter as "Local authority (Trading Standards)"
And I enter contact details
And I enter allegation details for product category "<Prod_cat>"
And I enter hazard type as "<Hazard-type>"
Then I should see "Allegation was successfully created"

Examples:

|Prod_cat								  					 |Hazard-type|
|Clothing, textiles and fashion items|Chemical	 |
|Cosmetics						  						 |Burns		   |


@regression @enquiry
Scenario: As opss user, I should be able to create an enquiry
Given I login as OPSS user
When I click button "Create a case"
And I select case type "Enquiry"
And I enter a date as when it was received
And I select how it was received "Email"
And I click continue 
Then I should see page "New enquiry"

When I select source "A consumer"
And I click continue input
And I enter contact person name "Auto test -Contact person"
And I click continue input
And I enter enquriy details
And I click continue input
Then I should see "Enquiry was successfully created."

@regression
Scenario: As opss user, I should be able to create an enquiry
Given I login as OPSS user
When I click button "Create a case"
And I select case type "Enquiry"
And I enter a date as when it was received
And I select how it was received "Phone"
And I click continue 
Then I should see page "New enquiry"

When I select source "Local authority (Trading Standards)"
And I click continue input
And I enter contact person name "Auto test -Contact person"
And I click continue input
And I enter enquriy details
And I click continue input
Then I should see "Enquiry was successfully created."

@regression @project
Scenario: As OPSS user, I should be able to create a project
Given I login as OPSS user
When I click button "Create a case"
And I select case type "Project"
And I enter project title
And I enter project summary
And I click continue
Then I should see "Project was successfully created"


