Feature: PSD user
	As an psd user
	In order to create a case
	I should have expected permissions
	
@regression  @covid @ts-case
Scenario: As Trading standard user, I should be able to create a case
Given I login as Trading standard user
When I click button "Create a case"
And I enter product details for product category "Clothing, textiles and fashion items"
And I click continue on ts case creation page
Then I should see page "Why are you reporting this product?"

When I select compliance type "unsafe"
And I click continue on ts case creation page
Then I should see page "Supply chain information"

#Enter supply chain information
When I select which parts of chain do you know as "Retailer"
And I click continue on ts case creation page
Then I should see page "Retailer details"

#Enter Retailer business details
When I enter business tradign name "Test Tesco"
And I enter legal name "Auto-test"
And I click continue on ts case creation page
Then I should see page "Has any corrective action been agreed or taken?"

#Enter corrective action no
When I select corrective action "No"
And I click continue on ts case creation page
Then I should see page "Other information and files"

When I click continue on ts case creation page
Then I should see page "Add your own reference number"

When I click "No"
And I click create case
Then I should see page "Case created"

@ts-user @regression @test1
Scenario: Add comment activity
Given I login as Trading standard user
And I open case "Auto-test Testproduct, Auto-test dishwasher – chemical hazard"
When I go to activity log
Then I should be able to add activity "Comment"

@ts-user @regression @corrective-action 
Scenario: Add corrective action
Given I login as Trading standard user
And I open case "Auto-test Testproduct, Auto-test dishwasher – chemical hazard"
When I go to supporting information tab
Then I should be able to add activity "Record corrective action"
And I should see "Corrective action was successfully recorded."

@ts-user @regression @corrective-action @add-attachment
Scenario: Add corrective action with a file
Given I login as Trading standard user
And I open case "Auto-test Testproduct, Auto-test dishwasher – chemical hazard"
When I go to supporting information tab
Then I should be add "Record corrective action" with a file
And I should see "Corrective action was successfully recorded."

  @regression @add-attachment @ts-user @test1
  Scenario: Validate error message when I don't chose a file
  Given I go to supporting information tab
  And I click link "Add supporting information"
  And I select "Other document or attachment"
  And I click continue button
  When I submit file upload
  Then I should see error message "Enter file"
  
  @regression @add-attachment @ts-user @test1
  Scenario: Validate error message when I don't enter attachment tile field
  When I add attachment to the case
  And I submit file upload
  And I Keep attachment title field empty
  And I click save attachment
  Then I should see error "Enter title"
 
  @regression  @add-attachment @ts-user @test1
  Scenario: Add attachment
  When I fill in attachment title
  And I click save attachment
  #Then I should see "File added sucessfully"
  
@regression @add-attachment @ts-user @test1
 Scenario: Validate back link on edit attachment page
 Given I click link "Edit document"
 When I click back
 Then I should see page "Supporting information"
 
@regression @add-attachment @ts-user @test1
 Scenario: Validate cancel on edit attachment page
 Given I click link "Edit document"
 When I click cancel
 Then I should see page "Supporting information"
 
 @regression @add-attachment @ts-user @test1
  Scenario: Validate back link on delete attachment
  Given I go to supporting information tab
  And I click link "Remove document"
  When I click back
  Then I should see page "Supporting information"
  
  @regression @add-attachment @ts-user @test1
  Scenario: remove attachment
  Given I click link "Remove document"
  And I click save attachment
  Then I should see "File was successfully removed"
  
 @regression @add-attachment @ts-users
 Scenario: Validate back link on add attachment
 Given I go to attachment tab
 And I click link "Add supporting information"
 When I click back
 Then I should see page "Overview"


Scenario: Add email via activity
Scenario: Add test results via activity







