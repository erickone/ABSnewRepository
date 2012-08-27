package com.abs.siif.budget.entities;

import com.abs.siif.budget.data.TotalCostType;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.CascadeType;
import java.util.List;
import javax.persistence.FetchType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Cascade;

/**
 * @author Luis Aguistin Carreón
 *
 */
@Entity
@Table(name = "SIIFPPPObjetoGasto")
public class ObjectExpenseEntity
        implements Serializable, Comparable<ObjectExpenseEntity> {

    // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdObjetoGasto")
    private Long ObjectExpenseId;
    // Identificador del nivel del objeto del gasto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdNivelObjGasto", nullable = false)
    private ObjectExpenseLevelEntity objectExpenseLevelEnt;
    // Identificador del Tipo de Gasto
    @Column(name = "IdTipoGasto")
    private Long objectExpenseType;
    // Identificador de la Definición de la Clave Presupuestal
    @Column(name = "IdDefCvePresupuestal")
    private Long objectExpensePresupuestalDefinitionKey;
    // Clave del Objeto del Gasto
    @Column(name = "Clave", length = 20)
    private int objectExpenseKey;
    // Descripción del Objeto del Gasto
    @Column(name = "Descripcion", length = 150)
    private String objectExpenseDescription;
    // Bandera que indica que el Objeto del Gasto es Irreductible
    @Column(name = "Irreductible")
    private boolean objectExpenseIrreductible;
    // Bandera que indica que el Objeto del Gasto es Viático
    @Column(name = "Viatico")
    private boolean objectExpenseViaticum;
    // Bandera que indica que el Objeto del Gasto es Fondo
    @Column(name = "Fondo")
    private boolean objectExpenseBackground;
    // Bandera que indica que el Objeto del Gasto es Arrendamiento
    @Column(name = "Arrendamiento")
    private boolean objectExpenseLease;
    // Bandera que indica que el Objeto del Gasto es Comisión Bancaria
    @Column(name = "ComBancaria")
    private boolean objectExpenseBankComp;
    // Bandera que indica que el Objeto del Gasto es Partida Especial
    @Column(name = "PartidaEspecial")
    private boolean objectExpenseSpecialItem;
    // Bandera que indica que el Objeto del Gasto es Inversión Pública
    @Column(name = "InversionPublica")
    private boolean objectExpensePublicInvestment;
    // Bandera que indica que el Objeto del Gasto es Habilitada
    @Column(name = "Habilitada")
    private boolean objectExpenseEnabled;
    // Bandera que indica que el Objeto del Gasto Genera Resguardo
    @Column(name = "GenResguardo")
    private boolean objectExpenseGuardGen;
    // Bandera que indica que el Objeto del Gasto es de ADEFAS
    @Column(name = "ADEFAS")
    private boolean objectExpenseAdefas;
    // Bandera que indica que el Objeto del Gasto es Basica
    @Column(name = "basico")
    private boolean objectExpenseBasic;
    // Bandera que indica que el Objeto del Gasto es usado para Comprometido Inicial
    @Column(name = "CompInicial")
    private boolean objectExpenseInitialComp;
    //Aqui se declara la jerarquia de Arbol nodos hijos y nodo padre
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ObjectExpenseFather", fetch = FetchType.LAZY)
    Set<ObjectExpenseEntity> objectExpenseChilds;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdPadre", nullable = true)
    private ObjectExpenseEntity ObjectExpenseFather;
    // Columna para marcar el Objeto del Gasto con los Tipos Totales de Gasto
    @Column(name = "TipoTotalGasto")
    @Enumerated(EnumType.ORDINAL)
    private TotalCostType objectExpenseTotalCostType;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private transient BudgetingCeilingEntity budgetingCeiling;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "siifpppObjGastoDestino", joinColumns = {
        @JoinColumn(name = "IdObjetoGasto", nullable = false, updatable = false)},
    inverseJoinColumns = {
        @JoinColumn(name = "IdDestino",
        nullable = false, updatable = false)})
    private List<DestinationEntity> destinationObjectExpense;
    // Relacion Many to Many con la Fuente de Financiamiento
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "siifpppObjGastoFueFinan", joinColumns = {
        @JoinColumn(name = "IdObjetoGasto", nullable = false, updatable = false)},
    inverseJoinColumns = {
        @JoinColumn(name = "IdFuenteFinanciamiento",
        nullable = false, updatable = false)})
    private List<FinancingSourceEntity> financingSourceObjectExpense;
    @OneToMany(mappedBy = "objectExpenseBudgetKey", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<BudgetKeyEntity> budgetKeyObjectExpense;
    
    
    //Liga a Presupuesto adicional
    @OneToMany(mappedBy = "budgetKeyAdditionalObjectExpense",
            fetch= FetchType.LAZY)
    private List<BudgetKeyAdditionalEntity> budgetKeyAdditionals;

    
    
    
    public List<BudgetKeyAdditionalEntity> getBudgetKeyAdditionals() {
        return budgetKeyAdditionals;
    }

    public Set<BudgetKeyEntity> getBudgetKeyObjectExpense() {
        return budgetKeyObjectExpense;
    }

    public List<FinancingSourceEntity> getFinancingSourceObjectExpense() {
        return financingSourceObjectExpense;
    }

    public List<DestinationEntity> getDestinationObjectExpense() {
        return destinationObjectExpense;
    }

    public void setDestinationObjectExpense(List<DestinationEntity> destinationObjectExpense) {
        this.destinationObjectExpense = destinationObjectExpense;
    }

    public Long getObjectExpenseId() {
        return ObjectExpenseId;
    }

    public void setObjectExpenseId(Long ObjectExpenseId) {
        this.ObjectExpenseId = ObjectExpenseId;
    }

    public ObjectExpenseLevelEntity getObjectExpenseLevelEnt() {
        return objectExpenseLevelEnt;
    }

    public void setObjectExpenseLevelEnt(ObjectExpenseLevelEntity objectExpenseLevelEnt) {
        this.objectExpenseLevelEnt = objectExpenseLevelEnt;
    }

    public TotalCostType getObjectExpenseTotalCostType() {
        return objectExpenseTotalCostType;
    }

    public void setObjectExpenseTotalCostType(TotalCostType objectExpenseTotalCostType) {
        this.objectExpenseTotalCostType = objectExpenseTotalCostType;
    }

    public boolean isObjectExpenseAdefas() {
        return objectExpenseAdefas;
    }

    public void setObjectExpenseAdefas(boolean objectExpenseAdefas) {
        this.objectExpenseAdefas = objectExpenseAdefas;
    }

    public boolean isObjectExpenseBackground() {
        return objectExpenseBackground;
    }

    public void setObjectExpenseBackground(boolean objectExpenseBackground) {
        this.objectExpenseBackground = objectExpenseBackground;
    }

    public boolean isObjectExpenseBankComp() {
        return objectExpenseBankComp;
    }

    public void setObjectExpenseBankComp(boolean objectExpenseBankComp) {
        this.objectExpenseBankComp = objectExpenseBankComp;
    }

    public String getObjectExpenseDescription() {
        return objectExpenseDescription != null ? objectExpenseDescription.trim()
                : objectExpenseDescription;
    }

    public void setObjectExpenseDescription(String objectExpenseDescription) {
        this.objectExpenseDescription = objectExpenseDescription;
    }

    public boolean isObjectExpenseEnabled() {
        return objectExpenseEnabled;
    }

    public void setObjectExpenseEnabled(boolean objectExpenseEnabled) {
        this.objectExpenseEnabled = objectExpenseEnabled;
    }

    public boolean isObjectExpenseGuardGen() {
        return objectExpenseGuardGen;
    }

    public void setObjectExpenseGuardGen(boolean objectExpenseGuardGen) {
        this.objectExpenseGuardGen = objectExpenseGuardGen;
    }

    public ObjectExpenseLevelEntity getobjectExpenseLevelEnt() {
        return objectExpenseLevelEnt;
    }

    public void setObjectExpenseId(ObjectExpenseLevelEntity objectExpenseLevelEnt) {
        this.objectExpenseLevelEnt = objectExpenseLevelEnt;
    }

    public boolean isObjectExpenseInitialComp() {
        return objectExpenseInitialComp;
    }

    public void setObjectExpenseInitialComp(boolean objectExpenseInitialComp) {
        this.objectExpenseInitialComp = objectExpenseInitialComp;
    }

    public boolean isObjectExpenseIrreductible() {
        return objectExpenseIrreductible;
    }

    public void setObjectExpenseIrreductible(boolean objectExpenseIrreductible) {
        this.objectExpenseIrreductible = objectExpenseIrreductible;
    }

    public int getObjectExpenseKey() {
        return objectExpenseKey;
    }

    public void setObjectExpenseKey(int objectExpenseKey) {
        this.objectExpenseKey = objectExpenseKey;
    }

    public boolean isObjectExpenseLease() {
        return objectExpenseLease;
    }

    public void setObjectExpenseLease(boolean objectExpenseLease) {
        this.objectExpenseLease = objectExpenseLease;
    }

    public Long getObjectExpensePresupuestalDefinitionKey() {
        return objectExpensePresupuestalDefinitionKey;
    }

    public void setObjectExpensePresupuestalDefinitionKey(Long objectExpensePresupuestalDefinitionKey) {
        this.objectExpensePresupuestalDefinitionKey = objectExpensePresupuestalDefinitionKey;
    }

    public boolean isObjectExpensePublicInvestment() {
        return objectExpensePublicInvestment;
    }

    public void setObjectExpensePublicInvestment(boolean objectExpensePublicInvestment) {
        this.objectExpensePublicInvestment = objectExpensePublicInvestment;
    }

    public boolean isObjectExpenseSpecialItem() {
        return objectExpenseSpecialItem;
    }

    public void setObjectExpenseSpecialItem(boolean objectExpenseSpecialItem) {
        this.objectExpenseSpecialItem = objectExpenseSpecialItem;
    }

    public Long getObjectExpenseType() {
        return objectExpenseType;
    }

    public void setObjectExpenseType(Long objectExpenseType) {
        this.objectExpenseType = objectExpenseType;
    }

    public boolean isObjectExpenseViaticum() {
        return objectExpenseViaticum;
    }

    public void setObjectExpenseViaticum(boolean objectExpenseViaticum) {
        this.objectExpenseViaticum = objectExpenseViaticum;
    }

    public ObjectExpenseEntity getObjectExpenseFather() {
        return ObjectExpenseFather;
    }

    public void setObjectExpenseFather(ObjectExpenseEntity ObjectExpenseFather) {
        this.ObjectExpenseFather = ObjectExpenseFather;
    }

    public BudgetingCeilingEntity getBudgetingCeiling() {
        return budgetingCeiling;
    }

    public void setBudgetingCeiling(BudgetingCeilingEntity budgetingCeiling) {
        this.budgetingCeiling = budgetingCeiling;
    }

    public Set<ObjectExpenseEntity> getObjectExpenseChilds() {
        return objectExpenseChilds;
    }

    public void setObjectExpenseChilds(Set<ObjectExpenseEntity> objectExpenseChilds) {
        this.objectExpenseChilds = objectExpenseChilds;
    }

    public boolean isObjectExpenseBasic()
    {
        return objectExpenseBasic;
    }

    public void setObjectExpenseBasic(boolean objectExpenseBasic)
    {
        this.objectExpenseBasic = objectExpenseBasic;
    }
    
    

    @Override
    public int compareTo(ObjectExpenseEntity anOtherEntity) {
        return this.ObjectExpenseId.compareTo(
                anOtherEntity.ObjectExpenseId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ObjectExpenseEntity) {
            return this.ObjectExpenseId.equals(
                    ((ObjectExpenseEntity) obj).ObjectExpenseId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.ObjectExpenseId != null
                ? this.ObjectExpenseId.hashCode() : 0);
        return hash;
    }
}
