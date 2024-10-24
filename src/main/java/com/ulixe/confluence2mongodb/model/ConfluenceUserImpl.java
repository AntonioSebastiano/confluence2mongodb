package com.ulixe.confluence2mongodb.model;

import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Objects;

@XmlRootElement(name = "object")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfluenceUserImpl {
    @XmlAttribute(required = true)
    private String key;

    @XmlAttribute(required = true)
    private String name;

    @XmlAttribute(required = true)
    private String lowerName;

    @XmlAttribute(required = true)
    private String atlassianAccountId;

    // Empty Constructor
    public ConfluenceUserImpl(){ }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLowerName() {
        return lowerName;
    }

    public void setLowerName(String lowerName) {
        this.lowerName = lowerName;
    }

    public String getAtlassianAccountId() {
        return atlassianAccountId;
    }

    public void setAtlassianAccountId(String atlassianAccountId) {
        this.atlassianAccountId = atlassianAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfluenceUserImpl that = (ConfluenceUserImpl) o;
        return Objects.equals(key, that.key) && Objects.equals(name, that.name) && Objects.equals(lowerName, that.lowerName) && Objects.equals(atlassianAccountId, that.atlassianAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, name, lowerName, atlassianAccountId);
    }

    @Override
    public String toString() {
        return "ConfluenceUserImpl{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", lowerName='" + lowerName + '\'' +
                ", atlassianAccountId='" + atlassianAccountId + '\'' +
                '}';
    }
}
