/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dto;

import java.io.Serializable;

/**
 *
 * @author Erick Leija
 */
public class DestinationDto implements Serializable
{
    private Long destinationId;
    private String destinationDesc;
    private String destinationKey;

    public DestinationDto(Long aDestinationId, String aDestinationdesc, String aDestinationKey)
    {
        this.destinationId = aDestinationId;
        this.destinationDesc = aDestinationdesc;
        this.destinationKey = aDestinationKey;
    }

    public String getDestinationDesc()
    {
        return destinationDesc;
    }

    public void setDestinationDesc(String destinationDesc)
    {
        this.destinationDesc = destinationDesc;
    }

    public Long getDestinationId()
    {
        return destinationId;
    }

    public void setDestinationId(Long destinationId)
    {
        this.destinationId = destinationId;
    }

    public String getDestinationKey()
    {
        return destinationKey;
    }

    public void setDestinationKey(String destinationKey)
    {
        this.destinationKey = destinationKey;
    }
    
}
