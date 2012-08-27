/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.base.exception.BaseBusinessException;
import com.abs.siif.base.management.ManagementBase;
import com.abs.siif.planning.dao.DependenceDao;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.DependenceLevelEntity;
import com.abs.siif.programming.dao.DraftProjectDao;
import com.abs.siif.programming.dao.FederalUrRegulatoryDao;
import com.abs.siif.programming.dao.InvPreFileDao;
import com.abs.siif.programming.dao.PromoterDao;
import com.abs.siif.programming.dto.DependenciesInvPreFileDto;
import com.abs.siif.programming.dto.InvPreFileDto;
import com.abs.siif.programming.entities.*;
import com.abs.siif.support.FormatNumber;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.springframework.stereotype.Service;

/**
 *
 * @author Israel Ruiz
 */
@Service("invPreFileManagement")
public class InvPreFileManagementImpl extends ManagementBase
        implements InvPreFileManagement
{

    @Resource(name = "DependenceDao")
    DependenceDao dependenceDao;
    @Resource(name = "promoterDao")
    PromoterDao promoterDao;
    @Resource(name = "draftProjectDaoImpl")
    DraftProjectDao draftProjectDao;
    @Resource(name = "deliveryManagement")
    DeliveryManagement deliveryManagement;
    @Resource(name = "invPreFileDao")
    InvPreFileDao invPreFileDao;
    @Resource(name = "federalUrRegulatoryDao")
    FederalUrRegulatoryDao federalUrRegulatoryDao;

    /**
     * Obtiene los catalogos e información para crear una ficha de inversión
     * nueva
     *
     * @param draftProject
     * @return
     */
    @Override
    public DependenciesInvPreFileDto getDepenInvPreFile(
            DraftProjectEntity draftProject)
    {

        DependenciesInvPreFileDto dto = new DependenciesInvPreFileDto();
        if (draftProject != null)
        {
            draftProject = draftProjectDao.findById(
                    draftProject.getDraftProjectId(), true);
            DependenceEntity ueg = draftProject.getDraftProjectDependency();
            ueg = dependenceDao.findById(ueg.getDependenceId(), true);
            draftProject.setDraftProjectDependency(ueg);
            DependenceEntity father = dependenceDao.findById(
                    ueg.getFather().getDependenceId(), true);
            father = dependenceDao.findById(father.getDependenceId(), true);
            dto.setUrExecutingFicha(ueg);
            dto.setUrExecuting(father);
            dto.setUnitExeExpend(dependenceDao.getChilds(father));
            dto.setPromoters(promoterDao.findAll());
            dto.setUrNorm(federalUrRegulatoryDao.getAllFederalURRegulatories());
            dto.setDraftFileType(draftProject.getDraftFileType());
            List<DeliveryEntity> deliveries =
                    deliveryManagement.getDeliveries(draftProject);

            if (deliveries != null && deliveries.size() > 0)
            {
                dto.setComponents(
                        new ArrayList<ComponentEntity>(
                        deliveries.get(0).getComponents()));
            }


        } else
        {
            throw new BaseBusinessException("escenario.noSoportado");
        }

        return dto;
    }

    /**
     * Obtiene el detalle de los datos requeridos para editar la información de
     * la preficha de inversión así como los catalaogos de depencia.
     *
     * @param invPreFileEntity
     * @return
     */
    @Override
    public DependenciesInvPreFileDto getInvPreFileEdit(
            InvPreFileEntity invPreFileEntity)
    {
        InvPreFileEntity resultPreFile;
        DependenciesInvPreFileDto depenDTO;
        DraftProjectEntity draftProject =
                invPreFileEntity.getInvPreFileDraftProject();
        depenDTO = getDepenInvPreFile(draftProject);
        resultPreFile = invPreFileDao.findById(
                invPreFileEntity.getInvPreFileId(), true);
        depenDTO.setInvPreFileEntity(resultPreFile);
        return depenDTO;
    }

    /**
     * Guarda la Información de la Preficha ficha de Inversion
     *
     * @param invPreFileEntity
     * @return
     */
    @Override
    public InvPreFileEntity saveGeneralDataInvPreFile(
            InvPreFileEntity invPreFileEntity)
    {
        String prefix = "";
        InvPreFileEntity result = invPreFileEntity;
        // Obtiene unidad ejecutora
        DependenceEntity ueg =
                invPreFileEntity.getInvPreFileDraftProject().
                getDraftProjectDependency();

        //Obtiene la unidad presupuestadora
        DependenceEntity unitBudget = getUP(ueg);

        if (unitBudget == null)
        {
            throw new BaseBusinessException("prefichaPrioridad.sinUP");
        }
        //Obtiene un prefijo para verificar si la prioridad no existe
        prefix = "" + unitBudget.getDependenceId().hashCode();
        prefix = prefix + "_" + invPreFileEntity.getPriority();

            //En caso que la prioridad no esta duplicada genera el folio
            //El folio se genera considerando la UP y la UR
        
        if (invPreFileEntity.getFolio().isEmpty()){
            String folio = invPreFileDao.getNexNumberInvPreFile(invPreFileEntity);
            invPreFileEntity.setPriority(prefix);
            invPreFileEntity.setFolio(folio);
        } 
        result = invPreFileDao.saveInvPreFile(invPreFileEntity);
           
        return result;
    }  
    
    @Override
    public void savePriority(InvPreFileEntity invPreFileEntity){
        
        String prefix = "";
      
        // Obtiene unidad ejecutora
        DependenceEntity ueg =
                invPreFileEntity.getInvPreFileDraftProject().
                getDraftProjectDependency();

        //Obtiene la unidad presupuestadora
        DependenceEntity unitBudget = getUP(ueg);

        if (unitBudget == null)
        {
            throw new BaseBusinessException("prefichaPrioridad.sinUP");
        }
        //Obtiene un prefijo para verificar si la prioridad no existe
        prefix = "" + unitBudget.getDependenceId().hashCode();

            //En caso que la prioridad no esta duplicada genera el folio
            //El folio se genera considerando la UP y la UR
        
            if(!invPreFileDao.findPriorityEqual(invPreFileEntity, prefix)){
                 String folio = invPreFileDao.getNexNumberInvPreFile(invPreFileEntity);
                 prefix = prefix + "_" + invPreFileEntity.getPriority();
                 invPreFileEntity.setPriority(prefix);
                 invPreFileEntity.setFolio(folio);
                 invPreFileDao.saveInvPreFile(invPreFileEntity);
            }else{
               String message = this.getMessage("ppp.programming.invprefile.repeated");
               throw new BaseBusinessException(message);
            }
    }

    /**
     * Elimina los datos de la preficha de inversion
     *
     * @param invPreFileEntity
     */
    @Override
    public void deleteInvPreFile(InvPreFileEntity invPreFileEntity)
    {
        invPreFileDao.deleteInvPreFile(invPreFileEntity);        
    }

    /**
     * *
     * Obtniene la dependencia que esta marcada como UP
     *
     * @param ueg Unidad ejecutora del gasto del cual se requiere obtener la
     * Unidad Presupuestadora
     * @return Dependencia marcada como Unidad Presupuestadora
     */
    private DependenceEntity getUP(DependenceEntity ueg)
    {
        DependenceEntity upDepedency = null;
        DependenceEntity tmpDepedency = null;
        DependenceLevelEntity upDependencyLevel = null;
        boolean isBudgetUnit = false;
        List ancestors =
                dependenceDao.getHierarchicalDependencies(ueg.getDependenceId());
        //Buscar la UP que pertenece la UEG para determinar un prefijo.
        for (int i = 0; i < ancestors.size(); i++)
        {

            Object[] ids = (Object[]) ancestors.get(i);
            tmpDepedency = dependenceDao.findById(Long.parseLong(ids[0].toString()), true);
            upDependencyLevel = tmpDepedency.getDependenceLevel();
            isBudgetUnit = upDependencyLevel.isDependencyLevelIsBudgetUnit();
            if (isBudgetUnit)
            {
                upDepedency = tmpDepedency;
                break;
            }
        }
        /*
         * for (DependenceEntity dependecy : ancestors) { if
         * (dependecy.getDependenceLevel().isDependencyLevelIsBudgetUnit()) {
         * upDepedency = dependecy; break; } }
         */
        return upDepedency;
    }



    /**
     * Este método regresa una entity de InvPrefile, para la edición desde la pantalla
     * de búsqueda de Pre-Ficha
     * @param anInvPrefileId
     * @return 
     */
  @Override
  public InvPreFileEntity getInvPreFileById(Long anInvPrefileId)
  {
    return this.invPreFileDao.getInvPreFileEntityById(anInvPrefileId);
  }
  
  @Override
  public FederalURRegulatoryEntity getDefaultFederalURN() {
      return this.federalUrRegulatoryDao.getDefaultFederalURN();
  }
  
  /**
     * Este método pone en un DTO los elementos de las Pre-Fichas a buscar
     * @param InvPreFileList
     * @return 
     */
  @Override
    public List<InvPreFileDto> getFilteredInvPrefileDTO(InvPreFileEntity InvPreFileEntityWithParams)
    {
        List<InvPreFileDto> myList;
        List<InvPreFileDto> myListOfFoundInvPrefile = new ArrayList<InvPreFileDto>();
        myList = this.invPreFileDao.getFilteredInvPrefileDTO(InvPreFileEntityWithParams);

        for (InvPreFileDto myPreFileDto : myList)
        {
            double myInitial = 0.00;
            double myAditional = 0.00;
            double myInitialAdictional = 0.00;


            String myPriority[] = myPreFileDto.getInvprefilepriority().split("_");
            if (myPriority.length >= 2)
            {
                myPreFileDto.setInvprefilepriority(myPriority[1].toString());
            } else
            {
                myPreFileDto.setInvprefilepriority("");
            }
            try
            {
                if(!myPreFileDto.getInvprefileinitialasignation().equals("")){
                    myInitial = Double.parseDouble(myPreFileDto.getInvprefileinitialasignation());
                    
                }else{
                    myInitial = 0.00;
                }
                if(!myPreFileDto.getInvprefileaditionalasignation().equals("")){
                    myAditional = Double.parseDouble(myPreFileDto.getInvprefileaditionalasignation());
                }else{
                    myAditional = 0.00;
                }
                myAditional = Double.parseDouble(myPreFileDto.getInvprefileaditionalasignation());
                myInitialAdictional = myInitial + myAditional;
                String myStringSum = FormatNumber.formatNumber(String.valueOf(myInitialAdictional));
                myPreFileDto.setInvprefiletotal(String.valueOf(myStringSum));
                String myInitialFormatted = FormatNumber.formatNumber(myPreFileDto.getInvprefileinitialasignation());
                myPreFileDto.setInvprefileinitialasignation(myInitialFormatted);
                String myAditionalFormatted = FormatNumber.formatNumber(myPreFileDto.getInvprefileaditionalasignation());
                myPreFileDto.setInvprefileaditionalasignation(myAditionalFormatted);
                myListOfFoundInvPrefile.add(myPreFileDto);
            } catch (Exception e)
            {
                System.out.println(e + " = " + myPreFileDto.getInvprefileid());
            }

        }
        return myListOfFoundInvPrefile;
    }
  
}
