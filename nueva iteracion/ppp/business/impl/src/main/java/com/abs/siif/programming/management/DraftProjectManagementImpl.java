/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.planning.dao.DependenceDao;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.ObjectiveEntity;
import com.abs.siif.programming.dao.*;
import com.abs.siif.programming.dto.DraftProjectSearchDto;
import com.abs.siif.programming.dto.DraftProjectStatusEnum;
import com.abs.siif.programming.entities.*;
import com.abs.siif.security.entities.UserEntity;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Este management se va a encargar del manejo de las operaciones con la entidadad
 * DraftProject.
 *
 * @author Erick Leija
 */
@Service("draftProjectManagement")
public class DraftProjectManagementImpl implements DraftProjectManagement
{

  @Resource(name = "draftProjectStateDaoImpl")
  private DraftProjectStateDao theirDraftProjectStateDao;
  @Resource(name = "draftProjectStatusDaoImpl")
  private DraftProjectStatusDao theirDraftProjectStatusDao;
  @Resource(name = "draftProjectTypeDaoImpl")
  private DraftProjectTypeDao theirDraftProjectTypeDao;
  @Resource(name = "draftProjectDaoImpl")
  private DraftProjectDao theirDraftProjectDao;
  @Resource(name = "draftProjectBinnacleDaoImpl")
  private DraftProjectBinnacleDao theirDraftProjectBinnacleDao;
  @Resource(name = "programmingDaoImpl")
  private ProgrammingDao programmingDaoImpl;
  @Resource(name = "DependenceDao")
  private DependenceDao dependenceDao;
  @Resource(name = "ObjectiveProgrammingDao")
  private ObjectiveProgrammingDao objectiveProgrammingDao;
  @Resource(name = "programminglevelDaoImpl")
  private ProgrammingLevelDao programmingLevelDao;
  @Resource(name = "deliveryDaoImpl")
  private DeliveryDao deliveryDao;
  @Resource(name = "draftProjectBinnacleManagement")
  private DraftProjectBinnacleManagement itsDraftProjectBinnacleManagement;
  @Resource(name = "vulnerableGroupManagement")
  VulnerableGroupManagement vulnerableGroupManagement;

  @Override
  public Map<String, List<?>> getSupportLists()
  {
    Collection<DraftProjectTypeEntity> myTypes = theirDraftProjectTypeDao.getAllDraftProjectTypes();
    Collection<DraftProjectStateEntity> myStatus = theirDraftProjectStateDao.getAllDraftProjectState();
    Collection<VulnerableGroupEntity> refVulnCatalog = vulnerableGroupManagement.getVulnerableGroups();
    Map<String, List<?>> mySupportList = new HashMap<String, List<?>>();

    mySupportList.put("DraftProjectTypes", new ArrayList<DraftProjectTypeEntity>(myTypes));
    mySupportList.put("DraftProjectState", new ArrayList<DraftProjectStateEntity>(myStatus));
    mySupportList.put("VulnerableGroupEntity", new ArrayList<VulnerableGroupEntity>(refVulnCatalog));
    return mySupportList;
  }

  @Transactional(readOnly = true)
  @Override
  public DraftProjectEntity getDraftProjectById(Long anIdentity)
  {
    return theirDraftProjectDao.getDraftProjectById(anIdentity);

  }

  @Transactional(readOnly = false)
  @Override
  public DraftProjectEntity saveDraftProject(DraftProjectEntity aDraftProject, Long anObjectiveId)
  {
    if (aDraftProject.getDraftProjectId() != null)
    {
      DependenceEntity temp = aDraftProject.getDraftProjectDependency();
      temp = dependenceDao.findById(temp.getDependenceId(), true);
      aDraftProject.setDraftProjectDependency(temp);
      ProgrammingEntity myProgramming = buildProgramming(aDraftProject, anObjectiveId);
      myProgramming.setProgrammingDependency(temp);
      aDraftProject.setDraftProjectProgramming(myProgramming);
      theirDraftProjectDao.merge(aDraftProject);
    }
    else
    {

      ProgrammingEntity myProgramming = buildProgramming(aDraftProject, anObjectiveId);
      aDraftProject.setDraftProjectProgramming(myProgramming);
      theirDraftProjectDao.save(aDraftProject);
      saveDelivery(aDraftProject);

      if (validateDraftProjectIsNotInitialState(aDraftProject))
      {
        saveDraftProjectInBinnacle(aDraftProject);
      }
    }

    return aDraftProject;
  }

  @Transactional(readOnly = false)
  private void saveDraftProjectInBinnacle(DraftProjectEntity aDraftProjectEntity)
  {
    final int CONSECUTIVO_INICIAL_BITACORA = 1;
    final int DIAS_REALES_EN_ESTADO_INICIAL_BITACORA = 0;

    Date myDate = new Date();
    long myDateInMilliseconds = myDate.getTime();
    Timestamp myTimestamp = new Timestamp(myDateInMilliseconds);

    UserEntity myUser = (UserEntity) SIIFContextBase.getParameterSession(SessionKeyEnum.USER);

    DraftProjectBinnacleEntity myBinnacle = new DraftProjectBinnacleEntity();
    myBinnacle.setDraftProject(aDraftProjectEntity);
    myBinnacle.setDraftProjectBinnacleConsecutive(CONSECUTIVO_INICIAL_BITACORA);
    myBinnacle.setDraftProjectBinnacleDate(myTimestamp);
    myBinnacle.setDraftProjectBinnacleRealDay(DIAS_REALES_EN_ESTADO_INICIAL_BITACORA);
    myBinnacle.setDraftProjectStatus(aDraftProjectEntity.getDraftProjectStatus());
    myBinnacle.setUserBinnacle(myUser);

    itsDraftProjectBinnacleManagement.saveDraftProjectBinnacle(myBinnacle);

  }

  /**
   * Este método regresa TRUE si no existe el estatus iniciado en la bitácora. Si ya
   * existe un estatus INICIADO para el anteproyecto, no se registra. Esto es para evitar
   * que se registre más de una vez el mismo estatus.
   *
   * @param aDraftProjectEntity
   * @return
   */
  private boolean validateDraftProjectIsNotInitialState(DraftProjectEntity aDraftProjectEntity)
  {
    boolean myResult = Boolean.FALSE;
    DraftProjectStatusEntity myDraftProjectStatusEntity = new DraftProjectStatusEntity();
    myDraftProjectStatusEntity = getStatusEntityByConsecutive(DraftProjectStatusEnum.INICIADO.ordinal());
    aDraftProjectEntity.setDraftProjectStatus(myDraftProjectStatusEntity);
    if (getDraftProjectInDeterminateStatus(aDraftProjectEntity) == 0)
    {
      myResult = Boolean.TRUE;
    }
    else
    {
      myResult = Boolean.FALSE;
    }
    return myResult;
  }

  @Transactional(readOnly = false)
  private ProgrammingEntity buildProgramming(DraftProjectEntity aDraftProject, Long anObjectiveId)
  {
    DependenceEntity depenEntity =
            aDraftProject.getDraftProjectDependency();
    ProgrammingEntity progEntity;
    progEntity = aDraftProject.getDraftProjectProgramming();
    if (progEntity == null)
    {
      progEntity = new ProgrammingEntity();
    }


    // TODO : Este Id es el de la entity ProgrammingLevelEntity, el registro
    // que esta marcado como AnteProyecto, para fines prácticos se pone en código
    // duro y queda pendiente la obtención del registro a través de la bandera
    ProgrammingLevelEntity entityOfDraftProjLevel = programmingLevelDao.getProgrammingLevelPreFichaForId();
    ProgrammingLevelEntity entLevel = new ProgrammingLevelEntity();

    entLevel.setProgrammingLevelId(entityOfDraftProjLevel.getProgrammingLevelId());
    entLevel = programmingLevelDao.findById(
            entLevel.getProgrammingLevelId(), true);
    progEntity.setProgrammingLevel(entLevel);

    // TODO : Este Id es el IdObjetivo que va ligado a la programación, 
    // para fines prácticos se pone en código duro y queda pendiente pues se debe de 
    // pasar u obtener de la UI de la Estructura de la Programación, 
    // la obtención del registro a través de la bandera
    ObjectiveEntity objL = new ObjectiveEntity();
    objL.setObjectiveId(anObjectiveId);
    objL = objectiveProgrammingDao.findById(objL.getObjectiveId(), true);
    progEntity.setProgrammingObjective(objL);


    depenEntity = dependenceDao.findById(
            depenEntity.getDependenceId(), true);
    progEntity.setProgrammingDependency(depenEntity);
    progEntity.setProgrammingDescription(
            aDraftProject.getDraftProjectShortName());
    return progEntity;
  }

  @Transactional(readOnly = false)
  private void saveDelivery(DraftProjectEntity aDraftProject)
  {
    DeliveryEntity delivery = new DeliveryEntity();
    delivery.setProposit("dummy");
    aDraftProject = theirDraftProjectDao.findById(
            aDraftProject.getDraftProjectId(), true);
    delivery.setDraftProject(aDraftProject);
    deliveryDao.save(delivery);
  }

  /**
   * @return the dependenceDao
   */
  public DependenceDao getDependenceDao()
  {
    return dependenceDao;
  }

  /**
   * @param dependenceDao the dependenceDao to set
   */
  public void setDependenceDao(DependenceDao dependenceDao)
  {
    this.dependenceDao = dependenceDao;
  }

  @Override
  public String getTotalProjects(Long anDependenceId)
  {
    DependenceEntity myDep = dependenceDao.getDependenceById(anDependenceId);
    long mySize = theirDraftProjectDao.getTotalProjects(myDep.getFather().getDependenceId());
    DecimalFormat myFormat = new DecimalFormat("000");
    return myFormat.format(mySize);
  }

  @Override
  public DraftProjectStatusEntity getStatusEntityByConsecutive(int aConsecutive)
  {
    return this.theirDraftProjectStatusDao.getStatusByConsecutive(aConsecutive);
  }

  /**
   * @return the theirDraftProjectStatusDao
   */
  public DraftProjectStatusDao getTheirDraftProjectStatusDao()
  {
    return theirDraftProjectStatusDao;
  }

  /**
   * @param theirDraftProjectStatusDao the theirDraftProjectStatusDao to set
   */
  public void setTheirDraftProjectStatusDao(DraftProjectStatusDao theirDraftProjectStatusDao)
  {
    this.theirDraftProjectStatusDao = theirDraftProjectStatusDao;
  }

  @Override
  public int getDraftProjectInDeterminateStatus(DraftProjectEntity aDraftProjectEntity)
  {
    return this.theirDraftProjectBinnacleDao.getDraftProjectInDeterminateStatus(aDraftProjectEntity);
  }

  /**
   * @return the theirDraftProjectBinnacleDao
   */
  public DraftProjectBinnacleDao getTheirDraftProjectBinnacleDao()
  {
    return theirDraftProjectBinnacleDao;
  }

  /**
   * @param theirDraftProjectBinnacleDao the theirDraftProjectBinnacleDao to set
   */
  public void setTheirDraftProjectBinnacleDao(DraftProjectBinnacleDao theirDraftProjectBinnacleDao)
  {
    this.theirDraftProjectBinnacleDao = theirDraftProjectBinnacleDao;
  }

  @Transactional(readOnly = true)
  @Override
  public DraftProjectEntity getDraftProjectByIdEager(Long anIdentity)
  {
    return this.theirDraftProjectDao.getDraftProjectEager(anIdentity);
  }

  @Override
  public Collection<DraftProjectEntity> getDraftProInWorkFlow(DraftProjectSearchDto aDraftProjectSearchDto)
  {
    return theirDraftProjectDao.getDraftProjectsInWorkFlow(aDraftProjectSearchDto);
  }

  @Override
  public boolean deleteDraftProject(Long aDraftProjectID)
  {
    DraftProjectEntity myDraft = theirDraftProjectDao.getDraftProjectById(aDraftProjectID);
    return theirDraftProjectDao.deleteDraftProject(myDraft);
  }

    @Override
    public DraftProjectEntity getDraftProjectByIdWithBudgets(Long anIdentity) {
       return theirDraftProjectDao.getDraftProjectByIdWithBudgets(anIdentity);
    }
}
