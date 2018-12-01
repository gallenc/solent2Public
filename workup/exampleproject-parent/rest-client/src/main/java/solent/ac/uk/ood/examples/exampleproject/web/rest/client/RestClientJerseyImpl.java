/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.exampleproject.web.rest.client;

import solent.ac.uk.ood.examples.exampleproject.model.Entity;
import solent.ac.uk.ood.examples.exampleproject.model.ReplyMessage;
import solent.ac.uk.ood.examples.exampleproject.model.RestInterface;

/**
 *
 * @author cgallen
 */
public class RestClientJerseyImpl implements RestInterface {

    @Override
    public ReplyMessage retrieveMatchingEntites(Entity entityTempate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public ReplyMessage retrieveEntity(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
