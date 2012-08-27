/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siif.base.context;

import com.abs.siif.base.context.SIIFContextBase;
import com.abs.siif.base.context.SessionKeyEnum;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Israel Ruiz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class SIIFContextBaseTest  {
   
   
    public SIIFContextBaseTest() {
        super();
    }
    
   
    @Test
    public void testgetAppContext(){
     //ApplicationContext context2 =
       //new ClassPathXmlApplicationContext(new String[]{"application-context.xml"});
       SIIFContextBase.initTest();
       SIIFContextBase.setParameterSession(SessionKeyEnum.USER, "User");
       String user = (String)SIIFContextBase.getParameterSession(SessionKeyEnum.USER);
       assertNotNull(user,"El objeto viene null");
       ApplicationContext context =  SIIFContextBase.getAppContext();
       String msg = context.getMessage("siif.ppp.mapExc",null,new Locale("es_MX") );
     
       assertNotNull(msg,"El objeto viene null");
       assertEquals("Hello Exception", msg);
       System.out.println(msg);

    }
}
