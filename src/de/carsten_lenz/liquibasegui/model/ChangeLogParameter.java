package de.carsten_lenz.liquibasegui.model;

public class ChangeLogParameter {

    private String key;
    private Object value;
    
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
}
