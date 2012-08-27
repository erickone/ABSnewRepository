/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.budget.dto;

import java.io.Serializable;

/**
 *
 * @author absvalenzuela
 */
public class ImportFileCeillingBudgetDto implements Serializable {
    
    private String pathFile;
    private String fileName;
    private long sizeFile;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getSizeFile() {
        return sizeFile;
    }

    public void setSizeFile(long size) {
        this.sizeFile = size;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String path) {
        this.pathFile = path;
    }
    
    
}
