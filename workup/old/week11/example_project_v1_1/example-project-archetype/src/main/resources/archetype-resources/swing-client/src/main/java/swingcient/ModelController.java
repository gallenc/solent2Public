#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ${package}.swingcient;

import ${package}.model.Entity;

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
