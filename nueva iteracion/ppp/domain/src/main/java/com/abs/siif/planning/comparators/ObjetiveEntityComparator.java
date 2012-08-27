/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.comparators;

import com.abs.siif.planning.entities.ObjectiveEntity;
import java.util.Comparator;

/**
 *
 * @author absvalenzuela
 */
public class ObjetiveEntityComparator implements Comparator<ObjectiveEntity> {

    public static final ObjetiveEntityComparator instanceObjCom;
    static{
         instanceObjCom = new ObjetiveEntityComparator();
    }
    
    @Override
    public int compare(ObjectiveEntity o1, ObjectiveEntity o2) {
        
        return Double.compare(Double.parseDouble(o1.getObjectiveKey()) , Double.parseDouble(o2.getObjectiveKey()));
        
    }
    
    public static ObjetiveEntityComparator getInstance(){
        return instanceObjCom;
    }
    
    private ObjetiveEntityComparator(){
        
    }
}
