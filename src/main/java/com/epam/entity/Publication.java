package com.epam.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public class Publication extends Entity {
    private String name;
    private String index;
    private String description;
    private String language;
    private BigDecimal price;
    private String titleImgLink;
    private Topic topic;

    public Publication() {
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitleImgLink() {
        return titleImgLink;
    }

    public void setTitleImgLink(String titleImgLink) {
        this.titleImgLink = titleImgLink;
    }



    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "index=" + index +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", price=" + price +
                ", titleImgLink='" + titleImgLink + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return Objects.equals(index, that.index) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(language, that.language) && Objects.equals(price, that.price) && Objects.equals(titleImgLink, that.titleImgLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, name, description, language, price, titleImgLink);
    }
}
