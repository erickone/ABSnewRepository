/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.comparators;

import com.abs.siif.programming.entities.ProgrammingEntity;
import java.util.Comparator;

/**
 *
 * @author jacob.flores
 */
public class ProgrammingKeyComparator implements Comparator<ProgrammingEntity>
{

  public static final ProgrammingKeyComparator instanceObjCom;

  static
  {
    instanceObjCom = new ProgrammingKeyComparator();
  }

  @Override
  public int compare(ProgrammingEntity o1, ProgrammingEntity o2)
  {
    return o1.getProgrammingKey().compareTo(o2.getProgrammingKey());
  }

  public static ProgrammingKeyComparator getInstance()
  {
    return instanceObjCom;
  }

  private ProgrammingKeyComparator()
  {
  }
}