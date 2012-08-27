/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.ppp.programming.dto.ObjectiveJoinLevelTreeviewDto;
import java.util.Comparator;

/**
 * Este Comparador sirve para comparar un nodo padre con los elementos de la
 * Lista y nos regresa todos los nodos hijos de la lista
 * @author Erick Leija
 */
public class ComparatorFatherAndChild implements Comparator
{

    @Override
    public int compare(Object child, Object father)
    {
        if (child instanceof ObjectiveJoinLevelTreeviewDto && 
                father instanceof ObjectiveJoinLevelTreeviewDto)
        {
            ObjectiveJoinLevelTreeviewDto ref1 = (ObjectiveJoinLevelTreeviewDto) child;
            ObjectiveJoinLevelTreeviewDto ref2 = (ObjectiveJoinLevelTreeviewDto) father;
            if (ref1.getItsFatherId()!=null)
            {
            return ref1.getItsFatherId().
                    compareTo(ref2.getItsObjectiveId());
            }
            
            
        }
        return -1;
    }
    
    
}
