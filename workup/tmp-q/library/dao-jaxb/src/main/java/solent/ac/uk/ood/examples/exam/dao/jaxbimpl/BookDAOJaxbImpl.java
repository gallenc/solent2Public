/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.exam.dao.jaxbimpl;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solent.ac.uk.ood.examples.exam.model.Book;
import solent.ac.uk.ood.examples.exam.model.BookDAO;
import solent.ac.uk.ood.examples.exam.model.BookList;

/**
 *
 * @author cgallen
 */
public class BookDAOJaxbImpl implements BookDAO {

    private static final Logger LOG = LoggerFactory.getLogger(BookDAOJaxbImpl.class);

    // jaxb context needs jaxb.index jaxbFile to be in same classpath
    // this path contains a list of Jaxb annotated classes for the context to parse
    private static final String CONTEXT_PATH = "solent.ac.uk.ood.examples.exam.model";

    // you must obtain lock before accessing objects and / or saving file
    public final Object Lock = new Object();

    private String dataFileLocation = null;

    private File jaxbFile = null;

    private BookList bookList = null;

    private JAXBContext jaxbContext = null;

    public BookDAOJaxbImpl(String dataFileLocation) {
        super();
        if (dataFileLocation == null) {
            throw new IllegalArgumentException("dataFile cannot be null");
        }
        this.dataFileLocation = dataFileLocation;
        load();
    }

    @Override
    public Book createBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("book cannot be null");
        }
        synchronized (Lock) {
            // set initial id if not set or increment by 1
            Integer id = (bookList.getLastBookId() == null) ? 1 : bookList.getLastBookId() + 1;

            bookList.setLastBookId(id);
            Book ecopy = copy(book);
            ecopy.setId(id);
            bookList.getBooks().add(ecopy);
            save();
            return ecopy;
        }
    }

    @Override
    public boolean deleteBook(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        synchronized (Lock) {
            Iterator<Book> it = bookList.getBooks().iterator();
            while (it.hasNext()) {
                Book en = it.next();
                if (id.equals(en.getId())) {
                    it.remove();
                    save();
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public void deleteAllBooks() {
        synchronized (Lock) {
            bookList.getBooks().clear();
        }
    }

    @Override
    public Book retrieveBook(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        synchronized (Lock) {
            for (Book en : bookList.getBooks()) {
                if (id.equals(en.getId())) {
                    return copy(en);
                }
            }
        }
        return null;
    }

    @Override
    public List<Book> retrieveAllBooks() {
        synchronized (Lock) {
            List<Book> returnList = new ArrayList<Book>();
            for (Book book : bookList.getBooks()) {
                returnList.add(copy(book));
            };
            return returnList;
        }
    }

    /**
     * Returns a list of all Books which match all of the set (i.e. not null)
     * fields of bookTemplate
     *
     * @param bookTemplate
     * @return
     */
    @Override
    public List<Book> retrieveMatchingBooks(Book bookTemplate) {
        if (bookTemplate == null) {
            throw new IllegalArgumentException("bookTemplate cannot be null");
        }
        List<Book> returnList = new ArrayList<Book>();
        synchronized (Lock) {
            for (Book book : bookList.getBooks()) {
                boolean match = true;
                if (bookTemplate.getId() != null) {
                    if (!bookTemplate.getId().equals(book.getId())) {
                        match = false;
                    }
                };
                if (bookTemplate.getTitle() != null) {
                    if (!bookTemplate.getTitle().equals(book.getTitle())) {
                        match = false;
                    }
                };
                if (bookTemplate.getAuthor() != null) {
                    if (!bookTemplate.getAuthor().equals(book.getAuthor())) {
                        match = false;
                    }
                };
                if (bookTemplate.getIsbn() != null) {
                    if (!bookTemplate.getIsbn().equals(book.getIsbn())) {
                        match = false;
                    }
                };
                if (match) {
                    returnList.add(copy(book));
                }
            }
            return returnList;
        }
    }

    @Override
    public Book updateBook(Book bookTemplate) {
        if (bookTemplate == null) {
            throw new IllegalArgumentException("book cannot be null");
        }
        synchronized (Lock) {
            for (Book en : bookList.getBooks()) {
                if (bookTemplate.getId().equals(en.getId())) {
                    boolean changedfield = false;

                    // update properties fields if only if bookTemplate field is set
                    if (bookTemplate.getTitle() != null) {
                        en.setTitle(bookTemplate.getTitle());
                        changedfield = true;
                    }
                    if (bookTemplate.getAuthor() != null) {
                        en.setAuthor(bookTemplate.getAuthor());
                        changedfield = true;
                    }
                    if (bookTemplate.getIsbn() != null) {
                        en.setIsbn(bookTemplate.getIsbn());
                        changedfield = true;
                    }
                    // save if anything changed
                    if (changedfield) {
                        save();
                    }
                    return copy(en);
                }

            }
        }
        return null; //book not found
    }

    /**
     * copies new Book data transfer objects to create detached object and so
     * avoid problems with indirect object modification
     *
     * @param book
     * @return independent copy of Book
     */
    private Book copy(Book book) {
        try {
            StringWriter sw1 = new StringWriter();
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(book, sw1);

            StringReader sr1 = new StringReader(sw1.toString());
            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
            Book newAccount = (Book) jaxbUnMarshaller.unmarshal(sr1);
            return newAccount;
        } catch (JAXBException ex) {
            throw new RuntimeException("problem copying jaxb object", ex);
        }
    }

    /**
     * loads jaxb file at beginning of program
     */
    private void load() {

        try {

            // jaxb context needs jaxb.index jaxbFile to be in same classpath
            // this contains a list of Jaxb annotated classes for the context to parse
            jaxbContext = JAXBContext.newInstance(CONTEXT_PATH);

            // try to load dataFileLocation
            jaxbFile = new File(dataFileLocation);
            LOG.debug("using dataFile:" + jaxbFile.getAbsolutePath());

            if (jaxbFile.exists()) {
                LOG.debug("dataFile exists loading:" + jaxbFile.getAbsolutePath());
                // load jaxbFile
                Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
                bookList = (BookList) jaxbUnMarshaller.unmarshal(jaxbFile);
            } else {
                // create annd save an empty jaxbFile
                LOG.debug("dataFile does not exist creating new " + jaxbFile.getAbsolutePath());

                bookList = new BookList();

                // make directories if dont exist
                jaxbFile.getParentFile().mkdirs();

                // save empty data to new file
                save();
            }

        } catch (JAXBException ex) {
            throw new RuntimeException("problem creating persistor", ex);
        }

    }

    /**
     * saves data to datafile on updates
     */
    private void save() {
        try {
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(bookList, jaxbFile);
            if (LOG.isDebugEnabled()) {
                StringWriter sw1 = new StringWriter();
                jaxbMarshaller.marshal(bookList, sw1);
                LOG.debug("saving xml to file:" + sw1.toString());
            }

        } catch (JAXBException ex) {
            throw new RuntimeException("problem persisting dao", ex);
        }
    }

}
