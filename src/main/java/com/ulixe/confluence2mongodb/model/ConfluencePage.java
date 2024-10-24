package com.ulixe.confluence2mongodb.model;

import jakarta.xml.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "object")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfluencePage {
    @XmlElement(name = "id")
    private String id;

    @XmlAttribute()
    private String hibernateVersion;
    
    @XmlAttribute()
    private String title;

    @XmlAttribute()
    private String lowerTitle;

    @XmlElement()
    private List<String> bodyContents;

    @XmlElement()
    private List<String> contentProperties;

    @XmlAttribute()
    private String version;

    @XmlAttribute()
    private String creator;

    @XmlAttribute()
    private String creationDate;

    @XmlAttribute()
    private String lastModifier;

    @XmlAttribute()
    private String lastModificationDate;

    @XmlAttribute()
    private String versionComment;

    @XmlAttribute()
    private String originalVersionId;

    @XmlAttribute()
    private String contentStatus;

    @XmlAttribute()
    private String navigationContent;

    @XmlElement()
    private List<String> customContent;

    @XmlElement()
    private String space;

    @XmlAttribute()
    private String parent;

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

    public  String getTitle() {
        return title;
    }

    public void setTitle( String title) {
        this.title = title;
    }

    public String getLowerTitle() {
        return lowerTitle;
    }

    public void setLowerTitle(String lowerTitle) {
        this.lowerTitle = lowerTitle;
    }

    public List<String> getBodyContents() {
        return bodyContents;
    }

    public void setBodyContents(List<String> bodyContents) {
        this.bodyContents = bodyContents;
    }

    public List<String> getContentProperties() {
        return contentProperties;
    }

    public void setContentProperties(List<String> contentProperties) {
        this.contentProperties = contentProperties;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public String getVersionComment() {
        return versionComment;
    }

    public void setVersionComment(String versionComment) {
        this.versionComment = versionComment;
    }

    public String getOriginalVersionId() {
        return originalVersionId;
    }

    public void setOriginalVersionId(String originalVersionId) {
        this.originalVersionId = originalVersionId;
    }

    public String getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(String contentStatus) {
        this.contentStatus = contentStatus;
    }

    public String getNavigationContent() {
        return navigationContent;
    }

    public void setNavigationContent(String navigationContent) {
        this.navigationContent = navigationContent;
    }

    public List<String> getCustomContent() {
        return customContent;
    }

    public void setCustomContent(List<String> customContent) {
        this.customContent = customContent;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfluencePage that = (ConfluencePage) o;
        return Objects.equals(id, that.id) && Objects.equals(hibernateVersion, that.hibernateVersion) && Objects.equals(title, that.title) && Objects.equals(lowerTitle, that.lowerTitle) && Objects.equals(bodyContents, that.bodyContents) && Objects.equals(contentProperties, that.contentProperties) && Objects.equals(version, that.version) && Objects.equals(creator, that.creator) && Objects.equals(creationDate, that.creationDate) && Objects.equals(lastModifier, that.lastModifier) && Objects.equals(lastModificationDate, that.lastModificationDate) && Objects.equals(versionComment, that.versionComment) && Objects.equals(originalVersionId, that.originalVersionId) && Objects.equals(contentStatus, that.contentStatus) && Objects.equals(navigationContent, that.navigationContent) && Objects.equals(customContent, that.customContent) && Objects.equals(space, that.space) && Objects.equals(parent, that.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hibernateVersion, title, lowerTitle, bodyContents, contentProperties, version, creator, creationDate, lastModifier, lastModificationDate, versionComment, originalVersionId, contentStatus, navigationContent, customContent, space, parent);
    }

    @Override
    public String toString() {
        return "ConfluencePage{" +
                "id='" + id + '\'' +
                ", hibernateVersion='" + hibernateVersion + '\'' +
                ", title='" + title + '\'' +
                ", lowerTitle='" + lowerTitle + '\'' +
                ", bodyContents=" + bodyContents +
                ", contentProperties=" + contentProperties +
                ", version='" + version + '\'' +
                ", creator=" + creator +
                ", creationDate='" + creationDate + '\'' +
                ", lastModifier='" + lastModifier + '\'' +
                ", lastModificationDate='" + lastModificationDate + '\'' +
                ", versionComment='" + versionComment + '\'' +
                ", originalVersionId='" + originalVersionId + '\'' +
                ", contentStatus='" + contentStatus + '\'' +
                ", navigationContent='" + navigationContent + '\'' +
                ", customContent=" + customContent +
                ", space=" + space +
                ", parent=" + parent +
                '}';
    }
}
