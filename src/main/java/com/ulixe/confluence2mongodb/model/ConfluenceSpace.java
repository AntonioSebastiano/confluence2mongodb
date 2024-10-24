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
public class ConfluenceSpace {
    @XmlElement(name = "id")
    private String id;

    @XmlAttribute()
    private String name;

    @XmlAttribute()
    private String key;

    
    @XmlAttribute()
    private String lowerKey;

    
    @XmlAttribute()
    private String description;

    
    @XmlAttribute()
    private String homePage;

    @XmlElement
    private List<String> permission;

    @XmlAttribute()
    private String creator; //class ConfluenceUserImpl

    @XmlAttribute()
    private String creationDate;

    @XmlAttribute()
    private String lastModifier;

    @XmlAttribute()
    private String lastModificationDate;

    @XmlAttribute()
    private String spaceType;  // enum?

    @XmlAttribute()
    private String spaceStatus;  // enum?

    // Empty Constructor
    public ConfluenceSpace() {
    }

    // Getter and Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public  String getName() {
        return name;
    }

    public void setName( String name) {
        this.name = name;
    }

    public  String getKey() {
        return key;
    }

    public void setKey( String key) {
        this.key = key;
    }

    public  String getLowerKey() {
        return lowerKey;
    }

    public void setLowerKey( String lowerKey) {
        this.lowerKey = lowerKey;
    }

    public  String getDescription() {
        return description;
    }

    public void setDescription( String description) {
        this.description = description;
    }

    public  String getHomePage() {
        return homePage;
    }

    public void setHomePage( String homePage) {
        this.homePage = homePage;
    }

    public List<String> getPermission() {
        return permission;
    }

    public void setPermission(List<String> permission) {
        this.permission = permission;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(String lastModifier) {
        this.lastModifier = lastModifier;
    }

    public String getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(String lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public String getSpaceType() {
        return spaceType;
    }

    public void setSpaceType(String spaceType) {
        this.spaceType = spaceType;
    }

    public String getSpaceStatus() {
        return spaceStatus;
    }

    public void setSpaceStatus(String spaceStatus) {
        this.spaceStatus = spaceStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfluenceSpace that = (ConfluenceSpace) o;
        return Objects.equals(name, that.name) && Objects.equals(key, that.key) && Objects.equals(lowerKey, that.lowerKey) && Objects.equals(description, that.description) && Objects.equals(homePage, that.homePage) && Objects.equals(permission, that.permission) && Objects.equals(creator, that.creator) && Objects.equals(creationDate, that.creationDate) && Objects.equals(lastModifier, that.lastModifier) && Objects.equals(lastModificationDate, that.lastModificationDate) && Objects.equals(spaceType, that.spaceType) && Objects.equals(spaceStatus, that.spaceStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, key, lowerKey, description, homePage, permission, creator, creationDate, lastModifier, lastModificationDate, spaceType, spaceStatus);
    }

    @Override
    public String toString() {
        return "ConfluenceSpace{" +
                "name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", lowerKey='" + lowerKey + '\'' +
                ", description='" + description + '\'' +
                ", homePage='" + homePage + '\'' +
                ", permission=" + permission +
                ", creator='" + creator + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", lastModifier='" + lastModifier + '\'' +
                ", lastModificationDate='" + lastModificationDate + '\'' +
                ", spaceType='" + spaceType + '\'' +
                ", spaceStatus='" + spaceStatus + '\'' +
                '}';
    }
}
