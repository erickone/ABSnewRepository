/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.dto;

import java.io.Serializable;

/**
 *
 * @author Erick Leija
 */

public class DraftProjectTreeviewDto implements Serializable
{
   
    private String itsNodeText;
    private String itsNodeTooltip;
    private Long itsDraftProjectId;
    private String itsDraftProjectShortName;
    private String itsDraftProjectName;
    private String itsDraftProjectDescription;
    
    private  short itsDraftProjectLevel;

   
    public DraftProjectTreeviewDto(String aNodeText) {
        this.itsNodeText = aNodeText;
        
    }

    public DraftProjectTreeviewDto(Long aDraftProjectId, String aNodeText, String aNodeTooltip) {
        this.itsNodeText = aNodeText;
        this.itsNodeTooltip = aNodeTooltip;
        this.itsDraftProjectId= aDraftProjectId;
    }

    public DraftProjectTreeviewDto(String aNodeText, String aNodeTooltip, Long aDraftProjectId,
            String aDraftProjectShortName, String aDraftProjectName, String aDraftProjectDescription, 
            Short aDraftProjectLevel) {
        this.itsNodeText = aNodeText;
        this.itsNodeTooltip = aNodeTooltip;
        
        this.itsDraftProjectId = aDraftProjectId;
        this.itsDraftProjectShortName = aDraftProjectShortName;
        this.itsDraftProjectName = aDraftProjectName;
        this.itsDraftProjectDescription = aDraftProjectDescription;
        this.itsDraftProjectLevel = aDraftProjectLevel;
        
    }

    public String getItsDraftProjectDescription()
    {
        return itsDraftProjectDescription;
    }

    public void setItsDraftProjectDescription(String itsDraftProjectDescription)
    {
        this.itsDraftProjectDescription = itsDraftProjectDescription;
    }

    public Long getItsDraftProjectId()
    {
        return itsDraftProjectId;
    }

    public void setItsDraftProjectId(Long itsDraftProjectId)
    {
        this.itsDraftProjectId = itsDraftProjectId;
    }

    public short getItsDraftProjectLevel()
    {
        return itsDraftProjectLevel;
    }

    public void setItsDraftProjectLevel(short itsDraftProjectLevel)
    {
        this.itsDraftProjectLevel = itsDraftProjectLevel;
    }

    public String getItsDraftProjectName()
    {
        return itsDraftProjectName;
    }

    public void setItsDraftProjectName(String itsDraftProjectName)
    {
        this.itsDraftProjectName = itsDraftProjectName;
    }

    public String getItsDraftProjectShortName()
    {
        return itsDraftProjectShortName;
    }

    public void setItsDraftProjectShortName(String itsDraftProjectShortName)
    {
        this.itsDraftProjectShortName = itsDraftProjectShortName;
    }

    public String getItsNodeText()
    {
        return itsNodeText;
    }

    public void setItsNodeText(String itsNodeText)
    {
        this.itsNodeText = itsNodeText;
    }

    public String getItsNodeTooltip()
    {
        return itsNodeTooltip;
    }

    public void setItsNodeTooltip(String itsNodeTooltip)
    {
        this.itsNodeTooltip = itsNodeTooltip;
    }

    
}

    

