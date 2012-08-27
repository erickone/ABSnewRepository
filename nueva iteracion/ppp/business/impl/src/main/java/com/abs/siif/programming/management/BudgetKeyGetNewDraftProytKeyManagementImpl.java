/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.programming.management;

import com.abs.siif.programming.entities.DraftProjectEntity;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Erick Leija
 */
@Service("BudgetKeyGetNewDraftProytKeyManagement")
public class BudgetKeyGetNewDraftProytKeyManagementImpl 
implements DraftProjectObserver, BudgetKeyGetNewDraftProytKeyManagement, Serializable
{
    private DraftProjectSubject theirDraftProjectSubject;
    private DraftProjectEntity theirDraftProjectEntity;
    

   
    @Override
    public void update(DraftProjectEntity aDraftProjectEntity)
    {
        //Here's where the Magic of Zavalita appears
        this.theirDraftProjectEntity = aDraftProjectEntity;
    }
    
}
