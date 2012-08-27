/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dto;


import com.abs.siif.admin.dto.BuildingConceptFrameDto;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Erick Leija
 */
public class SizingDto implements Serializable, Comparable<SizingDto>
{
     
    private Long itsSizingId;
    private String itsSizingAditional;
    private String itsSizingCantity;
    private String itsSizingconsecutive;
    private String itsSizingDescription;
    private String itsSizingInitial;
    private String itsSizingPercent;
    private String itsSizingSummatory;
    private Long itsSizingPreFileId;

    public SizingDto(){
    }
    
    public SizingDto(Long itsSizingId, String itsSizingAditional, String itsSizingCantity, String itsSizingconsecutive, String itsSizingDescription, String itsSizingInitial, String itsSizingPercent, String itsSizingSummatory, Long itsSizingPreFileId)
    {
        this.itsSizingId = itsSizingId;
        this.itsSizingAditional = formatNumber(itsSizingAditional);
        this.itsSizingCantity = formatNumber(itsSizingCantity);
        this.itsSizingconsecutive = formatNumber(itsSizingconsecutive);
        this.itsSizingDescription = itsSizingDescription;
        this.itsSizingInitial = formatNumber(itsSizingInitial);
        this.itsSizingPercent = formatNumber(itsSizingPercent);
        this.itsSizingSummatory = formatNumber(itsSizingSummatory);
        this.itsSizingPreFileId = itsSizingPreFileId;
    }

    public String getItsSizingAditional()
    {
        return itsSizingAditional;
    }

    public void setItsSizingAditional(String itsSizingAditional)
    {
        itsSizingAditional = formatNumber(itsSizingAditional);
        this.itsSizingAditional = itsSizingAditional;
    }

    public String getItsSizingCantity()
    {
        return itsSizingCantity;
    }

    public void setItsSizingCantity(String itsSizingCantity)
    {
        itsSizingCantity = formatNumber(itsSizingCantity);
        this.itsSizingCantity = itsSizingCantity;
    }

    public String getItsSizingDescription()
    {
        return itsSizingDescription;
    }

    public void setItsSizingDescription(String itsSizingDescription)
    {
        this.itsSizingDescription = itsSizingDescription;
    }

    public Long getItsSizingId()
    {
        return itsSizingId;
    }

    public void setItsSizingId(Long itsSizingId)
    {
        this.itsSizingId = itsSizingId;
    }

    public String getItsSizingInitial()
    {
        return itsSizingInitial;
    }

    public void setItsSizingInitial(String itsSizingInitial)
    {
        itsSizingInitial = formatNumber(itsSizingInitial);
        this.itsSizingInitial = itsSizingInitial;
    }

    public String getItsSizingPercent()
    {
        return itsSizingPercent;
    }

    public void setItsSizingPercent(String itsSizingPercent)
    {
        itsSizingPercent = formatNumber(itsSizingPercent);
        this.itsSizingPercent = itsSizingPercent;
    }

    public Long getItsSizingPreFileId()
    {
        return itsSizingPreFileId;
    }

    public void setItsSizingPreFileId(Long itsSizingPreFileId)
    {
        this.itsSizingPreFileId = itsSizingPreFileId;
    }

    public String getItsSizingSummatory()
    {
        return itsSizingSummatory;
    }

    public void setItsSizingSummatory(String itsSizingSummatory)
    {
        itsSizingSummatory = formatNumber(itsSizingSummatory);
        this.itsSizingSummatory = itsSizingSummatory;
    }

    public String getItsSizingconsecutive()
    {
        return itsSizingconsecutive;
    }

    public void setItsSizingconsecutive(String itsSizingconsecutive)
    {
        itsSizingconsecutive = formatNumber(itsSizingconsecutive);
        this.itsSizingconsecutive = itsSizingconsecutive;
    }
    
    /* Formatea numero ###,###
     * @param num numero sin formato 1000
     * @return numero formateado 1,000
     */
    public static String formatNumber(String num){
        if(num != null && !num.equals("0"))
        {
            num = num.replace(",", "");
            DecimalFormat df= new DecimalFormat("###,###");
            try{
                num=df.format(Double.parseDouble(num));            
            } catch (Exception ex) {
                Logger.getLogger(SizingDto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return num;
    }
    
    
    @Override
    public int compareTo(SizingDto o) {
        return this.getItsSizingId().compareTo(o.getItsSizingId());
    }
    
    public static Comparator<SizingDto> DescriptionComparator = new Comparator<SizingDto>(){
        @Override
        public int compare(SizingDto dto1, SizingDto dto2){
            String description1 = dto1.getItsSizingDescription().toUpperCase();
	    String description2 = dto2.getItsSizingDescription().toUpperCase();
            
            return description1.compareTo(description2);
        }
    };
    
}
