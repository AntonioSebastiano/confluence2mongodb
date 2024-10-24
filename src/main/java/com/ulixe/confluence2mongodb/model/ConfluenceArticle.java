package com.ulixe.confluence2mongodb.model;

import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;
import java.util.Objects;

public class ConfluenceArticle {
    
    @XmlAttribute()
    private String name;

    
    @XmlElement()
    private String content;

    @XmlElement
    private String space;

    @XmlElement
    private String parent;

    @XmlElement
    private List<String> attachment;

    @XmlElement
    private String creator;

    @XmlElement
    private List<String> comments;

    // Costruttore vuoto
    public ConfluenceArticle() {
    }

    public  String getname() {
        return name;
    }

    public void setname( String name) {
        this.name = name;
    }

    public  String getcontent() {
        return content;
    }

    public void setcontent( String content) {
        this.content = content;
    }

    public String getspace() {
        return space;
    }

    public void setspace(String space) {
        this.space = space;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<String> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<String> attachment) {
        this.attachment = attachment;
    }

    public String getcreator() {
        return creator;
    }

    public void setcreator(String creator) {
        this.creator = creator;
    }

    public List<String> getcomments() {
        return comments;
    }

    public void setcomments(List<String> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfluenceArticle ConfluenceArticle = (ConfluenceArticle) o;
        return Objects.equals(name, ConfluenceArticle.name) &&
                Objects.equals(content, ConfluenceArticle.content) &&
                Objects.equals(space, ConfluenceArticle.space) &&
                Objects.equals(parent, ConfluenceArticle.parent) &&
                Objects.equals(attachment, ConfluenceArticle.attachment) &&
                Objects.equals(creator, ConfluenceArticle.creator) &&
                Objects.equals(comments, ConfluenceArticle.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, content, space, parent, attachment, creator, comments);
    }

    @Override
    public String toString() {
        return "ConfluenceArticle{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", space='" + space + '\'' +
                ", parent='" + parent + '\'' +
                ", attachment=" + attachment +
                ", creator='" + creator + '\'' +
                ", comments=" + comments +
                '}';
    }
}
