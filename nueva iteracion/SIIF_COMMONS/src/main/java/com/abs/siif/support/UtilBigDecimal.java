/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  UtilBigDecimal
 *  Purpose:  [ short Description  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.support;

import java.math.BigDecimal;

/**
 *
 * @author Israel Ruiz
 */
public class UtilBigDecimal {
    /**
     * round(new BigDecimal("12.390"), 2, true); // => 12.39
     * round(new BigDecimal("12.391"), 2, true); // => 12.40
     * round(new BigDecimal("12.391"), 2, false); // => 12.39
     * round(new BigDecimal("12.399"), 2, false); // => 12.39
     * 
     * @param d
     * @param scale
     * @param roundUp
     * @return 
     */
    public static BigDecimal round(BigDecimal d, int scale, boolean roundUp) {
        int mode = (roundUp) ? BigDecimal.ROUND_UP : BigDecimal.ROUND_DOWN;
        return d.setScale(scale, mode);
    }
}
