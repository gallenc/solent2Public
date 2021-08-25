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
 * Container for validation result
 */
public class CardValidationResult {
    private boolean valid;
    private CardCompany cardType;
    private String error;
    private String cardNo;

    public CardValidationResult(String cardNo, String error) {
        this.cardNo = cardNo;
        this.error = error;
        this.valid = false;
    }
    public CardValidationResult(String cardNo, CardCompany cardType) {
        this.cardNo = cardNo;
        this.valid = true;
        this.cardType = cardType;
    }
    
    /**
     * 
     * @return true if the card value is valid
     */
    public boolean isValid() {
        return valid;
    }
    
    /**
     * If the card is valid 
     * @return an enum value corresponding to the issuer of the card
     */
    public CardCompany getCardType() {
        return cardType;
    }
    
    /**
     * if the card is not valid or there is an unknown issuer
     * @return error string explaining the problem
     */
    public String getError() {
        return error;
    }
    
    /**
     * 
     * @return the number of the card
     */
    public String cardNo() {
        return this.cardNo;
    }
    
    /**
     * 
     * @return a simple message explaining the status of the card.
     */
    public String getMessage() {
        return cardNo + "    >>    " + ((valid) ? ("card: " + this.cardType ): error) ;
    }
}