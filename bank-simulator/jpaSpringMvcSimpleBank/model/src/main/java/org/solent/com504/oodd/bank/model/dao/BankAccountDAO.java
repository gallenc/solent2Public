package org.solent.com504.oodd.bank.model.dao;

import java.util.List;
import java.util.Optional;
import org.solent.com504.oodd.bank.model.dto.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountDAO extends CrudRepository<BankAccount, Long> {

    BankAccount findBankAccountByNumber(String sortcode, String accountNo);

    BankAccount findBankAccountByCreditCardNo(String cardnumber);

    // crud repository interfaces
    long count();

    void delete(BankAccount t);

    void deleteAll();

    void deleteById(Long id);

    boolean existsById(Long id);

    List<BankAccount> findAll();

    Optional<BankAccount> findById(Long id);

    BankAccount getOne(Long id);

    <S extends BankAccount> S save(S s);

}
