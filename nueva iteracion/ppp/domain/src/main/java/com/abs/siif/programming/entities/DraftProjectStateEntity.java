/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 *Esta clae provve el mapeo necesario para la entidad
 * de DraftProjectState, que se refiere al estado del proyecto.
 * @author jacob.flores
 */
@Entity
@Table(name = "siifpppEstadoAnteProy")
public class DraftProjectStateEntity implements Serializable, Comparable<DraftProjectStateEntity>
{
   // Identificador
    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "IdEstadoAnteProyecto", nullable = false)
    private Long draftProjectStateId;
    
    // Descripción del estado de anteproyecto
    @Column(name="Descripcion",nullable=false,length=150)
    private String draftProjectStateDescription;

        // Mapeo con entidad anteproyecto (DraftProject)
    @OneToMany(mappedBy = "draftProjectState")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Set<DraftProjectEntity> draftProjects;
    
  @Override
  public int compareTo(DraftProjectStateEntity o)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * @return the draftProjectStateId
   */
  public Long getDraftProjectStateId()
  {
    return draftProjectStateId;
  }

  /**
   * @param draftProjectStateId the draftProjectStateId to set
   */
  public void setDraftProjectStateId(Long draftProjectStateId)
  {
    this.draftProjectStateId = draftProjectStateId;
  }

  /**
   * @return the draftProjectStateDescription
   */
  public String getDraftProjectStateDescription()
  {
    return draftProjectStateDescription != null ? draftProjectStateDescription.trim() : draftProjectStateDescription;
  }

  /**
   * @param draftProjectStateDescription the draftProjectStateDescription to set
   */
  public void setDraftProjectStateDescription(String draftProjectStateDescription)
  {
    this.draftProjectStateDescription = draftProjectStateDescription;
  }

  /**
   * @return the draftProjects
   */
  public Set<DraftProjectEntity> getDraftProjects()
  {
    return draftProjects;
  }

  /**
   * @param draftProjects the draftProjects to set
   */
  public void setDraftProjects(Set<DraftProjectEntity> draftProjects)
  {
    this.draftProjects = draftProjects;
  }

  }