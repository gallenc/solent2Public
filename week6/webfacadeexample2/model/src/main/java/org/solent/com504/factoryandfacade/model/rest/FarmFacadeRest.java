package org.solent.com504.factoryandfacade.model.rest;

import org.solent.com504.factoryandfacade.model.dto.ReplyMessage;


/**
 * To make the ReST interface easier to program.
 * All of the replies are contained in ReplyMessage classes 
 * but only the fields indicated are populated with each reply.
 * All replies will contain a code and a debug message.
 * Possible replies are:
 *     List<String>  replyMessage.getStringList()
 *     AnimalList replyMessage.getAnimalList()
 *     int replyMessage.getCode()
 *     replyMessage.getDebugMessage();
 *  * 
 * @author cgallen
 */
public interface FarmFacadeRest {

    /**
     * Get all animals on the farm
     *
     * @return list of all Animals in List<String>  replyMessage.getStringList()
     */
    public ReplyMessage getAllAnimals();
    
    /**
     * Creates an Animal of a given AnimalType with a name and adds the animal 
     * to the list of animals stored by this farm. Note only one animal can be
     * added with the same name
     *
     * @param animalType type of animal ( which must have been created by the AnimalTypeDao)
     * @param name name to give this animal - does not have to be unique
     * @return an animal of a given type with the supplied name which has been stored. Animal will also have been given an id.
     * @ returns error code if animal name is duplicated. All animal names must be unique.
     * AnimalList replyMessage.getAnimalList().get(0) contains returned animal
     */
    public ReplyMessage addAnimal(String animalType, String name);

    /**
     *
     * @param animalType type name for the animal type
     * @return returns a list of all animals of a given type
     *    AnimalList replyMessage.getAnimalList() contains returned animals
     */
    public ReplyMessage getAnimalsOfType(String animalType);

    /**
     * returns the first animal in the list which has a given name
     * Note that names should be unique
     * 
     * @param name
     * @return ReplyMesssage  AnimalList replyMessage.getAnimalList()
     */
    public ReplyMessage getAnimal(String name);

    /**
     * remove the first animal in the list which has a given name
     *
     * @param name
     * @return  ReplyMessage OK if deleted animal 
     */
    public ReplyMessage removeAnimal(String name);

    /**
     * returns a list of strings describing the supported animal types
     * @return  ReplyMessage List<String>  replyMessage.getStringList()
     */
    public ReplyMessage getSupportedAnimalTypes();
}
