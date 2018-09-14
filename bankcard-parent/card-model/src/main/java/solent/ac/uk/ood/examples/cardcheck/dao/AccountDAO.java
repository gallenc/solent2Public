package solent.ac.uk.ood.examples.cardcheck.dao;

import java.util.List;
import solent.ac.uk.ood.examples.cardcheck.model.Account;

public interface AccountDAO {

    public Account getById(Integer id);

    public List<Account> getAll();

    public Account add(Account account);

    public void delete(Account account);

    public void deleteById(Integer id);

    public Account update(Account account);

    public Account create();
}
