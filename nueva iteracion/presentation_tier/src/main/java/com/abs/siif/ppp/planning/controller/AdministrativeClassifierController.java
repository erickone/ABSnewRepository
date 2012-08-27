/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  AdministrativeClassifierController
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.planning.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.common.dto.TreeNodeDto;
import com.abs.siif.common.support.component.TreeNodeComponent;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.DependenceLevelEntity;
import com.abs.siif.planning.management.DependencyLevelManagement;
import com.abs.siif.planning.management.DependencyManagement;
import com.abs.siif.ppp.planning.uihelpers.DependencyUIHelper;
import com.abs.siif.programming.management.DependenceProgrammingManagement;
import com.abs.siif.utility.SaveType;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Controller("administrativeClassifier")
public class AdministrativeClassifierController
        extends SIIFControllerBase implements Serializable {

    @Resource(name = "treeNodeComponent")
    private TreeNodeComponent itsTreeNodeComponent;
    @Resource(name = "dependenceProgrammingManagement")
    private DependenceProgrammingManagement itsDependenceProgrammingManagement;
    @Resource(name = "dependencyManagement")
    private DependencyManagement itsDependencyManagement;
    @Resource(name = "dependencyLevelManagement")
    private DependencyLevelManagement itsLevelManagement;
    private TreeNode theirTreeNode;
    private Collection<String> itsCompositeName = new ArrayList<String>();
    private Map<String, List<?>> itsSupportList;
    private TreeNode theirSelectedNode;
    private String theirSelectedResponsible;
    private String theirSelectedAdmClassType;
    private String theirSelectedClassConac;
    private String theirSelectedStatusColective;
    private String theirColectiveType;
    private String theirColectiveKey;
    private String theirColectiveName;
    private String theirColectiveShortName;
    private Date theirRegisterDate;
    private Date theirLastModifiedDate;
    private String theirColectiveFundament;
    private String theirColectiveColectiveType;
    private List<String> theirSelectedOptions;
    private Long itsCurrentDependencyId;
    private Long itsCurrentColectiveId;
    private String itsParent;
    private String theirDescLong;
    private SaveType theirSaveType;
    private Long itsColectiveTypeId;
    private Long itsDependenceNextLevel;
    private String theirHeaderForm;
    private boolean theirDisabledEdit;
    private boolean theirDisabledClassifierType;
    private boolean theirDisabledCONAC;

    public boolean isTheirDisabledCONAC() {
        return theirDisabledCONAC;
    }

    public void setTheirDisabledCONAC(boolean theirDisabledCONAC) {
        this.theirDisabledCONAC = theirDisabledCONAC;
    }

    public boolean isTheirDisabledClassifierType() {
        return theirDisabledClassifierType;
    }

    public void setTheirDisabledClassifierType(boolean theirDisabledClassifierType) {
        this.theirDisabledClassifierType = theirDisabledClassifierType;
    }

    public boolean isTheirDisabledEdit() {
        return theirDisabledEdit;
    }

    public void setTheirDisabledEdit(boolean theirDisabledEdit) {
        this.theirDisabledEdit = theirDisabledEdit;
    }

    public String getTheirHeaderForm() {
        return theirHeaderForm;
    }

    public void setTheirHeaderForm(String theirHeaderForm) {
        this.theirHeaderForm = theirHeaderForm;
    }

    public Long getItsDependenceNextLevel() {
        return itsDependenceNextLevel;
    }

    public Long getItsColectiveTypeId() {
        return itsColectiveTypeId;
    }

    public SaveType getTheirSaveType() {
        return theirSaveType;
    }

    public void setTheirSaveType(SaveType theirSaveType) {
        this.theirSaveType = theirSaveType;
    }

    public String getItsParent() {
        return itsParent;
    }

    public String getTheirDescLong() {
        return theirDescLong;
    }

    public void setTheirDescLong(String theirDescLong) {
        this.theirDescLong = theirDescLong;
    }

    public Long getItsCurrentColectiveId() {
        return itsCurrentColectiveId;
    }

    public Long getItsCurrentDependencyId() {
        return itsCurrentDependencyId;
    }

    public List<String> getTheirSelectedOptions() {
        return theirSelectedOptions;
    }

    public void setTheirSelectedOptions(List<String> theirSelectedOptions) {
        this.theirSelectedOptions = theirSelectedOptions;
    }

    public String getTheirColectiveColectiveType() {
        return theirColectiveColectiveType;
    }

    public void setTheirColectiveColectiveType(String theirColectiveColectiveType) {
        this.theirColectiveColectiveType = theirColectiveColectiveType;
    }

    public String getTheirColectiveFundament() {
        return theirColectiveFundament;
    }

    public void setTheirColectiveFundament(String theirColectiveFundament) {
        this.theirColectiveFundament = theirColectiveFundament;
    }

    public String getTheirColectiveKey() {
        return theirColectiveKey;
    }

    public void setTheirColectiveKey(String theirColectiveKey) {
        this.theirColectiveKey = theirColectiveKey;
    }

    public String getTheirColectiveName() {
        return theirColectiveName;
    }

    public void setTheirColectiveName(String theirColectiveName) {
        this.theirColectiveName = theirColectiveName;
    }

    public String getTheirColectiveShortName() {
        return theirColectiveShortName;
    }

    public void setTheirColectiveShortName(String theirColectiveShortName) {
        this.theirColectiveShortName = theirColectiveShortName;
    }

    public String getTheirColectiveType() {
        return theirColectiveType;
    }

    public void setTheirColectiveType(String theirColectiveType) {
        this.theirColectiveType = theirColectiveType;
    }

    public Date getTheirLastModifiedDate() {
        return theirLastModifiedDate;
    }

    public void setTheirLastModifiedDate(Date theirLastModifiedDate) {
        this.theirLastModifiedDate = theirLastModifiedDate;
    }

    public Date getTheirRegisterDate() {
        return theirRegisterDate;
    }

    public void setTheirRegisterDate(Date theirRegisterDate) {
        this.theirRegisterDate = theirRegisterDate;
    }

    public Map<String, List<?>> getItsSupportList() {
        return itsSupportList;
    }

    public String getTheirSelectedAdmClassType() {
        return theirSelectedAdmClassType;
    }

    public void setTheirSelectedAdmClassType(String theirSelectedAdmClassType) {
        this.theirSelectedAdmClassType = theirSelectedAdmClassType;
    }

    public String getTheirSelectedClassConac() {
        return theirSelectedClassConac;
    }

    public void setTheirSelectedClassConac(String theirSelectedClassConac) {
        this.theirSelectedClassConac = theirSelectedClassConac;
    }

    public String getTheirSelectedResponsible() {
        return theirSelectedResponsible;
    }

    public void setTheirSelectedResponsible(String theirSelectedResponsible) {
        this.theirSelectedResponsible = theirSelectedResponsible;
    }

    public String getTheirSelectedStatusColective() {
        return theirSelectedStatusColective;
    }

    public void setTheirSelectedStatusColective(String theirSelectedStatusColective) {
        this.theirSelectedStatusColective = theirSelectedStatusColective;
    }

    public TreeNode getTheirSelectedNode() {
        return theirSelectedNode;
    }

    public void setTheirSelectedNode(TreeNode theirSelectedNode) {
        this.theirSelectedNode = theirSelectedNode;
    }

    public TreeNode getTheirTreeNode() {
        return theirTreeNode;
    }

    public void init() throws NoSuchFieldException, IllegalAccessException {

        Collection<DependenceEntity> myDependencies =
                itsDependenceProgrammingManagement.getViewDepWithoutFather();
        itsCompositeName.clear();
        itsCompositeName.add("dependenceKey");
        itsCompositeName.add("dependenceDescription");
            itsTreeNodeComponent.setItsTreeNode(null);
        itsTreeNodeComponent.initTreeNode(myDependencies, "Dependencias",
                "dependenceId", itsCompositeName, DependenceEntity.class,
                "dependenceLevel", "dependenceLevel", "dependenceLevelId");

        theirTreeNode = itsTreeNodeComponent.getItsTreeNode();
        loadSupportList();
    }

    private void loadClassifiers(DependenceLevelEntity myDependency) {
        theirDisabledClassifierType = !myDependency.isDependencyLevelHasClassifierUnit();
        theirDisabledCONAC = !myDependency.isDependencyLevelHasClassifFraming();
    }

    private DependenceLevelEntity loadLevel(SaveType aSaveType) {
        DependenceLevelEntity myLevel = new DependenceLevelEntity();
        TreeNodeDto myDto = (TreeNodeDto) theirSelectedNode.getData();
        myLevel.setDependenceLevelId(myDto.getItsLevelId());
        myLevel = itsLevelManagement.getDependenceLevelWithColective(myLevel);

        if (aSaveType == SaveType.Save) {
            short myNextLevel = (short) (myDto.getItsLevel() + 1);
            try {
                myLevel = itsLevelManagement.getDependenceLevelByLevelByAnnio(myNextLevel);
                itsDependenceNextLevel = myLevel.getDependenceLevelId();
            } catch (Exception ex) {
                theirDisabledEdit = true;
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        "",
                        getMessage(ex.getMessage()));

            }
        } else {
            String myHeader = getMessage("ppp.progr.AdminClassifierTitle");

            theirHeaderForm = myHeader + " " +  itsLevelManagement.getDependenceLevelByLevelByAnnio((short) myDto.getItsLevel()).
                    getDependenceLevelDescription();
        }

        return myLevel;
    }

    private void loadSupportList() {
        if (itsSupportList == null) {
            itsSupportList = itsDependencyManagement.getSupportList();
        }
    }

    public void prepareEditItem() {

        if (theirSelectedNode != null) {
            theirDisabledEdit = Boolean.FALSE;
            theirSaveType = SaveType.Update;
            TreeNodeDto mySelectedNode =
                    (TreeNodeDto) theirSelectedNode.getData();
            DependenceEntity myDependency = new DependenceEntity();
            myDependency.setDependenceId(
                    mySelectedNode.getItsIdentity());
            myDependency = itsDependencyManagement.getDependecyById(myDependency);
            loadClassifiers(myDependency.getDependenceLevel());

            if (myDependency != null) {
                itsCurrentDependencyId = myDependency.getDependenceId();
                itsCurrentColectiveId = myDependency.getColective().getColectiveId();
                theirColectiveKey = myDependency.getDependenceKey();
                theirColectiveName = myDependency.getDependenceDescription();
                theirDescLong = myDependency.getDependencyDescLong();
                theirColectiveColectiveType = myDependency.getDependenceLevel().getColectiveType().getColectiveTypeDescription();
                itsColectiveTypeId = myDependency.getDependenceLevel().getColectiveType().getColectiveTypeId();
                theirSelectedStatusColective = myDependency.getColective().getStatusColective().getStatusColectiveId().toString();

                if (myDependency.getAdministrativeClassifierType() != null) {
                    theirSelectedAdmClassType = myDependency.getAdministrativeClassifierType().
                            getAdminClassTypeId().toString();
                }

                theirRegisterDate = myDependency.getColective().getColectiveRegisterDate();
                theirLastModifiedDate = myDependency.getColective().getColectiveLastModifiedDate();
                theirColectiveShortName = myDependency.getDependencyShortName();
                theirColectiveFundament = myDependency.getDependencyFundamental();

                if (myDependency.getResponsible() != null) {
                    theirSelectedResponsible = myDependency.getResponsible().
                            getEmployeeId().toString();
                }


                theirSelectedOptions = DependencyUIHelper.convertOptionDataInSelectedOptions(myDependency);
                loadLevel(SaveType.Update);
            }
        }
    }

    public void prepareNewItem() {
        theirDisabledEdit = Boolean.FALSE;
        theirSaveType = SaveType.Save;
        setTheirColectiveType("");
        setTheirSelectedStatusColective("");
        setTheirColectiveKey("");
        setTheirColectiveName("");
        setTheirColectiveShortName("");
        setTheirDescLong("");

        Date myToday = new Date();
        //TODO: Fechas
        setTheirLastModifiedDate(myToday);
        setTheirRegisterDate(myToday);

        setTheirColectiveFundament("");
        setTheirSelectedResponsible("");
        setTheirSelectedResponsible("");
        setTheirSelectedAdmClassType("");
        setTheirSelectedClassConac("");

        theirSelectedOptions = new ArrayList<String>();
        itsCurrentColectiveId = -1L;
        itsCurrentDependencyId = -1L;
        DependenceLevelEntity myLevel = loadLevel(SaveType.Save);

        if (myLevel != null) {
            loadClassifiers(myLevel);
            theirColectiveColectiveType = myLevel.getColectiveType().
                    getColectiveTypeDescription();
            itsColectiveTypeId = myLevel.getColectiveType().getColectiveTypeId();
            theirHeaderForm = getMessage("ppp.progr.AdminClassifierTitle")
                    + " " + myLevel.getDependenceLevelDescription();
        }


    }

    public void onNodeExpand(NodeExpandEvent aEvent) {
        TreeNode myExpandNode = aEvent.getTreeNode();
        TreeNodeDto mySelectedNode = (TreeNodeDto) myExpandNode.getData();
        DependenceEntity myDependency = new DependenceEntity();
        myDependency.setDependenceId(mySelectedNode.getItsIdentity());
        Collection<DependenceEntity> myDependencies = itsDependenceProgrammingManagement.getViewDepByFather(
                myDependency);


        try {

            itsTreeNodeComponent.loadChilds(myDependencies, myExpandNode,
                    "dependenceId", itsCompositeName, DependenceEntity.class,
                    "dependenceLevel", "dependenceLevel", "dependenceLevelId");
  
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(AdministrativeClassifierController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AdministrativeClassifierController.class.getName()).log(Level.SEVERE, null, ex);


        }
    }

    public void persistDependency() {
        FacesMessage.Severity myMessageFaces = FacesMessage.SEVERITY_INFO;
        String myMessageUI = this.getMessage("ppp.progr.succesSave");
        try {

            DependenceEntity myDependency = DependencyUIHelper.MapperAtributtesInEntity(this);
            myDependency = itsDependencyManagement.persistDependency(myDependency);
            itsCurrentColectiveId = myDependency.getColective().getColectiveId();
            itsCurrentDependencyId = myDependency.getDependenceId();
            String myNodeText = myDependency.getDependenceKey() + " "
                    + myDependency.getDependencyShortName();

            if (theirSaveType == SaveType.Update) {
                ((TreeNodeDto) theirSelectedNode.getData()).setItsNodeText(myNodeText);
            } else {
                TreeNodeDto mySelectedNode = (TreeNodeDto) theirSelectedNode.getData();
                TreeNodeDto myDto = new TreeNodeDto(mySelectedNode.getItsIdentity(),
                        myDependency.getDependenceId(), myNodeText, false,
                        myDependency.getDependenceLevel().getDependenceLevel(),
                        myDependency.getDependenceLevel().getDependenceLevelId());
                TreeNode myChild = new DefaultTreeNode(myDto, theirSelectedNode);
            }

            theirSaveType = SaveType.Update;
        } catch (Exception ex) {
            myMessageFaces = FacesMessage.SEVERITY_ERROR;
            myMessageUI = this.getMessage(ex.getMessage());
        } finally {
            addMessageCurrentInstance(myMessageFaces,
                    myMessageUI,
                    myMessageUI);
        }
    }

    public void restartTreeNode() throws NoSuchFieldException, IllegalAccessException {
        itsTreeNodeComponent.setItsTreeNode(null);
        init();
    }

    public void delete() {
        FacesMessage.Severity myMessageFaces = FacesMessage.SEVERITY_INFO;
        String myMessageUI = this.getMessage("ppp.progr.succesSave");
        try {

            if (theirSelectedNode != null) {
                TreeNodeDto myDto = (TreeNodeDto) theirSelectedNode.getData();
                DependenceEntity myDependency = new DependenceEntity();
                myDependency.setDependenceId(myDto.getItsIdentity());
                itsDependencyManagement.deleteDependency(myDependency);
                restartTreeNode();
                init();

            }
        } catch (Exception ex) {
            myMessageFaces = FacesMessage.SEVERITY_ERROR;
            myMessageUI = ex.getMessage();
        } finally {
            addMessageCurrentInstance(myMessageFaces,
                    "",
                    myMessageUI);
        }
    }
}
