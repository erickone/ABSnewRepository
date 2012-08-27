package com.abs.siif.planning.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "siifpppProblema")
public class ProblemEntity implements 
        Comparable<ProblemEntity>, Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdProblema")
    private Long problemId;
    @ManyToOne
    @JoinColumn(name = "IdObjetivo", nullable = false)
    private ObjectiveEntity objective;
    @Column(name = "Descripcion", length = 150)
    private String problemDescription;

    public Long getProblemId() {
        return this.problemId;
    }

    public ObjectiveEntity getObjective() {
        return this.objective;
    }

    public String getProblemDescription() {
        return this.problemDescription != null ? problemDescription.trim() : problemDescription;
    }

    public void setProblemDescription(String aProblemDescription) {
        this.problemDescription = aProblemDescription;
    }

    protected void setObjective(ObjectiveEntity anObjective) {
        this.objective = anObjective;
    }

    public void seProblemId(Long aProblemId) {
        this.problemId = aProblemId;
    }

    @Override
    public int compareTo(ProblemEntity obj)
    {
        int result = -1;
        if (this.problemId != null && obj.problemId != null) {
        result = this.problemId.compareTo(obj.problemId);
        }
        return result;
    }
    
    @Override
    public boolean equals(Object obj){
        boolean result = false;
         if (obj instanceof ProblemEntity && this.problemId != null 
                 && ((ProblemEntity) obj).problemId != null) {
            result = this.problemId.equals(
                    ((ProblemEntity) obj).problemId);
        }

        return result;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 79 * hash + (this.problemId != null 
                ? this.problemId.hashCode() : 0);
        return hash;
    }
}
