/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.comparators;

import com.abs.siif.budget.entities.ObjectExpenseEntity;
import java.util.Comparator;

/**
 *
 * @author jacob.flores
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
    return Integer.toString(o1.getObjectExpenseKey()).compareTo(Integer.toString(o1.getObjectExpenseKey()));
  }
  
  public static ObjectExpenseKeyComparator getInstance()
  {
    return instanceObjCom;
  }

  private ObjectExpenseKeyComparator()
  {
  }
}
