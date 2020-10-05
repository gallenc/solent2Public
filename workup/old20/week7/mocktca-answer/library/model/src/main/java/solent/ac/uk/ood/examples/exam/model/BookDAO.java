package solent.ac.uk.ood.examples.exam.model;

import java.util.List;

public interface BookDAO {

    public Book createBook(Book book);

    public boolean deleteBook(Integer id);

    public void deleteAllBooks();

    public Book retrieveBook(Integer id);

    public List<Book> retrieveAllBooks();

    public List<Book> retrieveMatchingBooks(Book bookTempate);

    public Book updateBook(Book book);
}
