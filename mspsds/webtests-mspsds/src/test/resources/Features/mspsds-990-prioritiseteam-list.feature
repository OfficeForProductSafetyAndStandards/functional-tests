Feature: Display prioritise teamslist
	As an OPSS user
	in order to assign a case
	I should be able see prioritse team list

 @nasir
Scenario: Verify priority list displayed
Given I login as OPSS user
When I want to assign any case
Then I should see team "OPSS Processing"
And I should see team "OPSS Trading Standards Co-ordination"
And I should see team "OPSS Incident Management" 