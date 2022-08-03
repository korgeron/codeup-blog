package com.codeup.springblog.models;

public class Post {
    private Long id;
    private String title;
    private String body;
    private String href;

    public Post() {
    }

    public Post(Long id, String title, String body, String href) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.href = href;
    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Post(Long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
