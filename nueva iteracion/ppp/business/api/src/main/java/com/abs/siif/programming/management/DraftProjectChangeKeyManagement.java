/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.entities.DraftProjectEntity;

/**
 *esta es la interfaz que se encarga de realizar los cambios de la clave del
 * anteproyecto y tranasmitirlos a los diferentes observers
 * @author Erick Leija
 */
public interface DraftProjectChangeKeyManagement
{
    
 public void draftProjectKeyChanged();
 public void setNewDraftProjectKey(DraftProjectEntity aDraftProjectToChange);
 
}
