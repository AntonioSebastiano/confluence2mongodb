package com.ulixe.confluence2mongodb.model;

import jakarta.xml.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "object")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfluenceSpaceDescription {
    @XmlAttribute()
    private String id;

    @XmlAttribute()
    private String hibernateVersion;

    @XmlAttribute()
    private String title;

    @XmlElement()
    private String creator;

    @XmlAttribute()
    private String creationDate;

    @XmlAttribute()
    private String space;

    @XmlElement()
    private List<ConfluenceLabelling> labellings;

    public ConfluenceSpaceDescription(){ }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHibernateVersion() {
        return hibernateVersion;
    }

    public void setHibernateVersion(String hibernateVersion) {
        this.hibernateVersion = hibernateVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public List<ConfluenceLabelling> getLabellings() {
        return labellings;
    }

    public void setLabellings(List<ConfluenceLabelling> labellings) {
        this.labellings = labellings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfluenceSpaceDescription that = (ConfluenceSpaceDescription) o;
        return Objects.equals(id, that.id) && Objects.equals(hibernateVersion, that.hibernateVersion) && Objects.equals(title, that.title) && Objects.equals(creator, that.creator) && Objects.equals(creationDate, that.creationDate) && Objects.equals(space, that.space) && Objects.equals(labellings, that.labellings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hibernateVersion, title, creator, creationDate, space, labellings);
    }

    @Override
    public String toString() {
        return "StringDescription{" +
                "id='" + id + '\'' +
                ", hibernateVersion='" + hibernateVersion + '\'' +
                ", title='" + title + '\'' +
                ", creator=" + creator +
                ", creationDate='" + creationDate + '\'' +
                ", space=" + space +
                ", labellings=" + labellings +
                '}';
    }
}
