Feature: MSPSDS-1046 See other team members

 @your-team
Scenario: Verify user team is displayed
Given I login as OPSS user
When I am on dashboard
Then I should see my team name "Your team" displayed

@your-team
Scenario: Verify team
When I click team "Your team"
Then I should see team page "OPSS Testing"
And I should see team member "nasirkhan.beis@gmail.com"
