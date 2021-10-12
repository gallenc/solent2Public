/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.devops.message.jms;

/**
 *
 * @author cgallen
 */
public interface StringMessageHandler {
    
    public void onMessage(String msg);
    
}
