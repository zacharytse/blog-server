package com.xcq.blogsearch.document;

import com.xcq.blogsearch.core.search.core.dao.IDocumentQuery;
import com.xcq.blogsearch.core.search.index.domain.Document;
import com.xcq.blogsearch.core.search.index.domain.TextField;

import java.util.ArrayList;
import java.util.List;

public class TestQueryImpl implements IDocumentQuery {

    private List<Document> documents;

    public TestQueryImpl() {
        documents = new ArrayList<>();
        Document doc = new Document();
        doc.setDocId(0);
        doc.add(new TextField("多少分呢", "的范围"));
        doc.add(new TextField("发生的", "非完全阿斯顿"));
        documents.add(doc);
        doc = new Document();
        doc.setDocId(1);
        doc.add(new TextField("大大双", "分为范围是"));
        doc.add(new TextField("范文芳", "分为范围地方v我热夫"));
        documents.add(doc);
    }

    @Override
    public List<Document> findAllDocumentsById(List<Long> ids) {
        return documents;
    }

    @Override
    public Document findDocumentById(long id) {
        final Document document = documents.get((int) id);
        return document;
    }

    //方便测试的接口
    public List<Document> getDocuments() {
        return documents;
    }
}
