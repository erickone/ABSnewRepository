/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.ppp.programming.api.controller;

import com.abs.siif.programming.entities.ComponentEntity;
import java.util.List;

/**
 *
 * @author jacob.flores
 */
public interface SefinValidationControllerApi
{
 List<ComponentEntity> getTheirComponentList();
 void setTheirComponentList(List<ComponentEntity> theirComponentList);
 void init();
}