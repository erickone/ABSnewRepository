/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abs.siif.admin.dto;

import java.util.Comparator;

/**
 *
 * @author cesar.toledano
 */
public class BuildingConceptFrameDto implements Comparable<BuildingConceptFrameDto> {
    
    private Long id;
    private String description;
    private String key;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof BuildingConceptFrameDto){
            BuildingConceptFrameDto dto = (BuildingConceptFrameDto)o;
            return dto.getId().equals(this.getId());
        }else{
            return false;
        }        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 43 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 43 * hash + (this.key != null ? this.key.hashCode() : 0);
        return hash;
    }
    
    @Override
    public int compareTo(BuildingConceptFrameDto o) {
        return this.id.compareTo(o.getId());
    }
    
    public static Comparator<BuildingConceptFrameDto> DescriptionComparator = new Comparator<BuildingConceptFrameDto>(){
        @Override
        public int compare(BuildingConceptFrameDto dto1, BuildingConceptFrameDto dto2){
            String description1 = dto1.getDescription().toUpperCase();
	    String description2 = dto2.getDescription().toUpperCase();
            
            return description1.compareTo(description2);
        }
    };
    
    public static Comparator<BuildingConceptFrameDto> KeyComparator = new Comparator<BuildingConceptFrameDto>(){
        @Override
        public int compare(BuildingConceptFrameDto dto1, BuildingConceptFrameDto dto2){
            String key1 = dto1.getKey().toUpperCase();
	    String key2 = dto2.getKey().toUpperCase();
            
            return key1.compareTo(key2);
        }
    };
    
    @Override
    public String toString(){
        return this.id + "-" + this.description + "-" + this.key;
    }
    
        
    
}
