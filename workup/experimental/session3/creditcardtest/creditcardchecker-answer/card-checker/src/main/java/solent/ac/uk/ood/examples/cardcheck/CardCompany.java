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
 * enum for card company specifics
 * 
 */
public enum CardCompany {

    VISA ("^4[0-9]{12}(?:[0-9]{3})?$", "VISA"),
    MASTERCARD ("^5[1-5][0-9]{14}$", "MASTER"),
    AMEX ("^3[47][0-9]{13}$", "AMEX"),
    DINERS ("^3(?:0[0-5]|[68][0-9])[0-9]{11}$", "Diners"),
    DISCOVER ("^6(?:011|5[0-9]{2})[0-9]{12}$", "DISCOVER"),
    JCB ("^(?:2131|1800|35\\d{3})\\d{11}$", "JCB");

    private String regex;
    private String issuerName;

    CardCompany(String regex, String issuerName) {
        this.regex = regex;
        this.issuerName = issuerName;
    }

    public boolean matches(String card) {
        return card.matches(this.regex);
    }
    
    public String getIssuerName() {
        return this.issuerName;
    }
    
    /**
     * get an enum from a card number
     * @param card
     * @return
     */
    public static CardCompany gleanCompany(String card) {
        for (CardCompany cc : CardCompany.values()){
            if (cc.matches(card)) {
                return cc;
            }
        }
        return null;
    }

    /**
     * get an enum from an issuerName
     * @param issuerName
     * @return
     */
    public static CardCompany gleanCompanyByIssuerName(String issuerName) {
        for (CardCompany cc : CardCompany.values()){
            if (cc.getIssuerName().equals(issuerName)) {
                return cc;
            }
        }
        return null;
    }
}