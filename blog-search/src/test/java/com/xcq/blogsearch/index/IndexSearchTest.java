package com.xcq.blogsearch.index;

import com.xcq.blogsearch.core.search.index.dao.Directory;
import com.xcq.blogsearch.core.search.index.dao.impl.RAMDirectory;
import com.xcq.blogsearch.core.search.index.domain.Document;
import com.xcq.blogsearch.core.search.index.domain.Field;
import com.xcq.blogsearch.core.search.index.domain.IndexTerm;
import com.xcq.blogsearch.core.search.index.domain.TextField;
import com.xcq.blogsearch.core.search.index.op.config.IndexWriterConfig;
import com.xcq.blogsearch.core.search.index.op.search.IndexSearch;
import com.xcq.blogsearch.core.search.index.op.write.IndexWriter;
import com.xcq.blogsearch.core.singleton.TermDividerSingleton;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class IndexSearchTest {

    @Test
    public void testIndexSearch() {
        TermDividerSingleton.getDivider();
        Directory directory = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig();
        IndexWriter writer = new IndexWriter(directory, config);
        Document doc = new Document();
        Field field = new TextField("发生的", "的范围");
        doc.add(field);
        field = new TextField("放大的", "分为");
        doc.add(field);
        long startTime = System.currentTimeMillis();
        writer.addDocument(doc);
        doc = new Document();
        field = new TextField("发生的", "的范围");
        doc.add(field);
        field = new TextField("放大的", "分为");
        doc.add(field);
        writer.addDocument(doc);
        writer.commit();
        writer.commit();
        long endTime = System.currentTimeMillis();
        System.out.println("write time:" + (endTime - startTime));
        /*try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        startTime = System.currentTimeMillis();
        IndexSearch search = new IndexSearch(directory);
        endTime = System.currentTimeMillis();
        System.out.println("construct search time:" + (endTime - startTime));
        startTime = System.currentTimeMillis();
        IndexTerm term = search.searchTitle("的");
        Map<Long, List<Integer>> fieldIds = term.getFieldIds();
        for(Map.Entry<Long,List<Integer>> fieldId : fieldIds.entrySet()) {
            System.out.println(fieldId.getValue().size());
        }
        endTime = System.currentTimeMillis();
        System.out.println("search time:" + (endTime - startTime));
        /*for(IndexTerm term : terms) {
            System.out.println(term.getVal() + " " + term.getStartIds().get(0).get(0));
        }*/
    }
}
