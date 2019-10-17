/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.impl.dao.jaxb;

import java.io.File;
import java.io.StringWriter;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.solent.com504.factoryandfacade.impl.dao.simple.AnimalDaoImpl;
import org.solent.com504.factoryandfacade.model.dao.AnimalDao;
import org.solent.com504.factoryandfacade.model.dto.Animal;
import org.solent.com504.factoryandfacade.model.dto.AnimalList;
import org.solent.com504.factoryandfacade.model.dto.AnimalType;

/**
 *
 * @author gallenc
 */
public class AnimalDaoJaxbImpl extends AnimalDaoImpl implements AnimalDao {

    public AnimalDaoJaxbImpl(String filePath) {
        super();
        load();
    }
    
//    {
//        try{
//                   File file = new File("target/testTransactionData.xml");
//            System.out.println("writing test file to " + file.getAbsolutePath());
//
//            // this contains a list of Jaxb annotated classes for the context to parse
//            // NOTE you must also have a jaxb.index or ObjectFactory in the same classpath
//            JAXBContext jaxbContext = JAXBContext.newInstance("org.solent.com504.factoryandfacade.model.dto");
//
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//
//            // output pretty printed
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//
//            // create XML from the object
//            // marshal the object lists to system out, a file and a stringWriter
//            jaxbMarshaller.marshal(super.animalList, System.out);
//            jaxbMarshaller.marshal(super.animalList, file);
//
//            // string writer is used to compare received object
//            StringWriter sw1 = new StringWriter();
//            jaxbMarshaller.marshal(super.animalList, sw1);
//
//            // having written the file we now read in the file for test
//            Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
//            AnimalList receivedTransactionResult = (AnimalList) jaxbUnMarshaller.unmarshal(file);
//            } catch (JAXBException e) {
//            throw new RuntimeException("problem testing jaxb marshalling", e);
//        }
//    }

    /**
     * loads xml into dao using jaxb
     */
    private void load() {

    }

    /**
     * saves dao as xml using jaxb
     */
    private void save() {

    }

    @Override
    public synchronized Animal retrieve(long id) {
        return super.retrieve(id);
    }

    @Override
    public synchronized Animal updateOrSave(Animal animal) {
        Animal updatedAnimal = super.updateOrSave(animal);
        save(); // saves updated list
        return updatedAnimal;
    }

    @Override
    public synchronized boolean delete(long id) {
        boolean deleted = super.delete(id);
        save(); // saves updated list
        return deleted;
    }

    @Override
    public synchronized List<Animal> retrieve(Animal animalTemplate) {
        List<Animal> returnedAnimals = super.retrieve(animalTemplate);
        return returnedAnimals;
    }

    @Override
    public synchronized List<Animal> retrieveAll() {
        List<Animal> returnedAnimals = super.retrieveAll();
        return returnedAnimals;
    }

    @Override
    public Animal create(AnimalType animalType) {
        return super.create(animalType);
    }

}
