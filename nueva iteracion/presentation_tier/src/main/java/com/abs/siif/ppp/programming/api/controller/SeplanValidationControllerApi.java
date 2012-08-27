/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V.
 *  All rights reserved
 *  Filename:  SeplanValidationControllerApi
 *  Purpose:  [ short Description  ]
 *       
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be
 *  used and/or copied only with written permission from Advanced
 *  Business Systems S.A. de C.V. or in accordance with the terms
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.ppp.programming.api.controller;

import com.abs.siif.programming.dto.ValidationQuestionDto;
import com.abs.siif.programming.entities.ComponentEntity;
import com.abs.siif.programming.entities.ValidationEntity;
import com.abs.siif.programming.management.DraftProjectManagement;
import com.abs.siif.security.entities.UserEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author luis.carreon
 */
public interface SeplanValidationControllerApi {

    void actualizarEstatusEnBitacora();

    /**
     * @return the theirComponentList
     */
    List<ComponentEntity> getComponentList();

    /**
     * @return the theirEvaluationMap
     */
    HashMap<ComponentEntity, ValidationEntity[]> getEvaluationMap();

    /**
     * @return the itsBtnSrvSave
     */
    boolean getBtnSrvSave();

    /**
     * @return the itsUser
     */
    UserEntity getItsUser();

    /**
     * @return the theirObservation
     */
    String getObservation();

    /**
     * @return the theirDraftProjectManagement
     */
    DraftProjectManagement getTheirDraftProjectManagement();

    /**
     * @return the theirPreguntas
     */
    ArrayList<ValidationQuestionDto> getTheirPreguntas();

    ArrayList<ValidationQuestionDto> getTheirPreguntasComponente();

    ArrayList<ValidationQuestionDto> getTheirPreguntasIndicadores();

    ArrayList<ValidationQuestionDto> getTheirPreguntasMetas();

    ArrayList<ValidationQuestionDto> getTheirPreguntasProposito();

    ArrayList<String> getTheirTitulos();

    /**
     * @return the theirValidationEntity
     */
    List<ValidationEntity> getValidationList();

    void init();

    /**
     * @return the theirSepladeActivo
     */
    boolean getBtnSrvChangeStatus();

    void saveSeplanValidation();

    /**
     * @param theirComponentList the theirComponentList to set
     */
    void setComponentList(List<ComponentEntity> theirComponentList);

    /**
     * @param theirEvaluationMap the theirEvaluationMap to set
     */
    void setEvaluationMap(HashMap<ComponentEntity, ValidationEntity[]> theirEvaluationMap);

    /**
     * @param itsBtnSrvSave the itsBtnSrvSave to set
     */
    void setBtnSrvSave(boolean itsBtnSrvSave);

    /**
     * @param itsUser the itsUser to set
     */
    void setItsUser(UserEntity itsUser);

    /**
     * @param theirObservation the theirObservation to set
     */
    void setObservation(String theirObservation);

    /**
     * @param theirSepladeActivo the theirSepladeActivo to set
     */
    void setBtnSrvChangeStatus(boolean aSepladeActivo);

    /**
     * @param theirDraftProjectManagement the theirDraftProjectManagement to set
     */
    void setTheirDraftProjectManagement(DraftProjectManagement theirDraftProjectManagement);

    /**
     * @param theirPreguntas the theirPreguntas to set
     */
    void setTheirPreguntas(ArrayList<ValidationQuestionDto> theirPreguntas);

    void setTheirPreguntasComponente(ArrayList<ValidationQuestionDto> theirPreguntasComponente);

    void setTheirPreguntasIndicadores(ArrayList<ValidationQuestionDto> theirPreguntasIndicadores);

    void setTheirPreguntasMetas(ArrayList<ValidationQuestionDto> theirPreguntasMetas);

    void setTheirPreguntasProposito(ArrayList<ValidationQuestionDto> theirPreguntasProposito);

    void setTheirTitulos(ArrayList<String> theirTitulos);

    /**
     * @param theirValidationEntity the theirValidationEntity to set
     */
    void setValidationList(List<ValidationEntity> aValidationList);
    
}
