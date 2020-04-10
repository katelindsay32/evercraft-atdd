Feature: Character stuff

  Scenario: Create a character
    Given a new game is started
    When I create a character
    Then my character shows up in the game