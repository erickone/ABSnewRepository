/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dto;

/**
 *Esta enueración sirve para administrar los cambios de estatus
 * @author jacob.flores
 */
public enum DraftProjectStatusEnum
{
  INICIADO("name.iniciado"),
  VALIDADO_SEPLAN("name.validadoSeplan"),
  VALIDADO_SEFIN("name.validadoSefin"),
  AUTORIZADO("name.autorizado");
    
    private String name;
    private DraftProjectStatusEnum(String arg){
        this.name = arg;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}