/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.common.dao.EmployeeDao;
import com.abs.siif.common.entities.ColectiveEntity;
import com.abs.siif.common.entities.EmployeeEntity;
import com.abs.siif.planning.dao.DependenceDao;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.programming.dao.SignaturesDao;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.SignaturesEntity;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author jacob.flores
 */
@Service("signaturesManagement")
public class SignatureManagementImpl implements SignaturesManagement
{

  @Resource(name = "signaturesDao")
  SignaturesDao signaturesDao;
  @Resource(name = "DependenceDao")
  DependenceDao itsDependenceDao;
  @Resource(name = "employeeDao")
  EmployeeDao itsEmployee;

  @Override
  public List<SignaturesEntity> getSignaturesByDraftProjectId(DraftProjectEntity aDraftPtojectId)
  {
    return signaturesDao.getSignaturesByDraftProjectId(aDraftPtojectId);
  }

  @Override
  public List<String> saveOrUpdateSignatures(List<SignaturesEntity> aSignaturesEntityList)
  {
    return signaturesDao.saveOrUpdateSignatures(aSignaturesEntityList);
  }

  @Override
  public String getSignantByDependenceId(DependenceEntity aDependenceEntity)
  {
    String mySignant = "";

    //Obtener la Dependencia
    DependenceEntity myDependence = this.itsDependenceDao.getDependenceById(aDependenceEntity.getDependenceId());

    //Obtener lso datos del empleado
    EmployeeEntity myEmployee = myDependence.getResponsible();
    //Obtener la colectiva
    ColectiveEntity myColective = myEmployee.getColectiveEmployee();

    mySignant = myColective.getColectiveName()
            + " "
            + myColective.getColectiveLastName()
            + " "
            + myColective.getColectiveMiddleName();

    return mySignant;
  }
}