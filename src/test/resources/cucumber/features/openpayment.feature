Feature: Openpayment API

    Scenario: Provider Openpayments
        Given I have "132655" provider id
        When I ask for all the payments
        Then I receive 2 payments