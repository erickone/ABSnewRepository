package com.abs.siif.ppp.programming.controller;

/**
 *
 * @author jacob.flores
 */
public class DraftProjectBinnacle
{
  private String theirDraftProjectNumber;
  private String theirInitialStatus;
  private String theirBinnacleDate;
  private String theirUserName;
  private int theirEstimatedDays;
  private int theirRealDays;

  public DraftProjectBinnacle(String aDraftProjectNumber, String anInitialStatus,
                              String aBinnacleDate, String anUserName, int anEstimatedDays,
                              int aRealDays)
  {
    this.theirDraftProjectNumber = aDraftProjectNumber;
    this.theirInitialStatus = anInitialStatus;
    this.theirBinnacleDate = aBinnacleDate;
    this.theirUserName = anUserName;
    this.theirEstimatedDays = anEstimatedDays;
    this.theirRealDays = aRealDays;
  }
  /**
   * @return the theirDraftProjectNumber
   */
  public String getTheirDraftProjectNumber() {
    return theirDraftProjectNumber;
  }

  /**
   * @param theirDraftProjectNumber the theirDraftProjectNumber to set
   */
  public void setTheirDraftProjectNumber(String theirDraftProjectNumber) {
    this.theirDraftProjectNumber = theirDraftProjectNumber;
  }

  /**
   * @return the theirInitialStatus
   */
  public String getTheirInitialStatus() {
    return theirInitialStatus;
  }

  /**
   * @param theirInitialStatus the theirInitialStatus to set
   */
  public void setTheirInitialStatus(String theirInitialStatus) {
    this.theirInitialStatus = theirInitialStatus;
  }

  /**
   * @return the theirBinnacleDate
   */
  public String getTheirBinnacleDate() {
    return theirBinnacleDate;
  }

  /**
   * @param theirBinnacleDate the theirBinnacleDate to set
   */
  public void setTheirBinnacleDate(String theirBinnacleDate) {
    this.theirBinnacleDate = theirBinnacleDate;
  }

  /**
   * @return the theirUserName
   */
  public String getTheirUserName() {
    return theirUserName;
  }

  /**
   * @param theirUserName the theirUserName to set
   */
  public void setTheirUserName(String theirUserName) {
    this.theirUserName = theirUserName;
  }

  /**
   * @return the theirEstimatedDays
   */
  public int getTheirEstimatedDays() {
    return theirEstimatedDays;
  }

  /**
   * @param theirEstimatedDays the theirEstimatedDays to set
   */
  public void setTheirEstimatedDays(int theirEstimatedDays) {
    this.theirEstimatedDays = theirEstimatedDays;
  }

  /**
   * @return the theirRealDays
   */
  public int getTheirRealDays() {
    return theirRealDays;
  }

  /**
   * @param theirRealDays the theirRealDays to set
   */
  public void setTheirRealDays(int theirRealDays) {
    this.theirRealDays = theirRealDays;
  }

}
