package com.ulixe.confluence2mongodb.model;

import jakarta.xml.bind.annotation.*;

import java.util.Objects;

@XmlRootElement(name = "object")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfluenceBodyContent {
    @XmlAttribute()
    private String id;

    @XmlAttribute()
    private String body;

    @XmlElement()
    private String content;

    @XmlAttribute()
    private String bodyType;  //enum?

    // Empty Constructor
    public ConfluenceBodyContent() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfluenceBodyContent that = (ConfluenceBodyContent) o;
        return Objects.equals(id, that.id) && Objects.equals(body, that.body) && Objects.equals(content, that.content) && Objects.equals(bodyType, that.bodyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body, content, bodyType);
    }

    @Override
    public String toString() {
        return "ConfluenceBodyContent{" +
                "id='" + id + '\'' +
                ", body='" + body + '\'' +
                ", content=" + content +
                ", bodyType='" + bodyType + '\'' +
                '}';
    }
}
