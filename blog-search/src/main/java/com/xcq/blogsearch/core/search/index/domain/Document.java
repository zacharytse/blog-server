package com.xcq.blogsearch.core.search.index.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * 存储文档的实体类
 */
public class Document {
    private long docId;

    private List<Field> fields;

    public Document() {
        fields = new LinkedList<>();
    }

    public List<Field> getFields() {
        return fields;
    }

    public long getDocId() {
        return docId;
    }

    public void setDocId(long docId) {
        this.docId = docId;
    }

    public void add(Field field) {
        field.setFieldId(fields.size());
        fields.add(field);
    }
}
