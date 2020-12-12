package com.xcq.blogsearch.index;

import com.xcq.blogsearch.core.search.index.op.config.IndexWriterConfig;
import org.junit.jupiter.api.Test;

public class IndexWriteConfigTest {

    @Test
    public void testEDR() {
        IndexWriterConfig config = new IndexWriterConfig();
        System.out.println(config.isSimilar("哈哈", "哈哈"));
    }
}
