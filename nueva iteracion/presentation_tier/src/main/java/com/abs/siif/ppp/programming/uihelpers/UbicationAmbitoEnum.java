/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.uihelpers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Israel Ruiz
 */
public enum UbicationAmbitoEnum {
   PAIS(true,"Pais"),
   ESTADO(true,"Estado"),
   REGION(true,"Region"),
   MUNICIPIO(true,"Municipio"),
   LOCALIDAD(true,"Localidad"),
   COLONIA(true,"Colonia");

   private boolean ambito;
   private String name;

   UbicationAmbitoEnum(boolean ambito, String name){
       this.ambito = ambito;
       this.name = name;
   }

   public static List<UbicationAmbitoEnum> getValues(){
       List<UbicationAmbitoEnum> ubLts = new ArrayList<UbicationAmbitoEnum>();
       for(UbicationAmbitoEnum ubication : UbicationAmbitoEnum.values()){
           if(ubication.isAmbito()){
            ubLts.add(ubication);
           }
       }
       return ubLts;
   }
    /**
     * @return the ambito
     */
    public boolean isAmbito() {
        return ambito;
    }

    /**
     * @param ambito the ambito to set
     */
    public void setAmbito(boolean ambito) {
        this.ambito = ambito;
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
