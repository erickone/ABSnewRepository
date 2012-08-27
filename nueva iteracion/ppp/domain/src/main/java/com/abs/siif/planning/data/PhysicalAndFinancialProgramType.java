/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.data;

/**
 *
 * @author jacob.flores
 */
public enum PhysicalAndFinancialProgramType
{
  NEW("name.new"),
  INPROCESS("name.inprocess");
  private String name;
  private PhysicalAndFinancialProgramType(String arg)
  {
    this.name = arg;
  }

  /**
   * @return the name
   */
  public String getName() 
  {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) 
  {
    this.name = name;
  }
}
