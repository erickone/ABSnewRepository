/*
 *  Copyright (C) 2012 Advance Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  DepencenceDto
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advance Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advance 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.dto;

import java.io.Serializable;

/**
 *
 * @author Israel Ruiz
 */
public class DepencenceDto  implements Serializable
{

  private String clave;
  private String nameDepend;
  private Long idDependency;
  

  public DepencenceDto()
  {
  }

  
  public String getClave()
  {
    return clave;
  }

  public void setClave(String clave)
  {
    this.clave = clave;
  }

  public Long getIdDependency()
  {
    return idDependency;
  }

  public void setIdDependency(Long idDependency)
  {
    this.idDependency = idDependency;
  }

  public void setIddependency(Integer idDependency)
  {
    this.idDependency = idDependency.longValue();
  }

  public String getNameDepend()
  {
    return nameDepend;
  }

  public void setNameDepend(String nameDepend)
  {
    this.nameDepend = nameDepend;
  }

  public void setNamedepend(String nameDepend)
  {
    this.nameDepend = nameDepend;
  }

  /**
   * @return the composedDependenceName
   */
  public String getComposedDependenceName()
  {
    return(this.getClave() + " " + this.getNameDepend());
    
  }

}
