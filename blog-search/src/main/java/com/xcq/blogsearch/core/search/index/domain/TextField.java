package com.xcq.blogsearch.core.search.index.domain;

import com.xcq.blogsearch.core.search.domain.Term;

import java.util.List;

public class TextField extends Field {

    public TextField(String title, String content) {
        this(title,content,Store.YES);
    }

    public TextField(String title, String content, Store type) {
        super(type);
        this.name = title;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
