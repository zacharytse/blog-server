package com.xcq.blogsearch.core.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WrapperStream {

    private BufferedReader reader;
    private InputStream is;

    public WrapperStream(InputStream is) {
        this.is = is;
        reader = new BufferedReader(new InputStreamReader(is));
    }

    public String next() throws IOException {
        return reader.readLine();
    }

    public void close() throws IOException {
        reader.close();
        is.close();
    }
}
