package com.example.myapplication;

public class News {
    String title;
    String Link;
    String image;
    public News(String title, String link, String image) {
        this.title = title;
        Link = link;
        this.image = image;
    }
    public String getTitle() {
        return title;
    }

    public String getLink() {
        return Link;
    }

    public String getImage() {
        return image;
    }


}
