package com.abs.siif.ppp.programming.controller;

import java.text.DecimalFormat;

/**
 *
 * @author FENIX-02
 */

public class SizingBean
{
    private String theirSizingNumber = "";
    private String theirSizingDesc = "";
    private String theirSizingAmount = "";
    private String theirSizingIniAsig = "";
    private String theirSizingIniPetition = "";
    private double theirSizingSum;
    private double theirSizingPercentage;

    private boolean readOnly;

    public SizingBean(String aNumber,String aDesc,String anAmount,String anIniAsig,String anIniPet,boolean e){
        this.theirSizingNumber = aNumber;
        this.theirSizingDesc = aDesc;
        this.theirSizingAmount = anAmount;
        this.theirSizingIniAsig = anIniAsig;
        this.theirSizingIniPetition = anIniPet;
        this.theirSizingSum = Double.parseDouble(anIniAsig) + Double.parseDouble(anIniPet);
        this.readOnly=e;
        DecimalFormat formato = new DecimalFormat("#. ##") ;
        String s = formato.format(theirSizingSum);
        this.theirSizingSum = Double.parseDouble(s);
    }

    public SizingBean(String number,boolean e){
        this.theirSizingNumber= number;
        this.readOnly=e;
    }
    public boolean getReadOnly()
    {
        return readOnly;
    }

    public void setReadOnly(boolean enableBean)
    {
        this.readOnly = enableBean;
    }
    public String getTheirSizingAmount()
    {
        return theirSizingAmount;
    }

    public void setTheirSizingAmount(String aSizingAmount)
    {
        this.theirSizingAmount = aSizingAmount;
    }

    public String getTheirSizingDesc()
    {
        return theirSizingDesc;
    }

    public void setTheirSizingDesc(String aSizingDesc)
    {
        this.theirSizingDesc = aSizingDesc;
    }

    public String getTheirSizingIniAsig()
    {
        return theirSizingIniAsig;
    }

    public void setTheirSizingIniAsig(String aSizingIniAsig)
    {
        this.theirSizingIniAsig = aSizingIniAsig;
    }

    public String getTheirSizingIniPetition()
    {
        return theirSizingIniPetition;
    }

    public void setTheirSizingIniPetition(String aSizingIniPetition)
    {
        this.theirSizingIniPetition = aSizingIniPetition;
    }

    public String getTheirSizingNumber()
    {
        return theirSizingNumber;
    }

    public void setTheirSizingNumber(String aSizingNumber)
    {
        this.theirSizingNumber = aSizingNumber;
    }

    public double getTheirSizingPercentage()
    {
        return theirSizingPercentage;
    }

    public void setTheirSizingPercentage(double aSizingPercentage)
    {
        this.theirSizingPercentage = aSizingPercentage;
    }

    public double getTheirSizingSum()
    {
        return theirSizingSum;
    }

    public void setTheirSizingSum(double aSizingSum)
    {
        this.theirSizingSum = aSizingSum;
    }
}
