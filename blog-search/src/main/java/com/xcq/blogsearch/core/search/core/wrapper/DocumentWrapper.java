package com.xcq.blogsearch.core.search.core.wrapper;

import com.xcq.blogsearch.core.search.index.domain.Document;
import com.xcq.blogsearch.core.search.index.domain.Field;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 对Document重新做封装，方便搜索结果的使用
 */
public class DocumentWrapper {

    private Document document;

    private Map<Integer, List<Integer>> pos;

    public DocumentWrapper(Document document, Map<Integer, List<Integer>> pos) {
        this.document = document;
        this.pos = pos;
    }

    public List<Integer> getDocumentDetailPos(int fieldId) {
        return pos.get(fieldId);
    }

    public Document getDocument() {
        return document;
    }

    public List<Field> getFields() {
        List<Field> fields = new LinkedList<>();
        List<Field> allFields = document.getFields();
        for(Map.Entry entry : pos.entrySet()) {
            fields.add(allFields.get((Integer) entry.getKey()));
        }
        return fields;
    }
}
