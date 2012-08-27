/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DestinationKeyComparator
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

import com.abs.siif.budget.entities.DestinationEntity;
import java.util.Comparator;

/**
 *
 * @author Erick Leija
 */
public class DestinationKeyComparator implements Comparator<DestinationEntity>
{
     public static final DestinationKeyComparator instanceObjCom;

  static
  {
    instanceObjCom = new DestinationKeyComparator();
  }

  @Override
  public int compare(DestinationEntity o1, DestinationEntity o2)
  {
    return o1.getDestinationKey().compareTo(o2.getDestinationKey()); 
  }
  
  public static DestinationKeyComparator getInstance()
  {
    return instanceObjCom;
  }

  private DestinationKeyComparator()
  {
  }
}
