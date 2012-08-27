/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.entities.DraftProjectEntity;
import java.io.Serializable;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

/**
 *
 * @author Erick Leija
 */
@Service("draftProjectChangeKeyManagement")
public class DraftProjectChangeKeyManagementImpl 
implements DraftProjectChangeKeyManagement, DraftProjectSubject, Serializable
{
    private ArrayList draftProjectObservers;
    private DraftProjectEntity theirDraftProjectEntity;

    public DraftProjectChangeKeyManagementImpl()
    {
        draftProjectObservers= new ArrayList();
    }
    
     @Override
    public void registerObserver(DraftProjectObserver aDraftProjectObserver)
    {
        draftProjectObservers.add(this);
    }

    @Override
    public void removeObserver(DraftProjectObserver aDraftProjectObserver)
    {
        int i = draftProjectObservers.indexOf(aDraftProjectObserver);
        if (i>=0)
        {
            draftProjectObservers.remove(aDraftProjectObserver);
        }   
    }

   @Override
    public void notifyObservers()
    {
         for(int i=0;i<draftProjectObservers.size();i++)
        {
          DraftProjectObserver observer = (DraftProjectObserver) draftProjectObservers.get(i);
          observer.update(theirDraftProjectEntity);
        }
    }
    
    
    @Override
    public void draftProjectKeyChanged()
    {
        notifyObservers();
    }

    @Override
    public void setNewDraftProjectKey(DraftProjectEntity aDraftProjectToChange)
    {
        this.theirDraftProjectEntity = aDraftProjectToChange;
        draftProjectKeyChanged();
    }

    
    
}
