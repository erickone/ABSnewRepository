/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.comparators;

import com.abs.siif.programming.entities.DraftProjectEntity;
import java.util.Comparator;

/**
 *
 * @author jacob.flores
 */
public class DraftProjectShortNameComparator implements Comparator<DraftProjectEntity>
{

  public static final DraftProjectShortNameComparator instanceObjCom;

  static
  {
    instanceObjCom = new DraftProjectShortNameComparator();
  }

  @Override
  public int compare(DraftProjectEntity o1, DraftProjectEntity o2)
  {
   return o1.getDraftProjectShortName().compareTo(o2.getDraftProjectShortName());
  }
  
  public static DraftProjectShortNameComparator getInstance()
  {
    return instanceObjCom;
  }

  private DraftProjectShortNameComparator()
  {
  }
}
