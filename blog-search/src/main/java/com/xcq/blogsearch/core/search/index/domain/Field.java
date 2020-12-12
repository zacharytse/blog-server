package com.xcq.blogsearch.core.search.index.domain;

import com.xcq.blogsearch.core.search.domain.Term;

import java.util.List;

/**
 * 由Term组成
 */
public abstract class Field {

    /**
     * 是否将Field放入到index中
     * YES:放入index中
     * NO:反之
     */
    public enum Store {
        YES,
        NO
    }

    private Store type;

    public Field(Store type) {
        this.type = type;
    }

    protected String name;
    protected String content;
    protected int fieldId;

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public Store getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
