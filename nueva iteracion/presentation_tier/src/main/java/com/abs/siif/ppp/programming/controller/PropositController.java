package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.controller.SIIFControllerBase;
import com.abs.siif.base.exception.BaseBusinessException;
import com.abs.siif.ppp.planning.controller.OptionsController;
import com.abs.siif.ppp.programming.api.controller.DraftProjectHeaderControllerApi;
import com.abs.siif.ppp.programming.api.controller.PropositControllerApi;
import com.abs.siif.programming.entities.*;
import com.abs.siif.programming.management.DeliveryManagement;
import com.abs.siif.support.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.event.DateSelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Clase que se encarge de proporcionar los datos que refieren a la ficha
 * especificamente para el detalle del Proposito y sus componentes
 *
 * @author Israel Ruiz
 */
@Controller
@Scope("session")
public class PropositController extends SIIFControllerBase implements Serializable, PropositControllerApi {

    @Resource(name = "deliveryManagement")
    private transient DeliveryManagement deliveryManagement;
    @Resource(name = "draftProjectHeaderController")
    private transient DraftProjectHeaderControllerApi theirdraftProjectHeaderController;
    @Resource(name = "optionsController")
    private transient OptionsController theirOptionsController;
    /**
     * Entregable de la Ficha En este entregable se encuentra la informaciÃ³n de
     * los propositos que se han registrado en el entregable registrados en el
     * entregable
     */
    private DeliveryFile deliveryFile;
    /**
     * Colocara el itsComponent se que esta trabajando;
     */
    private ComponentEntity itsComponent;
    // UM Catalogo
    private Collection<UnitMeasureEntity> itsUMs;
    private UnitMeasureEntity itsSelectedUM;
    private Long itsSelectUMId;
    private Long propositId;
    /**
     * Cuando ses registra un proposito nuevo al cual se le colocara los
     * componentes
     */
    private String propositNew;
    private ActivityEntity selectedActivity;
    private ActivityEntity selectDeleteActivity;
    /**
     * Indica si el boton de agregar Actividad estar habilitado o no; true
     * deshabilitado, false habiliatado
     */
    private boolean disableActiv = true;
    private DraftProjectEntity draftProject;
    private List<DeliveryEntity> deliveries;
    private Map<String, String> goalProComp;
    private Map<String, String> advanProg;
    private Map<String, String> accProg;
    private int nextActivity;
    private Map<String, String> activityProComp;
    private Long draftProjectId;
    private String compNumb = "0";
    private String quantity;
    /**
     * Indicadores habilitado o no; textbos meses. true = dehabilidtado false =
     * habilitado
     */
    private boolean disableEn = true;
    private boolean disableFeb = true;
    private boolean disableMar = true;
    private boolean disableAbr = true;
    private boolean disableMay = true;
    private boolean disableJun = true;
    private boolean disableJul = true;
    private boolean disableAgt = true;
    private boolean disableSep = true;
    private boolean disableOct = true;
    private boolean disableNov = true;
    private boolean disableDic = true;
    private Date startDate;
    private List<Proposit> cataLogProposits;
    private boolean fromList;
    private boolean nosave = false;
    private static long idAutormatic = 0;
    private int count;
    private boolean btnSrvSave = false;
    private boolean errorSave = false;

    /**
     * Este metodo se encarga de cargar el entregable de la ficha De obtener los
     * catalogos de Unidad de Medida
     */
    @Override
    public void initProposit() {
        if (!errorSave) {
            setCount();
            fromList = false;
            nosave = false;
            this.setQuantity("0");
            this.deliveryFile = new DeliveryFile();
            ComponentEntity comIni = new ComponentEntity();
            ActivityEntity actvIni = new ActivityEntity();
            comIni.setCurrentActivity(actvIni);
            draftProject = new DraftProjectEntity();
            draftProject.setDraftProjectId(theirdraftProjectHeaderController.getTheirCurrentDraftProjectId());

            deliveryFile.setCurrentComp(comIni);

            setGoalProComp(new HashMap<String, String>());
            setActivityProComp(new HashMap<String, String>());
            setAdvanProg(new HashMap<String, String>());
            setAccProg(new HashMap<String, String>());
            itsComponent = new ComponentEntity();
            itsUMs = deliveryManagement.getUnitMeasureCatalog();

            if (draftProject.getDraftProjectId() != null) {
                if (draftProject.getDraftProjectId() > 0) {
                    setDeliveries(deliveryManagement.getDeliveries(draftProject));
                    cataLogProposits = PropositHelper.getListProposits(getDeliveries());
                    deliveryFile.setProposits(cataLogProposits);
                    deliveryFile.setPropositComp(
                            PropositHelper.setPropMapCom(
                            getDeliveries(),
                            cataLogProposits,
                            advanProg));
                    if (cataLogProposits != null && !cataLogProposits.isEmpty()) {

                        List<ComponentEntity> list =
                                deliveryFile.getPropositComp().
                                get(cataLogProposits.get(0));
                        setCompNumb(list != null ? "" + (list.size() + 1) : "1");

                    } else {
                        setCompNumb("1");
                    }
                } else {
                    //TODO bajar al proporties
                    addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                            this.getMessage("ppp.progr.noDataError"),
                            this.getMessage("ppp.progr.noDataError"));
                }


            } else {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.progr.noDataError"),
                        this.getMessage("ppp.progr.noDataError"));
            }
        }
        validateMaxComp();
    }

    /**
     * Se encarga de procesar la peticiÃ³n de guardado del componente sobre el
     * proposito seleccionado El componente que trea los datos es el componente
     * del control
     */
    @Override
    public void saveComponent() {
        theirdraftProjectHeaderController.setItsTabActiveIndex(2);
        setCount();
//        validateMaxComp();
        //Validar que traiga seleccionado Proposito, Unidad de medida
        ComponentEntity comp = itsComponent;
        nosave = true;
        boolean valid = true;
        int pondWeighingResourceSum = 0;
        int pondWeighingImportSum = 0;
        try {
            Proposit prop = new ArrayList<Proposit>(deliveryFile.getProposits()).get(0);
            String maxComp = this.getMessage("component.maxNumberComponents");
            List<ComponentEntity> componentEntitys = this.deliveryFile.getPropositComp().get(prop);
            if (componentEntitys.size() == Integer.parseInt(maxComp)
                    && Integer.parseInt(getCompNumb()) > Integer.parseInt(maxComp)) {
                theirdraftProjectHeaderController.setItsDisabledTab(false);
                theirdraftProjectHeaderController.setItsDisabledTabClassif(false);
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.progr.maxComponentMsg", new String[]{maxComp}),
                        this.getMessage("ppp.progr.maxComponentMsg", new String[]{maxComp}));

                errorSave = true;
                return;
            }

            if (!UtilValidations.notNullOrBlank(this.getComponent().getCompDesc())) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.progr.propositoReq"),
                        this.getMessage("ppp.progr.propositoReq"));
                valid = false;
            }
            if (!UtilValidations.notNullOrBlank(this.getComponent().getReponsable())) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.progr.responsableReq"),
                        this.getMessage("ppp.progr.responsableReq"));
                valid = false;
            }
            if (!UtilValidations.notNullOrBlank(this.getComponent().getIndicatorname())) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.progr.indicadorReq"),
                        this.getMessage("ppp.progr.indicadorReq"));
                valid = false;
            }

            if (!UtilValidations.notNullOrBlank(this.itsSelectUMId)) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.progr.unidadReq"),
                        this.getMessage("ppp.progr.unidadReq"));
                valid = false;
            }

            if (!UtilValidations.notNullOrBlank(this.getComponent().
                    getIndicatordescription())) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.progr.descIndicador"),
                        this.getMessage("ppp.progr.descIndicador"));
                valid = false;
            }
            if (this.getComponent().getWeighingImport() <= 0
                    || this.getComponent().getWeighingImport() > 100) {

                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.progr.podImportError"),
                        this.getMessage("ppp.progr.podImportError"));
                valid = false;

            }
            if (this.getComponent().getWeighingResource() <= 0
                    || this.getComponent().getWeighingResource() > 100) {

                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.progr.podResourceError"),
                        this.getMessage("ppp.progr.podResourceError"));
                valid = false;
            }


            if (!reviewProgrAmount(goalProComp)) {
                valid = false;
            } else {
                PropositHelper.putMensualComp(goalProComp, comp);
            }
            if (componentEntitys.size() > Integer.parseInt(getCompNumb()) - 1) {
                componentEntitys.remove(Integer.parseInt(getCompNumb()) - 1);
            }
            for (ComponentEntity entity : componentEntitys) {
                pondWeighingResourceSum += entity.getWeighingResource();
                pondWeighingImportSum += entity.getWeighingImport();
            }

            if (comp.getWeighingResource() > (100 - pondWeighingResourceSum)) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.podWeighingResourceError"),
                        this.getMessage("ppp.podWeighingResourceError"));
                valid = false;
            }

            if (comp.getWeighingImport() > (100 - pondWeighingImportSum)) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.podWeighingImportError"),
                        this.getMessage("ppp.podWeighingImportError"));
                valid = false;
            }

            if (!valid) {
                errorSave = true;
                return;
            } else {
                errorSave = false;
            }

            DeliveryEntity delivery = comp.getDelivery();
            if (delivery == null) {
                delivery = new DeliveryEntity();
                delivery.setDeliveryId(prop.getIdDelivery());
                delivery = SearchList.findObjectList(deliveries, delivery);
                comp.setDelivery(delivery);
            }

            UnitMeasureEntity umSelected = new UnitMeasureEntity();
            umSelected.setUnitMeasureId(itsSelectUMId);
            umSelected = SearchList.findObjectList(itsUMs, umSelected);
            comp.setUnitmeasure(umSelected);

            DeliveryEntity deliveryEntity = deliveryManagement.saveComponent(comp);

            setAdvanProg(new HashMap<String, String>());
            deliveries.clear();
            deliveries.add(deliveryEntity);
            advanProg = new HashMap<String, String>();
            List<Proposit> cataLogProposits = PropositHelper.getListProposits(deliveries);
            this.deliveryFile = new DeliveryFile();
            deliveryFile.setProposits(cataLogProposits);
            deliveryFile.setPropositComp(
                    PropositHelper.setPropMapCom(deliveries,
                    cataLogProposits, advanProg));
            //Salva el componenete de la Ficha
            //actualiza los componentes
            //Actualiza la lista de componentes que se tiene por delivery

            this.deliveryFile.getPropositComp().put(prop,
                    new ArrayList<ComponentEntity>(deliveryEntity.getComponents()));

            PropositHelper.setGoalProg(deliveryEntity.getComponents());
            //Verifica si el porsentenje de los componentes suman 100% por 
            //Concepto para habilitar las pestañas
            if (PropositHelper.validate100Poderation(
                    deliveryEntity.getComponents())) {

                theirdraftProjectHeaderController.setItsDisabledTab(false);
                theirdraftProjectHeaderController.setItsDisabledTabClassif(false);
                theirdraftProjectHeaderController.setItsTabActiveIndex(2);

            } else {
                theirdraftProjectHeaderController.setItsTabActiveIndex(2);
                theirdraftProjectHeaderController.setItsDisabledTab(true);
                theirdraftProjectHeaderController.setItsDisabledTabClassif(true);

                addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                        this.getMessage("ppp.progr.porMinMax"),
                        this.getMessage("ppp.progr.porMinMax"));
            }

            if (!PropositHelper.validMaxMin(deliveryEntity.getComponents())) {

                theirdraftProjectHeaderController.setItsDisabledTab(false);
                theirdraftProjectHeaderController.setItsDisabledTabClassif(false);


                addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                        this.getMessage("ppp.progr.porMinMax1"),
                        this.getMessage("ppp.progr.porMinMax1"));
            } else if (PropositHelper.validate100Poderation(
                    deliveryEntity.getComponents())) {
                theirdraftProjectHeaderController.setItsDisabledTab(true);
                theirdraftProjectHeaderController.setItsDisabledTabClassif(true);
            } else {
                theirdraftProjectHeaderController.setItsDisabledTab(false);
                theirdraftProjectHeaderController.setItsDisabledTabClassif(false);
            }

            addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                    this.getMessage("ppp.progr.succesSave"),
                    this.getMessage("ppp.progr.succesSave"));

            List<ComponentEntity> list =
                    deliveryFile.getPropositComp().
                    get(cataLogProposits.get(0));
            setCompNumb(list != null ? "" + (list.size() + 1) : "1");

//            validateMaxComp();

        } catch (RuntimeException exc) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage(exc.getMessage()),
                    this.getMessage(exc.getMessage()));
        }
        clearComponent();

    }

    /**
     * Revisa si almenos trae una cantidad y que la diferiencia entre Ini y Fin
     * no sea Mayor al Total
     *
     * @param progGoal
     */
    private boolean reviewProgrAmount(Map<String, String> progGoal) {
        boolean result = true;
        BigDecimal fin;
        BigDecimal ini;
        try {
            MonthsEnum[] months = MonthsEnum.values();
            IniToToTalEnum[] iniTotal = IniToToTalEnum.values();

            for (MonthsEnum month : months) {
                if (!reviewNumber(month.name(), progGoal)) {
                    result = false;
                }
            }
            for (IniToToTalEnum data : iniTotal) {
                if (!reviewNumber(data.name(), progGoal)) {
                    result = false;
                }
            }
            if (!result) {
                return result;
            }

            fin = new BigDecimal(FormatNumber.removeFormat(progGoal.get(
                    IniToToTalEnum.FIN.name())));
            ini = new BigDecimal(FormatNumber.removeFormat(progGoal.get(
                    IniToToTalEnum.INI.name())));

            BigDecimal diff = fin.subtract(ini);
            if (diff.doubleValue() < 0) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.progr.montosNegProg"),
                        this.getMessage("ppp.progr.montosNegProg"));
                result = false;
            }
            BigDecimal total = new BigDecimal(FormatNumber.removeFormat(progGoal.get(
                    IniToToTalEnum.TOTAL.name())));

            if (total.doubleValue() == 0) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.progr.montosCeroProg"),
                        this.getMessage("ppp.progr.montosCeroProg"));
                result = false;
            }
            if (total.doubleValue() < diff.doubleValue()) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.progr.montosMenorFin"),
                        this.getMessage("ppp.progr.montosMenorFin"));
                result = false;
            }
            if (total.doubleValue() > diff.doubleValue()) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.progr.montosAccedProg"),
                        this.getMessage("ppp.progr.montosAccedProg"));
                result = false;
            }

        } catch (Exception nfe) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.montosCeroProg"),
                    this.getMessage("ppp.progr.montosCeroProg"));
            result = false;
        }

        return result;
    }

    /**
     * Toma el componente selecionado para usuarlo en Actividades
     *
     * @param component
     */
    @Override
    public void setUpdateComNoDeleted(ComponentEntity component) {
        setCount();
        List arrayList;
        List<ComponentEntity> components;
        fromList = true;
        this.itsComponent = component;
        if (itsComponent.getActivities() == null) {
            itsComponent.setActivities(new HashSet<ActivityEntity>());
        }
        this.setNextActivity(itsComponent.getActivities().size() + 1);
        itsComponent.setCurrentActivity(new ActivityEntity());

        this.deliveryFile.setCurrentComp(component);

        arrayList = new ArrayList(this.deliveryFile.getPropositComp().values());
        if (arrayList != null) {
            components = (List<ComponentEntity>) arrayList.get(0);
            int indx = SearchList.indexList(components, component);
            this.compNumb = "" + (indx + 1);
        }
        ArrayList<ActivityEntity> array = new ArrayList<ActivityEntity>(itsComponent.getActivities());
        Collections.sort(array);
        this.deliveryFile.setCurrentActivities(array);
        itsSelectUMId = component.getUnitmeasure().getUnitMeasureId();
        propositId = component.getDelivery().getDeliveryId();
        PropositHelper.setMapToEdit(
                goalProComp, component.getMensualcomponents());
        blurPropMpp();
    }

    /**
     * Toma el componente selecionado para actualizarlo
     *
     * @param component
     */
    @Override
    public void setUpdateCom(ComponentEntity component) {
        setCount();
        setBtnSrvSave(false);
        if (itsComponent.getNumber() != null) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.CurrentNotEmpty"),
                    this.getMessage("ppp.progr.CurrentNotEmpty"));
            return;
        }
        List arrayList;
        List<ComponentEntity> components;
        this.itsComponent = component;
        if (itsComponent.getActivities() == null) {
            itsComponent.setActivities(new HashSet<ActivityEntity>());
        }
        this.setNextActivity(itsComponent.getActivities().size() + 1);
        itsComponent.setCurrentActivity(new ActivityEntity());

        this.deliveryFile.setCurrentComp(component);

        arrayList = new ArrayList(this.deliveryFile.getPropositComp().values());
        if (arrayList != null) {
            components = (List<ComponentEntity>) arrayList.get(0);
            int indx = SearchList.indexList(components, component);
            setCompNumb(String.valueOf(indx + 1));
            this.compNumb = component.getNumber();
            components.remove(indx);

            deliveryFile.getPropositComp().put(
                    cataLogProposits.get(0), components);
        }
        ArrayList<ActivityEntity> array = new ArrayList<ActivityEntity>(itsComponent.getActivities());
        Collections.sort(array);
        this.deliveryFile.setCurrentActivities(array);
        itsSelectUMId = component.getUnitmeasure().getUnitMeasureId();
        propositId = component.getDelivery().getDeliveryId();
        PropositHelper.setMapToEdit(
                goalProComp, component.getMensualcomponents());
        blurPropMpp();
        disableActiv = false;

    }

    @Override
    public void setDeleteCom(ComponentEntity component) {
        try {
            setCount();
            Proposit prop = new Proposit();
            prop.setId(component.getDelivery().getDeliveryId());

            DeliveryEntity deliveryEntity
                    = deliveryManagement.deleteComponent(component);

            int indx = SearchList.indexList(deliveries, deliveryEntity);
            if (indx >= 0) {
                deliveries.remove(indx);
            }
            advanProg = new HashMap<String, String>();
            deliveries.add(deliveryEntity);
            List<Proposit> cataLogProposits = PropositHelper.getListProposits(deliveries);
            deliveryFile.setProposits(cataLogProposits);


            deliveryFile.setPropositComp(
                    PropositHelper.setPropMapCom(deliveries,
                    cataLogProposits,
                    advanProg));

            List<ComponentEntity> list =
                    deliveryFile.getPropositComp().
                    get(cataLogProposits.get(0));
            setCompNumb(list != null ? "" + (list.size() + 1) : "1");
            validateMaxComp();
        } catch (BaseBusinessException ex) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    getMessage(ex.getMessage()),
                    getMessage(ex.getMessage()));
        }
    }

    @Override
    public void validateMaxComp() {
        double weighingImport = 0;
        double weighingResource = 0;
        boolean disableTab = Boolean.TRUE;
        boolean disableBtnSave = Boolean.FALSE;
        if (deliveryFile != null && !deliveryFile.getProposits().isEmpty()) {
            Proposit prop2 = new ArrayList<Proposit>(deliveryFile.getProposits()).get(0);
            String maxComp = this.getMessage("component.maxNumberComponents");
            List<ComponentEntity> componentEntitys = this.deliveryFile.getPropositComp().get(prop2);
            if (componentEntitys.size() == Integer.parseInt(maxComp)) {
                disableBtnSave = true;
                addMessageCurrentInstance(FacesMessage.SEVERITY_INFO,
                        this.getMessage("ppp.progr.maxComp", new String[]{"" + componentEntitys.size()}),
                        this.getMessage("ppp.progr.maxComp", new String[]{"" + componentEntitys.size()}));
            }
            for (ComponentEntity entity : componentEntitys) {
                weighingImport += entity.getWeighingImport();
                weighingResource += entity.getWeighingResource();
            }
            if (weighingResource == 100 && weighingImport == 100) {
                disableTab = false;
            } else {
                addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                        this.getMessage("ppp.progr.adjustPercent"),
                        this.getMessage("ppp.progr.adjustPercent"));
            }

        }
        setBtnSrvSave(disableBtnSave);
        theirdraftProjectHeaderController.setItsDisabledTab(disableTab);
        theirdraftProjectHeaderController.setItsDisabledTabClassif(disableTab);
        theirdraftProjectHeaderController.setItsTabActiveIndex(2);
    }

    /**
     * Limpia los elementos que contiene la forma de Entregables
     */
    @Override
    public void clearComponent() {
        setCount();
        List<ComponentEntity> arrayList = null;
        if (itsComponent.getNumber() != null) {

            arrayList = deliveryFile.getPropositComp().get(
                    cataLogProposits.get(0));
            if (arrayList == null) {
                arrayList = new ArrayList<ComponentEntity>();
            }
            if (!nosave) {
                arrayList.add(itsComponent);
                Collections.sort(arrayList);
                deliveryFile.getPropositComp().put(
                        cataLogProposits.get(0), arrayList);
            } else {
                nosave = false;
            }
        }

        ComponentEntity comIni = new ComponentEntity();
        ActivityEntity actvIni = new ActivityEntity();
        comIni.setCurrentActivity(actvIni);
        deliveryFile.setCurrentComp(comIni);
        itsComponent = new ComponentEntity();
        propositId = new Long(0);
        itsSelectUMId = new Long(0);
        this.goalProComp = new HashMap<String, String>();
        this.accProg = new HashMap<String, String>();
        disableActiv = true;
        List<Proposit> cataLogProposits = PropositHelper.getListProposits(deliveries);
        List<ComponentEntity> list =
                deliveryFile.getPropositComp().
                get(cataLogProposits.get(0));
        setCompNumb(list != null ? "" + (list.size() + 1) : "1");
        initProposit();
    }

    /**
     * Ingresa el proposito al entregable y recarga el catalogo de propositos
     */
    @Override
    public void reloadProposit() {
        advanProg = new HashMap<String, String>();
        if (propositNew != null) {
            DeliveryEntity prop = new DeliveryEntity();
            prop.setProposit(propositNew);
            prop.setDraftProject(draftProject);
            List<DeliveryEntity> ldeliveries =
                    deliveryManagement.addPropositToDelivery(prop);
            setDeliveries(ldeliveries);
            deliveryFile.setProposits(
                    PropositHelper.getListProposits(getDeliveries()));
            deliveryFile.setPropositComp(
                    PropositHelper.setPropMapCom(getDeliveries(),
                    new ArrayList<Proposit>(deliveryFile.getProposits()),
                    advanProg));

        }
    }

    /**
     * Metodo que permite habilitar o deshabilitar en caso que el entregable que
     * se este tabajando ya esta guardado, encaso que se este colocand un nuevo
     * el boton se muestra deshabilitado;
     *
     * @return
     */
    @Override
    public boolean isDisableActiv() {
        return disableActiv;
    }

    /**
     * Metodo que se encarga de ralizar los calculos para obtener los acumulados
     * de mes a mes del componente
     */
    @Override
    public void blurPropMpp() {
        Map<String, String> mapProgAcc = this.accProg;
        MonthsEnum[] months = MonthsEnum.values();
        BigDecimal bd = new BigDecimal("0");
        StringBuilder errors = new StringBuilder();
        String data;
        for (MonthsEnum month : months) {
            try {
                data = this.goalProComp.get(month.name());

//                data = (data == null
//                        || data.trim().length() == 0) ? "0" : data;
                if (data == null || data.trim().length() == 0) {
                    data = "0.0";
                } else {
                    data = data.replace(",", "");
                }

                if (data.equals("0")) {
                    mapProgAcc.put(month.name(), data);
                }

                bd = bd.add(new BigDecimal(data));
                mapProgAcc.put(month.name(), FormatNumber.formatNumber(bd.toString()));

            } catch (NumberFormatException nfe) {
                bd = bd.add(new BigDecimal("0"));
                mapProgAcc.put(month.name(), FormatNumber.formatNumber(bd.toString()));
                errors.append(
                        this.getMessage("ppp.progr.propositoErrorMes",
                        new String[]{
                            month.name()
                        }));
            }
        }
        //Valida formato de Numeros
        IniToToTalEnum[] totals = IniToToTalEnum.values();
        for (IniToToTalEnum iniToTotal : totals) {
            try {

                data = this.goalProComp.get(iniToTotal.name());

                if (data != null && data.trim().length() > 0) {
                    BigDecimal bd1 = new BigDecimal(FormatNumber.removeFormat(data));
                }
            } catch (NumberFormatException nfe) {

                errors.append(this.getMessage(
                        "ppp.progr.propositoErrorMes",
                        new String[]{
                            iniToTotal.name()
                        }));

            }
        }
        if (errors.length() > 0) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    errors.toString(),
                    errors.toString());
        }
        this.goalProComp.put("TOTAL", FormatNumber.formatNumber(bd.toString()));
        mapProgAcc.put("TOTAL", FormatNumber.formatNumber(bd.toString()));
    }

    /**
     *
     * @return quantity
     */
    @Override
    public String getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     */
    @Override
    public void setQuantity(String quantity) {
        quantity = FormatNumber.formatNumber(quantity);
        this.quantity = quantity;
    }

    /**
     * @return the itsUMs
     */
    @Override
    public Collection<UnitMeasureEntity> getItsSelectedUMs() {
        return itsUMs;
    }

    /**
     * @param itsUMs the itsUMs to set
     */
    @Override
    public void setItsSelectedUMs(Collection<UnitMeasureEntity> itsSelectedUMs) {
        this.itsUMs = itsSelectedUMs;
    }

    /**
     * @return the itsSelectedUM
     */
    @Override
    public UnitMeasureEntity getItsSelectedUM() {
        return itsSelectedUM;
    }

    /**
     * @param itsSelectedUM the itsSelectedUM to set
     */
    @Override
    public void setItsSelectedUM(UnitMeasureEntity itsSelectedUM) {
        this.itsSelectedUM = itsSelectedUM;
    }

    /**
     * @return the propositNew
     */
    @Override
    public String getPropositNew() {
        return propositNew;
    }

    /**
     * Esta propieda se llama demanera parcial desde PrimeFaces por lo que a
     * partir de entrar se ejecuta la recarga del Catalogo
     *
     * @param propositNew the propositNew to set
     */
    @Override
    public void setPropositNew(String propositNew) {
        this.propositNew = propositNew;
    }

    /**
     * @param selectedActivity the selectedActivity to set
     */
    @Override
    public void setSelectedActivity(ActivityEntity selectedActivity) {
        this.selectedActivity = selectedActivity;
        ArrayList<ActivityEntity> activities = new ArrayList<ActivityEntity>(this.deliveryFile.getCurrentComp().getActivities());
        Collections.sort(activities);
        nextActivity = SearchList.indexList(activities, selectedActivity);
        nextActivity++;
        this.deliveryFile.getCurrentComp().setCurrentActivity(selectedActivity);
        PropositHelper.setActProgToMap(selectedActivity, this.activityProComp);

        validDates(this.deliveryFile.getCurrentComp().
                getCurrentActivity().getStartDate(),
                this.deliveryFile.getCurrentComp().
                getCurrentActivity().getEndDate());
        this.setQuantity(String.valueOf(selectedActivity.getQuantity()));

    }

    /**
     * @param selectDeleteActivity the selectDeleteActivity to set
     */
    @Override
    public void setSelectedToDeleteAct(ActivityEntity selectedToDeleteAct) {
        selectedToDeleteAct.setComponent(deliveryFile.getCurrentComp());
        ComponentEntity comp =
                deliveryManagement.deleteActivity(selectedToDeleteAct);
        comp.setCurrentActivity(new ActivityEntity());
        this.deliveryFile.setCurrentComp(comp);
        ArrayList<ActivityEntity> array = new ArrayList<ActivityEntity>(comp.getActivities());
        Collections.sort(array);
        itsComponent = comp;
        this.deliveryFile.setCurrentActivities(array);
        this.nextActivity = deliveryFile.getCurrentActivities().size() + 1;
        this.activityProComp = new HashMap<String, String>();
    }

    /**
     * @return the propositId
     */
    @Override
    public Long getPropositId() {
        return propositId;
    }

    /**
     * @param propositId the propositId to set
     */
    @Override
    public void setPropositId(Long propositId) {
        this.propositId = propositId;
    }

    /**
     * @return the itsSelectUMId
     */
    @Override
    public Long getItsSelectUMId() {
        return itsSelectUMId;
    }

    /**
     * @param itsSelectUMId the itsSelectUMId to set
     */
    @Override
    public void setItsSelectUMId(Long itsSelectUMId) {
        this.itsSelectUMId = itsSelectUMId;
    }

    /**
     * @return the deliveryFile
     */
    @Override
    public DeliveryFile getDeliveryFile() {
        return deliveryFile;
    }

    /**
     * @param deliveryFile the deliveryFile to set
     */
    @Override
    public void setDeliveryFile(DeliveryFile deliveryFile) {
        this.deliveryFile = deliveryFile;
    }

    /**
     * @return the componenet
     */
    @Override
    public ComponentEntity getComponent() {
        return itsComponent;
    }

    /**
     * @param componenet the componenet to set
     */
    @Override
    public void setComponent(ComponentEntity componenet) {
        this.itsComponent = componenet;
    }

    /**
     * @param disableActiv the disableActiv to set
     */
    @Override
    public void setDisableActiv(boolean disableActiv) {
        this.disableActiv = disableActiv;
    }

    @Override
    public void setSaveAction(ActivityEntity args) {
        boolean validatonWrong = false;
        try {
            ActivityEntity lactivity = this.deliveryFile.getCurrentComp().getCurrentActivity();
            if (!UtilValidations.notNullOrBlank(lactivity.getDescription())) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.planning.description"),
                        this.getMessage("ppp.planning.description"));
                validatonWrong = true;

            }
            if (!UtilValidations.notNullOrBlank(lactivity.getResponsible())) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.planning.responsable"),
                        this.getMessage("ppp.planning.responsable"));
                validatonWrong = true;

            }

            if (lactivity.getStartDate() == null) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.planning.startDate"),
                        this.getMessage("ppp.planning.startDate"));
                validatonWrong = true;

            }

            if (lactivity.getEndDate() == null) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.planning.endDate"),
                        this.getMessage("ppp.planning.endDate"));
                validatonWrong = true;

            }

            if (activityProComp == null || activityProComp.isEmpty()) {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.planning.mensual"),
                        this.getMessage("ppp.planning.mensual"));
                validatonWrong = true;

            }

            if (validatonWrong) {
                return; // By error
            }

            lactivity.setComponent(this.deliveryFile.getCurrentComp());
            PropositHelper.quantity(activityProComp, lactivity);
            //Calcular la cantida en base a lo programado en la actividad
            PropositHelper.setMapProAct(lactivity, this.activityProComp);
            double qty = Double.parseDouble(FormatNumber.removeFormat(getQuantity()));
            double quantity1 = lactivity.getQuantity();
            if (quantity1 == qty) {
                ComponentEntity comp = deliveryManagement.addActivityToComp(lactivity);
                this.deliveryFile.setCurrentComp(comp);
                this.deliveryFile.getCurrentComp().setCurrentActivity(new ActivityEntity());
                ArrayList<ActivityEntity> activitiesUpdate =
                        new ArrayList<ActivityEntity>(comp.getActivities());
                Collections.sort(activitiesUpdate);
                this.deliveryFile.setCurrentActivities(activitiesUpdate);
                this.activityProComp = new HashMap<String, String>();
                this.nextActivity = activitiesUpdate.size() + 1;
                this.setQuantity("0");
                disableMonths();
            } else {
                addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                        this.getMessage("ppp.planning.qtyError"),
                        this.getMessage("ppp.planning.qtyError"));
            }

        } catch (RuntimeException ex) {
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.dataErrorDato"),
                    this.getMessage("ppp.progr.dataErrorDato"));
        }
    }

    @Override
    public void setCloseAction(ActivityEntity args) {
        this.deliveryFile.getCurrentComp().setCurrentActivity(new ActivityEntity());
        this.setNextActivity(itsComponent.getActivities().size() + 1);
        if (fromList) {
            itsComponent = new ComponentEntity();
            this.fromList = false;
        }

    }

    @Override
    public void setCloseProposit(String proposit) {
    }

    private boolean reviewNumber(String label, Map<String, String> progGoal) {
        String value;
        boolean result;
        value = FormatNumber.removeFormat(progGoal.get(label));

        if (value == null
                || value.trim().length() == 0) {

            progGoal.put(label, "0");
            value = progGoal.get(label);
        }

        if (value.matches("^[0-9]{1,15}(\\.[0-9]{0,2})?$")) {
            result = true;
        } else {
            result = false;
            addMessageCurrentInstance(FacesMessage.SEVERITY_ERROR,
                    this.getMessage("ppp.progr.noNumero", new String[]{
                        label
                    }),
                    this.getMessage("ppp.progr.noNumero", new String[]{
                        label
                    }));
        }

        return result;
    }

    /**
     * @return the draftProject
     */
    @Override
    public DraftProjectEntity getDraftProject() {
        return draftProject;
    }

    /**
     * @param draftProject the draftProject to set
     */
    @Override
    public void setDraftProject(DraftProjectEntity draftProject) {
        this.draftProject = draftProject;
    }

    /**
     * @return the deliveries
     */
    @Override
    public List<DeliveryEntity> getDeliveries() {
        return deliveries;
    }

    /**
     * @param deliveries the deliveries to set
     */
    @Override
    public void setDeliveries(List<DeliveryEntity> deliveries) {
        this.deliveries = deliveries;
    }

    /**
     * @return the goalProComp
     */
    @Override
    public Map<String, String> getGoalProComp() {
        return goalProComp;
    }

    /**
     * @param goalProComp the goalProComp to set
     */
    @Override
    public void setGoalProComp(Map<String, String> goalProComp) {
        this.goalProComp = goalProComp;
    }

    /**
     * @return the advanProg
     */
    @Override
    public Map<String, String> getAdvanProg() {
        return advanProg;
    }

    /**
     * @param advanProg the advanProg to set
     */
    @Override
    public void setAdvanProg(Map<String, String> advanProg) {
        this.advanProg = advanProg;
    }

    /**
     * @return the accProg
     */
    @Override
    public Map<String, String> getAccProg() {
        return accProg;
    }

    /**
     * @param accProg the accProg to set
     */
    @Override
    public void setAccProg(Map<String, String> accProg) {
        this.accProg = accProg;
    }

    /**
     * @return the nextActivity
     */
    @Override
    public int getNextActivity() {
        return nextActivity;
    }

    /**
     * @param nextActivity the nextActivity to set
     */
    @Override
    public void setNextActivity(int nextActivity) {
        this.nextActivity = nextActivity;
    }

    /**
     * @return the activityProComp
     */
    @Override
    public Map<String, String> getActivityProComp() {
        return activityProComp;
    }

    /**
     * @param activityProComp the activityProComp to set
     */
    @Override
    public void setActivityProComp(Map<String, String> activityProComp) {

        this.activityProComp = activityProComp;
    }

    /**
     * @return the draftProjectId
     */
    @Override
    public Long getDraftProjectId() {
        return draftProjectId;
    }

    /**
     * @param draftProjectId the draftProjectId to set
     */
    @Override
    public void setDraftProjectId(Long draftProjectId) {
        this.draftProjectId = draftProjectId;
    }

    @Override
    public String getMinDate() {
        String minDate = "1/1/" + this.getMessage("ppp.progr.MaxYear");
        return minDate;
    }

    @Override
    public String getMaxDate() {
        //String minDate = "31/12/" + theirOptionsController.getYear().substring(2, 4);
        String minDate = "31/12/" + this.getMessage("ppp.progr.MaxYear");
        return minDate;
    }

    @Override
    public void selectStarDate(DateSelectEvent event) {
        setStartDate(event.getDate());
    }

    @Override
    public void validateDatesToEnableMonths(DateSelectEvent event) {
        disableMonths();
        Date endDate = event.getDate();
        Date starDate = getStartDate();
        if (starDate == null) {
            starDate = this.deliveryFile.getCurrentComp().
                    getCurrentActivity().getStartDate();
        }
        validDates(starDate, endDate);
    }

    private void validDates(Date starDate, Date endDate) {
        int startMonth = starDate.getMonth();
        int endMonth = endDate.getMonth();
        if (starDate.before(endDate)) {

            for (int i = startMonth; i <= endMonth; i++) {
                switch (i) {
                    case 0:
                        setDisableEn(Boolean.FALSE);
                        break;
                    case 1:
                        setDisableFeb(Boolean.FALSE);
                        break;
                    case 2:
                        setDisableMar(Boolean.FALSE);
                        break;
                    case 3:
                        setDisableAbr(Boolean.FALSE);
                        break;
                    case 4:
                        setDisableMay(Boolean.FALSE);
                        break;
                    case 5:
                        setDisableJun(Boolean.FALSE);
                        break;
                    case 6:
                        setDisableJul(Boolean.FALSE);
                        break;
                    case 7:
                        setDisableAgt(Boolean.FALSE);
                        break;
                    case 8:
                        setDisableSep(Boolean.FALSE);
                        break;
                    case 9:
                        setDisableOct(Boolean.FALSE);
                        break;
                    case 10:
                        setDisableNov(Boolean.FALSE);
                        break;
                    case 11:
                        setDisableDic(Boolean.FALSE);
                        break;
                }
            }
        } else {
            addMessageCurrentInstance(FacesMessage.SEVERITY_WARN,
                    this.getMessage("ppp.progr.Deliverables.ErrDate"),
                    this.getMessage("ppp.progr.Deliverables.ErrDate"));
        }

    }

    /**
     * @param btnSrvSave the btnSrvSave to set
     */
    @Override
    public void setBtnSrvSave(boolean itsBtnSrvSave) {
        this.btnSrvSave = itsBtnSrvSave;
    }

    /**
     *
     * @return btnSrvSave
     */
    @Override
    public boolean getBtnSrvSave() {
        return btnSrvSave;
    }

    /**
     * @param disableEn the disableEn to set
     */
    @Override
    public void setDisableEn(boolean disableEn) {
        this.disableEn = disableEn;
    }

    /**
     *
     * @return disableEn
     */
    @Override
    public boolean isDisableEn() {
        return disableEn;
    }

    /**
     * @param disableFeb the disableFeb to set
     */
    @Override
    public void setDisableFeb(boolean disableFeb) {
        this.disableFeb = disableFeb;
    }

    /**
     *
     * @return disableFeb
     */
    @Override
    public boolean isDisableFeb() {
        return disableFeb;
    }

    /**
     * @param disableMar the disableMar to set
     */
    @Override
    public void setDisableMar(boolean disableMar) {
        this.disableMar = disableMar;
    }

    /**
     *
     * @return disableMar
     */
    @Override
    public boolean isDisableMar() {
        return disableMar;
    }

    /**
     * @param disableAbr the disableAbr to set
     */
    @Override
    public void setDisableAbr(boolean disableAbr) {
        this.disableAbr = disableAbr;
    }

    /**
     *
     * @return disableAbr
     */
    @Override
    public boolean isDisableAbr() {
        return disableAbr;
    }

    /**
     * @param disableMay the disableMay to set
     */
    @Override
    public void setDisableMay(boolean disableMay) {
        this.disableMay = disableMay;
    }

    /**
     *
     * @return disableMay
     */
    @Override
    public boolean isDisableMay() {
        return disableMay;
    }

    /**
     * @param disableJun the disableJun to set
     */
    @Override
    public void setDisableJun(boolean disableJun) {
        this.disableJun = disableJun;
    }

    /**
     *
     * @return disableJun
     */
    @Override
    public boolean isDisableJun() {
        return disableJun;
    }

    /**
     * @param disableJul the disableJul to set
     */
    @Override
    public void setDisableJul(boolean disableJul) {
        this.disableJul = disableJul;
    }

    /**
     *
     * @return disableJul
     */
    @Override
    public boolean isDisableJul() {
        return disableJul;
    }

    /**
     * @param disableAgt the disableAgt to set
     */
    @Override
    public void setDisableAgt(boolean disableAgt) {
        this.disableAgt = disableAgt;
    }

    /**
     *
     * @return disableAgt
     */
    @Override
    public boolean isDisableAgt() {
        return disableAgt;
    }

    /**
     * @param disableSep the disableSep to set
     */
    @Override
    public void setDisableSep(boolean disableSep) {
        this.disableSep = disableSep;
    }

    /**
     *
     * @return disableSep
     */
    @Override
    public boolean isDisableSep() {
        return disableSep;
    }

    /**
     * @param disableOct the disableOct to set
     */
    @Override
    public void setDisableOct(boolean disableOct) {
        this.disableOct = disableOct;
    }

    /**
     *
     * @return disableOct
     */
    @Override
    public boolean isDisableOct() {
        return disableOct;
    }

    /**
     * @param disableNov the disableNov to set
     */
    @Override
    public void setDisableNov(boolean disableNov) {
        this.disableNov = disableNov;
    }

    /**
     *
     * @return disableNov
     */
    @Override
    public boolean isDisableNov() {
        return disableNov;
    }

    /**
     * @param disableDic the disableDic to set
     */
    @Override
    public void setDisableDic(boolean disableDic) {
        this.disableDic = disableDic;
    }

    /**
     *
     * @return disableDic
     */
    @Override
    public boolean isDisableDic() {
        return disableDic;
    }

    /**
     * @param startDate the startDate to set
     */
    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     *
     * @return startDate
     */
    @Override
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Deshabilita los input de meses
     */
    @Override
    public void disableMonths() {
        setDisableEn(Boolean.TRUE);
        setDisableFeb(Boolean.TRUE);
        setDisableMar(Boolean.TRUE);
        setDisableAbr(Boolean.TRUE);
        setDisableMay(Boolean.TRUE);
        setDisableJun(Boolean.TRUE);
        setDisableJul(Boolean.TRUE);
        setDisableAgt(Boolean.TRUE);
        setDisableSep(Boolean.TRUE);
        setDisableOct(Boolean.TRUE);
        setDisableNov(Boolean.TRUE);
        setDisableDic(Boolean.TRUE);
    }

    /**
     * @return the compNumb
     */
    @Override
    public String getCompNumb() {
        return compNumb;
    }

    /**
     * @param compNumb the compNumb to set
     */
    @Override
    public void setCompNumb(String compNumb) {
        this.compNumb = compNumb;
    }

    @Override
    public String getNextId() {
        return "comp_" + (++idAutormatic % 1234567892L);
    }

    @Override
    public String getId() {
        return "comp_" + idAutormatic;
    }

    /**
     * Resetea el contador
     */
    @Override
    public void setCount() {
        this.count = 1;
    }

    /**
     * Extrae el siguiente contador
     *
     * @return int count
     */
    @Override
    public int getCount() {
        return count++;
    }
}
