package com.abs.siif.ppp.programming.controller;

import com.abs.siif.programming.entities.ActivityEntity;
import com.abs.siif.programming.entities.ComponentEntity;
import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author Israel Ruiz
 */
public class DeliveryFile implements Serializable{
    private String idFicha;
    private Collection<Proposit> proposits;
    private String idDelivery;
    private Map<Proposit, List<ComponentEntity>> propositComp;
    private ComponentEntity currentComp;
    private List<ActivityEntity> currentActivities;

    public DeliveryFile(){
       propositComp = new HashMap<Proposit, List<ComponentEntity>>();

       proposits = new ArrayList<Proposit>();
       currentComp = new ComponentEntity();
    }
    /**
     * @return the idFicha
     */
    public String getIdFicha() {
        return idFicha;
    }

    /**
     * @param idFicha the idFicha to set
     */
    public void setIdFicha(String idFicha) {
        this.idFicha = idFicha;
    }

    /**
     * @return the proposits
     */
    public Collection<Proposit> getProposits() {
        return proposits;
    }

    /**
     * @param proposits the proposits to set
     */
    public void setProposits(Collection<Proposit> proposits) {
        this.proposits = proposits;
    }

    /**
     * @return the idDelivery
     */
    public String getIdDelivery() {
        return idDelivery;
    }

    /**
     * @param idDelivery the idDelivery to set
     */
    public void setIdDelivery(String idDelivery) {
        this.idDelivery = idDelivery;
    }

    /**
     * @return the propositComp
     */
    public Map<Proposit, List<ComponentEntity>> getPropositComp() {
        return orderList(propositComp);
    }
    
    /**
     *  Ordena los elementos de la lista que esta en el mapa
     * @param propositComp
     * @return lista del mapa ordenara
     */
    public Map<Proposit, List<ComponentEntity>> orderList(Map<Proposit, List<ComponentEntity>> propositComp){
        Iterator iterator = propositComp.entrySet().iterator();
        Map.Entry mapEntry;
        List<ComponentEntity> list;
        while(iterator.hasNext()){
           mapEntry= (Entry) iterator.next();
           list= (List<ComponentEntity>) mapEntry.getValue();
           Collections.sort(list);
           mapEntry.setValue(list);
        }
        return propositComp;
    }

    /**
     * @param propositComp the propositComp to set
     */
    public void setPropositComp(Map<Proposit, List<ComponentEntity>> propositComp) {        
        this.propositComp = orderList(propositComp);
    }



    /**
     * @return the currentComp
     */
    public ComponentEntity getCurrentComp() {
        return currentComp;
    }

    /**
     * @param currentComp the currentComp to set
     */
    public void setCurrentComp(ComponentEntity currentComp) {
        this.currentComp = currentComp;
    }

    /**
     * @return the currentActivities
     */
    public List<ActivityEntity> getCurrentActivities() {
        return currentActivities;
    }

    /**
     * @param currentActivities the currentActivities to set
     */
    public void setCurrentActivities(List<ActivityEntity> currentActivities) {
        this.currentActivities = currentActivities;
    }

}
