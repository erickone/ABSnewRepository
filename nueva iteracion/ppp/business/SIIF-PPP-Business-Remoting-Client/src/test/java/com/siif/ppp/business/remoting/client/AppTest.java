package com.siif.ppp.business.remoting.client;


import com.siif.remoting.SiifPPPRemoting;
import java.util.*;
import javax.annotation.Resource;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class AppTest 
{
//   @Resource(name="remoting")
//   SiifPPPRemoting remoting;
   @Test
   public void executeRemoting(){
//       Map<String, Object> params = new HashMap<String, Object>();
//       List<Date> list = new ArrayList<Date>();
//       list.add(new Date());
//       list.add(new Date());
//       params.put("Date", new Date());
//       params.put("ListDate", list);
//       System.out.print(remoting.executeService(params));
       assertEquals(1, 1);
   }
    
}
