package com.xcq.blogsearch.core.io.builder;

import com.xcq.blogsearch.core.io.WrapperStream;
import com.xcq.blogsearch.core.io.factory.ReadFactory;

import java.io.IOException;
import java.io.InputStream;

public class StreamBuilder {

    public WrapperStream builder(String filename) throws IOException {
        InputStream is = new ReadFactory().read(filename);
        return new WrapperStream(is);
    }
}
