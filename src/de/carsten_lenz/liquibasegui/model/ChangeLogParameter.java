package de.carsten_lenz.liquibasegui.model;

public class ChangeLogParameter {

    private String key;
    private Object value;
    
    public ChangeLogParameter() {
        
    }
    
    public ChangeLogParameter(String key, Object value) {
        this.key = key;
        this.value = value;
    }
    
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    
    public ChangeLogParameter copy() {
        return new ChangeLogParameter(key, value);
    }
}
