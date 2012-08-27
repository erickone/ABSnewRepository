/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

/**
 * Esta interfaz es el subject, que es el encargado de manejar a los observers
 * para enviar las notificaciones y agregar y remover los observadores
 * @author Erick Leija
 */
public interface DraftProjectSubject
{
    /**
     * Registra los observadores que recibiran las notificaciones de cambio de clave
     * que se generen en el anteproyecto
     * @param aDraftProjectObserver 
     */
    public void registerObserver(DraftProjectObserver aDraftProjectObserver);
    /**
     * Por su parte este metodo se encarga de eliminar de la lista a un observador
     * el cual, ya no recibira notificaciones de los cambios en el anteproyecto
     * @param aDraftProjectObserver 
     */
    public void removeObserver(DraftProjectObserver aDraftProjectObserver);
    /**
     * Este metodo sirve para notificar el cambio a los observadores para que
     * realicen las actividades pertinentes
     * @param aDraftProjectObserver 
     */
    public void notifyObservers();
}
