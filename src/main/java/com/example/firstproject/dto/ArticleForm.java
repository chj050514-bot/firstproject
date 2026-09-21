package com.example.firstproject.dto;

public class ArticleForm {
    private String title;
    private String content;

    public ArticleForm(String title, String content) {
        this.content = content;
        this.title = title;
    }

    @Override
    public String toString() {
        return "ArticleForm{ " +
                "title=" + title + '\''
                + ", content='" + content + '\'' +
                '}';
    }
}

