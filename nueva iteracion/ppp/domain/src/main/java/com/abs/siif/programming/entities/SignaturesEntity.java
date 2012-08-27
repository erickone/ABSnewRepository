/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;

/**
 *
 * @author jacob.flores
 */
@Entity
@Table(name = "siifpppfirmaanteproy")
@FilterDefs(
{
  @FilterDef(name = "SignatureDraftProjectIdFilter", parameters =
  @ParamDef(name = "SignatureDraftProjectIdParam", type = "long"))
})
@Filters(
{
  @Filter(name = "SignatureDraftProjectIdFilter", condition = "IdAnteProyecto = :SignatureDraftProjectIdParam")
})
public class SignaturesEntity
        implements Comparable<SignaturesEntity>, Serializable
{

  @Id
  @GenericGenerator(name = "generator", strategy = "native")
  @GeneratedValue(generator = "generator")
  @Column(name = "idfirmaanteproy", nullable=false)
  private Long signatureId;
  @Column(name = "firma", length = 155, nullable = false)
  private String signature;
  @ManyToOne
  @JoinColumn(name = "idtipofirma", nullable = false)
  private SignatureTypeEntity signatureType;
  @Column(name = "fechaimpresion")
  @Temporal(javax.persistence.TemporalType.TIMESTAMP)
  private Date impresionDate;
  @Column(name = "impreso")
  private boolean isImpress;
  @ManyToOne
  @JoinColumn(name = "IdAnteProyecto", nullable = false)
  private DraftProjectEntity draftProject;

  @Override
  public int compareTo(SignaturesEntity o)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * @return the signatureId
   */
  public Long getSignatureId()
  {
    return signatureId;
  }

  /**
   * @param signatureId the signatureId to set
   */
  public void setSignatureId(Long signatureId)
  {
    this.signatureId = signatureId;
  }

  /**
   * @return the signature
   */
  public String getSignature()
  {
    return signature != null ? signature.trim():signature;
  }

  /**
   * @param signature the signature to set
   */
  public void setSignature(String signature)
  {
    this.signature = signature;
  }

  /**
   * @return the draftProject
   */
  public DraftProjectEntity getDraftProject()
  {
    return draftProject;
  }

  /**
   * @param draftProject the draftProject to set
   */
  public void setDraftProject(DraftProjectEntity draftProject)
  {
    this.draftProject = draftProject;
  }

  /**
   * @return the impresionDate
   */
  public Date getImpresionDate()
  {
    return impresionDate;
  }

  /**
   * @param impresionDate the impresionDate to set
   */
  public void setImpresionDate(Date impresionDate)
  {
    this.impresionDate = impresionDate;
  }

  /**
   * @return the isImpress
   */
  public boolean isIsImpress()
  {
    return isImpress;
  }

  /**
   * @param isImpress the isImpress to set
   */
  public void setIsImpress(boolean isImpress)
  {
    this.isImpress = isImpress;
  }

  /**
   * @return the signatureType
   */
  public SignatureTypeEntity getSignatureType()
  {
    return signatureType;
  }

  /**
   * @param signatureType the signatureType to set
   */
  public void setSignatureType(SignatureTypeEntity signatureType)
  {
    this.signatureType = signatureType;
  }
}