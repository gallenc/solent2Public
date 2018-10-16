## Week 4 - mock TCA

This week we are breaking from the bankcard project and doing a Mock Time Constrained Assessment.

The exercise will help us guage how we are progressing and where we need to improve.

It will also help you prepare better for the real TCA at the end of the module.

## Homework

Draw a use case and robustness diagram for the following scenario.

For the robustness diagram as a boundery objects use the cvv validation api  and card object already defined and also a transaction api which has the method transactionResult authoriseTransaction( CreditCard creditCard, String shopkeeperAccountId)

A credit card machine can read a card and authorise a transaction for a 'card unseen' transaction (i.e. over the phone). 

A shop keeper enters the £ amount, card number and cvv and selects enter.

The card machine contacts issues a transaction request to the payment gateway provider (using a ResT api). 

The transaction contains the card details and the account to which to transfer the money.

The card provider checks the cvv based upon the bank ID for the bank which issued the card.

If the cvv is valid, the gateway attemptsto transfer the money between the card owners account and the account of the credt card machine owner ( i.e. the shop keepers account).

If the card owner does not have enough money in their account the transaction fails.

A response is sent back to the card machien indicating transaction success or failuer and a reason code for failure if reqired.

The transaction success or failure is logged in the local card machine.

The transaction success or failure is logged in the card gateway.
