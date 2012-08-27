package com.abs.siif.common.dto;

import java.io.Serializable;
import java.util.Comparator;
import java.util.UUID;

/**
 *
 * @author Juan Zavala
 */
public class SampleEntityDto implements
        Comparable<SampleEntityDto>, Serializable,
        Comparator {

    private Long itsId;
    private String itsDescription;
    private Long institucionalObjectiveId;
    private String itsGenericItemId;

    public SampleEntityDto(Long anId, String aDescription) {
        this.itsId = anId;
        this.itsDescription = aDescription;
    }

    /*
     * La generación de el itsGenericItemId, es para el manejo de persistencia
     * virtual en las listas de del plan estrategico
     */
    public SampleEntityDto(String aDescription) {
        
        this.itsGenericItemId = UUID.randomUUID().toString();
        this.itsDescription = aDescription;
    }

    public String getItsDescription() {
        return itsDescription;
    }

    public void setItsDescription(String aDescription) {
        this.itsDescription = aDescription;
    }

    public Long getItsId() {
        return itsId;
    }

    public void setItsId(Long anId) {
        this.itsId = anId;
    }

    public Long getInstitucionalObjectiveId() {
        return institucionalObjectiveId;
    }

    public void setInstitucionalObjectiveId(Long institucionalObjectiveId) {
        this.institucionalObjectiveId = institucionalObjectiveId;
    }

    public String getItsGenericItemId() {
        return itsGenericItemId;
    }

    public void setItsGenericItemId(String itsGenericItemId) {
        this.itsGenericItemId = itsGenericItemId;
    }
    
    

    @Override
    public int compare(Object anObject1, Object anObject2) {
        SampleEntityDto myComparatorA = (SampleEntityDto) anObject1;
        SampleEntityDto myComparatorB = (SampleEntityDto) anObject2;

        return myComparatorA.getItsDescription().compareTo(myComparatorB.getItsDescription());

    }

    @Override
    public int compareTo(SampleEntityDto anOtherEntity) {
      int result = -1;
        if (this.itsId!= null
                && anOtherEntity.itsId != null) {
            result = this.itsId.compareTo(
                    anOtherEntity.itsId);
        }
        return result;
    }
}