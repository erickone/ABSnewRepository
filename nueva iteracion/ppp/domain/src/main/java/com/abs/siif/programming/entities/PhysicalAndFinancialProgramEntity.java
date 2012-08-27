package com.abs.siif.programming.entities;

import com.abs.siif.planning.data.PhysicalAndFinancialProgramPeriod;
import com.abs.siif.planning.data.PhysicalAndFinancialProgramType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import org.hibernate.annotations.GenericGenerator;


/**
 *Esta clase es la entidad para el encabezado del Programa Físico y Financiero
 * @author jacob.flores
 */


@Entity
@Table(name = "siifpppprogfisfinanciero")
public class PhysicalAndFinancialProgramEntity
        implements Serializable, Comparable<PhysicalAndFinancialProgramEntity>
{

  @Id
  @GenericGenerator(name = "generator", strategy = "native")
  @GeneratedValue(generator = "generator")
  @Column(name = "IdProgFisFinanciero", nullable = false)
  private Long progPhysicFinancialId;
  
  @Column(name = "ProgFisFinancieroEstatus", nullable = false)
  @Enumerated(EnumType.ORDINAL)
  private PhysicalAndFinancialProgramType progPhysicFinancialType;
  
  @Column(name = "ProgFisFinancieroFechaIni")
  @Temporal(javax.persistence.TemporalType.DATE)
  private Date progPhysicFinancialStartDate;
  
  @Column(name = "ProgFisFinancieroFechaFinal")
  @Temporal(javax.persistence.TemporalType.DATE)
  private Date progPhysicFinancialEndDate;
  
  @Column(name = "ProgFisFinancieroPeriodo")
  @Enumerated(EnumType.ORDINAL)
  private PhysicalAndFinancialProgramPeriod progPhysicFinancialPeriod;
  
  //PreFicha
  @ManyToOne(fetch= FetchType.LAZY)
  @JoinColumn(name = "IdPreFicha")
  private InvPreFileEntity physicalAndFinancialProgramInvPreFile;

    public InvPreFileEntity getPhysicalAndFinancialProgramInvPreFile() {
        return physicalAndFinancialProgramInvPreFile;
    }

    public void setPhysicalAndFinancialProgramInvPreFile(InvPreFileEntity physicalAndFinancialProgramInvPreFile) {
        this.physicalAndFinancialProgramInvPreFile = physicalAndFinancialProgramInvPreFile;
    }
  

  /**
   * @return the progPhysicFinancialId
   */
  public Long getProgPhysicFinancialId() 
  {
    return progPhysicFinancialId;
  }

  /**
   * @param progPhysicFinancialId the progPhysicFinancialId to set
   */
  public void setProgPhysicFinancialId(Long progPhysicFinancialId) 
  {
    this.progPhysicFinancialId = progPhysicFinancialId;
  }

  /**
   * @return the progPhysicFinancialType
   */
  public PhysicalAndFinancialProgramType getProgPhysicFinancialType() 
  {
    return progPhysicFinancialType;
  }

  /**
   * @param progPhysicFinancialType the progPhysicFinancialType to set
   */
  public void setProgPhysicFinancialType(PhysicalAndFinancialProgramType progPhysicFinancialType) 
  {
    this.progPhysicFinancialType = progPhysicFinancialType;
  }

  /**
   * @return the progPhysicFinancialStartDate
   */
  public Date getProgPhysicFinancialStartDate() 
  {
    return progPhysicFinancialStartDate;
  }

  /**
   * @param progPhysicFinancialStartDate the progPhysicFinancialStartDate to set
   */
  public void setProgPhysicFinancialStartDate(Date progPhysicFinancialStartDate) 
  {
    this.progPhysicFinancialStartDate = progPhysicFinancialStartDate;
  }

  /**
   * @return the progPhysicFinancialEndDate
   */
  public Date getProgPhysicFinancialEndDate() 
  {
    return progPhysicFinancialEndDate;
  }

  /**
   * @param progPhysicFinancialEndDate the progPhysicFinancialEndDate to set
   */
  public void setProgPhysicFinancialEndDate(Date progPhysicFinancialEndDate) 
  {
    this.progPhysicFinancialEndDate = progPhysicFinancialEndDate;
  }

  /**
   * @return the progPhysicFinancialPeriod
   */
  public PhysicalAndFinancialProgramPeriod getProgPhysicFinancialPeriod() 
  {
    return progPhysicFinancialPeriod;
  }

  /**
   * @param progPhysicFinancialPeriod the progPhysicFinancialPeriod to set
   */
  public void setProgPhysicFinancialPeriod(PhysicalAndFinancialProgramPeriod progPhysicFinancialPeriod) 
  {
    this.progPhysicFinancialPeriod = progPhysicFinancialPeriod;
  }

    @Override
    public int compareTo(PhysicalAndFinancialProgramEntity obj) {
        int result = -1;
        if (this.progPhysicFinancialId != null && obj.progPhysicFinancialId != null) {
            result = this.progPhysicFinancialId.compareTo(obj.progPhysicFinancialId);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof PhysicalAndFinancialProgramEntity
                && this.progPhysicFinancialId != null
                && ((PhysicalAndFinancialProgramEntity) obj).progPhysicFinancialId != null) {

            result = this.progPhysicFinancialId.equals(
                    ((PhysicalAndFinancialProgramEntity) obj).progPhysicFinancialId);

        }

        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.progPhysicFinancialId != null
                ? this.progPhysicFinancialId.hashCode() : 0);
        return hash;
    }

}
