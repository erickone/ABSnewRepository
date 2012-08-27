/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.planning.entities;

import com.abs.siif.budget.entities.ObjectExpenseEntity;
import com.abs.siif.programming.entities.ActionGBEntity;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author abs71
 */
@Entity
@Table(name = "siifpppencaccionobjetogasto")
public class ObjectExpenseActionEntity
        implements Serializable, Comparable<ObjectExpenseActionEntity> {

    // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idencaccionobjetogasto")
    private Long ObjectExpenseActionId;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private transient ObjectExpenseEntity objectExpenseEntity;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private transient ActionGBEntity actionGBEntity;

    public Long getObjectExpenseActionId() {
        return ObjectExpenseActionId;
    }

    public void setObjectExpenseActionId(Long ObjectExpenseActionId) {
        this.ObjectExpenseActionId = ObjectExpenseActionId;
    }

    public ActionGBEntity getActionGBEntity() {
        return actionGBEntity;
    }

    public void setActionGBEntity(ActionGBEntity actionGBEntity) {
        this.actionGBEntity = actionGBEntity;
    }

    public ObjectExpenseEntity getObjectExpenseEntity() {
        return objectExpenseEntity;
    }

    public void setObjectExpenseEntity(ObjectExpenseEntity objectExpenseEntity) {
        this.objectExpenseEntity = objectExpenseEntity;
    }

    @Override
    public int compareTo(ObjectExpenseActionEntity o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
