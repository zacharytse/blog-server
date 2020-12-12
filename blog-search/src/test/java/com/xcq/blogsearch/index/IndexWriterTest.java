package com.xcq.blogsearch.index;

import com.xcq.blogsearch.core.search.index.dao.Directory;
import com.xcq.blogsearch.core.search.index.dao.impl.RAMDirectory;
import com.xcq.blogsearch.core.search.index.domain.Document;
import com.xcq.blogsearch.core.search.index.domain.TextField;
import com.xcq.blogsearch.core.search.index.op.write.IndexWriter;
import com.xcq.blogsearch.core.search.index.op.config.IndexWriterConfig;
import org.junit.jupiter.api.Test;

public class IndexWriterTest {

    @Test
    public void testAddDocumentWriter() {
        Directory directory = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig();
        IndexWriter writer = new IndexWriter(directory,config);
        for(int i = 0; i < 10; ++i) {
            Document doc = new Document();
            doc.add(new TextField("分为氛围被称为为","范围比我确认二位夫人"));
            doc.add(new TextField("分为氛围被称为为","范围比我确认二位夫人"));
            doc.add(new TextField("分为氛围被称为为","范围比我确认二位夫人"));
            doc.add(new TextField("分为氛围被称为为","范围比我确认二位夫人"));
            doc.add(new TextField("分为氛围被称为为","范围比我确认二位夫人"));
            doc.add(new TextField("分为氛围被称为为","范围比我确认二位夫人"));
            doc.add(new TextField("分为氛围被称为为","范围比我确认二位夫人"));
            writer.addDocument(doc);
        }
    }
}
