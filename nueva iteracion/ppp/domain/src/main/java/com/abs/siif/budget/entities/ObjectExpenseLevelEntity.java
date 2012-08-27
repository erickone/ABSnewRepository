package com.abs.siif.budget.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Cascade;
import java.util.Set;
/**
 *
 * @author Juan Antonio Zavala Aguilar
 * Mapea la entidad de negocio Niveles del Objeto del gasto
 */
@Entity
@Table(name = "SIIFPPPNivelObjGasto")
public class ObjectExpenseLevelEntity implements
        Comparable<ObjectExpenseLevelEntity>, Serializable{

    // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdNivelObjGasto")
    private Long objectExpenseLevelId;

    // Clave del Nivel de Objeto del Gasto
    @Column(name = "Clave", length = 20)
    private String objectExpenseLevelKey;

    // Descripción del Nivel de Objeto del Gasto
    @Column(name = "Descripcion", length = 150)
    private String objectExpenseDescription;

    // Número para el Nivel
    @Column(name = "Nivel")
    private int objectExpenseLevel;

    // Prefijo para el armado de la clave del Objeto del Gasto
    @Column(name = "Prefijo", length = 10)
    private String objectExpenseLevelPrefix;

    // Número de Caracteres para el armado de la clave del Objeto del Gasto
    @Column(name = "NumCaracteres")
    private int objectExpenseLevelNumberCharacters;

    // Caracteres de relleno para el armado de la clave del Objeto del Gasto
    @Column(name = "CarRelleno", length = 1)
    private String objectExpenseLevelFillCharacters;

    // Bandera que indica si el Nivel de Objeto del Gasto se relaciona con el clasificador econóico
    @Column(name = "ClasifEconomico")
    private boolean objectExpenseEconomicClasific;

    @Column(name = "Capitulo")
    private boolean objectExpenseChapter;

    @Column(name = "Concepto")
    private boolean objectExpenseConcept;

    @Column(name = "ParGenerica")
    private boolean objectExpenseGenericPar;

    @Column(name = "ParEspecifica")
    private boolean objectExpenseSpecificPar;

    @OneToMany(mappedBy = "objectExpenseLevelEnt")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE}) private
    Set<ObjectExpenseEntity> levelObjectExpense;

    public int getObjectExpenseLevel() {
        return objectExpenseLevel;
    }

    public void setObjectExpenseLevel(int objectExpenseLevel) {
        this.objectExpenseLevel = objectExpenseLevel;
    }

    public String getObjectExpenseLevelFillCharacters() {
        return objectExpenseLevelFillCharacters != null ? objectExpenseLevelFillCharacters.trim() :
               objectExpenseLevelFillCharacters;
    }

    public void setObjectExpenseLevelFillCharacters(String objectExpenseLevelFillCharacters) {
        this.objectExpenseLevelFillCharacters = objectExpenseLevelFillCharacters;
    }

    public Long getObjectExpenseLevelId() {
        return objectExpenseLevelId;
    }

    public void setObjectExpenseLevelId(Long objectExpenseLevelId) {
        this.objectExpenseLevelId = objectExpenseLevelId;
    }

    public String getObjectExpenseLevelKey() {
        return objectExpenseLevelKey != null ? objectExpenseLevelKey.trim() :
               objectExpenseLevelKey;
    }

    public void setObjectExpenseLevelKey(String objectExpenseLevelKey) {
        this.objectExpenseLevelKey = objectExpenseLevelKey;
    }

    public int getObjectExpenseLevelNumberCharacters() {
        return objectExpenseLevelNumberCharacters;
    }

    public void setObjectExpenseLevelNumberCharacters(int objectExpenseLevelNumberCharacters) {
        this.objectExpenseLevelNumberCharacters = objectExpenseLevelNumberCharacters;
    }

    public String getObjectExpenseLevelPrefix() {
        return objectExpenseLevelPrefix != null ? objectExpenseLevelPrefix.trim() :
               objectExpenseLevelPrefix;
    }

    public void setObjectExpenseLevelPrefix(String objectExpenseLevelPrefix) {
        this.objectExpenseLevelPrefix = objectExpenseLevelPrefix;
    }

    public String getObjectExpenseDescription() {
        return objectExpenseDescription != null ? objectExpenseDescription.trim() :
               objectExpenseDescription;
    }

    public void setObjectExpenseDescription(String objectExpenseDescription) {
        this.objectExpenseDescription = objectExpenseDescription;
    }

    public boolean getObjectExpenseEconomicClasific() {
        return objectExpenseEconomicClasific;
    }

    public void setObjectExpenseEconomicClasific(boolean objectExpenseEconomicClasific) {
        this.objectExpenseEconomicClasific = objectExpenseEconomicClasific;
    }

    public boolean isObjectExpenseChapter()
    {
        return objectExpenseChapter;
    }

    public void setObjectExpenseChapter(boolean objectExpenseChapter)
    {
        this.objectExpenseChapter = objectExpenseChapter;
    }

    public boolean isObjectExpenseConcept()
    {
        return objectExpenseConcept;
    }

    public void setObjectExpenseConcept(boolean objectExpenseConcept)
    {
        this.objectExpenseConcept = objectExpenseConcept;
    }

    public boolean isObjectExpenseGenericPar()
    {
        return objectExpenseGenericPar;
    }

    public void setObjectExpenseGenericPar(boolean objectExpenseGenericPar)
    {
        this.objectExpenseGenericPar = objectExpenseGenericPar;
    }

    public boolean isObjectExpenseSpecificPar()
    {
        return objectExpenseSpecificPar;
    }

    public void setObjectExpenseSpecificPar(boolean objectExpenseSpecificPar)
    {
        this.objectExpenseSpecificPar = objectExpenseSpecificPar;
    }



    @Override
    public int compareTo(ObjectExpenseLevelEntity anOtherEntity) {
        return this.objectExpenseLevelId.compareTo(
                anOtherEntity.objectExpenseLevelId);
    }

    @Override
    public boolean equals(Object obj){
         if (obj instanceof ObjectExpenseLevelEntity) {
            return this.objectExpenseLevelId.equals(
                    ((ObjectExpenseLevelEntity)
                    obj).objectExpenseLevelId);
        }

        return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 89 * hash + (this.objectExpenseLevelId != null
                ? this.objectExpenseLevelId.hashCode() : 0);
        return hash;
    }

}
