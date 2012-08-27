/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.comparators;

import com.abs.siif.planning.dto.DepencenceDto;
import java.util.Comparator;

/**
 *
 * @author Erick Leija
 */
public class DependenceDtoKeyComparator implements Comparator<DepencenceDto> 
{
    
     private static final DependenceDtoKeyComparator instanceComp;
 static{
     instanceComp = new DependenceDtoKeyComparator();
 }
    @Override
    public int compare(DepencenceDto o1, DepencenceDto o2) 
    {
        return o1.getClave().compareTo(o2.getClave());
    }
    
    public static DependenceDtoKeyComparator getInstance(){
        return instanceComp;
        
    }
   
    private DependenceDtoKeyComparator(){}
    
}
