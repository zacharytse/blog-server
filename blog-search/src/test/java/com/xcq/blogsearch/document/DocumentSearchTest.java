package com.xcq.blogsearch.document;

import com.xcq.blogsearch.core.search.core.DocumentSearch;
import com.xcq.blogsearch.core.search.core.dao.IDocumentQuery;
import com.xcq.blogsearch.core.search.core.wrapper.DocumentWrapper;
import com.xcq.blogsearch.core.search.index.domain.Document;
import com.xcq.blogsearch.core.search.index.domain.Field;
import com.xcq.blogsearch.core.singleton.TermDividerSingleton;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DocumentSearchTest {

    @Test
    public void testDocumentSearch() {
        long startTime = System.currentTimeMillis();
        TermDividerSingleton.getDivider();
        long endTime = System.currentTimeMillis();
        System.out.println("construct divider time:" + (endTime - startTime));
        TestQueryImpl query = new TestQueryImpl();
        List<Document> documents = query.getDocuments();
        DocumentSearch search = new DocumentSearch(query);
        startTime = System.currentTimeMillis();
        for(Document document : documents) {
            search.addDocument(document);
        }
        //search.addDocument(documents.get(0));
        search.commit();
        endTime = System.currentTimeMillis();
        System.out.println("construct index time:" + (endTime - startTime));
        startTime = System.currentTimeMillis();
        List<DocumentWrapper> wrappers = search.searchContent("范");
        endTime = System.currentTimeMillis();
        System.out.println("search index time:" + (endTime - startTime));
        for(DocumentWrapper wrapper : wrappers) {
            List<Field> fields = wrapper.getFields();
            for(Field field : fields) {
                System.out.println(field.getName());
            }
        }
    }
}
