package com.abs.siif.planning.exception;

import com.abs.siif.base.exception.BaseBusinessException;

/**
 *
 * @author Erick Leija
 */
public class ObjectiveLevelDaoException extends BaseBusinessException {

    public ObjectiveLevelDaoException(String aMessage, Throwable aCause) {
        super(aMessage, aCause);
    }

    public ObjectiveLevelDaoException(String aMessage) {
        super(aMessage);
    }
}