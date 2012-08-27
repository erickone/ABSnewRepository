/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.entities.DraftProjectEntity;

/**
 * Este ese  el observer es quien recibira las notificaciones de los cambios que
 * se realizen ene el anteproyecto
 * @author Erick Leija
 */
public interface DraftProjectObserver
{
    /**
     * este metodo es el que se encargara de realizar las acciones pertinentes
     * cuando reciba una notificacion del cambio en el anteproyecto
     * @param aDraftProjectEntity 
     */
    public void update(DraftProjectEntity aDraftProjectEntity);
}
