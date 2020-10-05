#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ${package}.model;

/**
 *
 * @author cgallen
 */
public interface ServiceFactory {
    
    public ServiceFacade getServiceFacade();
    
}
