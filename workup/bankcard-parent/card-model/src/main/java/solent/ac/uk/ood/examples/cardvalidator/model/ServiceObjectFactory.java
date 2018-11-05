/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardvalidator.model;

/**
 *
 * @author cgallen
 */
public interface ServiceObjectFactory {
    
    TransactionApi getTransactionApi();
    
    BankApi getBankApi();
    
}
