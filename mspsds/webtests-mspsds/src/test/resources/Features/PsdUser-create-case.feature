Feature: PSD user
	As an psd user
	In order to create a case
	I should have expected permissions
	
@new-flow @regression
Scenario: As Trading standard user, I should see create a product record link
Given I login as Trading standard user
When I click on "Products" tab
Then I should see page "Your products"
And I should see the link "Create product record"
	
@regression  @covid @ts-case @new-flow @new-product
Scenario Outline: As Trading standard user, I should be able to create a product record
Given I login as Trading standard user
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
|Hand sanitiser                      |Sanitising liquid	 |Lifebuoy Hand Hygiene Gel 500ml|
|Cosmetics						  						 |FacePack powder		 |Superdrug facepack powder			 |



Scenario: Create another product as ts user
Given I login as Trading standard user
When I click on "Products" tab
And I click "Create product record"
And I enter product details for product category "Personal protective equipment (PPE)"
Then I should see confirmation message "Product record created"

@regression @new_case_flow
Scenario: Create a new case from product page
Given I login as Trading standard user
When I click on "Products" tab
And I go to "All products - Search"
Then I should see page "All products – Search"
And I open the product "Lifebuoy Hand Hygiene Gel 500ml"
And I see link "Create a new case for this product"


When I click link "Create a new case for this product"
And I select "A product is of concern"
And I click continue button
And I select "The product is unsafe (or suspected of being)"
And I select hazard "Burns"
And I click continue button
#reference number 
When I click "No"
And I click continue button
And I enter case name "QA-Autognerated test case"
Then I should see confirmation message "Case created"

When I click link "View the case"
Then I should see on the summary page "Product reported as unsafe"

@regression
Scenario: create a safe and compliant case
Given I login as Trading standard user
When I click on "Products" tab
And I go to "All products - Search"
Then I should see page "All products – Search"
And I open the product "Superdrug facepack powder"
And I see link "Create a new case for this product"


When I click link "Create a new case for this product"
And I select "A product is safe and compliant"
And I click continue button
#reference number 
When I click "No"
And I click continue button
And I enter case name "QA-Autognerated test case"
Then I should see confirmation message "Case created"

When I click link "View the case"
Then I should see on the summary page "Product reported as safe and compliant"

#Add a product to the case 
@regression
Scenario:Add a product to the case
Given I login as Trading standard user
And I open case "QA-Autognerated test" 
And I go to "Products" in left nav
When I click "Add a product to the case" on case summary page
Then I should see label "Enter a"

When I enter the productid "11"
And I click search
Then I should see page "Is this the correct product record to add to your case?"
When I click No and submit
Then I should see label "Enter a"

#On product search confirmation - Yes
When I enter the productid "11"
And I click search
Then I should see page "Is this the correct product record to add to your case?"
When I click Yes and submit
Then I should see "The product record was added to the case"


#Verify error messages on search product page
@addaproduct @regression
Scenario: Verify error message when I try to add same product again
Given I login as Trading standard user
And I open case "QA-Autognerated test" 
And I go to enter product reference number page
And I enter the productid "11"
And I click search
Then I should see error "Enter a product record which has not already been added to the case"

#Empty product search
When I enter the productid "11.2"
And I click search
Then I should see error "Enter a PSD product record reference number"


#Invalid product id
When I enter the productid "hfhhf"
And I click search
Then I should see error "Enter a PSD product record reference number"

#Invalid product id with decimals
When I enter the productid "11.2"
And I click search
Then I should see error "Enter a PSD product record reference number"

#Product id doesn't exist
When I enter the productid "psd-2001"
And I click search
Then I should see error "An active product record matching psd-2001 does not exist"



@ts-user 
Scenario: Add comment activity
Given I login as Trading standard user
And I open case "Auto-test Testproduct, Auto-test dishwasher – chemical hazard"
When I go to activity log
Then I should be able to add activity "Comment"

@ts-user 
Scenario: Add corrective action
Given I login as Trading standard user
And I open case "Auto-test Testproduct, Auto-test dishwasher – chemical hazard"
When I go to supporting information tab
Then I should be able to add activity "Record corrective action"
And I should see "Corrective action was successfully recorded."

@ts-user 
Scenario: Add corrective action with a file
Given I login as Trading standard user
And I open case "Auto-test Testproduct, Auto-test dishwasher – chemical hazard"
When I go to supporting information tab
Then I should be add "Record corrective action" with a file
And I should see "Corrective action was successfully recorded."

  
  Scenario: Validate error message when I don't chose a file
  Given I go to supporting information tab
  And I click link "Add supporting information"
  And I select "Other document or attachment"
  And I click continue button
  When I submit file upload
  Then I should see error message "Enter file"
  
  
  Scenario: Validate error message when I don't enter attachment tile field
  When I add attachment to the case
  And I submit file upload
  And I Keep attachment title field empty
  And I click save attachment
  Then I should see error "Enter title"
 
  
  Scenario: Add attachment
  When I fill in attachment title
  And I click save attachment
  #Then I should see "File added sucessfully"
  

 Scenario: Validate back link on edit attachment page
 Given I click link "Edit document"
 When I click back
 Then I should see page "Supporting information"
 

 Scenario: Validate cancel on edit attachment page
 Given I click link "Edit document"
 When I click cancel
 Then I should see page "Supporting information"
 
 
  Scenario: Validate back link on delete attachment
  Given I go to supporting information tab
  And I click link "Remove document"
  When I click back
  Then I should see page "Supporting information"
  
  
  Scenario: remove attachment
  Given I click link "Remove document"
  And I click save attachment
  Then I should see "File was successfully removed"
  
 
 Scenario: Validate back link on add attachment
 Given I go to attachment tab
 And I click link "Add supporting information"
 When I click back
 Then I should see page "Overview"


Scenario: Add email via activity
Scenario: Add test results via activity

@read-summary
Scenario: test summary
Given I login as Trading standard user
And I open case "Auto test487221"
Then I should see on the summary page "Auto test487221"
And I should see case owner "Nasir Khan - Southampton Council"
And I should see case reference "86868 Trading standards reference"


