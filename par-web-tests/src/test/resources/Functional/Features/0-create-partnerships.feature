Feature: Create new Partnership journey


@nasir @regression
Scenario: Verify primary authority can see your partnerships
   Given I login as "par_authority@example.com"
   Then I should be able to see "See your partnerships"
@regression   
Scenario: Verify primary authority has permission to create new partnership
   Given I login as "par_authority@example.com"
   Then I should be able to see "Apply for a new partnership"


@nasir @regression
Scenario: New direct Partnership
	Given I login as "par_authority@example.com"
	When I click on "Apply for a new partnership"
	And I select authority "City Enforcement Squad"
	And I click continue
	And I select partnership type "Direct"
	And I fill in partnership details
	Then I should see "Invite the business " page
  And I see invite email with the text "A partnership application has been started"
  
  When I click continue
	Then I should see partnership creation confirmation "Check partnership information "
	
  When I accept terms and conditions 
  And I click on save
  Then I should see "Partnership application completed"
	
@nasir @regression
Scenario: New Co-ordinated Partnership
	Given I login as "par_authority@example.com"
	When I click on "Apply for a new partnership"
	And I select authority "Upper West Side Borough Council"
	And I click continue
	And I select partnership type "Co-ordinated"
	And I fill in partnership details
	Then I should see "Invite the business " page
  And I see invite email with the text "A partnership application has been started"
  
  When I click continue
	Then I should see partnership creation confirmation "Check partnership information "
	
  When I accept terms and conditions 
  And I click on save
  Then I should see "Partnership application completed"
	

Scenario: Verify notification is sent out when partnership is created
@regression
Scenario: Verify authority user can confirm org details
	Given I login as "par_authority@example.com"
	When I click on "See your partnerships"
	And I click on "ABCD Mart"
	And I confirm on behalf organisation
	Then I should see "Confirm the details about the organisation"
	
	
	When I click continue
	Then I should see "Confirm the primary address details"
	
	When I click continue
	Then I should see "Confirm the primary contact details"
	
	When I click continue
	Then I should see "Confirm the SIC code"
	
	When I select sic code  "8.0  -  Property sale, letting and estate agency"
	And I click continue
	Then I should see "Confirm number of employee"
	
	When I select employee band "10 to 49"
  And I click continue
  Then I should see "Confirm the trading name"
  

