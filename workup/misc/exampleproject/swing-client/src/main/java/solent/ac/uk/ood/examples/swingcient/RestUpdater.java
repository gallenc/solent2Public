/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.swingcient;

import solent.ac.uk.ood.examples.model.EntityDAO;

/**
 *
 * @author cgallen
 */
public class RestUpdater {
    EntityDAO entityDAO = null;
    String updatechron ="";
    String baseUrl = "";
    
    public EntityDAO getEntityDAO() {
        return entityDAO;
    }

    public void setEntityDAO(EntityDAO entityDAO) {
        this.entityDAO = entityDAO;
    }

    public String getUpdatechron() {
        return updatechron;
    }

    public void setUpdatechron(String updatechron) {
        this.updatechron = updatechron;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    
    public boolean immediateUpdate(){
        return false;
    }
            
}
