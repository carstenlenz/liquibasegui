package de.carsten_lenz.liquibasegui.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RunConfiguration {
    
    private String configurationName;

    private String connectionString;
    
    private String driverClassName;
    
    private String userName;
    
    private String password;
    
    private File changeLog;
    
    private List<ChangeLogParameter> changeLogParameters;
    
    public RunConfiguration() {
        setChangeLogParameters(new ArrayList<ChangeLogParameter>());
    }
    
 // Generated getters and setters

    public String getConfigurationName() {
        return configurationName;
    }

    public void setConfigurationName(String configurationName) {
        this.configurationName = configurationName;
    }
    
    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public File getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(File changeLog) {
        this.changeLog = changeLog;
    }

    public void setChangeLogParameters(List<ChangeLogParameter> changeLogParameters) {
        this.changeLogParameters = changeLogParameters;
    }

    public List<ChangeLogParameter> getChangeLogParameters() {
        return changeLogParameters;
    }  
}
