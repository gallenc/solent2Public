package solent.ac.uk.ood.examples.cardcheck.dao;

import java.util.List;
import solent.ac.uk.ood.examples.cardcheck.model.Bank;

public interface BankDAO {

    public Bank getById(Integer id);

    public List<Bank> getAll();

    public Bank add(Bank bank);

    public void delete(Bank bank);

    public void deleteById(Integer id);

    public Bank update(Bank bank);

    public Bank create();
}
