package com.family168.springsecuritybook.x05;

public class Message {
    private Long id;
    private String title;

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

    public String toString() {
        return "Messsage{id=" + id + ",title=" + title + "}";
    }
}
