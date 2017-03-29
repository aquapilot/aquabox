Feature: Display useful informations at startup
  As a user,
  I want to see some feedback
  So that I know the aquabox application started correctly or not.

  Scenario: Launch aquabox
    Given I am a user
    When I run the aquabox jar
    Then I should see credits at startup

  Scenario: Get aquabox version
    Given I am a user
    When I run the aquabox jar with --version flag
    Then I should see the aquabox version

  Scenario: Launch aquabox in debug mode
    Given I am a user
    When I run the aquabox jar with --debug flag
    Then I should see debug messages in the console