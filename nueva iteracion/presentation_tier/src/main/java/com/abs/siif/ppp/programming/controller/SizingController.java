package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.ppp.programming.api.controller.SizingControllerApi;
import com.abs.siif.ppp.programming.uihelpers.SizingDataModel;
import com.abs.siif.programming.dto.SizingDto;
import com.abs.siif.programming.entities.InvPreFileEntity;
import com.abs.siif.programming.entities.SizingEntity;
import com.abs.siif.programming.management.SizingManagement;
import com.abs.siif.support.FormatNumber;
import com.abs.siif.support.SearchList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author FENIX-02
 */
@Controller("sizingController")
@Scope("session")
public class SizingController
        extends SIIFControllerBase
        implements Serializable, SizingControllerApi {
    //Esta Variable controla el id de la pre-ficha

    private Long itsPrefileId;
    private int number;
    //Estas variables son para controlar la carga de Dimensiones
    private String itsSizingDesc = "";
    private String itsSizingAmount;
    private String itsSizingIniAsig;
    private String itsSizingIniPetition;
    private String itsSizingSum;
    private String itsSizingPercentage;
    //Esta variable indica el reglon seleccionado
    private SizingDto itsSelectedRow;
    //Estas son las variables que controlan las Sumas
    private String itsSizingTotalAmount;
    private String itsSizingTotalIniAsig;
    private String itsSizingTotalIniPetition;
    private String itsSizingTotalSum;
    private String itsSizingTotalPercentage;
    //Esta variable contiene el  monto total de la Asignacion inicial
    private int itsSummatoryOfAsignations;
    //Esta variable contiene el  monto total del Adicional
    private int itsSummatoryOfAditional;
    private int itsSummatoryOfAditionals;
    //Esta es la lista de Dimensionamientos disponibles
    private List<SizingEntity> itsListOfSizingEntities;
    private List<SizingDto> itsListOfSizingEntitiesDto;
    //Data model para las operaciones del DataTable
    private SizingDataModel itsListSizingmodel;
    //Estas son la inyeccion del management
    @Resource(name = "sizingManagement")
    private transient SizingManagement theirSizingManagement;
    private int count;

    /*
     * The following is with test purpose
     *
     */
    @Override
    public String getItsSizingAmount() {
        return itsSizingAmount;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }
    
    @Override
    public void setItsSizingAmount(String itsSizingAmount) {
        itsSizingAmount = FormatNumber.formatNumber(itsSizingAmount);
        this.itsSizingAmount = itsSizingAmount;
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public String getItsSizingDesc() {
        return itsSizingDesc;
    }

    @Override
    public void setItsSizingDesc(String itsSizingDesc) {
        this.itsSizingDesc = itsSizingDesc;
    }

    @Override
    public String getItsSizingIniAsig() {
        return itsSizingIniAsig;
    }

    @Override
    public void setItsSizingIniAsig(String itsSizingIniAsig) {
        itsSizingIniAsig = FormatNumber.formatNumber(itsSizingIniAsig);
        this.itsSizingIniAsig = itsSizingIniAsig;
    }

    @Override
    public String getItsSizingIniPetition() {
        return itsSizingIniPetition;
    }

    @Override
    public void setItsSizingIniPetition(String itsSizingIniPetition) {
        itsSizingIniPetition = FormatNumber.formatNumber(itsSizingIniPetition);
        this.itsSizingIniPetition = itsSizingIniPetition;
    }

    @Override
    public String getItsSizingPercentage() {
        return itsSizingPercentage;
    }

    @Override
    public void setItsSizingPercentage(String itsSizingPercentage) {
        itsSizingPercentage = FormatNumber.formatNumber(itsSizingPercentage);
        this.itsSizingPercentage = itsSizingPercentage;
    }

    @Override
    public String getItsSizingSum() {
        return itsSizingSum;
    }

    @Override
    public void setItsSizingSum(String itsSizingSum) {
        itsSizingSum = FormatNumber.formatNumber(itsSizingSum);
        this.itsSizingSum = itsSizingSum;
    }

    @Override
    public String getItsSizingTotalAmount() {
        return itsSizingTotalAmount;
    }

    @Override
    public void setItsSizingTotalAmount(String itsSizingTotalAmount) {
        itsSizingTotalAmount = FormatNumber.formatNumber(itsSizingTotalAmount);
        this.itsSizingTotalAmount = itsSizingTotalAmount;
    }

    @Override
    public String getItsSizingTotalIniAsig() {
        return itsSizingTotalIniAsig;
    }

    @Override
    public void setItsSizingTotalIniAsig(String itsSizingTotalIniAsig) {
        itsSizingTotalIniAsig = FormatNumber.formatNumber(itsSizingTotalIniAsig);
        this.itsSizingTotalIniAsig = itsSizingTotalIniAsig;
    }

    @Override
    public String getItsSizingTotalIniPetition() {
        return itsSizingTotalIniPetition;
    }

    @Override
    public void setItsSizingTotalIniPetition(String itsSizingTotalIniPetition) {
        itsSizingTotalIniPetition = FormatNumber.formatNumber(itsSizingTotalIniPetition);
        this.itsSizingTotalIniPetition = itsSizingTotalIniPetition;
    }

    @Override
    public String getItsSizingTotalPercentage() {
        return itsSizingTotalPercentage;
    }

    @Override
    public void setItsSizingTotalPercentage(String itsSizingTotalPercentage) {
        itsSizingTotalPercentage = FormatNumber.formatNumber(itsSizingTotalPercentage);
        this.itsSizingTotalPercentage = itsSizingTotalPercentage;
    }

    @Override
    public String getItsSizingTotalSum() {
        return itsSizingTotalSum;
    }

    @Override
    public void setItsSizingTotalSum(String itsSizingTotalSum) {
        itsSizingTotalSum = FormatNumber.formatNumber(itsSizingTotalSum);
        this.itsSizingTotalSum = itsSizingTotalSum;
    }

    /*
     * Aqui va mucho codigo super loco
     */
    @Override
    public List<SizingEntity> getItsListOfSizingEntities() {
        return itsListOfSizingEntities;
    }

    @Override
    public void setItsListOfSizingEntities(List<SizingEntity> itsListOfSizingEntities) {
        this.itsListOfSizingEntities = itsListOfSizingEntities;
    }

    @Override
    public int getItsSummatoryOfAditionals() {
        return itsSummatoryOfAditionals;
    }

    @Override
    public void setItsSummatoryOfAditionals(int itsSummatoryOfAditionals) {
        this.itsSummatoryOfAditionals = itsSummatoryOfAditionals;
    }

    @Override
    public int getItsSummatoryOfAsignations() {
        return itsSummatoryOfAsignations;
    }

    @Override
    public void setItsSummatoryOfAsignations(int itsSummatoryOfAsignations) {
        this.itsSummatoryOfAsignations = itsSummatoryOfAsignations;
    }

    @Override
    public int getItsSummatoryOfAditional() {
        return itsSummatoryOfAditional;
    }

    @Override
    public void setItsSummatoryOfAditional(int itsSummatoryOfAditional) {
        this.itsSummatoryOfAditional = itsSummatoryOfAditional;
    }

    @Override
    public SizingManagement getTheirSizingManagement() {
        return theirSizingManagement;
    }

    @Override
    public void setTheirSizingManagement(SizingManagement theirSizingManagement) {
        this.theirSizingManagement = theirSizingManagement;
    }

    @Override
    public Long getItsPrefileId() {
        return itsPrefileId;
    }

    @Override
    public void setItsPrefileId(Long itsPrefileId) {
        this.itsPrefileId = itsPrefileId;
    }

    @Override
    public SizingDataModel getItsListSizingmodel() {
        return itsListSizingmodel;
    }

    @Override
    public void setItsListSizingmodel(SizingDataModel itsListSizingmodel) {
        this.itsListSizingmodel = itsListSizingmodel;
    }

    @Override
    public List<SizingDto> getItsListOfSizingEntitiesDto() {
        return itsListOfSizingEntitiesDto;
    }

    @Override
    public void setItsListOfSizingEntitiesDto(List<SizingDto> itsListOfSizingEntitiesDto) {
        this.itsListOfSizingEntitiesDto = itsListOfSizingEntitiesDto;
    }

    @Override
    public SizingDto getItsSelectedRow() {
        return itsSelectedRow;
    }

    @Override
    public void setItsSelectedRow(SizingDto itsSelectedRow) {
        this.itsSelectedRow = itsSelectedRow;
    }

    @Override
    public void initSizing() {
        itsListOfSizingEntitiesDto = new ArrayList<SizingDto>();
        itsSummatoryOfAsignations = theirSizingManagement.getTheTotalAmounts(itsPrefileId);
        itsSummatoryOfAditional = theirSizingManagement.getTheTotalAditionals(itsPrefileId);
        itsListOfSizingEntities = new ArrayList<SizingEntity>(theirSizingManagement.getListOfSizingByPreFileId(itsPrefileId));
        itsSizingAmount = "0";
        itsSizingDesc = "";
        itsSizingIniAsig = "0";
        itsSizingIniPetition = "0";
        itsSizingPercentage = "0";
        itsSizingSum = "0";
        itsSizingTotalAmount = "0";
        itsSizingTotalIniAsig = "0";
        itsSizingTotalIniPetition = "0";
        itsSizingTotalSum = "0";
        itsSizingTotalPercentage = "0";
        SizingDto myWorkingDto;
        List<SizingDto> myDTOs = new ArrayList<SizingDto>();
        for (Object myObjectiveCast : itsListOfSizingEntities) {
            Object[] myObjectFields = (Object[]) myObjectiveCast;
            myWorkingDto = new SizingDto(
                    (Long.parseLong(myObjectFields[0].toString())),
                    String.valueOf(myObjectFields[1]),
                    String.valueOf(myObjectFields[2]),
                    String.valueOf(myObjectFields[3]),
                    (myObjectFields[4]).toString(),
                    String.valueOf(myObjectFields[5]),
                    String.valueOf(myObjectFields[6]),
                    String.valueOf(myObjectFields[7]),
                    (Long.parseLong(myObjectFields[8].toString())));
            setItsSizingTotalAmount(String.valueOf(Integer.parseInt(FormatNumber.removeFormat(itsSizingTotalAmount)) + Integer.parseInt(FormatNumber.removeFormat(myWorkingDto.getItsSizingCantity()))));
            setItsSizingTotalIniAsig(String.valueOf(Integer.parseInt(FormatNumber.removeFormat(itsSizingTotalIniAsig)) + Integer.parseInt(FormatNumber.removeFormat(myWorkingDto.getItsSizingInitial()))));
            setItsSizingTotalIniPetition(String.valueOf(Integer.parseInt(FormatNumber.removeFormat(itsSizingTotalIniPetition)) + Integer.parseInt(FormatNumber.removeFormat(myWorkingDto.getItsSizingAditional()))));
            setItsSizingTotalSum(String.valueOf(Integer.parseInt(FormatNumber.removeFormat(itsSizingTotalSum)) + Integer.parseInt(FormatNumber.removeFormat(myWorkingDto.getItsSizingSummatory()))));
            myDTOs.add(myWorkingDto);
        }
        itsListSizingmodel = new SizingDataModel(myDTOs);
        itsListOfSizingEntitiesDto = myDTOs;
        calculatePercent();
        setItsSelectedRow(null);
        if (!itsListOfSizingEntities.isEmpty()) {
            setCount(itsListOfSizingEntitiesDto.size()+1);
            //setCount((Integer.parseInt(FormatNumber.removeFormat(itsListOfSizingEntitiesDto.get(itsListOfSizingEntitiesDto.size() - 1).getItsSizingconsecutive())) + 1));
        } else {
            setCount(1);
        }
    }

    @Override
    public void saveSizingEntity() {
        try {
            validations:
            if ((!((itsSizingDesc.equals("")) || (itsSizingDesc == null)))
                    && (!itsSizingAmount.equals("0"))) {
                if(itsSizingIniAsig.equals("0") && itsSizingIniPetition.equals("0")){
                    addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                            this.getMessage("ppp.Sizing.AmountsZero.Error"),
                            this.getMessage("ppp.Sizing.AmountsZero.Error"));
                    break validations;
                }
                if (itsSummatoryOfAsignations == 0 ) {
                    addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                            this.getMessage("ppp.Sizing.noDataInFinancialStrucutre"),
                            this.getMessage("ppp.Sizing.noDataInFinancialStrucutre"));
                    break validations;
                }
                int sumIniAsg = (Integer.parseInt(FormatNumber.removeFormat(getItsSizingTotalIniAsig())) + Integer.parseInt(FormatNumber.removeFormat(getItsSizingIniAsig())));
                int sumAdi = (Integer.parseInt(FormatNumber.removeFormat(getItsSizingTotalIniPetition())) + Integer.parseInt(FormatNumber.removeFormat(getItsSizingIniPetition())));
                if (sumIniAsg <= (itsSummatoryOfAsignations)) {
                    if (sumAdi <= (itsSummatoryOfAditional)) {


                        InvPreFileEntity myInput = new InvPreFileEntity();
                        myInput.setInvPreFileId(itsPrefileId);
                        SizingEntity myEntity = new SizingEntity();
                        myEntity.setInputInvPreFile(myInput);
                        myEntity.setSizingAditional(Integer.parseInt(FormatNumber.removeFormat(itsSizingIniPetition)));
                        myEntity.setSizingCantity(Integer.parseInt(FormatNumber.removeFormat(itsSizingAmount)));

                        myEntity.setSizingDescription(itsSizingDesc);
                        myEntity.setSizingInitial(Integer.parseInt(FormatNumber.removeFormat(itsSizingIniAsig)));
                        myEntity.setSizingPercent(Integer.parseInt(FormatNumber.removeFormat(itsSizingPercentage)));
                        int myNumber = 0;
                        if (!itsListOfSizingEntities.isEmpty()) {
                            myNumber = itsListOfSizingEntities.size();
                           //myNumber = Integer.parseInt(FormatNumber.removeFormat(itsListOfSizingEntitiesDto.get(itsListOfSizingEntitiesDto.size() - 1).getItsSizingconsecutive()));
                        }
                        if (getItsSelectedRow() != null) {
                            myEntity.setSizingId(getItsSelectedRow().getItsSizingId());
                            myEntity.setSizingConsecutiveNumber(Integer.parseInt(FormatNumber.removeFormat(getItsSelectedRow().getItsSizingconsecutive())));
                        } else {
                            myEntity.setSizingConsecutiveNumber(myNumber + 1);
                        }
                        int mySummatory = Integer.parseInt(FormatNumber.removeFormat(itsSizingIniAsig)) + Integer.parseInt(FormatNumber.removeFormat(itsSizingIniPetition));
                        myEntity.setSizingSummatory(mySummatory);
                        theirSizingManagement.saveSizing(myEntity);
                        addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                                this.getMessage("ppp.progr.succesSave"),
                                this.getMessage("ppp.progr.succesSave"));
                        initSizing();
                        if(sumIniAsg < itsSummatoryOfAsignations){
                            addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                                this.getMessage("ppp.Sizing.totalAsigIniLessZero.Error"),
                                this.getMessage("ppp.Sizing.totalAsigIniLessZero.Error"));
                        }
                        if(sumAdi < (itsSummatoryOfAditional)){
                            addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                                this.getMessage("ppp.Sizing.totalAditLessZero.Error"),
                                this.getMessage("ppp.Sizing.totalAditLessZero.Error"));
                        }
                    } else {

                        addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                                this.getMessage("ppp.Sizing.AsigExeded.Error"),
                                this.getMessage("ppp.Sizing.AsigExeded.Error"));
                    }
                } else {
                    addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                            this.getMessage("ppp.Sizing.AsigIniExeded.Error"),
                            this.getMessage("ppp.Sizing.AsigIniExeded.Error"));
                }
            } else {
                if (itsSizingAmount.equals("0")) {
                    addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                            this.getMessage("ppp.Sizing.CantZero.Error"),
                            this.getMessage("ppp.Sizing.CantZero.Error"));
                } else {
                  
                        addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                                this.getMessage("ppp.Sizing.DescEmpty.Error"),
                                this.getMessage("ppp.Sizing.DescEmpty.Error"));
                }
            }
        } catch (Exception exc) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));
        }
    }

    @Override
    public void deleteSizing() {
        try {
            int num = Integer.parseInt(itsSelectedRow.getItsSizingconsecutive());
            theirSizingManagement.deleteSizing(itsSelectedRow.getItsSizingId());
            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                    this.getMessage("ppp.progr.succesDelete"),
                    this.getMessage("ppp.progr.succesDelete"));
            initSizing();
        } catch (Exception exc) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));
        }

    }

    @Override
    public void calculatePercent() {
        int mySumOfPercent;
        List<SizingDto> myListOfDto = itsListOfSizingEntitiesDto;
        mySumOfPercent = 0;
        for (int i = 0; i < itsListOfSizingEntitiesDto.size(); i++) {
            int myOperator = ((Integer.parseInt(FormatNumber.removeFormat(itsListOfSizingEntitiesDto.get(i).getItsSizingSummatory())) * 100) / Integer.parseInt(FormatNumber.removeFormat(itsSizingTotalSum)));
            itsListOfSizingEntitiesDto.get(i).setItsSizingPercent(String.valueOf(myOperator));
            mySumOfPercent += myOperator;
        }
        if (itsListOfSizingEntitiesDto.size() > 0) {
            int j = findMaxPercent(itsListOfSizingEntitiesDto);
            int valueToChange = Integer.parseInt(FormatNumber.removeFormat(itsListOfSizingEntitiesDto.get(j).getItsSizingPercent()));
            if (mySumOfPercent < 100) {
                valueToChange += (100 - mySumOfPercent);

            } else {
                valueToChange -= (100 - mySumOfPercent);
            }
            itsListOfSizingEntitiesDto.get(j).setItsSizingPercent(String.valueOf(valueToChange));
        }
        itsListSizingmodel = new SizingDataModel(itsListOfSizingEntitiesDto);
    }

    @Override
    public int findMaxPercent(List<SizingDto> mylistTofind) {
        int maxNumber;
        int comparator = 0;
        int iteratorNumber = 0;
        for (int i = 0; i < mylistTofind.size(); i++) {
            if (Integer.parseInt(FormatNumber.removeFormat(mylistTofind.get(i).getItsSizingPercent())) > comparator) {
                maxNumber = Integer.parseInt(FormatNumber.removeFormat(mylistTofind.get(i).getItsSizingPercent()));
                iteratorNumber = i;
                comparator = maxNumber;
            }

        }
        return iteratorNumber;
    }

    /**
     * Seleccion alguna fila de la tabla se carga para editar o ser eliminada
     */
    @Override
    public void selectedRow() { 
        int index = SearchList.indexList(itsListOfSizingEntitiesDto,getItsSelectedRow());
        setItsSizingDesc(getItsSelectedRow().getItsSizingDescription());
        setItsSizingAmount(String.valueOf(getItsSelectedRow().getItsSizingCantity()));
        setItsSizingIniAsig(String.valueOf(getItsSelectedRow().getItsSizingInitial()));
        setItsSizingIniPetition(String.valueOf(getItsSelectedRow().getItsSizingAditional()));
        setItsSizingSum(String.valueOf(getItsSelectedRow().getItsSizingSummatory()));
        setItsSizingPercentage(String.valueOf(getItsSelectedRow().getItsSizingPercent()));
        //setCount(Integer.valueOf(getItsSelectedRow().getItsSizingconsecutive()));
        setCount(index+1);
        
        setItsSizingTotalIniAsig(String.valueOf(Integer.parseInt
                (FormatNumber.removeFormat(getItsSizingTotalIniAsig())) - 
                Integer.parseInt(FormatNumber.removeFormat(getItsSizingIniAsig()))));
        
        setItsSizingTotalIniPetition(String.valueOf(Integer.parseInt
                (FormatNumber.removeFormat(getItsSizingTotalIniPetition())) - 
                Integer.parseInt(FormatNumber.removeFormat(getItsSizingIniPetition()))));

    }

    /**
     * Cancelar alguna accion
     */
    @Override
    public void cancelSelection() {
        initSizing();
    }
}
