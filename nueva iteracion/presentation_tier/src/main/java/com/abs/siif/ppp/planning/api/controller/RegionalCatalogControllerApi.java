/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.planning.api.controller;

import org.primefaces.event.NodeExpandEvent;

/**
 *
 * @author jacob.flores
 */
public interface RegionalCatalogControllerApi
{
 public void prepareToEdit();
 
  public void initRegionalCatalog() throws NoSuchFieldException, IllegalAccessException;
  
  public void onNodeExpand(NodeExpandEvent aNodeExpandEvent);
  
  public void saveOrUpdateRegionalCatalog();
  
  public void prepareNewItem();
  
   public String returnTomainMenu();
          
}