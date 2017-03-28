Feature: Display credits at startup
  As a user,
  I want to see a credit message at startup
  So that I know I started the aquabox application.
  
  Scenario: Launch aquabox
    Given I am a user
    When I run the aquabox jar
    Then I should see credits at startup