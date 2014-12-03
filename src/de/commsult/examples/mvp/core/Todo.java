package de.commsult.examples.mvp.core;

import java.util.Date;

public class Todo {

    private String name;
    private Date until;
    private String details;

    public Todo(String name, String detail, Date until) {
        this.name = name;
        details = detail;
        this.until = until;
    }

    public Todo() {
        this.name = "";
        this.details = "";
        this.until = new Date();
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((details == null) ? 0 : details.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((until == null) ? 0 : until.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Todo other = (Todo)obj;
        if (details == null) {
            if (other.details != null)
                return false;
        } else if (!details.equals(other.details))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (until == null) {
            if (other.until != null)
                return false;
        } else if (!until.equals(other.until))
            return false;
        return true;
    }

}
