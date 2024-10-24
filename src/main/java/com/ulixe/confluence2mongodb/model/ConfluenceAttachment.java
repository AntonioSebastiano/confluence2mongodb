package com.ulixe.confluence2mongodb.model;

import jakarta.xml.bind.annotation.*;

import java.util.Objects;

@XmlRootElement(name = "object")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfluenceAttachment {
    @XmlAttribute()
    private String id;

    @XmlAttribute()
    private String hibernateVersion;

    @XmlAttribute()
    private String title;

    @XmlAttribute()
    private String lowerTitle;

    @XmlElement()
    private String creator;

    @XmlAttribute()
    private String creationDate;

    @XmlElement()
    private String space;

    @XmlElement()
    private String containerContent;

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

    public String getLowerTitle() {
        return lowerTitle;
    }

    public void setLowerTitle(String lowerTitle) {
        this.lowerTitle = lowerTitle;
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

    public String getContainerContent() {
        return containerContent;
    }

    public void setContainerContent(String containerContent) {
        this.containerContent = containerContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfluenceAttachment that = (ConfluenceAttachment) o;
        return Objects.equals(id, that.id) && Objects.equals(hibernateVersion, that.hibernateVersion) && Objects.equals(title, that.title) && Objects.equals(lowerTitle, that.lowerTitle) && Objects.equals(creator, that.creator) && Objects.equals(creationDate, that.creationDate) && Objects.equals(space, that.space) && Objects.equals(containerContent, that.containerContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hibernateVersion, title, lowerTitle, creator, creationDate, space, containerContent);
    }

    @Override
    public String toString() {
        return "ConfluenceAttachment{" +
                "id='" + id + '\'' +
                ", hibernateVersion='" + hibernateVersion + '\'' +
                ", title='" + title + '\'' +
                ", lowerTitle='" + lowerTitle + '\'' +
                ", creator=" + creator +
                ", creationDate='" + creationDate + '\'' +
                ", space=" + space +
                ", containerContent=" + containerContent +
                '}';
    }
}
