/*
 *  Copyright (C) 2012 Advanced Business Systems S.A. de C.V. 
 *  All rights reserved 
 *  Filename:  TreeNodeDto
 *  Purpose:  [ Elemento de soporte para la creacion de los nodos del arbol  ]
 *        
 *  The copyright to the computer program(s) herein is the property
 *  of Advanced Business Systems S.A. de C.V. The programs may be 
 *  used and/or copied only with written permission from Advanced 
 *  Business Systems S.A. de C.V. or in accordance with the terms 
 *  and conditions stipulated in the agreement/contract under which
 *  the program(s) have been supplied.
 */
package com.abs.siif.common.dto;

/**
 *
 * @author Juan Antonio Zavala Aguilar
 */
public class TreeNodeDto {

    private Long itsLevelId;
    private Long itsParent;
    private Long itsIdentity;
    private String itsNodeText;
    private boolean itsIsRoot;
    private int itsLevel;

    public Long getItsLevelId() {
        return itsLevelId;
    }

    public void setItsLevelId(Long itsLevelId) {
        this.itsLevelId = itsLevelId;
    }

    private TreeNodeDto() {
    }

    public Long getItsParent() {
        return itsParent;
    }

    public void setItsParent(Long itsParent) {
        this.itsParent = itsParent;
    }

    public TreeNodeDto(Long aParent,
            Long anIdentity, String aNodeText,
            boolean aIsRoot, int aLevel, Long aLevelId) {
        this.itsParent = aParent;
        this.itsIdentity = anIdentity;
        this.itsNodeText = aNodeText;
        this.itsIsRoot = aIsRoot;
        this.itsLevel = aLevel;
        this.itsLevelId = aLevelId;
    }

    public int getItsLevel() {
        return itsLevel;
    }

    public void setItsLevel(int itsLevel) {
        this.itsLevel = itsLevel;
    }

    public Long getItsIdentity() {
        return itsIdentity;
    }

    public void setItsIdentity(Long itsIdentity) {
        this.itsIdentity = itsIdentity;
    }

    public String getItsNodeText() {
        return itsNodeText;
    }

    public void setItsNodeText(String itsNodeText) {
        this.itsNodeText = itsNodeText;
    }

    public boolean isItsIsRoot() {
        return itsIsRoot;
    }

    public void setItsIsRoot(boolean itsIsRoot) {
        this.itsIsRoot = itsIsRoot;
    }
}
