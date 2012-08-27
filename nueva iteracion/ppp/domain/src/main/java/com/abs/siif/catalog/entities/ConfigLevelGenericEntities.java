/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.catalog.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author absvalenzuela
 */
@Entity
@Table(name = "siifabsconfignivgenerico")
public class ConfigLevelGenericEntities implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    @Column(name = "idconfignivgenerico")
    private Long configLevelGenericId;

    @Column(name = "anio")
    private Integer year;

    @Column(name = "nivel")
    private Integer level;

    @Column(name = "clavemanual")
    private boolean claveManual;

    @Column(name = "longitudpornivel")
    private boolean longitudPorNivel;

    @Column(name = "consecutivopornivel")
    private boolean consecutivoPorNivel;

    @Column(name = "concatenapadre")
    private boolean concatenaPadre;

    @Column(name = "alfanumerico")
    private boolean alfanumerico;

    @Column(name = "autocompletar")
    private boolean autocompletar;

    @Column(name = "rellenaizq")
    private boolean rellenarIzq;

    @Column(name = "numcaracteres")
    private Integer countCharacter;

    @Column(name = "siglasprefijo")
    private String prefixAcronym;

    @Column(name = "carrelleno")
    private String fillingChar;

    @OneToOne
    @JoinColumn(name = "idtipoconfig", nullable = true)
    private TypeConfigCatalogEntities typeConfigEntity;

    public ConfigLevelGenericEntities() {
        this.setLevel(null);
        this.setYear(null);
        this.setClaveManual(false);
        this.setLongitudPorNivel(true);
        this.setConsecutivoPorNivel(false);
        this.setConcatenaPadre(true);
        this.setAlfanumerico(true);
        this.setAutocompletar(true);
        this.setRellenarIzq(true);
        this.setFillingChar(null);
        this.setCountCharacter(null);
        this.setPrefixAcronym(null);
        this.setTypeConfigEntity(null);
   }
    
    public Long getConfigLevelGenericId() {
        return configLevelGenericId;
    }

    public void setConfigLevelGenericId(Long configLevelGenericId) {
        this.configLevelGenericId = configLevelGenericId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public boolean isClaveManual() {
        return claveManual;
    }

    public void setClaveManual(boolean claveManual) {
        this.claveManual = claveManual;
    }

    public boolean isLongitudPorNivel() {
        return longitudPorNivel;
    }

    public void setLongitudPorNivel(boolean longitudPorNivel) {
        this.longitudPorNivel = longitudPorNivel;
    }

    public boolean isConsecutivoPorNivel() {
        return consecutivoPorNivel;
    }

    public void setConsecutivoPorNivel(boolean consecutivoPorNivel) {
        this.consecutivoPorNivel = consecutivoPorNivel;
    }

    public boolean isConcatenaPadre() {
        return concatenaPadre;
    }

    public void setConcatenaPadre(boolean concatenaPadre) {
        this.concatenaPadre = concatenaPadre;
    }

    public boolean isAlfanumerico() {
        return alfanumerico;
    }

    public void setAlfanumerico(boolean alfanumerico) {
        this.alfanumerico = alfanumerico;
    }

    public boolean isAutocompletar() {
        return autocompletar;
    }

    public void setAutocompletar(boolean autocompletar) {
        this.autocompletar = autocompletar;
    }

    public boolean isRellenarIzq() {
        return rellenarIzq;
    }

    public void setRellenarIzq(boolean rellenarIzq) {
        this.rellenarIzq = rellenarIzq;
    }

    public Integer getCountCharacter() {
        return countCharacter;
    }

    public void setCountCharacter(Integer countCharacter) {
        this.countCharacter = countCharacter;
    }

    public String getPrefixAcronym() {
        return prefixAcronym;
    }

    public void setPrefixAcronym(String prefixAcronym) {
        this.prefixAcronym = prefixAcronym;
    }

    public String getFillingChar() {
        return fillingChar;
    }

    public void setFillingChar(String fillingChar) {
        this.fillingChar = fillingChar;
    }

    public TypeConfigCatalogEntities getTypeConfigEntity() {
        return typeConfigEntity;
}

    public void setTypeConfigEntity(TypeConfigCatalogEntities typeConfigEntity) {
        this.typeConfigEntity = typeConfigEntity;
    }
}
