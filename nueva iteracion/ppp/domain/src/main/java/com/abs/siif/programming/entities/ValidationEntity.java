/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.entities;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author jacob.flores
 */
@Entity
@Table(name = "siifpppvalseplan")
public class ValidationEntity   
              implements Comparable<ValidationEntity>, Serializable
{
  @Id
  @GenericGenerator(name = "generator", strategy = "native")
  @GeneratedValue(generator = "generator")
  @Column(name = "IdValidacion", nullable = false)
  private Long ValidationId;


  //@ManyToOne(fetch= FetchType.LAZY)
  @Column(name = "idTipoChecklist", nullable=true)
  private Long validationChecklistTypeId;
  
  //@ManyToOne(fetch = FetchType.LAZY)
  @Column(name = "idPregunta", nullable=true)
  private Long validationQuestionId;
  
  @Column(name = "DescripcionPregunta", nullable = true)
  private String validationQuestionDescription;
  
  @Column(name = "RespuestaPregunta")
  private boolean validationAnswer;
  
  @Column(name = "Observaciones")
  private String validationObservations;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "IdComponente", nullable=false)
  private ComponentEntity Component;
  
  
  
  @Override
  public int compareTo(ValidationEntity o) 
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * @return the validationObservations
   */
  public String getValidationObservations() {
    return validationObservations != null ?validationObservations.trim():validationObservations;
  }

  /**
   * @param validationObservations the validationObservations to set
   */
  public void setValidationObservations(String validationObservations) {
    this.validationObservations = validationObservations;
  }

  /**
   * @return the ValidationId
   */
  public Long getValidationId() {
    return ValidationId;
  }

  /**
   * @param ValidationId the ValidationId to set
   */
  public void setValidationId(Long ValidationId) {
    this.ValidationId = ValidationId;
  }

  /**
   * @return the validationChecklistTypeId
   */
  public Long getValidationChecklistTypeId() {
    return validationChecklistTypeId;
  }

  /**
   * @param validationChecklistTypeId the validationChecklistTypeId to set
   */
  public void setValidationChecklistTypeId(Long validationChecklistTypeId) {
    this.validationChecklistTypeId = validationChecklistTypeId;
  }

  /**
   * @return the validationQuestionId
   */
  public Long getValidationQuestionId() {
    return validationQuestionId;
  }

  /**
   * @param validationQuestionId the validationQuestionId to set
   */
  public void setValidationQuestionId(Long validationQuestionId) {
    this.validationQuestionId = validationQuestionId;
  }

  /**
   * @return the validationQuestionDescription
   */
  public String getValidationQuestionDescription() {
    return validationQuestionDescription != null ? validationQuestionDescription.trim():validationQuestionDescription;
  }

  /**
   * @param validationQuestionDescription the validationQuestionDescription to set
   */
  public void setValidationQuestionDescription(String validationQuestionDescription) {
    this.validationQuestionDescription = validationQuestionDescription;
  }

  /**
   * @return the validationAnswer
   */
  public boolean isValidationAnswer() {
    return validationAnswer;
  }

  /**
   * @param validationAnswer the validationAnswer to set
   */
  public void setValidationAnswer(boolean validationAnswer) {
    this.validationAnswer = validationAnswer;
  }

  /**
   * @return the Component
   */
  public ComponentEntity getComponent() 
  {
    return Component;
  }

  /**
   * @param Component the Component to set
   */
  public void setComponent(ComponentEntity Component) 
  {
    this.Component = Component;
  }
  
  

  
}