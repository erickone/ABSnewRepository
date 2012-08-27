package com.abs.siif.budget.data;

/**
 *
 * @author Luis Agustin Carreón
 */
public enum TotalCostType {
    

    OPERATION("name.operation"),
    INVESTMENT("name.investment");
    
    private String name;
    private TotalCostType(String arg){
        this.name = arg;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}