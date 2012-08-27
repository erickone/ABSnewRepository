package com.abs.siif.planning.validator;

import com.abs.siif.planning.entities.ObjectiveLevelEntity;
import java.util.List;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 *
 */
public interface ObjectiveLevelBusinessValidator {

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
    boolean validateObjectiveLevelInSequence(ObjectiveLevelEntity anObjectiveLevel);

    /**
     *@param  anObjectiveLevel Nivel objetivo a validar
     * @return Solamente un nivel debe ser capaz de poder ser tomado encuenta
     * para realizar encuadres con la Unidad Ejecutora del Gasto, este metodo
     * valida si ya existe un nivel configurado para poder realizar encuadres
     * con la Unidad Ejecutora del Gasto, en caso de existir retornara un True,
     * de otra forma False.
     */
    boolean existObjectiveLevelWithRelationshipUEG(ObjectiveLevelEntity anObjectiveLevel);

    boolean canDeleteObjectiveLevels(List<ObjectiveLevelEntity> anObjectives);
}
