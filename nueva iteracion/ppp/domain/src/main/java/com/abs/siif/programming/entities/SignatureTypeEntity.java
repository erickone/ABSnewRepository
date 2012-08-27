/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;

/**
 *
 * @author jacob.flores
 */
@Entity
@Table(name = "siifabstipofirma")
@FilterDefs(
{
  @FilterDef(name = "SignatureTypeFilter", parameters =
  @ParamDef(name = "SignatureTypeParam", type = "string"))
})
@Filters(
{
  @Filter(name = "SignatureTypeFilter", condition = "tipo = :SignatureTypeParam")
})
public class SignatureTypeEntity
        implements Comparable<SignatureTypeEntity>, Serializable
{

  @Id
  @GenericGenerator(name = "generator", strategy = "native")
  @GeneratedValue(generator = "generator")
  @Column(name = "idtipofirma", nullable=false)
  private Long signatureTypeId;
  @Column(name = "tipo")
  private String signatureTypeType;
  @Column(name = "descripcion")
  private String signatureTypeDescription;


  /*
   * @OneToMany(mappedBy = "signatureType")
   * @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE}) private
   * Set<SignaturesEntity> Signature;
   */
  @Override
  public int compareTo(SignatureTypeEntity o)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * @return the signatureTypeId
   */
  public Long getSignatureTypeId()
  {
    return signatureTypeId;
  }

  /**
   * @param signatureTypeId the signatureTypeId to set
   */
  public void setSignatureTypeId(Long signatureTypeId)
  {
    this.signatureTypeId = signatureTypeId;
  }

  /**
   * @return the signatureTypeType
   */
  public String getSignatureTypeType()
  {
    return signatureTypeType != null ? signatureTypeType.trim():signatureTypeType;
  }

  /**
   * @param signatureTypeType the signatureTypeType to set
   */
  public void setSignatureTypeType(String signatureTypeType)
  {
    this.signatureTypeType = signatureTypeType;
  }

  /**
   * @return the signatureTypeDescription
   */
  public String getSignatureTypeDescription()
  {
    return signatureTypeDescription != null ? signatureTypeDescription.trim():signatureTypeDescription;
  }

  /**
   * @param signatureTypeDescription the signatureTypeDescription to set
   */
  public void setSignatureTypeDescription(String signatureTypeDescription)
  {
    this.signatureTypeDescription = signatureTypeDescription;
  }
}