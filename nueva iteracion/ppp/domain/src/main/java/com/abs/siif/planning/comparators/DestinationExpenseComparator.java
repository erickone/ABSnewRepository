/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.comparators;

import com.abs.siif.budget.entities.DestinationEntity;
import java.util.Comparator;

/**
 *
 * @author jacob.flores
 */
public class DestinationExpenseComparator implements Comparator<DestinationEntity>
{
   public static final DestinationExpenseComparator instanceObjCom;

  static
  {
    instanceObjCom = new DestinationExpenseComparator();
  }

  @Override
  public int compare(DestinationEntity o1, DestinationEntity o2)
  {
    return o1.getDestinationKey().compareTo(o2.getDestinationKey());
  }
  
  public static DestinationExpenseComparator getInstance()
  {
    return instanceObjCom;
  }

  private DestinationExpenseComparator()
  {
  }
}
