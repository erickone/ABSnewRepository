package com.abs.siif.ppp.programming.controller;

/**
 *
 * @author jacob.flores
 */
public class FederalDependenceEntityConcept
{
  private String theirFederalDependenceId;
  private String theirFederalDependenceDescription;

  public FederalDependenceEntityConcept(String aFederalDependenceId, String aFederalDependenceDescription)
  {
    this.theirFederalDependenceId = aFederalDependenceId;
    this.theirFederalDependenceDescription = aFederalDependenceDescription;
  }
  /**
   * @return the theirFederalDependenceId
   */
  public String getFederalDependenceId() {
    return theirFederalDependenceId;
  }

  /**
   * @param theirFederalDependenceId the theirFederalDependenceId to set
   */
  public void setFederalDependenceId(String theirFederalDependenceId) {
    this.theirFederalDependenceId = theirFederalDependenceId;
  }

  /**
   * @return the theirFederalDependenceDescription
   */
  public String getFederalDependenceDescription() {
    return theirFederalDependenceDescription;
  }

  /**
   * @param theirFederalDependenceDescription the theirFederalDependenceDescription to set
   */
  public void setFederalDependenceDescription(String theirFederalDependenceDescription) {
    this.theirFederalDependenceDescription = theirFederalDependenceDescription;
  }
}
