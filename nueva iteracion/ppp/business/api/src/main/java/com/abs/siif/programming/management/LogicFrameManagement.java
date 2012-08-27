/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  LogicFrameManagement
 *  Purpose:  [ Esta clase es la interfaz del management del marco logico
 *  el cual se encarga de realizar todas las validaciones a nivel negocio
 *  para el guardado de la información de los archivos que son guardados en 
 *  el servidor, para complementar la información de un  anteproyecto]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.management;

import com.abs.siif.common.entities.DocumentTypeEntity;
import com.abs.siif.programming.entities.LogicFrameEntity;
import java.util.Collection;

/**
 * Aqui se enlistan todas las actividades que realizara la implementación del
 * management, como lo son guardar, borrar, actualizar y listar los registros
 * de la base de datos
 * @author Erick Leija
 */
public interface LogicFrameManagement 
{
    /**
     * Este metodo regresa desde el Dao una collection de los diferentes
     * tipos de documentos que existen en la base de datos
     * @return
     */
    Collection<DocumentTypeEntity> getAllDocumentTypes();
    
   /**
     * Este metodo regresa una Lista(en forma de Collection), de los datos 
     * de los Archivos que se han guardado en el servidor, mediante el
     * Identificador del Anteproyecto
     * @param aDraftProjectId un String que representa un Id de un Anteproyecto
     * @return Una Collection de Regristros de la tabla de Marco Lógico
     */
    Collection<LogicFrameEntity> getLogicFrameByDraftProjectID(Long aDraftProjectId);
    /**
     * Este metodo se utliza para guardar los datos de un archivo(nombre, tipo
     * path, id) que ha sido subido al servidor para complementar la información
     * de un Anteproyecto, aqui se realizan todas las validaciones a nivel 
     * que afectan al agardado de la información de los archivos guardados
     * @param aLogicFrameEntity, una entidad formada para ser guardada en la
     * base de datos
     * @return Un String de el Id, de la entidad que se acaba de guardar
     */
    Long saveLogicFrameFileData(LogicFrameEntity aLogicFrameEntity);
    /**
     * Este metodo se encarga de borrar el registro que se ha seleccionado, 
     * ademas de realizarse las validaciones a nivel negocio y las condiciones
     * para el antes y despues del borrado de un registro de la base de datos
     * @param aLogicFrameId recibe como parametro el Id del registro que se 
     * desea borrar
     * @return un string que indica como termino la operación de borrado,
     * ("success" si se ha completado con exito y "fail" si ha habido un error
     * en la operación de borrado
     */
    void deleteLogicFrameFileData(LogicFrameEntity aLogicFrameId);
    /**
     * Este metodo será el encargado de realizar las actualizaciones a la 
     * información que esta en la base de datos, aqui se realiza además todas las 
     * validaciones de negocio, así como las precondiciones y las postcondiciones
     * necesarias para llevar a cabo esta actividad
     * @param aLogicFrameEntity recibe una entidad, la cual contiene los datos
     * actualizados que se desea plasmar en la base de datos
     * @return Regresa un string que indica como termino la operación de 
     * actualización ("success" si se ha completado con exito y 
     * "fail" si ha habido un error en la operación de actualización
     */
    Long updateLogicFrameFileData(LogicFrameEntity aLogicFrameEntity);
}
