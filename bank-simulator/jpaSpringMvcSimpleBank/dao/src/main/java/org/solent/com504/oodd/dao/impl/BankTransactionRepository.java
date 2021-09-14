/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.dao.impl;

import java.util.List;
import org.solent.com504.oodd.bank.model.dto.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.solent.com504.oodd.bank.model.dao.BankTransactionDAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author cgallen
 */
@Repository
public interface BankTransactionRepository extends BankTransactionDAO, JpaRepository<BankTransaction, Long> {
  
   @Override
   @Query("SELECT bt FROM BankTransaction bt WHERE bt.fromAccount.creditcard.cardnumber = :cardnumber ")
   List<BankTransaction> findBankTransactionsFromCreditCardNumber(@Param("cardnumber") String cardnumber);
    
  
}
