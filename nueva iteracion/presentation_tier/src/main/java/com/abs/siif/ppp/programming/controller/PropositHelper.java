package com.abs.siif.ppp.programming.controller;

import com.abs.siif.base.common.ResourceBundleMassage;
import com.abs.siif.programming.entities.*;
import com.abs.siif.support.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 *
 * @author Israel Ruiz
 */
public class PropositHelper {

    /**
     * Obtiene la lista de proposistos del anteproyecto
     *
     * @param deliveries
     * @return
     */
    public static List<Proposit> getListProposits(List<DeliveryEntity> deliveries) {
        List<Proposit> catalogProposit = new ArrayList<Proposit>();
        Proposit proposit;
        for (DeliveryEntity delivery : deliveries) {
            proposit = new Proposit(delivery.getDeliveryId(),
                    delivery.getDeliveryId().toString());
            if (!catalogProposit.contains(proposit)) {
                catalogProposit.add(new Proposit(delivery.getDeliveryId(),
                        delivery.getDeliveryId().toString()));
            }

        }
        return catalogProposit;

    }

    /**
     * Coloca los componentes existentes por proposito
     *
     * @param deliveries propositos que se tiene registrados
     * @param props Catalogo de propositos obtenidos
     * @return Mapa de Propostios vs Componentes
     */
    public static Map<Proposit, List<ComponentEntity>> setPropMapCom(
            List<DeliveryEntity> deliveries,
            List<Proposit> props,
            Map<String, String> mapAdvProg) {

        Map<Proposit, List<ComponentEntity>> propComp =
                new HashMap<Proposit, List<ComponentEntity>>();
        List<ComponentEntity> comp;
        Proposit prop;

        for (DeliveryEntity delivery : deliveries) {

            // BUsca el proposito en el Catalogo
            prop = SearchList.findObjectList(props,
                    new Proposit(delivery.getDeliveryId()));
            //Encuentra cada Componente de entregable
            comp = new ArrayList<ComponentEntity>(delivery.getComponents());
            Collections.sort(comp);
            int indx = 1;
            for (ComponentEntity componentMap : comp) {
                componentMap.setNumber("" + (indx++));
                componentMap.setMapGolPro(
                        getMapGolPro(componentMap.getMensualcomponents()));
            }
            setMapAdvProg(mapAdvProg, comp);
            delivery.setComponents(new HashSet<ComponentEntity>(comp));
            propComp.put(prop, comp);



        }
        return propComp;
    }

    /**
     * Se obtiene el mapa de los valores programados del componente
     *
     * @param comp componente del cual se obtendra su mapa
     * @return Mapa con la programaciÃ³n anualizada
     */
    public static Map<String, ConceptProg> getProGoal(ComponentEntity comp) {
        Map<String, ConceptProg> proGoal = new HashMap<String, ConceptProg>();
        ConceptProg concept;
        for (MensualComponentEntity mensPro : comp.getMensualcomponents()) {
            concept = new ConceptProg(mensPro.getMensualComponentId(),
                    new BigDecimal(mensPro.getProgrammedGoal()).toString());
            proGoal.put(mensPro.getConceptProg(), concept);

        }
        return proGoal;
    }

    /**
     * Se obtiene la coleccion de meta programada para el componente
     *
     * @param proGoal Mapa con el detalle de la meta programada
     * @return Set de Entidades de Mensual programado
     */
    public static void putMensualComp(
            Map<String, String> proGoal, ComponentEntity comp) {

        MonthsEnum[] months = MonthsEnum.values();
        IniToToTalEnum[] iniFin = IniToToTalEnum.values();
        String valMap;
        boolean findIt = false;
        boolean newComp = false;
        Set<MensualComponentEntity> toMap = comp.getMensualcomponents();
        if (toMap == null) {
            toMap = new HashSet<MensualComponentEntity>();
            comp.setMensualcomponents(toMap);
            newComp = true;
        }

        nextMont:
        for (MonthsEnum month : months) {
            valMap = proGoal.get(month.name());
//            valMap = (valMap == null || valMap.trim().length() == 0)
//                    ? "0.0" : valMap;
            if (valMap == null || valMap.trim().length() == 0) {
                valMap = "0.0";
            } else {
                valMap = valMap.replace(",", "");
            }
            if (!newComp) {
                //Cuando el Collection no esta vacia
                for (MensualComponentEntity menEntity :
                        comp.getMensualcomponents()) {
                    if (month.name().equals(menEntity.getConceptProg())) {
                        menEntity.setProgrammedGoal(
                                new BigDecimal(valMap).doubleValue());
                        findIt = true;
                        continue nextMont;
                    }
                }
            }
            if (!findIt) {
                //Obtien la colección
                Set values = comp.getMensualcomponents();
                //Crea componente tipo coleccion
                MensualComponentEntity dataNew = new MensualComponentEntity();
                dataNew.setConceptProg(month.name());
                dataNew.setProgrammedGoal(new BigDecimal(valMap).doubleValue());
                values.add(dataNew);
            }

        }

        nextInit:
        for (IniToToTalEnum nameIni : iniFin) {
            valMap = proGoal.get(nameIni.name());
//            valMap = (valMap == null || valMap.trim().length() == 0)
//                    ? "0.0" : valMap;   
            if (valMap == null || valMap.trim().length() == 0) {
                valMap = "0.0";
            } else {
                valMap = valMap.replace(",", "");
            }
            valMap = valMap.replace(",", "");

            if (!newComp) {
                for (MensualComponentEntity menEntity :
                        comp.getMensualcomponents()) {
                    if (nameIni.name().equals(menEntity.getConceptProg())) {
                        menEntity.setProgrammedGoal(
                                new BigDecimal(valMap).doubleValue());
                        findIt = true;
                        continue nextInit;
                    }
                }
            }
            if (!findIt) {
                Set values = comp.getMensualcomponents();
                MensualComponentEntity dataNew = new MensualComponentEntity();
                dataNew.setConceptProg(nameIni.name());
                dataNew.setProgrammedGoal(new BigDecimal(valMap).doubleValue());
                values.add(dataNew);
            }


        }
    }

    private static Map<String, String> getMapGolPro(
            Set<MensualComponentEntity> mensualcomponents) {
        Map<String, String> map = new HashMap<String, String>();


        int indx = 0;


        List<MensualComponentEntity> monthData =
                new ArrayList<MensualComponentEntity>(mensualcomponents);
        if (!monthData.isEmpty()) {
            MensualComponentEntity mes;
            for (MonthsEnum monthE : MonthsEnum.values()) {

                while (true) {
                    mes = monthData.get(indx++ % monthData.size());
                    if (monthE.name().equals(mes.getConceptProg())) {
                        break;
                    }
                }
                map.put(mes.getConceptProg(),
                        FormatNumber.formatNumber("" + mes.getProgrammedGoal()));


            }
            for (IniToToTalEnum ini : IniToToTalEnum.values()) {

                while (true) {
                    mes = monthData.get(indx++ % monthData.size());
                    if (ini.name().equals(mes.getConceptProg())) {
                        break;
                    }
                }
                map.put(mes.getConceptProg(),
                        FormatNumber.formatNumber("" + mes.getProgrammedGoal()));

            }
        }
        return map;
    }

    /**
     * Mapea los datos que se tiene del programado actual para presentarlos en
     * pantalla
     *
     * @param goalProComp
     * @param mensualcomponents
     */
    public static void setMapToEdit(Map<String, String> goalProComp,
            Set<MensualComponentEntity> mensualcomponents) {

        for (MensualComponentEntity month : mensualcomponents) {

            String toString = UtilBigDecimal.round(new BigDecimal(month.getProgrammedGoal()), 0, false).toString();
            goalProComp.put(month.getConceptProg(),
                    FormatNumber.formatNumber(toString));
        }

    }

    public static void quantity(Map<String, String> activityProComp,
            ActivityEntity lactivity) {
        MonthsEnum[] months = MonthsEnum.values();
        BigDecimal bd = new BigDecimal("0.0");
        String value;
        for (MonthsEnum month : months) {
            value = activityProComp.get(month.name());
//            value = (value == null || value.trim().length() == 0) ? "0" : value;
            if (value == null || value.trim().length() == 0) {
                value = "0.0";
            } else {
                value = value.replace(",", "");
            }
            bd = bd.add(new BigDecimal(value));
        }
        lactivity.setQuantity(bd.doubleValue());
    }

    /**
     * Coloca las cantidades del mapa que proviene de la pantalla a la actividad
     *
     * @param lactivity
     * @param activityProComp
     */
    public static void setMapProAct(ActivityEntity lactivity,
            Map<String, String> activityProComp) {
        if (lactivity == null || activityProComp == null) {
            throw new IllegalArgumentException("Argumementos Nulos");
        }

        MonthsEnum[] months = MonthsEnum.values();
        String valMap;
        boolean findIt = false;
        boolean newComp = false;
        Set<MensualActivityEntity> toMap = lactivity.getMensualactivities();
        if (toMap == null) {
            toMap = new HashSet<MensualActivityEntity>();
            lactivity.setMensualactivities(toMap);
            newComp = true;
        }

        nextMont:
        for (MonthsEnum month : months) {
            valMap = activityProComp.get(month.name());

            if (valMap == null || valMap.trim().length() == 0) {
                valMap = "0.0";
            } else {
                valMap = valMap.replace(",", "");
            }
            if (!newComp) {
                for (MensualActivityEntity menEntity :
                        lactivity.getMensualactivities()) {
                    if (month.name().equals(menEntity.getMes())) {
                        menEntity.setDistribution(
                                new BigDecimal(valMap).doubleValue());
                        findIt = true;
                        continue nextMont;
                    }
                }
            }
            if (!findIt) {
                Set values = lactivity.getMensualactivities();
                MensualActivityEntity dataNew = new MensualActivityEntity();
                dataNew.setMes(month.name());
                dataNew.setDistribution(new BigDecimal(valMap).doubleValue());
                values.add(dataNew);
            }

        }
    }

    /**
     * Coloca lo que viene las cantidades programadas de la actividad al mapa
     * para presentarlo en la pantalla
     *
     * @param lactivity
     * @param activityProComp o
     */
    public static void setActProgToMap(ActivityEntity lactivity,
            Map<String, String> activityProComp) {
        DecimalFormat df = new DecimalFormat("###,###");
        if (lactivity == null || activityProComp == null) {
            throw new IllegalArgumentException("Argumementos Nulos");
        }
        activityProComp.clear();
        for (MensualActivityEntity month : lactivity.getMensualactivities()) {
            activityProComp.put(month.getMes(), "" + df.format(month.getDistribution()));
        }
    }

    static void setGoalProg(Set<ComponentEntity> components) {
        Set<MensualComponentEntity> monthsComp;
        Map<String, String> map;
        for (ComponentEntity comp : components) {
            map = new HashMap<String, String>();
            monthsComp = comp.getMensualcomponents();
            comp.setMapGolPro(map);
            map = comp.getMapGolPro();
            for (MensualComponentEntity monthComp : monthsComp) {
                map.put(monthComp.getConceptProg(),
                        FormatNumber.formatNumber(UtilBigDecimal.round(new BigDecimal(monthComp.getProgrammedGoal()), 2, false).toString()));
            }
        }
    }

    /**
     * Revisa los porcentajes de los componentes, si la suma de cada porcentaje
     * dan 100 % el metodo regresa true en caso contrario regresa false;
     *
     * @param components
     * @return
     */
    static boolean validate100Poderation(Set<ComponentEntity> components) {
        int accPR = 0;
        int accPI = 0;
        boolean band;
        for (ComponentEntity currentComp : components) {
            accPR += currentComp.getWeighingResource();
            accPI += currentComp.getWeighingImport();
        }
        return accPR == 100 && accPI == 100 ? true : false;

    }

    static boolean validMaxMin(Set<ComponentEntity> components) {
        boolean result = false;
        if (components != null) {
            int maxComponent =
                    ResourceBundleMassage.getIntValue("component.maxNumberComponents");
            int minComponent =
                    ResourceBundleMassage.getIntValue("component.minNumberComponents");
            if (minComponent <= components.size()) {
                result = true;
            }
        }
        return result;
    }

    private static void setMapAdvProg(Map<String, String> mapAdvProg, List<ComponentEntity> comp) {

        double total = 0;
        if (comp != null && !comp.isEmpty()) {
            double[][] valProg = new double[MonthsEnum.values().length][comp.size()];
            MensualComponentEntity month;

            // Sumatoria de Totales
//            for (ComponentEntity component : comp) {
//                int indx = 0;
//                List<MensualComponentEntity> monthData =
//                        new ArrayList<MensualComponentEntity>(component.getMensualcomponents());
//                while (true) {
//                    month = monthData.get(indx++ % monthData.size());
//                    if (month.getConceptProg().equals("TOTAL")) {
//                        total += month.getProgrammedGoal();
//                        break;
//                    }
//                }
//            }

            // Arma matriz de componenetes
            int idxMoth = 0;
            for (MonthsEnum monthE : MonthsEnum.values()) {
                int idxComp = 0;
                int indx;
                List<MensualComponentEntity> monthData;
                total = 0;

                for (ComponentEntity component : comp) {
                    indx = 0;
                    monthData =
                            new ArrayList<MensualComponentEntity>(component.getMensualcomponents());
                    
                    while (true) {
                        month = monthData.get(indx++ % monthData.size());
                        if (month.getConceptProg().equals("TOTAL")) {
                            total = month.getProgrammedGoal();
                            break;
                        }
                        
                    }
                    
                    while (true) {
                        month = monthData.get(indx++ % monthData.size());
                        if (monthE.name().equals(month.getConceptProg())) {
                            break;
                        }
                    }
                    valProg[idxMoth][idxComp++] = (month.getProgrammedGoal() * 100) / total;

                }
                idxMoth++;
            }
            MonthsEnum monthE[] = MonthsEnum.values();
            int indEnum = 0;
            double accMoth = 0.0;
            double totalP = 0;
            //Calcula porcentajes por mes
            for (double[] lstData : valProg) {
                double acc = 0.0;
                double divComp = 0.0;

                for (double progData : lstData) {
                    acc += progData;
                }

//                divComp = (acc * 100) / total;
                divComp = acc / comp.size();

                if (monthE[indEnum] != MonthsEnum.ENE) {

                    accMoth += divComp;
                    if (monthE[indEnum] == MonthsEnum.DIC) {
                        mapAdvProg.put(monthE[indEnum++].name(),
                                FormatNumber.formatNumberDecimal(UtilBigDecimal.round(
                                new BigDecimal("" + Math.round(accMoth)), 2, false).toString()));
                    } else {
                        mapAdvProg.put(monthE[indEnum++].name(),
                                FormatNumber.formatNumberDecimal(UtilBigDecimal.round(
                                new BigDecimal("" + accMoth), 2, false).toString()));
                    }

                } else {
                    accMoth += divComp;
                    mapAdvProg.put(monthE[indEnum++].name(),
                            FormatNumber.formatNumberDecimal(UtilBigDecimal.round(
                            new BigDecimal("" + divComp), 2, false).toString()));
                }

            }
        }

    }
}
