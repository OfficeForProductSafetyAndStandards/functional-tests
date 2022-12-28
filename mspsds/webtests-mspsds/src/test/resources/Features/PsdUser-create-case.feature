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
	
@regression  @covid @ts-case @new-flow
Scenario: As Trading standard user, I should be able to create a product record
Given I login as Trading standard user
When I click on "Products" tab
Then I should see page "Your products"
And I should see the link "Create product record"

When I click "Create product record"
And I enter product details for product category "Clothing, textiles and fashion items"
Then I should see confirmation message "Product record created"

@regression
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
And I open the product "Day to night cream"
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
And I open the product "Day to night cream"
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
Scenario:Add a product to the case
Given I open a case "QA-Automated test case" 
And I go to products tab
And I click "Add product"
Then I should search product page

When I enter the productid "88"
And I click search
Then I should see product "xxx"
When I click No and submit
Then I should see product search

#On product search confirmation - Yes
When I enter the productid "88"
And I click search
Then I should see product "xxx"
When I click Yes and submit
Then I should see success banner with message "Product added successfully"

#Validation error message when same product is added to the case
When I enter the productid "88"
And I click search
Then I see validation error message "Product is already added to the case"







#When I select compliance type "unsafe"
#And I click continue on ts case creation page
#Then I should see page "Supply chain information"

#Enter supply chain information
#When I select which parts of chain do you know as "Retailer"
#And I click continue on ts case creation page
#Then I should see page "Retailer details"
#
#Enter Retailer business details
#When I enter business tradign name "Test Tesco"
#And I enter legal name "Auto-test"
#And I click continue on ts case creation page
#Then I should see page "Has any corrective action been agreed or taken?"
#
#Enter corrective action no
#When I select corrective action "No"
#And I click continue on ts case creation page
#Then I should see page "Other information and files"
#
#When I click continue on ts case creation page
#Then I should see page "Add your own reference number"
#
#When I click "No"
#And I click create case
#Then I should see page "Case created"

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


