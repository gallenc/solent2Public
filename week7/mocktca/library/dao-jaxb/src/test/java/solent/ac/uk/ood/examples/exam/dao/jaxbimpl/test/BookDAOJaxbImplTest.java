/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.exam.dao.jaxbimpl.test;

import java.io.File;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.exam.dao.jaxbimpl.BookDAOJaxbImpl;
import solent.ac.uk.ood.examples.exam.model.Book;
import solent.ac.uk.ood.examples.exam.model.BookDAO;

/**
 * tests for bookDao.createBook(book) bookDao.deleteBook(Id) bookDao.retrieveAllBooks() bookDao.retrieveBook(Id)
 * bookDao.retrieveMatchingEntites(bookTempate) bookDao.updateBook(book)
 *
 * @author cgallen
 */
public class BookDAOJaxbImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(BookDAOJaxbImplTest.class);

    public final String TEST_DATA_FILE_LOCATION = "target/testDaofile.xml";

    @Test
    public void testDestinationsDAOJaxb() {

        // delete test file at start of test
        File file = new File(TEST_DATA_FILE_LOCATION);
        file.delete();
        assertFalse(file.exists());

        // create dao
        BookDAO bookDao = new BookDAOJaxbImpl(TEST_DATA_FILE_LOCATION);

        // check that new file created
        assertTrue(file.exists());

        // check there are no books
        assertTrue(bookDao.retrieveAllBooks().isEmpty());

        // add a 3 books
        int ENTITY_NUMBER = 4;
        for (int intityId = 0; intityId < ENTITY_NUMBER; intityId++) {
            Book book = new Book();
            book.setTitle("title_" + intityId);
            book.setAuthor("author_" + intityId);;
            book.setIsbn("isbn_" + intityId);;

            LOG.debug("adding book:" + book);
            Book e = bookDao.createBook(book);
            assertNotNull(e);
        }

        // check 3 books added
        assertTrue(ENTITY_NUMBER == bookDao.retrieveAllBooks().size());

        // check return false for delete unknown book
        assertFalse(bookDao.deleteBook(Integer.SIZE));

        // find an book to delete
        List<Book> elist = bookDao.retrieveAllBooks();
        Integer idToDelete = elist.get(1).getId();
        LOG.debug("deleting  book:" + idToDelete);

        // check found and deleted
        assertTrue(bookDao.deleteBook(idToDelete));

        // check no longer found after deletion
        assertNull(bookDao.retrieveBook(idToDelete));

        // check books size decremeted
        List<Book> elist2 = bookDao.retrieveAllBooks();
        assertTrue(ENTITY_NUMBER - 1 == elist2.size());

        // update book
        Book bookToUpdate = elist2.get(1);
        LOG.debug("updating book: " + bookToUpdate);

        // add 3 newProperties for book
        bookToUpdate.setTitle("title_Update");
        bookToUpdate.setAuthor("author_Update");
        bookToUpdate.setIsbn(null); // do not update field C
        LOG.debug("update template: " + bookToUpdate);

        Book updatedBook = bookDao.updateBook(bookToUpdate);
        LOG.debug("updated book: " + updatedBook);
        assertNotNull(updatedBook);

        // check book updated
        Book retrievedBook = bookDao.retrieveBook(updatedBook.getId());
        LOG.debug("retreived book: " + retrievedBook);
        assertEquals(bookToUpdate.getTitle(), retrievedBook.getTitle());
        assertEquals(bookToUpdate.getTitle(), retrievedBook.getTitle());
        assertNotEquals(bookToUpdate.getIsbn(), retrievedBook.getIsbn());

        // test retrieve matching books
        List<Book> bookList = bookDao.retrieveAllBooks();
        Book searchfor = bookList.get(2);
        LOG.debug("searching for: " + searchfor);

        Book template = new Book();
        template.setAuthor(searchfor.getAuthor());
        LOG.debug("using template : " + template);

        List<Book> retrievedList = bookDao.retrieveMatchingBooks(template);
        assertEquals(1, retrievedList.size());

        LOG.debug("found : " + retrievedList.get(0));
        assertEquals(searchfor, retrievedList.get(0));

    }

}
