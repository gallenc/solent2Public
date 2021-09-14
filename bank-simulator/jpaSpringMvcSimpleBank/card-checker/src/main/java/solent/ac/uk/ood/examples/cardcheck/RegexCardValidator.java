/* Modifications Copyright 2018 Craig Gallen
  *********************************************
  * Original gist Copyright 2018 Ian Chan
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at

  * http://www.apache.org/licenses/LICENSE-2.0

  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
*/

package solent.ac.uk.ood.examples.cardcheck;

/**
 * Validator for credit card numbers
 * Checks validity and returns card type
 * 
 * see https://gist.github.com/icchan/47d83bacc5113db59fbc
 * 
 * @author ian.chen
 */
public class RegexCardValidator {
    
    /**
     * Checks if the field is a valid credit card number.
     * @param card The card number to validate.
     * @return CardValidationResult provides information on the card if the card is valid.
     */
    public static CardValidationResult isValid(final String cardIn) {
        
        if (cardIn == null) {
            return new CardValidationResult("","card cannot be null");
        }
        
        String card = cardIn.replaceAll("[^0-9]+", ""); // remove all non-numerics
        if ((card == null) || (card.length() < 13) || (card.length() > 19)) {
            return new CardValidationResult(card,"failed length check");
        }

        if (!luhnCheck(card)) {
            return new CardValidationResult(card,"failed luhn check");
        }

        CardCompany cc = CardCompany.gleanCompany(card);
        if (cc == null) return new CardValidationResult(card,"failed card company check");
        
        return new CardValidationResult(card,cc);
    }

    /**
     * Checks for a valid credit card number.
     * @param cardNumber Credit Card Number.
     * @return true if the card number passes the luhnCheck.
     */
    protected static boolean luhnCheck(String cardNumber) {
        // number must be validated as 0..9 numeric first!!
        int digits = cardNumber.length();
        int oddOrEven = digits & 1;
        long sum = 0;
        for (int count = 0; count < digits; count++) {
            int digit = 0;
            try {
                digit = Integer.parseInt(cardNumber.charAt(count) + "");
            } catch(NumberFormatException e) {
                return false;
            }

            if (((count & 1) ^ oddOrEven) == 0) { // not
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }

        return (sum == 0) ? false : (sum % 10 == 0);
    }

}