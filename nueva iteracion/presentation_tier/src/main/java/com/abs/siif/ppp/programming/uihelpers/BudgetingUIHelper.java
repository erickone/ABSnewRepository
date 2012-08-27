/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.uihelpers;

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import com.abs.siif.budget.dto.BudgetDispURDto;
import com.abs.siif.budget.dto.BudgetKeyBreakDownDto;
import com.abs.siif.budget.entities.*;
import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.ppp.programming.api.controller.BudgetingControllerApi;
import com.abs.siif.support.FormatNumber;
import com.abs.siif.support.MonthsEnum;
import com.abs.siif.support.SearchList;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Erick Leija
 */
public class BudgetingUIHelper {

    public static void getProgramaticKey(BudgetingControllerApi budgetingController) {

        Map<String, Long> myMap = new HashMap<String, Long>();
        myMap.put("iddependencia", budgetingController.getItsDependenceId());
        myMap.put("idproyecto", budgetingController.getItsDraftProjectId());
        myMap.put("idobjetogasto", budgetingController.getItsSelectedObjectExpense());
        String myBudgetingKey = budgetingController.getTheirbudgetKeyConfigurationManagement().getBudgetKey(myMap);
        myBudgetingKey += budgetingController.getItsSelectedDestinationId();


    }

    public static BudgetKeyEntity saveBudgetingKey(BudgetingControllerApi budgetingController) {
        BudgetKeyEntity myKeyToSave = new BudgetKeyEntity();
        FinancingSourceEntity myFinancingSource = null;
        Set<MensualBudgetKeyEntity> myListOfMonths = new HashSet<MensualBudgetKeyEntity>();
        Set<MensualBudgetKeyEntity> myList = new HashSet<MensualBudgetKeyEntity>();

        BudgetDispURDto dto = new BudgetDispURDto();;
        //creacion del objeto del gasto a guardar
        BudgetKeyDefinitionEntity myConfToSave = budgetingController.getTheirBudgetKeyDefinitionManagement().
                getBudgetDefinitionByYear(Integer.parseInt(SIIFContextBase.getParameterSession(SessionKeyEnum.YEAR).toString()));
        myKeyToSave.setBudgetKeyDefinitionBudgetKey(myConfToSave);
        ObjectExpenseEntity myObject = new ObjectExpenseEntity();
        myObject.setObjectExpenseId(budgetingController.getItsSelectedObjectExpense());
        myKeyToSave.setObjectExpenseBudgetKey(myObject);

        //Creacion de la fuente de financiamiento
        if (!(budgetingController.getItsSelectedFinancingSourceId() == 0)) {
            myFinancingSource = new FinancingSourceEntity();
            myFinancingSource.setFinancingSourceId(budgetingController.getItsSelectedFinancingSourceId());
            myKeyToSave.setFinancingSourceBudgetKey(myFinancingSource);
        }

        myKeyToSave.setAdditionalJustification(budgetingController.getItsMyJustification());

        myKeyToSave.setAdditionalAmount(budgetingController.getItsSummatoryAdititionalCantity().doubleValue());
        dto.setIsAdditionalAmount(budgetingController.isIsAditionalBudgeting());

        if (!budgetingController.isIsAditionalBudgeting()) {

            myKeyToSave.setOriginalAmount(0d);

            //Creacion de la fuente de financiamiento
            if (!(budgetingController.getItsSelectedFinancingSourceId() == 0)) {
                myFinancingSource = new FinancingSourceEntity();
                myFinancingSource.setFinancingSourceId(budgetingController.getItsSelectedFinancingSourceId());
                myKeyToSave.setFinancingSourceBudgetKey(myFinancingSource);
            }
            if ((FormatNumber.removeFormat(budgetingController.getItsSummatoryCantity()).trim().equals("0"))) {
                Double totalSummatory = 0.0;
                MonthsEnum[] months = MonthsEnum.values();
                for (MonthsEnum month : months) {
                    double valor = Double.parseDouble(String.valueOf(budgetingController.getItsMonthlyAmounts().get(month.name())));
                    totalSummatory += valor;
                }
                myKeyToSave.setOriginalAmount(totalSummatory);
            } else {
                myKeyToSave.setOriginalAmount(Double.valueOf(FormatNumber.removeFormat(budgetingController.getItsSummatoryCantity().trim())));
            }

            myListOfMonths = putMensualComp(budgetingController.getItsMonthlyAmounts());

            for (MensualBudgetKeyEntity myMonth : myListOfMonths) {
                myMonth.setBudgetKeyMensualBudgetKey(myKeyToSave);
                myList.add(myMonth);
            }
        }

        Map<String, Long> myMap = new HashMap<String, Long>();
        myMap.put("iddependencia", budgetingController.getItsDependenceId());
        myMap.put("idproyecto", budgetingController.getItsDraftProjectId());
        myMap.put("idobjetogasto", budgetingController.getItsSelectedObjectExpense());
        //myMap.put("iddestino", budgetingController.getItsSelectedDestinationId());
        String myBudgetingKey = budgetingController.getTheirbudgetKeyConfigurationManagement().getBudgetKey(myMap);
        myBudgetingKey += budgetingController.getItsSelectedDestinationId();
        myKeyToSave.setBudgetKeyBProgramaticKey(myBudgetingKey);
        myKeyToSave.setMensualBudgetKeyBudgetKey(myList);


        Map<String, List<BudgetDetailsKeyEntity>> myConcreteIds =
                budgetingController.getTheirbudgetKeyConfigurationManagement().
                getBudgetKeyWithBudgetKeyItems(myMap);

        String myBudgetingSKey = "";
        List<BudgetDetailsKeyEntity> myBudgetItems = new ArrayList<BudgetDetailsKeyEntity>();

        for (Map.Entry<String, List<BudgetDetailsKeyEntity>> entry : myConcreteIds.entrySet()) {
            myBudgetingSKey = entry.getKey();
            myBudgetItems = entry.getValue();
            for (BudgetDetailsKeyEntity myItem : myBudgetItems) {
                myItem.setBudgetKey(myKeyToSave);
            }
        }

        myKeyToSave.setBudgetDetailKeys(myBudgetItems);


        DependenceEntity myDependence = new DependenceEntity();
        myDependence.setDependenceId(budgetingController.getItsDependenceId());


        if (!budgetingController.isIsAditionalBudgeting()) {
            List<DestinyObjectExpenseRUBUEntity> myEntity = budgetingController.getTheirBudgetingManagement().
                    getTheBudgetingDestinationByObject(myDependence, myObject);
            if (myEntity.size() > 0) {

                if (myEntity.get(0).isDestinyObjectEpenseRUBUBasic()) {
                    long amount = myKeyToSave.getOriginalAmount().longValue();
                    long basic = budgetingController.getMyBasicDiference();
                    if (basic < 0) {
                        basic = 0;
                    }
                    long noBasic = 0;
                    if(amount < basic){
                       basic = amount;
                    }else if(amount > basic){
                        noBasic = amount-basic;
                    }
                   
                    myKeyToSave.setBudgetKeyBasic(basic);
                    myKeyToSave.setBudgetKeyNoBasic(noBasic);


                } else {

                    myKeyToSave.setBudgetKeyBasic(0L);
                    myKeyToSave.setBudgetKeyNoBasic(myKeyToSave.getOriginalAmount().longValue());

                }

            }




        }
        dto.setObjectExpenseEntity(myObject);
        dto.setFinancingSourceEntity(myFinancingSource);
        dto.setDependenceEntity(myDependence);
        dto.setDesting(budgetingController.getItsSelectedDestinationId());
        dto.setIdDraftProject(budgetingController.getItsDraftProjectId());
        Long myKeyId = budgetingController.getTheirBudgetingManagement().saveBudgetKey(myKeyToSave, dto, Boolean.TRUE);
        return myKeyToSave;
    }

    public static Set<MensualBudgetKeyEntity> putMensualComp(Map<String, String> proGoal) {
        BudgetKeyEntity comp = new BudgetKeyEntity();
        MonthsEnum[] months = MonthsEnum.values();
        String valMap;
        boolean findIt = false;
        boolean newComp = false;
        Set<MensualBudgetKeyEntity> toMap = comp.getMensualBudgetKeyBudgetKey();
        if (toMap == null) {
            toMap = new HashSet<MensualBudgetKeyEntity>();
            comp.setMensualBudgetKeyBudgetKey(toMap);
            newComp = true;
        }


        nextMont:
        for (MonthsEnum month : months) {
            valMap = String.valueOf(FormatNumber.removeFormat(proGoal.get(month.name())));
            valMap = (valMap == null || valMap.trim().length() == 0)
                    ? "0" : valMap;
            if (!newComp) {
                //Cuando el Collection no esta vacia
                for (MensualBudgetKeyEntity menEntity : comp.getMensualBudgetKeyBudgetKey()) {
                    if (month.name().equals(menEntity.getMonth())) {
                        menEntity.setOriginalAmount(new BigDecimal(valMap).doubleValue());
                        findIt = true;
                        continue nextMont;
                    }
                }
            }
            if (!findIt) {
                //Obtien la colección
                Set values = comp.getMensualBudgetKeyBudgetKey();
                //Crea componente tipo coleccion
                MensualBudgetKeyEntity dataNew = new MensualBudgetKeyEntity();
                dataNew.setMonth(month.name());
                dataNew.setOriginalAmount(new BigDecimal(valMap).doubleValue());
                values.add(dataNew);
            }
        }

        return comp.getMensualBudgetKeyBudgetKey();
    }

    public static void setMapToEdit(Map<String, String> goalProComp,
            Set<MensualBudgetKeyEntity> mensualcomponents) {

        for (MensualBudgetKeyEntity month : mensualcomponents) {
            goalProComp.put(month.getMonth(),
                    new BigDecimal(month.getOriginalAmount()).toString());
        }

    }

    public static void persistABudgetKey(BudgetingControllerApi myBudgetingController) {

        Set<MensualBudgetKeyEntity> menEntitySet = null;
        BudgetKeyEntity myBudget = myBudgetingController.getItsMyBudgetKeyToModify();
        myBudget.setIsAdditional(myBudgetingController.isIsAditionalBudgeting());
        BudgetDispURDto dataReferencedto = new BudgetDispURDto();
        DependenceEntity myDependence = new DependenceEntity();
        myDependence.setDependenceId(myBudgetingController.getItsDependenceId());

        Long idObjExpense = myBudgetingController.getItsSelectedObjectExpense();
        List<ObjectExpenseEntity> lstObj = myBudgetingController.getItsListOfFrammingObjectExpense();
        ObjectExpenseEntity dummy = new ObjectExpenseEntity();

        dummy.setObjectExpenseId(idObjExpense);
        dummy = SearchList.findObjectList(lstObj, dummy);
        dataReferencedto.setObjectExpenseEntity(dummy);

        FinancingSourceEntity myFinancingSource = null;
        List<DestinyObjectExpenseRUBUEntity> myEntity = myBudgetingController.getTheirBudgetingManagement().
                getTheBudgetingDestinationByObject(myDependence, dummy);

        if (!(myBudgetingController.getItsSelectedFinancingSourceId() == 0)) {
            myFinancingSource = new FinancingSourceEntity();
            myFinancingSource.setFinancingSourceId(myBudgetingController.getItsSelectedFinancingSourceId());
        }
        dataReferencedto.setFinancingSourceEntity(myFinancingSource);



        dataReferencedto.setDependenceEntity(myDependence);
        dataReferencedto.setDesting(myBudgetingController.getItsSelectedDestinationId());
        dataReferencedto.setItsUrDependence(myBudgetingController.getItsUrDependence());

        dataReferencedto.setCeilingBudgetObjectExpense(Long.parseLong(myBudgetingController.getItsMyBasicCeiling().replaceAll(",", "")));
        dataReferencedto.setCeilingBudgetObjectExpenseUsed(Long.parseLong(myBudgetingController.getItsMyBasicCeilingUsed().replaceAll(",", "")));

        if (!myBudget.isIsAdditional()) {
            boolean isEmptyMonths = myBudget.getMensualBudgetKeyBudgetKey() == null || (myBudget.getMensualBudgetKeyBudgetKey() != null
                    && myBudget.getMensualBudgetKeyBudgetKey().isEmpty());
            MensualBudgetKeyEntity ref = null;
            menEntitySet = new HashSet<MensualBudgetKeyEntity>();
            MonthsEnum[] months = MonthsEnum.values();
            nextMont:
            for (MonthsEnum month : months) {

                String valMap = String.valueOf(myBudgetingController.getItsMonthlyAmounts().get(month.name()));
                valMap = (valMap == null || valMap.trim().length() == 0) ? "0" : valMap;
                ref = new MensualBudgetKeyEntity();
                if (isEmptyMonths) {

                    ref = new MensualBudgetKeyEntity();
                    ref.setMonth(month.name());
                    ref.setOriginalAmount(Double.parseDouble(FormatNumber.removeFormat(valMap)));
                    ref.setBudgetKeyMensualBudgetKey(myBudget);
                    menEntitySet.add(ref);
                } else {

                    for (MensualBudgetKeyEntity menEntity : myBudget.getMensualBudgetKeyBudgetKey()) {
                        if (month.name().equals(menEntity.getMonth())) {
                            menEntity.setOriginalAmount(Double.parseDouble(FormatNumber.removeFormat(valMap)));

                            continue nextMont;
                        }
                    }
                }

            }
            if (isEmptyMonths) {
                myBudget.setMensualBudgetKeyBudgetKey(menEntitySet);
            }

            Double totalSummatory = 0.0;

            for (MonthsEnum month : months) {
                double valor = Double.parseDouble(String.valueOf(
                        FormatNumber.removeFormat(
                        myBudgetingController.getItsMonthlyAmounts().get(month.name()))));
                totalSummatory += valor;
            }
            myBudgetingController.getMyAvailableREsult();
            myBudget.setOriginalAmount(totalSummatory);
            BudgetKeyBreakDownDto myDto = myBudgetingController.getTheirBudgetingManagement().
                    getBudgetDesgloce(myBudget.getBudgetKeyId());
//            if (myEntity.size() > 0) {
//
//                if (myEntity.get(0).isDestinyObjectEpenseRUBUBasic()) {
//                    long amount = myBudget.getOriginalAmount().longValue();
//                    long basic = myBudgetingController.getMyBasicDiference();
//                    if (basic < 0) {
//                        basic = 0;
//                    }
//                    long noBasic = 0;
//                    if(amount < basic){
//                       basic = amount;
//                    }else if(amount > basic){
//                        noBasic = amount-basic;
//                    }
//                   
//                    myBudget.setBudgetKeyBasic(basic);
//                    myBudget.setBudgetKeyNoBasic(noBasic);
//
//
//                } else {
//
//                    myBudget.setBudgetKeyBasic(0L);
//                    myBudget.setBudgetKeyNoBasic(myBudget.getOriginalAmount().longValue());
//
//                }
//
//            }
            // No importa ya que sera sobreescrito por el proceso de actualización de Julio Valenzuela 
                myBudget.setBudgetKeyBasic(totalSummatory.longValue());
                myBudget.setBudgetKeyNoBasic(0L);
                
            if (myDto != null) {
                myDto.setMonto(BigDecimal.valueOf(totalSummatory.longValue()));
                //Long myResult = totalSummatory.longValue() - myDto.getNobasico().longValue();
                //Long myBasic = myDto.getNobasico().longValue();
                //myBudget.setBudgetKeyNoBasic(myBasic + myResult);
               //* myDto.setNobasico(BigDecimal.valueOf(myBudget.getBudgetKeyNoBasic()));
                //myBudget.setBudgetKeyBasic(myDto.getBasico().longValue());
                //myDto.setBasico(BigDecimal.valueOf(myBudget.getBudgetKeyBasic()));
                dataReferencedto.setMyBudget(myBudget);
                myBudgetingController.getTheirBudgetingManagement().modifyCeilingBudget(dataReferencedto, myDto);
                //myBudgetingController.getTheirBudgetingManagement().updateABudgetKeyEntity(myBudget, dataReferencedto);

            } else {
                myBudget.setAdditionalAmount(myBudgetingController.getItsSummatoryAdititionalCantity().doubleValue());
                myBudget.setAdditionalJustification(myBudgetingController.getItsMyJustification());
                myBudget.setIsAdditional(myBudgetingController.isIsAditionalBudgeting());
                myBudgetingController.getTheirBudgetingManagement().updateABudgetKeyEntity(myBudget, dataReferencedto);

            }

        } else {
            myBudget = myBudgetingController.getTheirBudgetingManagement().getBudgetEntityById(myBudget.getBudgetKeyId());
            myBudget.setAdditionalAmount(myBudgetingController.getItsSummatoryAdititionalCantity().doubleValue());
            myBudget.setAdditionalJustification(myBudgetingController.getItsMyJustification());
            myBudget.setIsAdditional(myBudgetingController.isIsAditionalBudgeting());
            myBudgetingController.getTheirBudgetingManagement().updateABudgetKeyEntity(myBudget, dataReferencedto);
        }




    }
}
