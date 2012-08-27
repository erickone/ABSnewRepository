package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.context.KeyContextEnum;
import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.budget.dto.BudgetSummaryDto;
import com.abs.siif.budget.dto.BudgetingDependenceTotalDto;
import com.abs.siif.budget.dto.BudgetingSummaryDto;
import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.budget.management.BudgetKeyConfigurationManagement;
import com.abs.siif.planning.comparators.DependenceDtoKeyComparator;
import com.abs.siif.planning.dto.DepencenceDto;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.ppp.programming.api.controller.BudgetingSummaryControllerApi;
import com.abs.siif.ppp.programming.api.controller.DraftProjectHeaderControllerApi;
import com.abs.siif.ppp.programming.uihelpers.BudgetingSummaryDtoDataModel;
import com.abs.siif.programming.dto.UrlConnectionReportDTO;
import com.abs.siif.programming.management.BudgetingSummaryManagement;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Erick Leija Este metodo es el que se encargada de la administración
 * de la pestaña de Resumen Presupuestal
 */
@Scope("session")
@Controller("budgetingSummaryController")
public class BudgetingSummaryController
        extends SIIFControllerBase
        implements Serializable, BudgetingSummaryControllerApi {
    //Aqui se resliza la inyeccion del servicio para el manejo de los componentes

    @Resource(name = "budgetingSummaryManagement")
    private transient BudgetingSummaryManagement theirBudgetingSummaryManagement;
    @Resource(name = "draftProjectHeaderController")
    private transient DraftProjectHeaderControllerApi theirdraftProjectHeaderController;
    @Resource(name = "budgetKeyConfigurationManagement")
    private transient BudgetKeyConfigurationManagement theirbudgetKeyConfigurationManagement;
    private List<DepencenceDto> myListOfUEG;
//    private List<ComponentEntity> myListOfComponents;
    private List<ObjectExpenseEntity> myListOfObjectExpense;
    private List<BudgetingSummaryDto> myrealList;
    private List<BudgetingSummaryDto> myRootList;
    private List<BudgetingSummaryDto> myComboOfObjectExpense;
    private List<BudgetingDependenceTotalDto> myListOfBreakDownDependences;
    BudgetingSummaryDtoDataModel theirBudgetingSummaryDtoDataModel;
    private BudgetingSummaryDto selectedRow;
    private boolean itsOneClick = false;
    private boolean itsDoubleClick = false;
    private boolean itsMegaSummaryCheck = false;
    private boolean itsComboDependenceCheck = false;
    private Long myDependenceId;
    private Long myObjectExpenseId;
    private Long myDraftProjectId;
    private Long myAditionalTotal;
    private Long myInitialTotal;
    private Long mySumTotal;
    
    //Estas Variables son para mostrar los totales del resumen de las dependencias
    private String myadditionalTotalDependenceStringFormat;
    private String myInitialTotalDependenceStringFormat;
    private String mySumTotalDependenceStringformat;
    //****************************************************************************
    private String myAditionalTotalStringFormat;
    private String myInitialTotalStringFormat;
    private String mySumTotalStringFormat;
    private String myOperationalExpenseTotalInit;
    private String myOperationalExpenseTotalAditional;
    private String myOperationalExpenseTotalSum;
    private String myInvestExpenseTotalInit;
    private String myInvestExpenseTotalAditional;
    private String myInvestExpenseTotalSum;
    private String myTotalByChapter;
    private String imprimirReporte;
    private Long itsSelectedDependenceId;
    public NumberFormat numberValidator = NumberFormat.getInstance(Locale.US);
    private boolean load = false;

    @Override
    public BudgetingSummaryDto getSelectedRow() {
        return selectedRow;
    }

    @Override
    public void setSelectedRow(BudgetingSummaryDto selectedRow) {
        this.selectedRow = selectedRow;
    }

    @Override
    public Long getMyDependenceId() {
        return myDependenceId;
    }

    @Override
    public void setMyDependenceId(Long myDependenceId) {
        this.myDependenceId = myDependenceId;
    }

    @Override
    public Long getMyObjectExpenseId() {
        return myObjectExpenseId;
    }

    @Override
    public void setMyObjectExpenseId(Long myObjectExpenseId) {
        this.myObjectExpenseId = myObjectExpenseId;
    }

    public BudgetingSummaryController() {
        //TODO: cuando se enlazen las pantallas se necesita realizar metodo init
        //que inicialize las variables de dependencia y
        this.myListOfBreakDownDependences = new ArrayList<BudgetingDependenceTotalDto>();
        this.myrealList = new ArrayList<BudgetingSummaryDto>();

        this.myListOfUEG = new ArrayList<DepencenceDto>();
        this.myRootList = new ArrayList<BudgetingSummaryDto>();


    }

    @Override
    public List<BudgetingSummaryDto> getMyrealList() {
        return myrealList;
    }

    @Override
    public BudgetingSummaryDtoDataModel getTheirBudgetingSummaryDtoDataModel() {
        return theirBudgetingSummaryDtoDataModel;
    }

    @Override
    public void setMyrealList(List<BudgetingSummaryDto> myrealList) {
        this.myrealList = myrealList;
    }

    @Override
    public List<ObjectExpenseEntity> getMyListOfObjectExpense() {
        return myListOfObjectExpense;
    }

    @Override
    public void setMyListOfObjectExpense(List<ObjectExpenseEntity> myListOfObjectExpense) {
        this.myListOfObjectExpense = myListOfObjectExpense;
    }

    @Override
    public List<DepencenceDto> getMyListOfUEG() {
        return myListOfUEG;
    }

    @Override
    public void setMyListOfUEG(List<DepencenceDto> myListOfUEG) {
        this.myListOfUEG = myListOfUEG;
    }

    /**
     * Este metodo es el encargado de inicializar la lista de los capitulos que
     * hay en la base de datos, asi como sus techos presupuestales
     */
    @Override
    public void initListOfComponents() {
        //Esta llamada al controller del project se activa cuando integremos esta
        //vista a un tab de la ventana del DraftProject

        selectedRow = new BudgetingSummaryDto();
        myDraftProjectId = theirdraftProjectHeaderController.getTheirCurrentDraftProjectId();
        myDependenceId =  theirdraftProjectHeaderController.getIdDependency();

        
        if (myDependenceId != 0)
        {
            DependenceEntity myDEp = new DependenceEntity();
            myDEp.setDependenceId(myDependenceId);
            myListOfUEG = theirBudgetingSummaryManagement.getTheRelatedUEGs(myDEp);
            Collections.sort(myListOfUEG, DependenceDtoKeyComparator.getInstance());
            //itsSelectedDependenceId = myDependenceId;
            loadTheURSummary();
        }
    }
        
    @Override
    public void loadTheURSummary()
    {
        myListOfBreakDownDependences.clear();
        myListOfBreakDownDependences = theirBudgetingSummaryManagement.
                getMyDependenceSummaryDTO(myListOfUEG, myDraftProjectId);
        Long myAdditional=0L;
        Long myInitital = 0L;
        Long mySummatory = 0L;
        
        for (BudgetingDependenceTotalDto myDTOtogetTotals : myListOfBreakDownDependences)
        {
            myInitital += Long.parseLong(myDTOtogetTotals.getItsDependenceInitialTotal().replace(",", ""));
            myAdditional += Long.parseLong(myDTOtogetTotals.getItsDependenceAdditionalTotal().replace(",", ""));
            mySummatory += Long.parseLong(myDTOtogetTotals.getItsDependenceSummatoryTotal().replace(",", ""));
        }
        myadditionalTotalDependenceStringFormat = numberValidator.format(myAdditional);
        myInitialTotalDependenceStringFormat = numberValidator.format(myInitital);
        mySumTotalDependenceStringformat = numberValidator.format(mySummatory);
        loadTheSummary();
    }
    
    public void loadBudgetMegaSummaryByUR()
    {
        if (itsMegaSummaryCheck)
        {
            itsComboDependenceCheck = true;
        }
        else
        {
            itsComboDependenceCheck = false;
            return;
        }
   
        myrealList = 
                theirBudgetingSummaryManagement.getTheMegaSummaryByUR
                (myListOfUEG, myDraftProjectId, 
                theirBudgetingSummaryManagement.getObjectExpenseRoots());
        myRootList = myrealList;
        myAditionalTotal=0L;
        myInitialTotal=0L;
        mySumTotal=0L;
        BudgetSummaryDto mySummaryInvest = new BudgetSummaryDto();
        for (BudgetingSummaryDto myInvest : myrealList)
        {
            myAditionalTotal += Long.parseLong(myInvest.getItsBudgetingSummaryAditional().replace(",", ""));
            myInitialTotal += Long.parseLong(myInvest.getItsBudgetingSummaryInitial().replace(",", ""));
            mySumTotal += Long.parseLong(myInvest.getItsBudgetingSummarySum().replace(",", ""));
            if (myInvest.getItsBudgetingSummaryChapter() == 6000)
            {
                mySummaryInvest.setAdditionalAmount(Double.parseDouble(myInvest.getItsBudgetingSummaryAditional().replace(",", "")));
                mySummaryInvest.setOriginalAmount(Double.parseDouble(myInvest.getItsBudgetingSummaryInitial().replace(",", "")));
                mySummaryInvest.setSum(Double.parseDouble(myInvest.getItsBudgetingSummarySum().replace(",", "")));
            }
        }
        myAditionalTotalStringFormat = numberValidator.format(myAditionalTotal);
        myInitialTotalStringFormat = numberValidator.format(myInitialTotal);
        mySumTotalStringFormat = numberValidator.format(mySumTotal);
        theirBudgetingSummaryDtoDataModel = new BudgetingSummaryDtoDataModel(myrealList);

        Long myInitInvest = 0L;
        Long myAdicInvest = 0L;
        Long mySumInvest = 0L;
        if (mySummaryInvest != null) {
            myInitInvest=mySummaryInvest.getOriginalAmount().longValue();
            myAdicInvest=mySummaryInvest.getAdditionalAmount().longValue();
            mySumInvest=myInitInvest + myAdicInvest;
        }
 
        myInvestExpenseTotalInit = numberValidator.format(myInitInvest);
        myInvestExpenseTotalAditional = numberValidator.format(myAdicInvest);
        myInvestExpenseTotalSum = numberValidator.format(mySumInvest);

        myOperationalExpenseTotalInit = numberValidator.format(myInitialTotal - myInitInvest);
        myOperationalExpenseTotalAditional = numberValidator.format(myAditionalTotal - myAdicInvest);
        myOperationalExpenseTotalSum = numberValidator.format(mySumTotal - mySumInvest);

        UrlConnectionReportDTO objUrlServer = (UrlConnectionReportDTO) SIIFContextBase.getParamContext(KeyContextEnum.URLSERVERREPORT);
        objUrlServer.setTipoFicha(this.getMessage("ppp.progr.presupPorFicha.report"));
        objUrlServer.setTodo(this.getMessage("ppp.progr.presupPorFicha.report.todoTrue"));
        objUrlServer.setIdObjecto(myDraftProjectId.toString());
        setImprimirReporte(objUrlServer.getUrlDocumento());
    }
    
    
    
    @Override
    public void loadTheSummary()
    {

        Map<String, Long> myMap = new HashMap<String, Long>();
        if (myListOfUEG.size() < 1) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                    this.getMessage("ppp.budgeting.noRelatedDep"),
                    this.getMessage("ppp.budgeting.noRelatedDep"));
            return;
        }
        myMap.put("iddependencia", itsSelectedDependenceId);
        myMap.put("idproyecto", theirdraftProjectHeaderController.getTheirCurrentDraftProjectId());
        String itsProgrammaticKey = theirbudgetKeyConfigurationManagement.getBudgetKey(myMap);

        myListOfObjectExpense = theirBudgetingSummaryManagement.getObjectExpenseRoots();
        myrealList = new ArrayList<BudgetingSummaryDto>();
        myAditionalTotal = (long) 0;
        myInitialTotal = (long) 0;
        mySumTotal = (long) 0;

        for (ObjectExpenseEntity myObjectEntity : myListOfObjectExpense) {
            String myKeyToFind = itsProgrammaticKey
                    + String.valueOf(myObjectEntity.getObjectExpenseKey() / 1000);

            BudgetSummaryDto mySummary = theirBudgetingSummaryManagement.getAllAmountsOfChapters(myKeyToFind, "f");

            myAditionalTotal += mySummary.getAdditionalAmount().longValue();
            myInitialTotal += mySummary.getOriginalAmount().longValue();
            Long mySum = mySummary.getOriginalAmount().longValue()
                    + mySummary.getAdditionalAmount().longValue();
            mySumTotal += mySum;

            BudgetingSummaryDto myDTO = new BudgetingSummaryDto(myObjectEntity.getObjectExpenseId(), myObjectEntity.getObjectExpenseDescription(),
                    myObjectEntity.getObjectExpenseKey(),
                    numberValidator.format(mySummary.getOriginalAmount()),
                    numberValidator.format(mySummary.getAdditionalAmount()),
                    numberValidator.format(mySummary.getSum()));
            myrealList.add(myDTO);
        }
        myRootList = myrealList;
        myAditionalTotalStringFormat = numberValidator.format(myAditionalTotal);
        myInitialTotalStringFormat = numberValidator.format(myInitialTotal);
        mySumTotalStringFormat = numberValidator.format(mySumTotal);
        theirBudgetingSummaryDtoDataModel = new BudgetingSummaryDtoDataModel(myrealList);

        BudgetSummaryDto mySummaryInvest = theirBudgetingSummaryManagement.getAllAmountsOfChapters(itsProgrammaticKey + "6", "f");
        Long myInitInvest = 0L;
        Long myAdicInvest = 0L;
        Long mySumInvest = 0L;
        if (mySummaryInvest != null) {
            myInitInvest = mySummaryInvest.getOriginalAmount().longValue();
            myAdicInvest = mySummaryInvest.getAdditionalAmount().longValue();
            mySumInvest = myInitInvest + myAdicInvest;
        }

        myInvestExpenseTotalInit = numberValidator.format(myInitInvest);
        myInvestExpenseTotalAditional = numberValidator.format(myAdicInvest);
        myInvestExpenseTotalSum = numberValidator.format(mySumInvest);

        myOperationalExpenseTotalInit = numberValidator.format(myInitialTotal - myInitInvest);
        myOperationalExpenseTotalAditional = numberValidator.format(myAditionalTotal - myAdicInvest);
        myOperationalExpenseTotalSum = numberValidator.format(mySumTotal - mySumInvest);

        UrlConnectionReportDTO objUrlServer = (UrlConnectionReportDTO) SIIFContextBase.getParamContext(KeyContextEnum.URLSERVERREPORT);
        objUrlServer.setTipoFicha(this.getMessage("ppp.progr.presupPorFicha.report"));
        objUrlServer.setTodo(this.getMessage("ppp.progr.presupPorFicha.report.todoTrue"));
        objUrlServer.setIdObjecto(myDraftProjectId.toString());
        setImprimirReporte(objUrlServer.getUrlDocumento());
        load = true;

    }

    private Long getLongOfMyString(String aStringToChange) {
        long myLongToChange;
        if (aStringToChange.compareTo("null") == 0) {
            myLongToChange = 0;
        } else {
            myLongToChange = (new Double(Double.parseDouble(aStringToChange))).longValue();
        }
        return myLongToChange;
    }

    /**
     * este metodo se ejecuta cuando se selecciona un registro de la tabla
     * entonces de manera magica por medio del id del objeto gasto nos traemos
     * sus parientes de ultimo nivel, así como su encuadre con los techos
     *
     * @param event
     */
    @Override
    public void onRowSelect(SelectEvent event) {
        BudgetingSummaryDto mySelectedDto = (BudgetingSummaryDto) event.getObject();
        myTotalByChapter = mySelectedDto.getItsBudgetingSummarySum();
        myObjectExpenseId = mySelectedDto.getItsBudgetingSummaryId();
        List<ObjectExpenseEntity> myObjectivesToCast = new ArrayList<ObjectExpenseEntity>(theirBudgetingSummaryManagement.getTheObjectExpenseHeirachy(mySelectedDto.getItsBudgetingSummaryId()));
        myrealList = new ArrayList<BudgetingSummaryDto>();
        Long myLevelId = theirBudgetingSummaryManagement.getTheLevelofTheSpecificParId();

        if (!(myObjectivesToCast == null || myObjectivesToCast.isEmpty())) {
            for (Object myObjectiveCast : myObjectivesToCast) {
                Object[] myObjectFields = (Object[]) myObjectiveCast;

                String myChapter = String.valueOf(myObjectFields[2]);
                int myChapterCast = Integer.parseInt(myChapter);
                Long myLeveldto = Long.parseLong(myObjectFields[4].toString());
                if (myLeveldto.equals(myLevelId)) {
                    BudgetingSummaryDto myDTO = new BudgetingSummaryDto(Long.parseLong(myObjectFields[0].toString()),
                            (myObjectFields[1].toString()), myChapterCast, "0", "0", "0");
                    myrealList.add(myDTO);
                }
            }
        }
        myComboOfObjectExpense = myrealList;

    }

    /**
     * este metodo se ejcuta cuando se realiza un doble click en el registro
     * nota: solo soportado a partir de la version 3.3 de primefaces
     *
     * @param event
     */
    @Override
    public void onRowDblselect(SelectEvent event) {
        if (itsDoubleClick) {
        }
    }

    @Override
    public Long getMyDraftProjectId() {
        return myDraftProjectId;
    }

    @Override
    public void setMyDraftProjectId(Long myDraftProjectId) {
        this.myDraftProjectId = myDraftProjectId;
    }

    @Override
    public Long getItsSelectedDependenceId() {
        return itsSelectedDependenceId;
    }

    @Override
    public void setItsSelectedDependenceId(Long itsSelectedDependenceId) {
        this.itsSelectedDependenceId = itsSelectedDependenceId;
    }

    public Long getMyAditionalTotal() {
        return myAditionalTotal;
    }

    public void setMyAditionalTotal(Long myAditionalTotal) {
        this.myAditionalTotal = myAditionalTotal;
    }

    public Long getMyInitialTotal() {
        return myInitialTotal;
    }

    public void setMyInitialTotal(Long myInitialTotal) {
        this.myInitialTotal = myInitialTotal;
    }

    public Long getMySumTotal() {
        return mySumTotal;
    }

    public void setMySumTotal(Long mySumTotal) {
        this.mySumTotal = mySumTotal;
    }

    public List<BudgetingSummaryDto> getMyComboOfObjectExpense() {
        return myComboOfObjectExpense;
    }

    public void setMyComboOfObjectExpense(List<BudgetingSummaryDto> myComboOfObjectExpense) {
        this.myComboOfObjectExpense = myComboOfObjectExpense;
    }

    public String getMyAditionalTotalStringFormat() {
        return myAditionalTotalStringFormat;
    }

    public void setMyAditionalTotalStringFormat(String myAditionalTotalStringFormat) {
        this.myAditionalTotalStringFormat = myAditionalTotalStringFormat;
    }

    public String getMyInitialTotalStringFormat() {
        return myInitialTotalStringFormat;
    }

    public void setMyInitialTotalStringFormat(String myInitialTotalStringFormat) {
        this.myInitialTotalStringFormat = myInitialTotalStringFormat;
    }

    public String getMySumTotalStringFormat() {
        return mySumTotalStringFormat;
    }

    public void setMySumTotalStringFormat(String mySumTotalStringFormat) {
        this.mySumTotalStringFormat = mySumTotalStringFormat;
    }

    public String getMyInvestExpenseTotalAditional() {
        return myInvestExpenseTotalAditional;
    }

    public void setMyInvestExpenseTotalAditional(String myInvestExpenseTotalAditional) {
        this.myInvestExpenseTotalAditional = myInvestExpenseTotalAditional;
    }

    public String getMyInvestExpenseTotalInit() {
        return myInvestExpenseTotalInit;
    }

    public void setMyInvestExpenseTotalInit(String myInvestExpenseTotalInit) {
        this.myInvestExpenseTotalInit = myInvestExpenseTotalInit;
    }

    public String getMyInvestExpenseTotalSum() {
        return myInvestExpenseTotalSum;
    }

    public void setMyInvestExpenseTotalSum(String myInvestExpenseTotalSum) {
        this.myInvestExpenseTotalSum = myInvestExpenseTotalSum;
    }

    public String getMyOperationalExpenseTotalAditional() {
        return myOperationalExpenseTotalAditional;
    }

    public void setMyOperationalExpenseTotalAditional(String myOperationalExpenseTotalAditional) {
        this.myOperationalExpenseTotalAditional = myOperationalExpenseTotalAditional;
    }

    public String getMyOperationalExpenseTotalInit() {
        return myOperationalExpenseTotalInit;
    }

    public void setMyOperationalExpenseTotalInit(String myOperationalExpenseTotalInit) {
        this.myOperationalExpenseTotalInit = myOperationalExpenseTotalInit;
    }

    public String getMyOperationalExpenseTotalSum() {
        return myOperationalExpenseTotalSum;
    }

    public void setMyOperationalExpenseTotalSum(String myOperationalExpenseTotalSum) {
        this.myOperationalExpenseTotalSum = myOperationalExpenseTotalSum;
    }

    public String getMyTotalByChapter() {
        return myTotalByChapter;
    }

    public void setMyTotalByChapter(String myTotalByChapter) {
        this.myTotalByChapter = myTotalByChapter;
    }

    public String getImprimirReporte() {
        return imprimirReporte;
    }

    public void setImprimirReporte(String imprimirReporte) {
        this.imprimirReporte = imprimirReporte;
    }
    @Override
    public List<BudgetingDependenceTotalDto> getMyListOfBreakDownDependences()
    {
        return myListOfBreakDownDependences;
    }

    @Override
    public void setMyListOfBreakDownDependences(List<BudgetingDependenceTotalDto> myListOfBreakDownDependences)
    {
        this.myListOfBreakDownDependences = myListOfBreakDownDependences;
    }

    @Override
    public String getMyadditionalTotalDependenceStringFormat()
    {
        return myadditionalTotalDependenceStringFormat;
    }

    @Override
    public void setMyadditionalTotalDependenceStringFormat(String myadditionalTotalDependenceStringFormat)
    {
        this.myadditionalTotalDependenceStringFormat = myadditionalTotalDependenceStringFormat;
    }

    @Override
    public String getMyInitialTotalDependenceStringFormat()
    {
        return myInitialTotalDependenceStringFormat;
    }

    @Override
    public void setMyInitialTotalDependenceStringFormat(String myInitialTotalDependenceStringFormat)
    {
        this.myInitialTotalDependenceStringFormat = myInitialTotalDependenceStringFormat;
    }

    @Override
    public String getMySumTotalDependenceStringformat()
    {
        return mySumTotalDependenceStringformat;
    }

    @Override
    public void setMySumTotalDependenceStringformat(String mySumTotalDependenceStringformat)
    {
        this.mySumTotalDependenceStringformat = mySumTotalDependenceStringformat;
    }

    @Override
    public boolean isItsMegaSummaryCheck()
    {
        return itsMegaSummaryCheck;
    }

    @Override
    public void setItsMegaSummaryCheck(boolean itsMegaSummaryCheck)
    {
        this.itsMegaSummaryCheck = itsMegaSummaryCheck;
    }

    @Override
    public boolean isItsComboDependenceCheck()
    {
        return itsComboDependenceCheck;
    }

    @Override
    public void setItsComboDependenceCheck(boolean itsComboDependenceCheck)
    {
        this.itsComboDependenceCheck = itsComboDependenceCheck;
    }
       
}
