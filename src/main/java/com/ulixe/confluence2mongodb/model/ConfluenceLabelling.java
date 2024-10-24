package com.ulixe.confluence2mongodb.model;

import jakarta.xml.bind.annotation.*;

import java.util.Objects;

@XmlRootElement(name = "object")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfluenceLabelling {
    @XmlAttribute()
    private String id;

    @XmlAttribute()
    private String label;

    @XmlAttribute()
    private String content;

    @XmlElement()
    private String owningUser;

    @XmlAttribute()
    private String creationDate;

    public ConfluenceLabelling() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOwningUser() {
        return owningUser;
    }

    public void setOwningUser(String owningUser) {
        this.owningUser = owningUser;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfluenceLabelling that = (ConfluenceLabelling) o;
        return Objects.equals(id, that.id) && Objects.equals(label, that.label) && Objects.equals(content, that.content) && Objects.equals(owningUser, that.owningUser) && Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, content, owningUser, creationDate);
    }

    @Override
    public String toString() {
        return "ConfluenceLabelling{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", content='" + content + '\'' +
                ", owningUser=" + owningUser +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }
}
