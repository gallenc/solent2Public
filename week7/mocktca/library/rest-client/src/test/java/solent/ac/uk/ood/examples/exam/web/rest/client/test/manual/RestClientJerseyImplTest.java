/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.exam.web.rest.client.test.manual;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.junit.Test;
import static org.junit.Assert.*;
import solent.ac.uk.ood.examples.exam.model.Book;
import solent.ac.uk.ood.examples.exam.model.ReplyMessage;
import solent.ac.uk.ood.examples.exam.web.rest.client.RestClientJerseyImpl;

/**
 *
 * @author cgallen
 */
public class RestClientJerseyImplTest {

    String baseUrl = "http://localhost:8680/";

    MediaType mediaType = MediaType.APPLICATION_XML_TYPE;

    @Test
    public void restClientRetreiveTest() {

        RestClientJerseyImpl restClient = new RestClientJerseyImpl(baseUrl, mediaType);

        // try to retreive an unknown book
        ReplyMessage replyMessage = restClient.retrieveBook(Integer.SIZE);
        assertNotNull(replyMessage);
        assertTrue(replyMessage.getBookList().getBooks().isEmpty());

        // try to retreive book with id 1
        ReplyMessage replyMessage2 = restClient.retrieveBook(1);
        assertNotNull(replyMessage2);
        assertEquals(1, replyMessage2.getBookList().getBooks().size());

        Book book = replyMessage2.getBookList().getBooks().get(0);
        System.out.println("Received Book: " + book);

    }

    @Test
    public void restClientRetreiveTemplateTest() {

        RestClientJerseyImpl restClient = new RestClientJerseyImpl(baseUrl, mediaType);

        Book bookTempate = new Book();
        bookTempate.setTitle("abcd");

        // try to retreive an unknown book
        ReplyMessage replyMessage = restClient.retrieveMatchingBooks(bookTempate);
        assertNotNull(replyMessage);

        List<Book> bookList =  replyMessage.getBookList().getBooks();
        System.out.println("Received "
                + bookList.size()
                + " Books");
        
       for(Book e: bookList){
           System.out.println("   "+ e);
       }
        
    }
}
