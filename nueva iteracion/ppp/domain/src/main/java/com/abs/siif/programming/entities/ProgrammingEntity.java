package com.abs.siif.programming.entities;

import com.abs.siif.planning.entities.DependenceEntity;
import com.abs.siif.planning.entities.ObjectiveEntity;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Entity
@Table(name = "SiifPPPProgramacion")
public class ProgrammingEntity implements
        Serializable, Comparable<ProgrammingEntity> {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdProgramacion")
    private Long programmingId;
    @Column(name = "Clave", length = 20)
    private String programmingKey;
    @Column(name = "Descripcion", length = 150)
    private String programmingDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "father")
    Set<ProgrammingEntity> programmingChilds;
    @ManyToOne
    @JoinColumn(name = "IdPadre", nullable = true)
    private ProgrammingEntity father;
    @ManyToOne
    @JoinColumn(name = "IdNivelProgramacion", nullable = false)
    private ProgrammingLevelEntity programmingLevel;
    
     @OneToOne(mappedBy = "draftProjectProgramming")
    private DraftProjectEntity draftProject;

    public DraftProjectEntity getDraftProject() {
        return draftProject;
    }

    public void setDraftProject(DraftProjectEntity draftProject) {
        this.draftProject = draftProject;
    }
    
     
    /**
     * Dependencia a la que pertenece el objetivo
     */
    @ManyToOne
    @JoinColumn(name = "IdDependencia", nullable = false)
    private DependenceEntity programmingDependency;
    /**
     * Programación a la que pertenece el objetivo
     */
    @ManyToOne
    @JoinColumn(name = "IdObjetivo", nullable = false)
    private ObjectiveEntity programmingObjective;


    

    public DependenceEntity getProgrammingDependency() {
        return programmingDependency;
    }

    public void setProgrammingDependency(DependenceEntity aProgrammingDependency) {
        this.programmingDependency = aProgrammingDependency;
    }

    public ProgrammingLevelEntity getProgrammingLevel() {
        return programmingLevel;
    }

    public void setProgrammingLevel(ProgrammingLevelEntity aProgrammingLevel) {
        this.programmingLevel = aProgrammingLevel;
    }

    public ProgrammingEntity getFather() {
        return father;
    }

    public void setFather(ProgrammingEntity aFather) {
        this.father = aFather;
    }

    public Set<ProgrammingEntity> getProgrammingChilds() {
        return programmingChilds;
    }

    public String getProgrammingDescription() {
        return programmingDescription != null ? programmingDescription.trim() : programmingDescription ;
    }

    public void setProgrammingDescription(String aProgrammingDescription) {
        this.programmingDescription = aProgrammingDescription;
    }

    public Long getProgrammingId() {
        return programmingId;
    }

    public void setProgrammingId(Long aProgrammingId) {
        this.programmingId = aProgrammingId;
    }

    public String getProgrammingKey() {
        return programmingKey != null ? programmingKey.trim() : programmingKey;
    }

    public void setProgrammingKey(String aProgrammingKey) {
        this.programmingKey = aProgrammingKey;
    }

    public ObjectiveEntity getProgrammingObjective() {
        return programmingObjective;
    }

    public void setProgrammingObjective(ObjectiveEntity aProgrammingObjective) {
        this.programmingObjective = aProgrammingObjective;
    }

    @Override
    public int compareTo(ProgrammingEntity anOtherEntity) {
        int result = -1;
        if (this.programmingId != null && anOtherEntity.programmingId != null) {
            result = this.programmingId.compareTo(anOtherEntity.programmingId);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof ProgrammingEntity && this.programmingId != null
                && ((ProgrammingEntity) obj).programmingId != null) {
            result = this.programmingId.equals(
                    ((ProgrammingEntity) obj).programmingId);
        }

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.programmingId != null
                ? this.programmingId.hashCode() : 0);
        hash = 79 * hash + (this.programmingKey != null
                ? this.programmingKey.hashCode() : 0);
        return hash;
    }
}
