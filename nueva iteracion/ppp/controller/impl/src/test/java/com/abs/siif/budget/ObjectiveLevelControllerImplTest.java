/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget;

import com.abs.siif.controller.ObjectiveLevelControllerImpl;
import com.abs.siif.budget.domain.ObjectiveLevelData;
import com.abs.siif.budget.management.ObjectiveLevelManagement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Juan Antonio Zavala Aguilar / Erick Leija Cardenas
 */
@RunWith(MockitoJUnitRunner.class)
public class ObjectiveLevelControllerImplTest {

    @Mock
    private ObjectiveLevelManagement theirObjectiveLevelManagement;
    private ObjectiveLevelController theirObjectiveLevelController;

    /**
     * Verificar que la lista de niveles no sea modificada por el management
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testGetAllObjectiveLevels() {
        ObjectiveLevelData myItemA = mock(ObjectiveLevelData.class);
        ObjectiveLevelData myItemB = mock(ObjectiveLevelData.class);
        ObjectiveLevelData myItemC = mock(ObjectiveLevelData.class);

        when(myItemA.getObjectiveLevelKey()).thenReturn("Item A");
        when(myItemB.getObjectiveLevelKey()).thenReturn("Item B");
        when(myItemC.getObjectiveLevelKey()).thenReturn("Item C");

        List<ObjectiveLevelData> myList = new ArrayList<ObjectiveLevelData>();
        myList.add(myItemA);
        myList.add(myItemB);
        myList.add(myItemC);

        when(theirObjectiveLevelManagement.getAllObjectiveLevels()).thenReturn(
               Collections.unmodifiableList(myList));
        theirObjectiveLevelController = new ObjectiveLevelControllerImpl(theirObjectiveLevelManagement);

        List<ObjectiveLevelData> myNewList=theirObjectiveLevelController.getObjectiveLevels();
        int mySizeCollection=myNewList.size();
        assertEquals(3, mySizeCollection);
               
        
        myNewList.add(myItemA);
         
    }
}
