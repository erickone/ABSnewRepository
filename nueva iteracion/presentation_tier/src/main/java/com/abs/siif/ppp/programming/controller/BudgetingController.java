package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.budget.dto.BudgetDispURDto;
import com.abs.siif.budget.dto.BudgetingKeysDto;
import com.abs.siif.budget.dto.BudgetingSummaryDto;
import com.abs.siif.budget.dto.DestinationDto;
import com.abs.siif.budget.dto.FinancingSourceDto;
import com.abs.siif.budget.entities.*;
import com.abs.siif.budget.management.BudgetKeyConfigurationManagement;
import com.abs.siif.budget.management.BudgetKeyDefinitionManagement;
import com.abs.siif.budget.management.CeilingBudgetManagement;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.management.DependenceManagement;
import com.abs.siif.ppp.programming.api.controller.BudgetingControllerApi;
import com.abs.siif.ppp.programming.uihelpers.BudgetingKeysDtoDataModel;
import com.abs.siif.ppp.programming.uihelpers.BudgetingUIHelper;
import com.abs.siif.programming.entities.DraftProjectEntity;
import com.abs.siif.programming.entities.ProgrammingEntity;
import com.abs.siif.programming.management.BudgetingManagement;
import com.abs.siif.programming.management.DraftProjectManagement;
import com.abs.siif.support.FormatNumber;
import com.abs.siif.support.MonthsEnum;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * este controller es el encargado de la pagina de presupuestación
 *
 * @author Erick Leija
 */
@Scope("session")
@Controller("budgetingController")
public class BudgetingController
        extends SIIFControllerBase
        implements Serializable, BudgetingControllerApi
{
    //Inyeccion del Management

    @Resource(name = "budgetingManagement")
    private transient BudgetingManagement theirBudgetingManagement;
    @Resource(name = "budgetKeyDefinitionManagement")
    private transient BudgetKeyDefinitionManagement theirBudgetKeyDefinitionManagement;
    @Resource(name = "budgetingSummaryController")
    private transient BudgetingSummaryController theirbudgetingSummaryController;
    @Resource(name = "budgetKeyConfigurationManagement")
    private transient BudgetKeyConfigurationManagement theirbudgetKeyConfigurationManagement;
    @Resource(name = "ceillingBudgetManagement")
    private transient CeilingBudgetManagement theirCeilingBudgetManagement;
    @Resource(name = "draftProjectManagement")
    private transient DraftProjectManagement theirDraftProjectManagement;
    @Resource(name = "dependenceManagement")
    private transient DependenceManagement theirDependeceManagement;
    //Variables que guardan los montos de los meses
    private Map<String, String> itsMonthlyAmounts;
    //Variables para guadar los objetos seleccionados en los SelectOneMenues
    private Long itsDependenceId;
    private Long itsSelectedFinancingSourceId;
    private String itsSelectedDestinationId;
    private Long itsSelectedObjectExpense;
    private String itsSummatoryCantity;
    private Long itsSummatoryAdititionalCantity;
    private Long itsBudgetingKeyId;
    private Long itsDraftProjectId;
    private String itsProgrammaticKey;
    private Long itsMyObjectExpenseId;
    private Long myCeilingBasic;
    private String itsMyJustification;
    private String itsTheMessageBox;
    private String mySumTotalStringFormat;
    //Listas de para llenar
    private List<FinancingSourceEntity> itsListOfFinancingSources;
    private List<DestinyObjectExpenseRUBUEntity> itsListOfDestinations;
    private List<ObjectExpenseEntity> itsListOfFrammingObjectExpense;
    private boolean isAditionalBudgeting;
    private boolean btnSrvSave = false;
    //Listas de Dto
    private List<BudgetingKeysDto> itsListOfBudgetingKeys;
    private List<FinancingSourceDto> itsListOfFinancingSourceDto;
    private BudgetingKeysDtoDataModel itsBudgetingKeysModel;
    private BudgetingKeysDto itsSelectedBudgetKeyRow;
    private boolean itsModifyingABudgetKey = false;
    private boolean itsJustificationTextBoxReadOnly = true;
    private BudgetKeyEntity itsMyBudgetKeyToModify;
    public NumberFormat numberValidator = NumberFormat.getInstance(Locale.US);
    private String itsMyBasicCeiling;
    private String itsMyBasicCeilingUsed;
    private String itsMyBasicAvailable;
    private String itsMyCeilingToshow;
    private String itsMyCeilingAvailableToShow;
    private String itsMyFinCeilingToShow;
    private String itsMyFinAvailableToShow;
    private Long itsMyAvailableToMakeOp;
    private Long myAvailableREsult;
    private Long myAvailableCeilingBudget;
    private Long myResultCeiling;
    private Long myResultCeilingBudget;
    //Variables total por capitulo
    private Long itsTotalPerChapter;
    private String itsTotalPerChapterString;
    private Long myBasicDiference;
    private String itsBudgetKeyToModify;
    private DepencenceDto itsUrDependence;
    private ObjectExpenseEntity objectExpenseSelected;
    private String itsSummatoryAdititionalQuantityAux;
    private Long itsModifyngCantity;

    public BudgetingController()
    {
        itsMonthlyAmounts = new HashMap<String, String>();
    }

    @Override
    public Long getItsDependenceId()
    {
        return itsDependenceId;
    }

    @Override
    public void setItsDependenceId(Long itsDependenceId)
    {
        this.itsDependenceId = itsDependenceId;
    }

    /**
     * este metodo se ejecuta al cargar la pagina y sirve para inicializar las
     * combos
     */
    @Override
    public void initBudgeting()
    {
        try
        {
            itsMonthlyAmounts = new HashMap<String, String>();
            isAditionalBudgeting = false;
            itsMyJustification = "";
            itsSummatoryAdititionalCantity = 0L;
            itsSummatoryAdititionalQuantityAux = "";
            itsTotalPerChapter = 0L;
            btnSrvSave = false;
            this.itsDependenceId = theirbudgetingSummaryController.getItsSelectedDependenceId();
            objectExpenseSelected = null;
            this.itsUrDependence = theirBudgetingManagement.getDependenciesRespUnitBytDependIdRelated(this.itsDependenceId);
            this.itsDraftProjectId = theirbudgetingSummaryController.getMyDraftProjectId();
            itsModifyingABudgetKey = false;
            Map<String, Long> myMap = new HashMap<String, Long>();
            myMap.put("iddependencia", this.getItsDependenceId());
            myMap.put("idproyecto", this.getItsDraftProjectId());
            BudgetingSummaryDto myDto = theirbudgetingSummaryController.getSelectedRow();
            int myChapter = myDto.getItsBudgetingSummaryChapter() / 1000;
            this.itsProgrammaticKey = this.getTheirbudgetKeyConfigurationManagement().getBudgetKey(myMap);
            this.itsProgrammaticKey += String.valueOf(myChapter);
            this.itsBudgetingKeysModel = new BudgetingKeysDtoDataModel(getMyListOfDtos(itsProgrammaticKey));

            setMonthlyAmounts(new HashMap<String, String>());
              MonthsEnum months[] = MonthsEnum.values();
              this.itsSelectedFinancingSourceId=0L;
              this.itsSummatoryAdititionalQuantityAux="";
              this.itsMyJustification="";
              this.isAditionalBudgeting=false;
            for (MonthsEnum month : months)
            {
                itsMonthlyAmounts.put(month.name(), "0");

            }
            DependenceEntity myDependence = new DependenceEntity();
            myDependence.setDependenceId(itsDependenceId);
            itsListOfFrammingObjectExpense =
                    new ArrayList<ObjectExpenseEntity>(theirBudgetingManagement.getTheBudgetingFramming(myDependence, myDto, false));
            if (itsListOfFrammingObjectExpense == null || itsListOfFrammingObjectExpense.isEmpty())
            {
                addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                        this.getMessage("ppp.budgeting.error.ThereisnoFramminUP-UR-OBJ-DEST"),
                        this.getMessage("ppp.budgeting.error.ThereisnoFramminUP-UR-OBJ-DEST"));
                itsListOfFinancingSourceDto = new ArrayList<FinancingSourceDto>();
                itsListOfDestinations = new ArrayList<DestinyObjectExpenseRUBUEntity>();
                DraftProjectEntity myDraft = new DraftProjectEntity();
                return;
            }

            itsListOfFinancingSourceDto = new ArrayList<FinancingSourceDto>();
            itsListOfDestinations = new ArrayList<DestinyObjectExpenseRUBUEntity>();
            isAditionalBudgeting = false;
            itsSummatoryCantity = "0";
            itsSelectedObjectExpense = (long) 0;
            itsSelectedFinancingSourceId = (long) 0;
            itsSelectedDestinationId = "";
            itsJustificationTextBoxReadOnly = true;
            itsMyJustification = "";
            itsMyCeilingToshow = "";
            itsMyFinCeilingToShow = "";
            itsMyFinAvailableToShow = "";
            itsMyCeilingAvailableToShow = "";
            itsMyBasicCeiling = "";
            itsMyBasicCeilingUsed = "";
            itsMyBasicAvailable = "";
            DraftProjectEntity myDraft = new DraftProjectEntity();
            myDraft.setDraftProjectId(itsDraftProjectId);
            mySumTotalStringFormat = numberValidator.format(theirCeilingBudgetManagement.getBudgetByUEG(myDraft));
        } catch (Exception exc)
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));
            //exc.printStackTrace(System.out);
        }

    }

    //Este Metodo se encarga de llenar los Combos de Destino y Fuente de Financiamiento
    //A Partir de la Seleccion de un Objeto del Gasto
    @Override
    public void chargeOfCombos()
    {

        itsListOfFinancingSources = new ArrayList<FinancingSourceEntity>(theirBudgetingManagement.getFinancingSourceByObjectExpense(itsSelectedObjectExpense));
        itsListOfFinancingSourceDto = new ArrayList<FinancingSourceDto>();
        for (Object myObjectiveCast : itsListOfFinancingSources)
        {
            Object[] myObjectFields = (Object[]) myObjectiveCast;
            FinancingSourceDto myFSDto = new FinancingSourceDto(Long.parseLong(myObjectFields[0].toString()),
                    (myObjectFields[1].toString()), (myObjectFields[2].toString()));
            itsListOfFinancingSourceDto.add(myFSDto);
        }
        DependenceEntity myDependence = new DependenceEntity();
        myDependence.setDependenceId(itsDependenceId);
        ObjectExpenseEntity myObject = new ObjectExpenseEntity();
        myObject.setObjectExpenseId(itsSelectedObjectExpense);
        itsListOfDestinations = theirBudgetingManagement.getTheBudgetingDestinationByObject(myDependence, myObject);
        for (DestinyObjectExpenseRUBUEntity myentity : itsListOfDestinations)
        {
            String MyString = myentity.getDestinyObjectEpenseRUBUDestiny().trim();
            myentity.setDestinyObjectEpenseRUBUDestiny(MyString);
        }
        this.itsSelectedDestinationId = "";
        this.itsMyCeilingToshow = "";
        this.itsMyCeilingAvailableToShow = "";
        this.itsMyBasicCeiling = "";
        this.itsMyBasicCeilingUsed = "";
        this.itsMyBasicAvailable = "";
    }

    //
    /**
     * Este Metodo Carga La lista para la tabla de las Claves Presupuestales de
     * la Parte de Abajo de la pantalla de presupuestación AKA Calendarizacion
     *
     * @param aProgrammaticKey
     * @return
     */
    @Override
    public List<BudgetingKeysDto> getMyListOfDtos(String aProgrammaticKey)
    {
        Collection<BudgetKeyEntity> myListToDto = theirBudgetingManagement.getTheBudgetingKeysWithProgramaticKey(aProgrammaticKey);
        List<BudgetingKeysDto> myListOfDto = new ArrayList<BudgetingKeysDto>();
        for (BudgetKeyEntity myEntity : myListToDto)
        {
            String myDestiny = myEntity.getBudgetKeyBProgramaticKey();
            String myDestinyKey = myDestiny.substring(myDestiny.length() - 2);
            Long myObjectExpenseId = myEntity.getObjectExpenseBudgetKey().getObjectExpenseId();
            DestinationDto myDestinationDTO = theirBudgetingManagement.getEncDepObjGasDest(
                    myObjectExpenseId, itsDependenceId, myDestinyKey);
            if (myDestinationDTO != null)
            {
                BudgetingKeysDto myDto =
                        new BudgetingKeysDto(myEntity.getBudgetKeyId(), myEntity.getBudgetKeyBProgramaticKey(),
                        String.valueOf(myEntity.getObjectExpenseBudgetKey().getObjectExpenseKey())
                        + " " + myEntity.getObjectExpenseBudgetKey().getObjectExpenseDescription(),
                        numberValidator.format(myEntity.getOriginalAmount()), false,
                        String.valueOf(myDestinationDTO.getDestinationKey()
                        + " " + myDestinationDTO.getDestinationDesc()));
                myDto.setItsAditionalBudgetingKeyAmount(
                        numberValidator.format(myEntity.getAdditionalAmount()));
                myListOfDto.add(myDto);
                itsTotalPerChapter = itsTotalPerChapter + Long.parseLong(FormatNumber.removeFormat(myDto.getItsBudgetingKeyAmount()));
            }
        }

//        

        return myListOfDto;
    }

    @Override
    public String getItsSummatoryCantity()
    {
        return itsSummatoryCantity;
    }

    @Override
    public void setItsSummatoryCantity(String itsSummatoryCantity)
    {
        this.itsSummatoryCantity = itsSummatoryCantity;
    }

    @Override
    public Long getItsSummatoryAdititionalCantity()
    {
        return itsSummatoryAdititionalCantity;
    }

    @Override
    public void setItsSummatoryAdititionalCantity(Long itsSummatoryAdititionalCantity)
    {
        this.itsSummatoryAdititionalCantity = itsSummatoryAdititionalCantity;
    }

    @Override
    public String getItsSelectedDestinationId()
    {
        return itsSelectedDestinationId;
    }

    @Override
    public void setItsSelectedDestinationId(String itsSelectedDestinationId)
    {
        this.itsSelectedDestinationId = itsSelectedDestinationId;
    }

    @Override
    public Long getItsSelectedFinancingSourceId()
    {
        return itsSelectedFinancingSourceId;
    }

    @Override
    public void setItsSelectedFinancingSourceId(Long itsSelectedFinancingSourceId)
    {
        this.itsSelectedFinancingSourceId = itsSelectedFinancingSourceId;
    }

    @Override
    public Long getItsSelectedObjectExpense()
    {
        return itsSelectedObjectExpense;
    }

    @Override
    public void setItsSelectedObjectExpense(Long itsSelectedObjectExpense)
    {
        this.itsSelectedObjectExpense = itsSelectedObjectExpense;
    }

    @Override
    public boolean isIsAditionalBudgeting()
    {
        return isAditionalBudgeting;
    }

    @Override
    public void setIsAditionalBudgeting(boolean isAditionalBudgeting)
    {
        this.isAditionalBudgeting = isAditionalBudgeting;
    }

    @Override
    public List<DestinyObjectExpenseRUBUEntity> getItsListOfDestinations()
    {
        return itsListOfDestinations;
    }

    @Override
    public void setItsListOfDestinations(List<DestinyObjectExpenseRUBUEntity> itsListOfDestinations)
    {
        this.itsListOfDestinations = itsListOfDestinations;
    }

    @Override
    public List<FinancingSourceEntity> getItsListOfFinancingSources()
    {
        return itsListOfFinancingSources;
    }

    @Override
    public void setItsListOfFinancingSources(List<FinancingSourceEntity> itsListOfFinancingSources)
    {
        this.itsListOfFinancingSources = itsListOfFinancingSources;
    }

    @Override
    public List<FinancingSourceDto> getItsListOfFinancingSourceDto()
    {
        return itsListOfFinancingSourceDto;
    }

    @Override
    public void setItsListOfFinancingSourceDto(List<FinancingSourceDto> itsListOfFinancingSourceDto)
    {
        this.itsListOfFinancingSourceDto = itsListOfFinancingSourceDto;
    }

    @Override
    public Map<String, String> getItsMonthlyAmounts()
    {
        return itsMonthlyAmounts;
    }

    @Override
    public void setMonthlyAmounts(Map<String, String> amonthlyAmounts)
    {
        this.itsMonthlyAmounts = amonthlyAmounts;
    }

    /**
     * Este Metodo nos guarda la clave Presupuestal, Manda a llamar el
     * BudgetinUIHelper para que realize toda la operación
     */
    @Override
    public void saveBudgetingKey()
    {
        try
        {
            if (!itsModifyingABudgetKey)
            {

                if (isAditionalBudgeting)
                {

                    if (budgetValitationFail())
                    {
                        return;
                    }



                    DependenceEntity myDepEntity = new DependenceEntity();
                    myDepEntity.setDependenceId(itsDependenceId);

                    ObjectExpenseEntity myObjExpen = new ObjectExpenseEntity();
                    myObjExpen.setObjectExpenseId(itsSelectedObjectExpense);

                    BudgetingUIHelper.saveBudgetingKey(this);

                    addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                            this.getMessage("ppp.progr.succesSave"),
                            this.getMessage("ppp.progr.succesSave"));



                } else
                {
                    if (budgetValitationFail())
                    {
                        return;
                    }

                    if (myResultCeilingBudget == null || myResultCeilingBudget <= 0)
                    {
                        addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                                this.getMessage("ppp.budgeting.ThereIsNoBudgetCeiling"),
                                this.getMessage("ppp.budgeting.ThereIsNoBudgetCeiling"));

                    }

                    DependenceEntity myDepEntity = new DependenceEntity();
                    myDepEntity.setDependenceId(itsDependenceId);

                    ObjectExpenseEntity myObjExpen = new ObjectExpenseEntity();
                    myObjExpen.setObjectExpenseId(itsSelectedObjectExpense);
                    List<DestinyObjectExpenseRUBUEntity> myEntity = theirBudgetingManagement.getTheBudgetingDestinationByObject(myDepEntity, myObjExpen);
                    boolean myValidation = false;
                    if (myEntity.size() > 0)
                    {

                        if (myEntity.get(0).isDestinyObjectEpenseRUBUBasic())
                        {
                            if ((myAvailableREsult + myResultCeilingBudget) > Long.valueOf(FormatNumber.removeFormat(itsSummatoryCantity)))
                            {
                                myValidation = true;
                            } else
                            {
                                myValidation = false;
                            }

                        } else
                        {

                            if (myAvailableREsult >= Long.valueOf(FormatNumber.removeFormat(itsSummatoryCantity)))
                            {
                                myValidation = true;
                            } else
                            {
                                myValidation = false;
                            }

                        }

                    }

                    if (myValidation)
                    {
                        BudgetingUIHelper.saveBudgetingKey(this);

                        addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                                this.getMessage("ppp.progr.succesSave"),
                                this.getMessage("ppp.progr.succesSave"));
                    } else
                    {
                        addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                                this.getMessage("ppp.budgeting.ThereIsNoEnoughtAvailable"),
                                this.getMessage("ppp.budgeting.ThereIsNoEnoughtAvailable"));
                        return;
                    }


                    for (ObjectExpenseEntity elementObjectExpense : itsListOfFrammingObjectExpense)
                    {

                        if (elementObjectExpense.getObjectExpenseId().longValue() == this.itsSelectedObjectExpense.longValue())
                        {
                            objectExpenseSelected = elementObjectExpense;
                            break;
                        }

                    }
                }
            } else
            {
                if (budgetValitationFail())
                {
                    return;
                }


                BudgetingUIHelper.persistABudgetKey(this);

                itsModifyingABudgetKey = false;
                addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                        this.getMessage("ppp.progr.succesSave"),
                        this.getMessage("ppp.progr.succesSave"));
            }
            this.itsBudgetingKeysModel = new BudgetingKeysDtoDataModel(getMyListOfDtos(itsProgrammaticKey));
            itsSummatoryCantity = "0";
            itsMonthlyAmounts.clear();
            initBudgeting();

        } catch (Exception exc)
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));
            exc.printStackTrace(System.out);
        }

    }

    private boolean budgetValitationFail()
    {

       boolean resultValidation = false;

        if (itsSelectedDestinationId.equals("-1"))
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                    this.getMessage("ppp.budgeting.error.Destination"),
                    this.getMessage("ppp.budgeting.error.Destination"));
            return true;
        }
        MonthsEnum months[] = MonthsEnum.values();
        long dataCalendar = 0;
        Object dataLong = null;
        for (MonthsEnum month : months)
        {
            dataLong = FormatNumber.removeFormat(itsMonthlyAmounts.get(month.name()));

            if (dataLong instanceof String && dataLong != null
                    && ((String) dataLong).trim().length() != 0)
            {
                dataCalendar += new BigDecimal((String) dataLong).longValue();
            } else if (dataLong instanceof BigDecimal)
            {
                dataCalendar += ((BigDecimal) dataLong).longValue();
            }

        }



        if (isAditionalBudgeting)
        {
            if (itsSelectedObjectExpense == 0)
            {
                addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                        this.getMessage("ppp.notObjectExpense.Error"),
                        this.getMessage("ppp.notObjectExpense.Error"));
                return true;

            }
            if (itsMyJustification == null || itsMyJustification.isEmpty())
            {
                addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                        this.getMessage("ppp.notJustification.Error"),
                        this.getMessage("ppp.notJustification.Error"));
                return true;
            }
        } else
        {


            if (itsMyCeilingAvailableToShow == null || itsMyCeilingAvailableToShow.isEmpty())
            {

                addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                        this.getMessage("ppp.notAvailable.Error"),
                        this.getMessage("ppp.notAvailable.Error"));
                return true;

            } else
            {
                try
                {
                    Double.parseDouble(FormatNumber.removeFormat(itsMyCeilingAvailableToShow));
                } catch (Exception err)
                {
                    err.printStackTrace(System.out);
                    addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                            this.getMessage("ppp.notAvailable.Error"),
                            this.getMessage("ppp.notAvailable.Error"));
                    return true;
                }
            }

            if (dataCalendar != Long.valueOf((FormatNumber.removeFormat(itsSummatoryCantity))))
            {
                addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                        this.getMessage("ppp.calendarTopptoAnual.Error"),
                        this.getMessage("ppp.calendarTopptoAnual.Error"));
                return true;
            }


            if (myAvailableREsult == null)
            {
                addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                        this.getMessage("ppp.budgeting.ThereIsNoBudgetCeiling"),
                        this.getMessage("ppp.budgeting.ThereIsNoBudgetCeiling"));
                return true;
            } else
            {
                if (itsModifyingABudgetKey)
                {
                    Long myDiference = Long.valueOf((FormatNumber.removeFormat(itsSummatoryCantity))) - itsModifyngCantity;
                    if (myDiference > 0)
                    {
                        myBasicDiference = myBasicDiference < 0 ? 0 :myBasicDiference;
                                                
                        if (myAvailableREsult + myBasicDiference < myDiference)
                        {
                            addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                                    this.getMessage("error.budgetamout"),
                                    this.getMessage("error.budgetamout"));
                            return resultValidation = true;
                        }
                    }
                } else
                {
                    if (myAvailableREsult <= 0)
                    {
                        addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                                this.getMessage("ppp.budgeting.ThereIsNoBudgetCeiling"),
                                this.getMessage("ppp.budgeting.ThereIsNoBudgetCeiling"));
                        return resultValidation = true;
                    }
                }
            }
        }
        return resultValidation;
    }

    @Override
    public void totalOfSummatory()
    {
        MonthsEnum[] months = MonthsEnum.values();
        Long ItsmySummatory = 0L;
        itsSummatoryCantity = "0";
        for (MonthsEnum month : months)
        {
            String initValue = String.valueOf(FormatNumber.removeFormat(itsMonthlyAmounts.get(month.name())));
            if (initValue.equals("null"))
            {
                initValue = "0";
            }
            Double myMonth = Double.parseDouble(initValue);
            ItsmySummatory = ItsmySummatory + myMonth.longValue();
        }
        itsSummatoryCantity = numberValidator.format(ItsmySummatory);
    }

    /**
     * Este metodo sirve para igualar los meses segun lo que se haya puesto en
     * enero
     *
     */
    @Override
    public void evenMonths()
    {
        MonthsEnum[] months = MonthsEnum.values();
        String initValue = String.valueOf(FormatNumber.removeFormat(itsMonthlyAmounts.get("ENE")));
        itsSummatoryCantity = numberValidator.format((Long.parseLong(initValue) * 12));
        for (MonthsEnum month : months)
        {
            itsMonthlyAmounts.put(month.name(), numberValidator.format(Double.valueOf(initValue)));
        }
    }

    /**
     * Este Metodo es el que se encarga de hacer la division entre los 12 meses
     * del annio
     */
    @Override
    public void anualMonths()
    {
        DecimalFormat df = new DecimalFormat("#.##");

        String summatory = String.valueOf(Long.valueOf(FormatNumber.removeFormat(itsSummatoryCantity)) / 12);
        MonthsEnum[] months = MonthsEnum.values();
        long mySumatoryControl = Long.parseLong(summatory);
        mySumatoryControl = mySumatoryControl * 12;
        long myDiference = Long.valueOf(FormatNumber.removeFormat(itsSummatoryCantity)) - mySumatoryControl;
        mySumatoryControl = (Long.parseLong(summatory)) + myDiference;
        String mySummatoryWithDiference = numberValidator.format(mySumatoryControl);
        for (MonthsEnum month : months)
        {
            if (month.name().equals("ENE"))
            {
                itsMonthlyAmounts.put(month.name(), mySummatoryWithDiference);
            } else
            {
                itsMonthlyAmounts.put(month.name(), numberValidator.format(Long.valueOf(summatory)));
            }
        }

    }

    @Override
    public Long getItsBudgetingKeyId()
    {
        return itsBudgetingKeyId;
    }

    @Override
    public void setItsBudgetingKeyId(Long itsBudgetingKeyId)
    {
        this.itsBudgetingKeyId = itsBudgetingKeyId;
    }

    @Override
    public BudgetingManagement getTheirBudgetingManagement()
    {
        return theirBudgetingManagement;
    }

    @Override
    public void setTheirBudgetingManagement(BudgetingManagement theirBudgetingManagement)
    {
        this.theirBudgetingManagement = theirBudgetingManagement;
    }

    @Override
    public BudgetingSummaryController getTheirbudgetingSummaryController()
    {
        return theirbudgetingSummaryController;
    }

    @Override
    public void setTheirbudgetingSummaryController(BudgetingSummaryController theirbudgetingSummaryController)
    {
        this.theirbudgetingSummaryController = theirbudgetingSummaryController;
    }

    @Override
    public Long getItsDraftProjectId()
    {
        return itsDraftProjectId;
    }

    @Override
    public void setItsDraftProjectId(Long itsDraftProjectId)
    {
        this.itsDraftProjectId = itsDraftProjectId;
    }

    @Override
    public BudgetKeyConfigurationManagement getTheirbudgetKeyConfigurationManagement()
    {
        return theirbudgetKeyConfigurationManagement;
    }

    @Override
    public void setTheirbudgetKeyConfigurationManagement(BudgetKeyConfigurationManagement theirbudgetKeyConfigurationManagement)
    {
        this.theirbudgetKeyConfigurationManagement = theirbudgetKeyConfigurationManagement;
    }

    @Override
    public BudgetingKeysDtoDataModel getItsBudgetingKeysModel()
    {
        return itsBudgetingKeysModel;
    }

    @Override
    public void setItsBudgetingKeysModel(BudgetingKeysDtoDataModel itsBudgetingKeysModel)
    {
        this.itsBudgetingKeysModel = itsBudgetingKeysModel;
    }

    @Override
    public List<BudgetingKeysDto> getItsListOfBudgetingKeys()
    {
        return itsListOfBudgetingKeys;
    }

    @Override
    public void setItsListOfBudgetingKeys(List<BudgetingKeysDto> itsListOfBudgetingKeys)
    {
        this.itsListOfBudgetingKeys = itsListOfBudgetingKeys;
    }

    @Override
    public BudgetingKeysDto getItsSelectedBudgetKeyRow()
    {
        return itsSelectedBudgetKeyRow;
    }

    @Override
    public void setItsSelectedBudgetKeyRow(BudgetingKeysDto itsSelectedBudgetKeyRow)
    {
        this.itsSelectedBudgetKeyRow = itsSelectedBudgetKeyRow;
    }

    /**
     * Este metodo nos sirve para modificar una clave presupuestal que se haya
     * capturado
     */
    @Override
    public void modifyBudgetingKey()
    {
        try
        {
            itsMonthlyAmounts.clear();
            itsMyBudgetKeyToModify = theirBudgetingManagement.getBudgetEntityById(itsSelectedBudgetKeyRow.getItsBudgetingKeyId());
            for (MensualBudgetKeyEntity month : itsMyBudgetKeyToModify.getMensualBudgetKeyBudgetKey())
            {
                itsMonthlyAmounts.put(month.getMonth(),
                        numberValidator.format(month.getOriginalAmount().longValue()));
            }

            itsSummatoryCantity = numberValidator.format(itsMyBudgetKeyToModify.getOriginalAmount());
            itsModifyngCantity = itsMyBudgetKeyToModify.getOriginalAmount().longValue();
            if (!(itsMyBudgetKeyToModify.getFinancingSourceBudgetKey() == null))
            {
                itsSelectedFinancingSourceId = itsMyBudgetKeyToModify.getFinancingSourceBudgetKey().getFinancingSourceId();
            }
            itsSelectedObjectExpense = itsMyBudgetKeyToModify.getObjectExpenseBudgetKey().getObjectExpenseId();
            chargeOfCombos();
            int init = itsMyBudgetKeyToModify.getBudgetKeyBProgramaticKey().length() - 2;
            int end = itsMyBudgetKeyToModify.getBudgetKeyBProgramaticKey().length();
            String myKey = itsMyBudgetKeyToModify.getBudgetKeyBProgramaticKey().substring(init, end);
            itsSelectedDestinationId = myKey;
            budgetingCeilingAmount();

            itsSummatoryAdititionalCantity = itsMyBudgetKeyToModify.getAdditionalAmount().longValue();
            setItsSummatoryAdititionalQuantityAux(numberValidator.format(itsSummatoryAdititionalCantity));
            itsMyJustification = itsMyBudgetKeyToModify.getAdditionalJustification();
//            
            itsModifyingABudgetKey = true;

        } catch (Exception exc)
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));
        }
    }

    /**
     * Este metodo nos sirve para borrar una clave presupuestal
     */
    @Override
    public void deleteBudgetingKey()
    {
        try
        {
            BudgetDispURDto dtoToDelete = new BudgetDispURDto();
            BudgetKeyEntity myBudgetToDelete = new BudgetKeyEntity();
            myBudgetToDelete.setBudgetKeyId(itsSelectedBudgetKeyRow.getItsBudgetingKeyId());
            DraftProjectEntity myDraftProject =
                    theirDraftProjectManagement.getDraftProjectByIdWithBudgets(itsDraftProjectId);

            BudgetKeyAndDraftProjectFramingEntity myEntityToFraming =
                    theirBudgetingManagement.getABudgetAndProjectFramingByIds(itsDraftProjectId, myBudgetToDelete.getBudgetKeyId());

            myDraftProject.getDraftProjectBudgetDetail().remove(myEntityToFraming);
            ProgrammingEntity myProgram = myDraftProject.getDraftProjectProgramming();

            theirDraftProjectManagement.saveDraftProject(myDraftProject,
                    myProgram.getProgrammingObjective().getObjectiveId());


           
            dtoToDelete.setMyEntityToFraming(myEntityToFraming);
            dtoToDelete.setMyBudget(myBudgetToDelete);
            theirBudgetingManagement.deleteBudgetProcess(dtoToDelete);
            

            this.itsBudgetingKeysModel = new BudgetingKeysDtoDataModel(getMyListOfDtos(itsProgrammaticKey));
            initBudgeting();

            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                    this.getMessage("ppp.budgeting.keyDelete"),
                    this.getMessage("ppp.budgeting.keyDelete"));

        } catch (Exception exc)
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));
        }
    }

    @Override
    public boolean isItsModifyingABudgetKey()
    {
        return itsModifyingABudgetKey;
    }

    @Override
    public void setItsModifyingABudgetKey(boolean itsModifyingABudgetKey)
    {
        this.itsModifyingABudgetKey = itsModifyingABudgetKey;
    }

    @Override
    public BudgetKeyEntity getItsMyBudgetKeyToModify()
    {
        return itsMyBudgetKeyToModify;
    }

    @Override
    public void setItsMyBudgetKeyToModify(BudgetKeyEntity itsMyBudgetKeyToModify)
    {
        this.itsMyBudgetKeyToModify = itsMyBudgetKeyToModify;
    }

    @Override
    public String getItsProgrammaticKey()
    {
        return itsProgrammaticKey;
    }

    @Override
    public void setItsProgrammaticKey(String itsProgrammaticKey)
    {
        this.itsProgrammaticKey = itsProgrammaticKey;
    }

    @Override
    public Long getItsMyObjectExpenseId()
    {
        return itsMyObjectExpenseId;
    }

    @Override
    public void setItsMyObjectExpenseId(Long itsMyObjectExpenseId)
    {
        this.itsMyObjectExpenseId = itsMyObjectExpenseId;
    }

    @Override
    public String getItsMyJustification()
    {
        return itsMyJustification;
    }

    @Override
    public void setItsMyJustification(String itsMyJustification)
    {
        this.itsMyJustification = itsMyJustification;
    }

    @Override
    public String getItsTheMessageBox()
    {
        return itsTheMessageBox;
    }

    @Override
    public void setItsTheMessageBox(String itsTheMessageBox)
    {
        this.itsTheMessageBox = itsTheMessageBox;
    }

    /**
     * Este metodo prepara el Confirm Dialog para la ejecución de la
     * Activación/Desactivación del CheckBoolean de Presupuesto Adicional
     */
    @Override
    public void prepareTheMessage()
    {
        if (isAditionalBudgeting)
        {
            this.setItsTheMessageBox(
                    this.getMessage("ppp.budgeting.Justification.Message.FalseToTrue"));
            itsJustificationTextBoxReadOnly = false;
        } else
        {
            this.setItsTheMessageBox(this.getMessage("ppp.budgeting.Justification.Message.TrueToFalse"));
            itsJustificationTextBoxReadOnly = true;
            itsMyJustification = "";
        }
    }

    /**
     * Este metodo selecciona la accion correspondiente, segun el valor de la
     * variable
     */
    @Override
    public void selectActionToDo()
    {
        if (!isAditionalBudgeting)
        {
            initBudgeting();
        }

    }

    @Override
    public void disableAditional()
    {
        setIsAditionalBudgeting(!isAditionalBudgeting);
    }

    @Override
    public boolean isItsJustificationTextBoxReadOnly()
    {
        return itsJustificationTextBoxReadOnly;
    }

    @Override
    public void setItsJustificationTextBoxReadOnly(boolean aJustificationTextBoxReadOnly)
    {
        this.itsJustificationTextBoxReadOnly = aJustificationTextBoxReadOnly;
    }

    @Override
    public boolean getBtnSrvSave()
    {
        return btnSrvSave;
    }

    @Override
    public void setBtnSrvSave(boolean itsBtnSrvSave)
    {
        this.btnSrvSave = itsBtnSrvSave;
    }

    @Override
    public void budgetingCeilingAmountBasic()
    {
    }

    @Override
    public void budgetingCeilingAmount()
    {
        if (itsSelectedDestinationId.equals("-1"))
        {
            this.setItsMyBasicCeiling("0");
            this.setItsMyBasicCeilingUsed("0");
            this.setItsMyBasicAvailable("0");
            this.setItsMyCeilingToshow("0");
            this.setItsMyCeilingAvailableToShow("0");
            addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                    this.getMessage("ppp.Destination.Error"),
                    this.getMessage("ppp.Destination.Error"));
            return;
        }
        try
        {
            DependenceEntity myDepEntity = new DependenceEntity();
            myDepEntity.setDependenceId(itsDependenceId);

            ObjectExpenseEntity myObjExpen = new ObjectExpenseEntity();
            myObjExpen.setObjectExpenseId(itsSelectedObjectExpense);
            FinancingSourceEntity myFinanceSources = new FinancingSourceEntity();
            myFinanceSources.setFinancingSourceId(itsSelectedFinancingSourceId);

            myResultCeilingBudget = theirCeilingBudgetManagement.getBudgetForDraftProject(myDepEntity, myObjExpen, itsSelectedDestinationId, myFinanceSources);
            Long myCeiling = theirCeilingBudgetManagement.getBudgetForUR(myDepEntity, myObjExpen, itsSelectedDestinationId, myFinanceSources);
            if (myCeiling == 0)
            {
                throw new Exception("No se cuenta con techos cargados para la UR");
            }
            if (myResultCeilingBudget == null)
            {
                myResultCeilingBudget = (long) 0;
            }
            myAvailableCeilingBudget =
                    theirCeilingBudgetManagement.getBudgetAvailableForDraftProject(myDepEntity, myObjExpen, itsSelectedDestinationId, myFinanceSources, false);
            myAvailableREsult = myCeiling - myAvailableCeilingBudget;


            this.setItsMyBasicCeiling("0");
            this.setItsMyBasicCeilingUsed("0");
            this.setItsMyBasicAvailable("0");
            this.setItsMyCeilingToshow("0");
            this.setItsMyCeilingAvailableToShow("0");

            List<DestinyObjectExpenseRUBUEntity> myEntity =
                    theirBudgetingManagement.getTheBudgetingDestinationByObject(myDepEntity, myObjExpen);

            if (myEntity.size() > 0)
            {

                if (myEntity.get(0).isDestinyObjectEpenseRUBUBasic())
                {
                    myCeilingBasic = theirCeilingBudgetManagement.getBudgetAvailableForDraftProject(myDepEntity, myObjExpen, itsSelectedDestinationId, myFinanceSources, true);
                    this.setItsMyBasicCeiling(numberValidator.format(myResultCeilingBudget));
                    this.setItsMyBasicCeilingUsed(numberValidator.format(myCeilingBasic));
                    myBasicDiference = myResultCeilingBudget - myCeilingBasic;
                    if (myBasicDiference > 0)
                    {
                        this.setItsMyBasicAvailable(numberValidator.format(myBasicDiference));
                    } else
                    {
                        this.setItsMyBasicAvailable(numberValidator.format(0));
                    }
                    this.setItsMyCeilingAvailableToShow(numberValidator.format(myAvailableREsult));
                } else
                {
                    this.setItsMyCeilingToshow(numberValidator.format(myResultCeilingBudget));
                    this.setItsMyCeilingAvailableToShow(numberValidator.format(myAvailableREsult));
                }

            }
        } catch (Exception exc)
        {

            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));
        }
    }

    @Override
    public void financingCeilingAmount()
    {
        try
        {
            FinancingSourceEntity myFinanceSources = new FinancingSourceEntity();
            myFinanceSources.setFinancingSourceId(itsSelectedFinancingSourceId);

            myResultCeiling = theirCeilingBudgetManagement.getFinancingSourceCeiling(myFinanceSources);
            if (myResultCeiling == null)
            {
                myResultCeiling = (long) 0;
            }
            itsMyFinCeilingToShow = numberValidator.format(myResultCeiling);
            Long myAvailable = theirCeilingBudgetManagement.getFinancingSourceCeilingAvailable(myFinanceSources);
            if (myAvailable == null)
            {
                myAvailable = (long) 0;
            }
            itsMyAvailableToMakeOp = myResultCeiling - myAvailable;
            itsMyFinAvailableToShow = numberValidator.format(itsMyAvailableToMakeOp);
        } catch (Exception exc)
        {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));
        }

    }

    @Override
    public Long getItsMyAvailableToMakeOp()
    {
        return itsMyAvailableToMakeOp;
    }

    @Override
    public void setItsMyAvailableToMakeOp(Long itsMyAvailableToMakeOp)
    {
        this.itsMyAvailableToMakeOp = itsMyAvailableToMakeOp;
    }

    @Override
    public String getItsMyFinAvailableToShow()
    {
        return itsMyFinAvailableToShow;
    }

    @Override
    public void setItsMyFinAvailableToShow(String itsMyFinAvailableToShow)
    {
        this.itsMyFinAvailableToShow = FormatNumber.removeFormat(itsMyFinAvailableToShow);
    }

    @Override
    public String getItsMyFinCeilingToShow()
    {
        return itsMyFinCeilingToShow;
    }

    @Override
    public void setItsMyFinCeilingToShow(String itsMyFinCeilingToShow)
    {
        this.itsMyFinCeilingToShow = FormatNumber.removeFormat(itsMyFinCeilingToShow);
    }

    @Override
    public String getItsMyCeilingToshow()
    {
        return FormatNumber.formatNumber(itsMyCeilingToshow);
    }

    @Override
    public void setItsMyCeilingToshow(String itsMyCeilingToshow)
    {
        this.itsMyCeilingToshow = itsMyCeilingToshow;
    }

    @Override
    public DraftProjectManagement getTheirDraftProjectManagement()
    {
        return theirDraftProjectManagement;
    }

    @Override
    public void setTheirDraftProjectManagement(DraftProjectManagement theirDraftProjectManagement)
    {
        this.theirDraftProjectManagement = theirDraftProjectManagement;
    }

    @Override
    public BudgetKeyDefinitionManagement getTheirBudgetKeyDefinitionManagement()
    {
        return theirBudgetKeyDefinitionManagement;
    }

    @Override
    public void setTheirBudgetKeyDefinitionManagement(BudgetKeyDefinitionManagement theirBudgetKeyDefinitionManagement)
    {
        this.theirBudgetKeyDefinitionManagement = theirBudgetKeyDefinitionManagement;
    }

    @Override
    public CeilingBudgetManagement getTheirCeilingBudgetManagement()
    {
        return theirCeilingBudgetManagement;
    }

    @Override
    public void setTheirCeilingBudgetManagement(CeilingBudgetManagement theirCeilingBudgetManagement)
    {
        this.theirCeilingBudgetManagement = theirCeilingBudgetManagement;
    }

    @Override
    public String getItsMyCeilingAvailableToShow()
    {
        return itsMyCeilingAvailableToShow;
    }

    @Override
    public void setItsMyCeilingAvailableToShow(String itsMyCeilingAvailableToShow)
    {
        this.itsMyCeilingAvailableToShow = itsMyCeilingAvailableToShow;
    }

    @Override
    public String getItsMyBasicAvailable()
    {
        return FormatNumber.formatNumber(itsMyBasicAvailable);
    }

    @Override
    public void setItsMyBasicAvailable(String itsMyBasicAvailable)
    {
        this.itsMyBasicAvailable = itsMyBasicAvailable;
    }

    @Override
    public String getItsMyBasicCeiling()
    {
        return FormatNumber.formatNumber(itsMyBasicCeiling);
    }

    @Override
    public void setItsMyBasicCeiling(String itsMyBasicCeiling)
    {
        this.itsMyBasicCeiling = itsMyBasicCeiling;
    }

    @Override
    public String getItsMyBasicCeilingUsed()
    {
        return FormatNumber.formatNumber(itsMyBasicCeilingUsed);
    }

    @Override
    public void setItsMyBasicCeilingUsed(String itsMyBasicCeilingUsed)
    {
        this.itsMyBasicCeilingUsed = itsMyBasicCeilingUsed;
    }

    @Override
    public List<ObjectExpenseEntity> getItsListOfFrammingObjectExpense()
    {
        return itsListOfFrammingObjectExpense;
    }

    @Override
    public void setItsListOfFrammingObjectExpense(List<ObjectExpenseEntity> itsListOfFrammingObjectExpense)
    {
        this.itsListOfFrammingObjectExpense = itsListOfFrammingObjectExpense;
    }

    @Override
    public Long getMyResultCeilingBudget()
    {
        return myResultCeilingBudget;
    }

    @Override
    public void setMyResultCeilingBudget(Long myResultCeilingBudget)
    {
        this.myResultCeilingBudget = myResultCeilingBudget;
    }

    @Override
    public String getMySumTotalStringFormat()
    {
        return mySumTotalStringFormat;
    }

    @Override
    public void setMySumTotalStringFormat(String mySumTotalStringFormat)
    {
        this.mySumTotalStringFormat = mySumTotalStringFormat;
    }

    @Override
    public Long getitsTotalByChapter()
    {
        return itsTotalPerChapter;
    }

    @Override
    public void setitsTotalByChapter(Long myTotalByChapter)
    {
        this.itsTotalPerChapter = myTotalByChapter;
    }

    @Override
    public String getItsTotalPerChapterString()
    {
        return FormatNumber.formatNumber(this.itsTotalPerChapter.toString());
    }

    @Override
    public void setItsTotalPerChapterString(String itsTotalPerChapterString)
    {
        this.itsTotalPerChapterString = itsTotalPerChapterString;
    }

    @Override
    public Long getMyBasicDiference()
    {
        return myBasicDiference;
    }

    @Override
    public void setMyBasicDiference(Long myBasicDiference)
    {
        this.myBasicDiference = myBasicDiference;
    }

    @Override
    public Long getMyAvailableREsult()
    {
        return myAvailableREsult;
    }

    @Override
    public void setMyAvailableREsult(Long myAvailableREsult)
    {
        this.myAvailableREsult = myAvailableREsult;
    }

    @Override
    public String getItsBudgetKeyToModify()
    {
        return itsBudgetKeyToModify;
    }

    @Override
    public void setItsBudgetKeyToModify(String itsBudgetKeyToModify)
    {
        this.itsBudgetKeyToModify = itsBudgetKeyToModify;
    }

    @Override
    public DepencenceDto getItsUrDependence()
    {
        return itsUrDependence;
    }

    @Override
    public void setItsUrDependence(DepencenceDto itsUrDependence)
    {
        this.itsUrDependence = itsUrDependence;
    }

    @Override
    public ObjectExpenseEntity getObjectExpenseSelected()
    {
        return objectExpenseSelected;
    }

    @Override
    public void setObjectExpenseSelected(ObjectExpenseEntity objectExpenseSelected)
    {
        this.objectExpenseSelected = objectExpenseSelected;
    }

    @Override
    public void setItsSummatoryAdititionalQuantityAux(String input)
    {
        this.itsSummatoryAdititionalQuantityAux = input;
        setItsSummatoryAdititionalCantity(Long.valueOf(FormatNumber.removeFormat(input)));
    }

    @Override
    public String getItsSummatoryAdititionalQuantityAux()
    {
        return itsSummatoryAdititionalQuantityAux;
    }
}
