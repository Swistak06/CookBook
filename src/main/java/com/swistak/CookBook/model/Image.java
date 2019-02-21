package com.swistak.CookBook.model;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue
    @Column(name = "image_id")
    private long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Image() {
    }

    public Image(String url, Recipe recipe) {
        this.url = url;
        this.recipe = recipe;
    }

    public Image(String url) {
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
