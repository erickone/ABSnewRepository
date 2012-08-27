/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  ObjectExpenseKeyComparator
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.budget.comparators;

import com.abs.siif.budget.entities.ObjectExpenseEntity;
import java.util.Comparator;

/**
 *
 * @author Erick Leija
 */
public class ObjectExpenseKeyComparator implements Comparator<ObjectExpenseEntity>
{
     public static final ObjectExpenseKeyComparator instanceObjCom;

  static
  {
    instanceObjCom = new ObjectExpenseKeyComparator();
  }

  @Override
  public int compare(ObjectExpenseEntity o1, ObjectExpenseEntity o2)
  {
      String s1 = String.valueOf(o1.getObjectExpenseKey());
      String s2 = String.valueOf(o2.getObjectExpenseKey());
    return s1.compareTo(s2); 
  }
  
  public static ObjectExpenseKeyComparator getInstance()
  {
    return instanceObjCom;
  }

  private ObjectExpenseKeyComparator()
  {
  }
}
