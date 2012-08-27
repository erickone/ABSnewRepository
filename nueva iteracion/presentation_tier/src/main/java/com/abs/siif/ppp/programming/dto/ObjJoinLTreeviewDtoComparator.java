/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ObjJoinLTreeviewDtoComparator
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.dto;

import java.util.Comparator;

/**
 *
 * @author Israel Ruiz
 */
public class ObjJoinLTreeviewDtoComparator implements Comparator<ObjectiveJoinLevelTreeviewDto>{
      public static final ObjJoinLTreeviewDtoComparator instanceObjCom;
    static{
         instanceObjCom = new ObjJoinLTreeviewDtoComparator();
    }
    
    @Override
    public int compare(ObjectiveJoinLevelTreeviewDto o1, 
            ObjectiveJoinLevelTreeviewDto o2) {
        
        return o1.getFinallyText().compareTo(o2.getFinallyText());
        
    }
    
    public static ObjJoinLTreeviewDtoComparator getInstance(){
        return instanceObjCom;
    }
    
    private ObjJoinLTreeviewDtoComparator(){
        
    }

}
