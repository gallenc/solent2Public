/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.exam.service.test;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import solent.ac.uk.ood.examples.exam.model.Book;
import solent.ac.uk.ood.examples.exam.model.ServiceFacade;
import solent.ac.uk.ood.examples.exam.model.ServiceFactory;
import solent.ac.uk.ood.examples.exam.service.ServiceFactoryImpl;

/**
 *
 * @author cgallen
 */
public class ServiceFacadeImplTest {

    public static final String TEST_DATA_FILE = "./target/testfile.xml";

    // Only some basic tests as most tests already done in BookDAO tests
    @Test
    public void simpleServiceFacadeTest() {

        // use service factory to get access to service
        ServiceFactory serviceFactory = new ServiceFactoryImpl(TEST_DATA_FILE);
        assertNotNull(serviceFactory);

        ServiceFacade serviceFacade = serviceFactory.getServiceFacade();
        assertNotNull(serviceFacade);
        
        // clear file before anything else
        serviceFacade.deleteAllBooks();

        Book book = new Book();
        book.setTitle("testFieldA");

        serviceFacade.createBook(book);
        List<Book> retrievedBooks = serviceFacade.retrieveMatchingBooks(book);

        assertEquals(1, retrievedBooks.size());
    }
}
