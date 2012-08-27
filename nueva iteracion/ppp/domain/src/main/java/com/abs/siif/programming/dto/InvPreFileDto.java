/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.dto;

import com.abs.siif.support.FormatNumber;
import java.io.Serializable;

/**
 *Este DTO sirve para procesar la información recibida porla búsqueda
 * @author jacob.flores
 */
public class InvPreFileDto implements Serializable
{
  private String invprefileid;
  private String invprefilenumber;
  private String invprefilename;
  private String invprefilepriority;
  private String invprefileinitialasignation;
  private String invprefileaditionalasignation;
  private String invprefiletotal;
  private String invprefiledraftprojectnumber;
  private String invprefiledraftprojectshortname;
  private String invprefileuegnumber;
  private String invprefileuegdescription;

    /**
     * @return the invprefileid
     */
    public String getInvprefileid()
    {
        return invprefileid;
    }

    /**
     * @param invprefileid the invprefileid to set
     */
    public void setInvprefileid(String invprefileid)
    {
        this.invprefileid = invprefileid;
    }

    /**
     * @return the invprefilenumber
     */
    public String getInvprefilenumber()
    {
        return invprefilenumber;
    }

    /**
     * @param invprefilenumber the invprefilenumber to set
     */
    public void setInvprefilenumber(String invprefilenumber)
    {
        this.invprefilenumber = invprefilenumber;
    }

    /**
     * @return the tinvprefilename
     */
    public String getInvprefilename()
    {
        return invprefilename;
    }

    /**
     * @param tinvprefilename the tinvprefilename to set
     */
    public void setInvprefilename(String tinvprefilename)
    {
        this.invprefilename = tinvprefilename;
    }

    /**
     * @return the rinvprefilepriority
     */
    public String getInvprefilepriority()
    {
        return invprefilepriority;
    }

    /**
     * @param rinvprefilepriority the rinvprefilepriority to set
     */
    public void setInvprefilepriority(String rinvprefilepriority)
    {
        this.invprefilepriority = rinvprefilepriority;
    }

    /**
     * @return the invprefileinitialasignation
     */
    public String getInvprefileinitialasignation()
    {
        return invprefileinitialasignation;
    }

    /**
     * @param invprefileinitialasignation the invprefileinitialasignation to set
     */
    public void setInvprefileinitialasignation(String invprefileinitialasignation)
    {
        this.invprefileinitialasignation = invprefileinitialasignation;
    }

    /**
     * @return the invprefileaditionalasignation
     */
    public String getInvprefileaditionalasignation()
    {
        return invprefileaditionalasignation;
    }

    /**
     * @param invprefileaditionalasignation the invprefileaditionalasignation to set
     */
    public void setInvprefileaditionalasignation(String invprefileaditionalasignation)
    {
        this.invprefileaditionalasignation = invprefileaditionalasignation;
    }

    /**
     * @return the invprefiletotal
     */
    public String getInvprefiletotal()
    {
        return invprefiletotal;
    }

    /**
     * @param invprefiletotal the invprefiletotal to set
     */
    public void setInvprefiletotal(String invprefiletotal)
    {
        this.invprefiletotal = invprefiletotal;
    }

    /**
     * @return the invprefiledraftprojectnumber
     */
    public String getInvprefiledraftprojectnumber()
    {
        return invprefiledraftprojectnumber;
    }

    /**
     * @param invprefiledraftprojectnumber the invprefiledraftprojectnumber to set
     */
    public void setInvprefiledraftprojectnumber(String invprefiledraftprojectnumber)
    {
        this.invprefiledraftprojectnumber = invprefiledraftprojectnumber;
    }

    /**
     * @return the invprefiledraftprojectshortname
     */
    public String getInvprefiledraftprojectshortname()
    {
        return invprefiledraftprojectshortname;
    }

    /**
     * @param invprefiledraftprojectshortname the invprefiledraftprojectshortname to set
     */
    public void setInvprefiledraftprojectshortname(String invprefiledraftprojectshortname)
    {
        this.invprefiledraftprojectshortname = invprefiledraftprojectshortname;
    }

    /**
     * @return the invprefileuegnumber
     */
    public String getInvprefileuegnumber()
    {
        return invprefileuegnumber;
    }

    /**
     * @param invprefileuegnumber the invprefileuegnumber to set
     */
    public void setInvprefileuegnumber(String invprefileuegnumber)
    {
        this.invprefileuegnumber = invprefileuegnumber;
    }

    /**
     * @return the invprefileuegdescription
     */
    public String getInvprefileuegdescription()
    {
        return invprefileuegdescription;
    }

    /**
     * @param invprefileuegdescription the invprefileuegdescription to set
     */
    public void setInvprefileuegdescription(String invprefileuegdescription)
    {
        this.invprefileuegdescription = invprefileuegdescription;
    }

    
}