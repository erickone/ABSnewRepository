package com.abs.siif.programming.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
@Entity
@Table(name="SiifPPPNivelProg")
public class ProgrammingLevelEntity implements 
        Serializable, Comparable<ProgrammingLevelEntity> {
    
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdNivelProgramacion")
    private Long programmingLevelId;

    @Column(name = "Clave", unique = true, nullable = false, length = 20)
    private String programmingLevelKey;
    @Column(name = "Descripcion", length = 150)
    private String programmingLevelDescription;
    @Column(name = "Nivel", unique = true, nullable = false)
    private short programmingLevel;
    @Column(name = "AnteProyecto")
    private boolean programmingLevelIsDraftProject;
    
    @OneToMany(mappedBy = "programmingLevel")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<ProgrammingEntity> programming;

    public Set<ProgrammingEntity> getProgramming() {
        return programming;
    }

    public Long getProgrammingLevelId() {
        return programmingLevelId;
    }

    public void setProgrammingLevelId(Long aProgrammingLevelId) {
        this.programmingLevelId = aProgrammingLevelId;
    }

    public short getProgrammingLevel() {
        return programmingLevel;
    }

    public void setProgrammingLevel(short aProgrammingLevel) {
        this.programmingLevel = aProgrammingLevel;
    }

    public String getProgrammingLevelDescription() {
        return programmingLevelDescription != null ? programmingLevelDescription.trim() : programmingLevelDescription;
    }

    public void setProgrammingLevelDescription(String aProgrammingLevelDescription) {
        this.programmingLevelDescription = aProgrammingLevelDescription;
    }

    public boolean isProgrammingLevelIsDraftProject() {
        return programmingLevelIsDraftProject;
    }

    public void setProgrammingLevelIsDraftProject(boolean aProgrammingLevelIsDraftProject) {
        this.programmingLevelIsDraftProject = aProgrammingLevelIsDraftProject;
    }

    public String getProgrammingLevelKey() {
        return programmingLevelKey != null ? programmingLevelKey.trim() : programmingLevelKey;
    }

    public void setProgrammingLevelKey(String aProgrammingLevelKey) {
        this.programmingLevelKey = aProgrammingLevelKey;
    }
        
    @Override
    public int compareTo(ProgrammingLevelEntity anOtherEntity) {
        int result = -1;
        if (this.programmingLevelId != null 
                && anOtherEntity.programmingLevelId != null) {
            result = this.programmingLevelId.compareTo(
                    anOtherEntity.programmingLevelId);
        }
        return result;
    }
    
    @Override
    public boolean equals(Object obj){
         boolean result = false;
         if (obj instanceof ProgrammingLevelEntity 
                 && this.programmingLevelId != null 
                 && ((ProgrammingLevelEntity) obj).programmingLevelId != null) {
            result = this.programmingLevelId.equals(
                    ((ProgrammingLevelEntity) obj).programmingLevelId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 97 * hash + (this.programmingLevelId != null 
                ? this.programmingLevelId.hashCode() : 0);
        return hash;
    }
}
