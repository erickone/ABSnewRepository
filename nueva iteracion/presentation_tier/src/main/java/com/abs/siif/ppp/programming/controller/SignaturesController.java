/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.context.KeyContextEnum;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.management.DependenceManagement;
import com.abs.siif.ppp.programming.api.controller.DraftProjectHeaderControllerApi;
import com.abs.siif.ppp.programming.api.controller.SignaturesControllerApi;
import com.abs.siif.programming.dto.UrlConnectionReportDTO;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.SignatureTypeEntity;
import com.abs.siif.programming.entities.SignaturesEntity;
import com.abs.siif.programming.management.SignaturesManagement;
import com.abs.siif.programming.management.SignaturesTypeManagement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jacob.flores
 */
@Controller("SignaturesController")
@Scope("session")
public class SignaturesController
        extends SIIFControllerBase implements Serializable, SignaturesControllerApi
{

  @Resource(name = "draftProjectHeaderController")
  private transient DraftProjectHeaderControllerApi itsDraftProjectHeaderController;
  @Resource(name = "signaturesManagement")
  private transient SignaturesManagement itsSignaturesManagement;
  @Resource(name = "signaturesTypeManagement")
  private transient SignaturesTypeManagement itsSignaturesTypeManagement;
  @Resource(name = "dependenceManagement")
  private transient DependenceManagement itsDependenceManagement;
  private String theirFormulatedBy;
  private String theirRevisedBy;
  private String theirVerifiedAndValidatedBy;
  private String theirAuthorizedBy;
  private List<SignaturesEntity> theirSignatures;
  private String imprimirReporte;

  @Override
  public void initSignature()
  {
    cleanFields();
    long myProjectId = Long.valueOf(this.itsDraftProjectHeaderController.getTheirCurrentDraftProjectId());
    if (!(myProjectId == Long.valueOf(0))
            || !(this.itsDraftProjectHeaderController.getTheirCurrentDraftProjectId() == null))
    {
      //cargar datos
      loadSignantsByDraftProjectId(this.itsDraftProjectHeaderController.getTheirCurrentDraftProjectId());
      //if(this.theirSignatures != null)
      //{
      setSignantsToGui();
      loadAutomatedSignants();
      //}

    }
    UrlConnectionReportDTO objUrlServer = (UrlConnectionReportDTO) SIIFContextBase.getParamContext(KeyContextEnum.URLSERVERREPORT);
    objUrlServer.setIdObjecto(this.itsDraftProjectHeaderController.getTheirCurrentDraftProjectId().toString());
    objUrlServer.setTipoFicha(this.getMessage("ppp.progr.draftproject.report.ficha"));
    setImprimirReporte(objUrlServer.getUrlDocumento());
  }

  @Override
  public void cleanFields()
  {
    this.setFormulatedBy("");
    this.setRevisedBy("");
    this.setVerifiedAndValidatedBy("");
    this.setAuthorizedBy("");
    this.theirSignatures = null;
  }

  /**
   * Este método se encarga de cargar las firmas para el proyecto indicado, en casod e que
   * existan
   *
   * @param aDraftProjectId
   */
  @Override
  public void loadSignantsByDraftProjectId(Long aDraftProjectId)
  {
    DraftProjectEntity myEntity = new DraftProjectEntity();
    myEntity.setDraftProjectId(aDraftProjectId);
    this.setTheirSignatures(this.itsSignaturesManagement.getSignaturesByDraftProjectId(myEntity));
  }

  /**
   * Este método pinta las cajas de texto con la información obtenida en la lista de
   * firmantes. Se tiene que discriminar por tipo de firma
   */
  @Override
  public void setSignantsToGui()
  {
    for (SignaturesEntity signType : this.theirSignatures)
    {
      if (signType.getSignatureType().getSignatureTypeType().equals("F"))
      {
        this.setFormulatedBy(signType.getSignature());
      }
      else if (signType.getSignatureType().getSignatureTypeType().equals("R"))
      {
        this.setRevisedBy(signType.getSignature());
      }
      else if (signType.getSignatureType().getSignatureTypeType().equals("V"))
      {
        this.setVerifiedAndValidatedBy(signType.getSignature());
      }
      else if (signType.getSignatureType().getSignatureTypeType().equals("A"))
      {
        this.setAuthorizedBy(signType.getSignature());
      }
    }
  }

  /**
   * Se genera un List , ya que cada firmante constitutye un registro
   */
  @Override
  public void saveEditSignatures()
  {
    prepareToSave();
    if (!this.itsSignaturesManagement.saveOrUpdateSignatures(theirSignatures).isEmpty())
    {
      addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
              this.getMessage("ppp.progr.InvPrefile.Signatures.SuccesSave"),
              this.getMessage("ppp.progr.InvPrefile.Signatures.SuccesSave"));
    }
    else
    {
      addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
              this.getMessage("ppp.progr.InvPrefile.Signatures.UnsuccesSave"),
              this.getMessage("ppp.progr.InvPrefile.Signatures.UnsuccesSave"));
    }
    initSignature();
  }

  @Override
  public void printReport()
  {
  }

  /**
   * @return the theirAuthorizedBy
   */
  @Override
  public String getAuthorizedBy()
  {
    return theirAuthorizedBy;
  }

  /**
   * @param theirAuthorizedBy the theirAuthorizedBy to set
   */
  @Override
  public void setAuthorizedBy(String anAuthorizedBy)
  {
    this.theirAuthorizedBy = anAuthorizedBy;
  }

  /**
   * @return the theirFormulatedBy
   */
  @Override
  public String getFormulatedBy()
  {
    return theirFormulatedBy;
  }

  /**
   * @param theirFormulatedBy the theirFormulatedBy to set
   */
  @Override
  public void setFormulatedBy(String aFormulatedBy)
  {
    this.theirFormulatedBy = aFormulatedBy;
  }

  /**
   * @return the theirRevisedBy
   */
  @Override
  public String getRevisedBy()
  {
    return theirRevisedBy;
  }

  /**
   * @param theirRevisedBy the theirRevisedBy to set
   */
  @Override
  public void setRevisedBy(String aRevisedBy)
  {
    this.theirRevisedBy = aRevisedBy;
  }

  /**
   * @return the theirVerifiedAndValidatedBy
   */
  @Override
  public String getVerifiedAndValidatedBy()
  {
    return theirVerifiedAndValidatedBy;
  }

  /**
   * @param theirVerifiedAndValidatedBy the theirVerifiedAndValidatedBy to set
   */
  @Override
  public void setVerifiedAndValidatedBy(String aVerifiedAndValidatedBy)
  {
    this.theirVerifiedAndValidatedBy = aVerifiedAndValidatedBy;
  }

  /**
   * @return the itsDraftProjectHeaderController
   */
  public DraftProjectHeaderControllerApi getItsDraftProjectHeaderController()
  {
    return itsDraftProjectHeaderController;
  }

  /**
   * @param itsDraftProjectHeaderController the itsDraftProjectHeaderController to set
   */
  public void setItsDraftProjectHeaderController(DraftProjectHeaderControllerApi itsDraftProjectHeaderController)
  {
    this.itsDraftProjectHeaderController = itsDraftProjectHeaderController;
  }

  /**
   * @return the itsSignaturesManagement
   */
  public SignaturesManagement getItsSignaturesManagement()
  {
    return itsSignaturesManagement;
  }

  /**
   * @param itsSignaturesManagement the itsSignaturesManagement to set
   */
  public void setItsSignaturesManagement(SignaturesManagement itsSignaturesManagement)
  {
    this.itsSignaturesManagement = itsSignaturesManagement;
  }

  /**
   * @return the theirSignatures
   */
  public List<SignaturesEntity> getTheirSignatures()
  {
    return theirSignatures;
  }

  /**
   * @param theirSignatures the theirSignatures to set
   */
  public void setTheirSignatures(List<SignaturesEntity> theirSignatures)
  {
    this.theirSignatures = theirSignatures;
  }

  /**
   * @return the itsSignaturesTypeManagement
   */
  public SignaturesTypeManagement getItsSignaturesTypeManagement()
  {
    return itsSignaturesTypeManagement;
  }

  /**
   * @param itsSignaturesTypeManagement the itsSignaturesTypeManagement to set
   */
  public void setItsSignaturesTypeManagement(SignaturesTypeManagement itsSignaturesTypeManagement)
  {
    this.itsSignaturesTypeManagement = itsSignaturesTypeManagement;
  }

  /**
   * @return the imprimirReporte
   */
  public String getImprimirReporte()
  {
    return imprimirReporte;
  }

  /**
   * @param imprimirReporte the imprimirReporte to set
   */
  public void setImprimirReporte(String imprimirReporte)
  {
    this.imprimirReporte = imprimirReporte;
  }

  /**
   * Este método se encarga de guardar las modificaciones. si la lista de firmas esta
   * vacía es un registro nuevo, de otro modo es una edicion
   */
  @Override
  public void prepareToSave()
  {
    if (!(this.theirSignatures.isEmpty()) && !(this.theirSignatures == null))
    {
      for (SignaturesEntity signType : this.theirSignatures)
      {
        if (signType.getSignatureType().getSignatureTypeType().equals("F"))
        {
          signType.setSignature(this.getFormulatedBy());
        }
        else if (signType.getSignatureType().getSignatureTypeType().equals("R"))
        {
          signType.setSignature(this.getRevisedBy());
        }
        else if (signType.getSignatureType().getSignatureTypeType().equals("V"))
        {
          signType.setSignature(this.getVerifiedAndValidatedBy());
        }
        else if (signType.getSignatureType().getSignatureTypeType().equals("A"))
        {
          signType.setSignature(this.getAuthorizedBy());
        }
      }
    }
    else
    {
      this.theirSignatures = new ArrayList<SignaturesEntity>();
      this.theirSignatures.add(prepareSignant("F"));
      this.theirSignatures.add(prepareSignant("R"));
      this.theirSignatures.add(prepareSignant("V"));
      this.theirSignatures.add(prepareSignant("A"));
    }
  }

  private SignaturesEntity prepareSignant(String aSignType)
  {
    SignaturesEntity mySignant = new SignaturesEntity();
    SignatureTypeEntity mySignType = new SignatureTypeEntity();
    DraftProjectEntity myDraft = new DraftProjectEntity();

    //Se setea el draft project
    myDraft.setDraftProjectId(this.itsDraftProjectHeaderController.getTheirCurrentDraftProjectId());
    mySignant.setDraftProject(myDraft);
    //Se setea el id de la firma, enn base al tipo de firma seleccionado
    mySignType = this.itsSignaturesTypeManagement.getSignaturesTypeByType(aSignType);
    mySignant.setSignatureType(mySignType);

    //Setear los firmantes enbase
    if (mySignant.getSignatureType().getSignatureTypeType().equals("F"))
    {
      mySignant.setSignature(this.getFormulatedBy());
    }
    else if (mySignant.getSignatureType().getSignatureTypeType().equals("R"))
    {
      mySignant.setSignature(this.getRevisedBy());
    }
    else if (mySignant.getSignatureType().getSignatureTypeType().equals("V"))
    {
      mySignant.setSignature(this.getVerifiedAndValidatedBy());
    }
    else if (mySignant.getSignatureType().getSignatureTypeType().equals("A"))
    {
      mySignant.setSignature(this.getAuthorizedBy());
    }
    //se setea por defaylt en False el campo de impreso
    mySignant.setIsImpress(Boolean.FALSE);
    return mySignant;
  }

  @Override
  public void validateEmptyFields()
  {
    if (getRevisedBy().trim().equals(""))
    {
      addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
              getMessage("ppp.progr.InvPrefile.Signatures.emptySignature", "Revisó"),
              getMessage("ppp.progr.InvPrefile.Signatures.emptySignature", "Revisó"));
    }
    if (getVerifiedAndValidatedBy().trim().equals(""))
    {
      addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
              getMessage("ppp.progr.InvPrefile.Signatures.emptySignature", "Verificó y Validó Presupuesto"),
              getMessage("ppp.progr.InvPrefile.Signatures.emptySignature", "Verificó y Validó Presupuesto"));
    }
    if (!getVerifiedAndValidatedBy().trim().equals("") && !getRevisedBy().trim().equals(""))
    {
      addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
              getMessage("ppp.progr.InvPrefile.Signatures.messageSave"),
              getMessage("ppp.progr.InvPrefile.Signatures.messageSave"));
    }

  }

  private void loadAutomatedSignants()
  {

    Long myDependenceId = this.itsDraftProjectHeaderController.getIdDependency();
    DependenceEntity myDependence = itsDependenceManagement.getDependenceById(myDependenceId);
    String myFormulator = this.itsSignaturesManagement.getSignantByDependenceId(myDependence);
    String myAuthorizer = this.itsSignaturesManagement.getSignantByDependenceId(myDependence.getFather().getFather());

    this.setFormulatedBy(myFormulator);
    this.setAuthorizedBy(myAuthorizer);

  }

  /**
   * @return the itsDependenceManagement
   */
  public DependenceManagement getItsDependenceManagement()
  {
    return itsDependenceManagement;
  }

  /**
   * @param itsDependenceManagement the itsDependenceManagement to set
   */
  public void setItsDependenceManagement(DependenceManagement itsDependenceManagement)
  {
    this.itsDependenceManagement = itsDependenceManagement;
  }
}