/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  LogicFrameDao
 *  Purpose:  [ Este archivo es el Dao, el cual se encargara de las operaciones
 *  de la base de datos, del proceso de marco logico, en esta enterfaz se 
 *  enlistaran las operaciones disponibles para si implementación]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.programming.dao;

import com.abs.siif.base.dao.SIIFBaseDao;
import com.abs.siif.common.entities.DocumentTypeEntity;
import com.abs.siif.programming.entities.LogicFrameEntity;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Erick Leija
 */

public interface LogicFrameDao extends SIIFBaseDao<LogicFrameEntity, Long>
{
    /**
     * este metodo sirve para buscar todos los tipos documentos de la base de 
     * Datos y traerlos a la vista, para realizar operaciones con ellos
     * @return una collection de DocumentTypeEntity
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
     * de un Anteproyecto
     * @param aLogicFrameEntity, una entidad formada para ser guardada en la
     * base de datos
     * @return Un String de el Id, de la entidad que se acaba de guardar
     */
    Long saveLogicFrameFileData(LogicFrameEntity aLogicFrameEntity);
    /**
     * Este metodo se encarga de borrar el registro que se ha seleccionado
     * @param aLogicFrameId recibe como parametro el Id del registro que se 
     * desea borrar
     * @return un string que indica como termino la operación de borrado,
     * ("success" si se ha completado con exito y "fail" si ha habido un error
     * en la operación de borrado
     */
    void deleteLogicFrameFileData(LogicFrameEntity aLogicFrameId);
    /**
     * Este metodo será el encargado de realizar las actualizaciones a la 
     * información que esta en la base de datos
     * @param aLogicFrameEntity recibe una entidad, la cual contiene los datos
     * actualizados que se desea plasmar en la base de datos
     * @return Regresa un string que indica como termino la operación de 
     * actualización ("success" si se ha completado con exito y 
     * "fail" si ha habido un error en la operación de actualización
     */
    Long updateLogicFrameFileData(LogicFrameEntity aLogicFrameEntity);
}
