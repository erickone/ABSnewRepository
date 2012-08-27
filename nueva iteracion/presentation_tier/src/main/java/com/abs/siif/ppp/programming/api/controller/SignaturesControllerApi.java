/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.api.controller;

/**
 *
 * @author jacob.flores
 */
public interface SignaturesControllerApi
{

  public String getAuthorizedBy();

  public void setAuthorizedBy(String anAuthorizedBy);

  public String getFormulatedBy();

  public void setFormulatedBy(String aFormulatedBy);

  public String getRevisedBy();

  public void setRevisedBy(String aRevisedBy);

  public String getVerifiedAndValidatedBy();

  public void setVerifiedAndValidatedBy(String aVerifiedAndValidatedBy);

  public void initSignature();

  public void saveEditSignatures();

  public void printReport();

  public void loadSignantsByDraftProjectId(Long aDraftProjectId);

  public void setSignantsToGui();

  public void cleanFields();
  
  public void prepareToSave();
  
  public void validateEmptyFields();
}