package com.codedao.footballapp.rssparser;

/**
 * Created by vanthanh on 12/12/17.
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco Gomiero on 12/02/2015.
 */
public class Article {

    private String title;
    private String author;
    private String link;
    private String pubString;
    private String description;
    private String content;
    private String image;
    private List<String> categories;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getLink() {
        return link;
    }

    public String getPubDate() {
        return pubString;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPubDate(String pubString) {
        this.pubString = pubString;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void addCategory(String category) {
        if (categories == null)
            categories = new ArrayList<>();
        categories.add(category);
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", link='" + link + '\'' +
                ", pubString=" + pubString +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", categories=" + categories +
                '}';
    }
}
