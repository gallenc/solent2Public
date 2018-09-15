/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template jaxbFile, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.cardcheck.dao.jaxbimpl;

import java.io.File;
import java.io.StringWriter;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import solent.ac.uk.ood.examples.cardcheck.model.Account;
import solent.ac.uk.ood.examples.cardcheck.model.Bank;
import solent.ac.uk.ood.examples.cardcheck.model.BankModelLists;
import solent.ac.uk.ood.examples.cardcheck.model.Transaction;

/**
 * for Generic dao
 * https://www.baeldung.com/simplifying-the-data-access-layer-with-spring-and-java-generics
 * for jdbc dao https://github.com/danielniko/SimpleJspServletDB
 * https://danielniko.wordpress.com/2012/04/17/simple-crud-using-jsp-servlet-and-mysql/
 *
 * @author gallenc
 */
public class ModelJaxbPersistor {
    
    private static final Logger LOG = LoggerFactory.getLogger(ModelJaxbPersistor.class);
    
    private final String dataFileLocation;
    
    private final File jaxbFile;
    
    // you must obtain lock before accessing objects and / or saving file
    public final Object Lock = new Object();
    
    private final JAXBContext jaxbContext;
    
    private final BankModelLists bankModelLists;
    
    public ModelJaxbPersistor(String dataFileLocation) {
        
       super();
        if (dataFileLocation == null) throw new IllegalArgumentException("dataFile cannot be null");
        
        try {

            // jaxb context needs jaxb.index jaxbFile to be in same classpath
            // this contains a list of Jaxb annotated classes for the context to parse
            jaxbContext = JAXBContext.newInstance("solent.ac.uk.ood.examples.cardcheck.model");

            // try to load dataFileLocation
            this.dataFileLocation = dataFileLocation;
            jaxbFile = new File(dataFileLocation);
            LOG.debug("using dataFile:" + jaxbFile.getAbsolutePath());
            
            if (jaxbFile.exists()) {
                // load jaxbFile
                Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
                bankModelLists = (BankModelLists) jaxbUnMarshaller.unmarshal(jaxbFile);
            } else {
                // create annd save an empty jaxbFile
                bankModelLists = new BankModelLists();
                save();
            }
            
        } catch (JAXBException ex) {
            throw new RuntimeException("problem creating persistor", ex);
        }
        
    }

    /**
     * saves data to datafile
     */
    public void save() {
        try {
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(bankModelLists, jaxbFile);
            if (LOG.isDebugEnabled()) {
                StringWriter sw1 = new StringWriter();
                jaxbMarshaller.marshal(bankModelLists, sw1);
                LOG.debug("saving xml to file:" + sw1.toString());
            }
             
        } catch (JAXBException ex) {
            throw new RuntimeException("problem creating persistor", ex);
        }
    }

    /**
     * @return the accountList
     */
    public List<Account> getAccountList() {
        List<Account> accountList = bankModelLists.getAccountList();
        return accountList;
    }

    /**
     * @return the bankList
     */
    public List<Bank> getBankList() {
        List<Bank> bankList = bankModelLists.getBankList();
        return bankList;
    }

    /**
     * @return the transactionList
     */
    public List<Transaction> getTransactionList() {
        List<Transaction> transactionList = bankModelLists.getTransactionList();
        return transactionList;
    }
    
}
