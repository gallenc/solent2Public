package solent.ac.uk.ood.examples.cardcheck.dao;

import java.util.List;
import solent.ac.uk.ood.examples.cardcheck.model.Transaction;

public interface TransactionDAO {

    public Transaction getById(Integer id);

    public List<Transaction> getAll();

    public Transaction add(Transaction transaction);

    public void delete(Transaction transaction);

    public void deleteById(Integer id);

    public Transaction update(Transaction transaction);

    public Transaction create();
}
