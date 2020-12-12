package com.xcq.blogsearch.core.search.index.op.write;

import com.xcq.blogsearch.core.search.index.dao.Directory;
import com.xcq.blogsearch.core.search.index.domain.Document;
import com.xcq.blogsearch.core.search.index.op.config.IndexWriterConfig;

/**
 * 写入索引数据
 */
public class IndexWriter {

    private DocumentsWriter writer;

    public IndexWriter(Directory directory,IndexWriterConfig config) {
        writer = new DocumentsWriter(directory,config);
    }

    /**
     * 将document加入到待写入索引队列中
     * @param doc
     */
    public void addDocument(Document doc) {
        writer.add(doc);
    }

    /**
     * 只有调用该方法，才会真正把索引写入进去
     */
    public void commit() {
        writer.commit();
    }
}
