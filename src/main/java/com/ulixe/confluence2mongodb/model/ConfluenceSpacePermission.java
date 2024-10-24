package com.ulixe.confluence2mongodb.model;

import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Objects;

@XmlRootElement(name = "object")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfluenceSpacePermission {


    @XmlAttribute()
    private String name;

    // Empty Constructor
    public ConfluenceSpacePermission() { }

    public  String getName() {
        return name;
    }

    public void setName( String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfluenceSpacePermission that = (ConfluenceSpacePermission) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "ConfluenceSpacePermission{" +
                "name='" + name + '\'' +
                '}';
    }
}
