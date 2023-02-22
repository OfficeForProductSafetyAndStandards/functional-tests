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

@regression @fix
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


	
@regression  @covid @ts-case @new-flow @new-product
Scenario Outline: As as OPSS user, I should be able to create a product record
Given I login as OPSS user
When I click on "Products" tab
Then I should see page "Your products"
And I should see the link "Create product record"

When I click "Create a product record"
And I select product cat "<Prod_cat>"
And I enter subcat "<Prod_subcat>"
And I enter product name "<Prod_name>"
And I enter other product details
Then I should see confirmation message "Product record created"

Examples:

|Prod_cat								  					 |Prod_subcat        |Prod_name                      |
|Decorative articles                 |Fairy led lights	 |Fairy music LED lights-10W		 |
|Gas appliances and components			 |Gas Hob       		 |Beko 8 burner Gas hob					 |

@regression
Scenario:Add a product to the case
Given I login as OPSS user
And I open case "Clothing" 
And I go to "Products" in left nav
When I click "Add a product to the case" on case summary page
Then I should see label "Enter a"

When I enter the productid "12"
And I click search
Then I should see page "Is this the correct product record to add to your case?"
When I click Yes and submit
Then I should see "The product record was added to the case"


@regression @enquiry @fix
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

@regression @fix1
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

@regression @project @fix1
Scenario: As OPSS user, I should be able to create a project
Given I login as OPSS user
When I click button "Create a case"
And I select case type "Project"
And I enter project title
And I enter project summary
And I click the button "Create project"
Then I should see "Project was successfully created"


