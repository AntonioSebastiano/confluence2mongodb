package com.ulixe.confluence2mongodb.model;

import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "object")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfluenceContentProperty {
    @XmlAttribute()
    private String id;

    @XmlAttribute()
    private String name;

    @XmlAttribute()
    private String stringValue;

    @XmlAttribute()
    private String longValue;

    @XmlAttribute()
    private String dataValue;

    // Empty Constructor
    public ConfluenceContentProperty() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getLongValue() {
        return longValue;
    }

    public void setLongValue(String longValue) {
        this.longValue = longValue;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfluenceContentProperty that = (ConfluenceContentProperty) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(stringValue, that.stringValue) && Objects.equals(longValue, that.longValue) && Objects.equals(dataValue, that.dataValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, stringValue, longValue, dataValue);
    }

    @Override
    public String toString() {
        return "ConfluenceContentProperty{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", stringValue='" + stringValue + '\'' +
                ", longValue='" + longValue + '\'' +
                ", dataValue='" + dataValue + '\'' +
                '}';
    }
}
