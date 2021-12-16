Feature:check Add/del items
  @AfterAll
  Scenario: add 3 duck
    When user open main form magazine
    Then user add 3 duck
    Then user del 3 duck

