package com.example.user.newstoday.model;

public class dataholder {
    String author;
    String publishAt;
    String image;
    String desc;

    String detail;

    public dataholder(String author, String publishAt, String image, String desc,String detail) {
        this.author = author;
        this.publishAt = publishAt;
        this.image = image;
        this.desc = desc;
        this.detail=detail;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishAt() {
        return publishAt;
    }

    public String getImage() {
        return image;
    }

    public String getDesc() {
        return desc;
    }

    public String getDetail() {
        return detail;
    }
}
