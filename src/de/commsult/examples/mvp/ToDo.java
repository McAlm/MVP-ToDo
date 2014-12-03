package de.commsult.examples.mvp;

import java.util.Date;

public class ToDo {

    private String name;
    private Date until;
    private String details;

    public ToDo(String name, String detail, Date until) {
        this.name = name;
        details = detail;
        this.until = until;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUntil() {
        return until;
    }

    public void setUntil(Date until) {
        this.until = until;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
