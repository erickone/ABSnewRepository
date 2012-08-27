/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  FuncClassifLevelBussinessValidator
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.planning.validator;

import com.abs.siif.planning.entities.FunctionalLevelClassifier;
import java.util.List;

/**
 *
 * @author FENIX-02
 */
public interface FuncClassifLevelBussinessValidator
{
    /**
     *
     * @param aLevel Indica el valor numerico del nivel a salvar, este debe ser
     * mayor al ultimo existente en base de datos, la estructura jerarquica
     * siempre debe tener un valor secuencial 1,2,3, etc. El valor 4 en la
     * siguiente cadena 1,2, 4 es invalido.
     * @return La responsabilidad de este metodo es devolver True si el nivel a
     * guardar es secuencial de acuerdo al ultimo registro existente.Por ejemplo
     * si existen 1,2,3,4,5 y el
     * @param aLevel tiene un valor 6 el metodo retornara True, en caso de no
     * cumplir de forma secuencial con el ultimo existen retornara False
     */
    boolean validateFuncClassifLevelInSequence(FunctionalLevelClassifier anFCLevel);

    /**
     *@param  anObjectiveLevel Nivel objetivo a validar
     * @return Solamente un nivel debe ser capaz de poder ser tomado encuenta
     * para realizar encuadres con la Planeacion Estrategica, este metodo
     * valida si ya existe un nivel configurado para poder realizar encuadres
     * con la Unidad Ejecutora del Gasto, en caso de existir retornara un True,
     * de otra forma False.
     */
    boolean existFuncClassifLevelWithRelationshipPlanEst(FunctionalLevelClassifier anFCLevel);
    
    /**
     * Este metodo valida si la lista de elementos a borrar va en orden 
     * secuencial. Teniendo niveles 1,2,3,4,5 es valido borrar solo el nivel 5 o
     * los niveles 4 y 5, es invalido borrar solo el nivel 4 o los niveles 5 y
     * otro que no sea 4.
     * @param anEntitieslist
     * @return 
     */
    boolean canDeleteFuncClassifLevels(List<FunctionalLevelClassifier> anEntitieslist);
    
    /**
     * Este metodo revisa que la clave del nivel de clasificador funcional no 
     * se repita.
     * @param anFCLevel
     * @return 
     */
    boolean isFuncClassifKeyValid(FunctionalLevelClassifier anFCLevel);
}
