package com.abs.siif.ppp.programming.controller;

import com.abs.siif.ppp.programming.api.controller.DraftProjectBinnacleControllerApi;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.ppp.programming.api.controller.DraftProjectHeaderControllerApi;
import com.abs.siif.programming.entities.DraftProjectBinnacleEntity;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.management.DraftProjectBinnacleManagement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jacob.flores
 */
@Controller("DraftProjectBinnacleController")
@Scope("session")
public class DraftProjectBinnacleController extends SIIFControllerBase 
        implements Serializable, DraftProjectBinnacleControllerApi
{
  @Resource(name="draftProjectHeaderController")
  private transient DraftProjectHeaderControllerApi itsDraftProjectHeaderController;

  @Resource(name="draftProjectBinnacleManagement")
  private transient DraftProjectBinnacleManagement itsDraftProjectBinnacleManagement;
  private DraftProjectEntity theirDraftProjectEntity;
  private String TheirDraftProjectNumber;

  //Variables de uso temporal
  private Collection<DraftProjectBinnacleEntity> theirBinnacleCollection = new ArrayList<DraftProjectBinnacleEntity>();

  public DraftProjectBinnacleController(){};


    @Override
  public void initBinnacle()
  {
     this.theirDraftProjectEntity = new DraftProjectEntity();


     if (itsDraftProjectHeaderController != null)
    {
      this.theirDraftProjectEntity.setDraftProjectId(itsDraftProjectHeaderController.getTheirCurrentDraftProjectId());

    this.theirBinnacleCollection = itsDraftProjectBinnacleManagement.getDraftProjectBinnaclebyDraftProjectId(this.theirDraftProjectEntity.getDraftProjectId());
    this.theirDraftProjectEntity.setDraftProjectKey(itsDraftProjectHeaderController.getDraftNumber());
    }

  }

  /**
   * @return the theirBinnacleCollection
   */
    @Override
  public Collection<DraftProjectBinnacleEntity> getTheirBinnacleCollection()
  {
    return theirBinnacleCollection;
  }

  /**
   * @param theirBinnacleCollection the theirBinnacleCollection to set
   */
    @Override
  public void setTheirBinnacleCollection(Collection<DraftProjectBinnacleEntity> theirBinnacleCollection)
  {
    this.theirBinnacleCollection = theirBinnacleCollection;
  }

  /**
   * @return the theirDraftProjectEntity
   */
    @Override
  public DraftProjectEntity getTheirDraftProjectEntity() {
    return theirDraftProjectEntity;
  }

  /**
   * @param theirDraftProjectEntity the theirDraftProjectEntity to set
   */
    @Override
  public void setTheirDraftProjectEntity(DraftProjectEntity theirDraftProjectEntity) {
    this.theirDraftProjectEntity = theirDraftProjectEntity;
  }

  /**
   * @return the TheirDraftProjectNumber
   */
    @Override
  public String getDraftProjectNumber() {
    return TheirDraftProjectNumber;
  }

  /**
   * @param TheirDraftProjectNumber the TheirDraftProjectNumber to set
   */
    @Override
  public void setDraftProjectNumber(String TheirDraftProjectNumber) {
    this.TheirDraftProjectNumber = TheirDraftProjectNumber;
  }
}