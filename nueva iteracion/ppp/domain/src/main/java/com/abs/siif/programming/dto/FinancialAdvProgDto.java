/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author ABS15
 */
public class FinancialAdvProgDto implements Serializable{
    
    private String clave;
    private int ftefinanciamiento;
    private BigDecimal montooriginal;
    private BigDecimal ene;
    private BigDecimal feb;
    private BigDecimal mar;
    private BigDecimal abr;
    private BigDecimal may;
    private BigDecimal jun;
    private BigDecimal jul;
    private BigDecimal ago;
    private BigDecimal sep;
    private BigDecimal oct;
    private BigDecimal nov;
    private BigDecimal dic;
    private int eneid;
    private int febid;
    private int marid;
    private int abrid;
    private int mayid;
    private int junid;
    private int julid;
    private int agoid;
    private int sepid;
    private int octid;
    private int novid;
    private int dicid;
    private int idcvepresupuestal;
    private int idobjetogasto;
    private int iddefcvepresupuestal;
    private int idcveestado;
    private BigDecimal totalCve;

    public BigDecimal getTotalCve() {
        return totalCve;
    }

    public void setTotalCve(BigDecimal totalCve) {
        this.totalCve = totalCve;
    } 
    
    public int getIdcveestado() {
        return idcveestado;
    }

    public void setIdcveestado(int idcveestado) {
        this.idcveestado = idcveestado;
    }

    public int getIddefcvepresupuestal() {
        return iddefcvepresupuestal;
    }

    public void setIddefcvepresupuestal(int iddefcvepresupuestal) {
        this.iddefcvepresupuestal = iddefcvepresupuestal;
    }
    
    public int getIdobjetogasto() {
        return idobjetogasto;
    }

    public void setIdobjetogasto(int idobjetogasto) {
        this.idobjetogasto = idobjetogasto;
    }  

    public BigDecimal getAbr() {
        return abr;
    }

    public void setAbr(BigDecimal abr) {
        this.abr = abr;
    }

    public int getAbrid() {
        return abrid;
    }

    public void setAbrid(int abrid) {
        this.abrid = abrid;
    }

    public BigDecimal getAgo() {
        return ago;
    }

    public void setAgo(BigDecimal ago) {
        this.ago = ago;
    }

    public int getAgoid() {
        return agoid;
    }

    public void setAgoid(int agoid) {
        this.agoid = agoid;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public BigDecimal getDic() {
        return dic;
    }

    public void setDic(BigDecimal dic) {
        this.dic = dic;
    }

    public int getDicid() {
        return dicid;
    }

    public void setDicid(int dicid) {
        this.dicid = dicid;
    }

    public BigDecimal getEne() {
        return ene;
    }

    public void setEne(BigDecimal ene) {
        this.ene = ene;
    }

    public int getEneid() {
        return eneid;
    }

    public void setEneid(int eneid) {
        this.eneid = eneid;
    }

    public BigDecimal getFeb() {
        return feb;
    }

    public void setFeb(BigDecimal feb) {
        this.feb = feb;
    }

    public int getFebid() {
        return febid;
    }

    public void setFebid(int febid) {
        this.febid = febid;
    }

    public int getFtefinanciamiento() {
        return ftefinanciamiento;
    }

    public void setFtefinanciamiento(int ftefinanciamiento) {
        this.ftefinanciamiento = ftefinanciamiento;
    }

    public int getIdcvepresupuestal() {
        return idcvepresupuestal;
    }

    public void setIdcvepresupuestal(int idcvepresupuestal) {
        this.idcvepresupuestal = idcvepresupuestal;
    }

    public BigDecimal getJul() {
        return jul;
    }

    public void setJul(BigDecimal jul) {
        this.jul = jul;
    }

    public int getJulid() {
        return julid;
    }

    public void setJulid(int julid) {
        this.julid = julid;
    }

    public BigDecimal getJun() {
        return jun;
    }

    public void setJun(BigDecimal jun) {
        this.jun = jun;
    }

    public int getJunid() {
        return junid;
    }

    public void setJunid(int junid) {
        this.junid = junid;
    }

    public BigDecimal getMar() {
        return mar;
    }

    public void setMar(BigDecimal mar) {
        this.mar = mar;
    }

    public int getMarid() {
        return marid;
    }

    public void setMarid(int marid) {
        this.marid = marid;
    }

    public BigDecimal getMay() {
        return may;
    }

    public void setMay(BigDecimal may) {
        this.may = may;
    }

    public int getMayid() {
        return mayid;
    }

    public void setMayid(int mayid) {
        this.mayid = mayid;
    }

    public BigDecimal getMontooriginal() {
        return montooriginal;
    }

    public void setMontooriginal(BigDecimal montooriginal) {
        this.montooriginal = montooriginal;
    }

    public BigDecimal getNov() {
        return nov;
    }

    public void setNov(BigDecimal nov) {
        this.nov = nov;
    }

    public int getNovid() {
        return novid;
    }

    public void setNovid(int novid) {
        this.novid = novid;
    }

    public BigDecimal getOct() {
        return oct;
    }

    public void setOct(BigDecimal oct) {
        this.oct = oct;
    }

    public int getOctid() {
        return octid;
    }

    public void setOctid(int octid) {
        this.octid = octid;
    }

    public BigDecimal getSep() {
        return sep;
    }

    public void setSep(BigDecimal sep) {
        this.sep = sep;
    }

    public int getSepid() {
        return sepid;
    }

    public void setSepid(int sepid) {
        this.sepid = sepid;
    }
}
