/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.exam.service;

import java.util.List;
import solent.ac.uk.ood.examples.exam.model.Book;
import solent.ac.uk.ood.examples.exam.model.BookDAO;
import solent.ac.uk.ood.examples.exam.model.ServiceFacade;

/**
 *
 * @author cgallen
 */
public class ServiceFacadeImpl implements ServiceFacade {
    
    BookDAO bookDAO = null;

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public void deleteAllBooks() {
       bookDAO.deleteAllBooks();
    }

    @Override
    public Book createBook(Book book) {
        return bookDAO.createBook(book);
    }

    @Override
    public boolean deleteBook(Integer id) {
        return bookDAO.deleteBook(id);
    }

    @Override
    public Book retrieveBook(Integer id) {
        return bookDAO.retrieveBook(id);
    }

    @Override
    public List<Book> retrieveAllBooks() {
        return bookDAO.retrieveAllBooks();
    }

    @Override
    public List<Book> retrieveMatchingBooks(Book bookTempate) {
        return bookDAO.retrieveMatchingBooks(bookTempate);
    }

    @Override
    public Book updateBook(Book book) {
        return bookDAO.updateBook(book);
    }
    
}
