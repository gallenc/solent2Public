/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.swingcient;

import solent.ac.uk.ood.examples.model.Entity;

/**
 *
 * @author cgallen
 */
public interface ModelController {

    void clearSearch();

    void findMatchingSearch(Entity templateEntity);

    EntityListTableModel getEntityListTableModel();
    
    boolean forceReloadData();
    
}
