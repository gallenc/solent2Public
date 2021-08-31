/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardvalidator.impl;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * card identifiers taken from https://www.bincodes.com/bin-search/
 *
 * @author cgallen
 */
public interface SupportedCardIssuerIdentificationNumbers {

    public static final String VISA_BANK_OF_IRELAND_UK = "424519";

    public static final String VISA_NAT_WEST = "428586";

    public static final String MASTERCARD_TSB_BANK = "513388";

    public static final String MASTERCARD_LLOYDS_BANK_PLC = "512569";

    public static final String AMERICAN_EXPRESS_LLOYDS_BANK_PLC = "377064";

    // this creates a constant map. You cannot change a Collections.unmodifiableMap
    public static final Map<String, String> ISSUER_IDENTIFICATION_MAP
            = Collections.unmodifiableMap(new HashMap() {
                {
                    put("VISA_BANK_OF_IRELAND_UK", VISA_BANK_OF_IRELAND_UK);
                    put("VISA_NAT_WEST", VISA_NAT_WEST);
                    put("MASTERCARD_TSB_BANK", MASTERCARD_TSB_BANK);
                    put("MASTERCARD_LLOYDS_BANK_PLC", MASTERCARD_LLOYDS_BANK_PLC);
                   // bug - not validating - left out  
                   // put("AMERICAN_EXPRESS_LLOYDS_BANK_PLC", AMERICAN_EXPRESS_LLOYDS_BANK_PLC);
                }
            });
}