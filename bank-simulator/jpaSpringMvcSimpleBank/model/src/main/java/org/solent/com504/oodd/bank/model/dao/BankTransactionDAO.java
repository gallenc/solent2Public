package org.solent.com504.oodd.bank.model.dao;

import java.util.List;
import java.util.Optional;
import org.solent.com504.oodd.bank.model.dto.BankTransaction;
import org.springframework.data.repository.CrudRepository;

public interface BankTransactionDAO  extends CrudRepository<BankTransaction,Long> {
    
    List<BankTransaction> findBankTransactionsFromCreditCardNumber(String cardnumber);
    
    // from crud repository
    long count();
    
    void delete(BankTransaction t);
    
    void deleteAll();

    void deleteById(Long id);

    boolean existsById(Long id);

    List<BankTransaction> findAll();

    Optional<BankTransaction> findById(Long id);

    BankTransaction getOne(Long id);
    
    <S extends BankTransaction> S save(S s);
    
}
