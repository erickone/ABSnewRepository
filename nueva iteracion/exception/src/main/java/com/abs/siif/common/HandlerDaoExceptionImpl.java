/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.common;

import com.abs.siif.base.exception.BaseBusinessException;
import com.abs.siif.base.exception.GeneralException;
import com.abs.siif.common.api.HandlerDaoException;
import java.sql.SQLException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author FENIX-01
 */
@Aspect
public class HandlerDaoExceptionImpl implements HandlerDaoException {

    @AfterThrowing(pointcut = "execution(* com.abs.siif.planning.*.*.*(..))"
    + "|| execution(* com.abs.siif.budget.*.*.*(..))",
    throwing = "anError")
    @Override
    public void logAfterExecuteException(JoinPoint joinPoint, Throwable anError) {
        BaseBusinessException myBusinessException = null;
        if (anError instanceof org.hibernate.exception.GenericJDBCException) {
            myBusinessException = new BaseBusinessException("ppp.planning.failConexion", anError);
        } else {
            myBusinessException = castBaseBussinesException(anError);
        }
        throw myBusinessException;
    }

    private BaseBusinessException castBaseBussinesException(Throwable anError) {
        BaseBusinessException myBusinessException = null;


        if ((anError.getCause() instanceof ConstraintViolationException)) {

            myBusinessException =
                    MapperDaoException.
                    convertConstraintExceptionInBusinessException(
                    (ConstraintViolationException) anError.getCause());
        }else
        if ((anError.getCause() instanceof SQLException)) {
            myBusinessException =
                    MapperDaoException.
                    convertSQLExceptionInBusinessException(
                    (SQLException) anError.getCause());
        }else
        if( anError instanceof BaseBusinessException){
            myBusinessException =(BaseBusinessException) anError;
        }else{
            myBusinessException = new BaseBusinessException("noneMsg",anError);
            anError.printStackTrace(System.out);
        }
        return myBusinessException;

    }
}
