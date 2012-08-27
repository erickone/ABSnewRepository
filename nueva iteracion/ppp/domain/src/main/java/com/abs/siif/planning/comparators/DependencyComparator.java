/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.comparators;

import com.abs.siif.planning.entities.DependenceEntity;
import java.util.Comparator;

/**
 *
 * @author jacob.flores
 */
public class DependencyComparator implements Comparator<DependenceEntity>
{

  public static final DependencyComparator instanceObjCom;

  static
  {
    instanceObjCom = new DependencyComparator();
  }

  @Override
  public int compare(DependenceEntity o1, DependenceEntity o2)
  {
    return o1.getDependenceKey().compareTo(o2.getDependenceKey());
  }
  
  public static DependencyComparator getInstance()
  {
    return instanceObjCom;
  }

  private DependencyComparator()
  {
  }
}
