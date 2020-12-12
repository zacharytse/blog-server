package com.xcq.blogsearch.core.io.factory;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class ReadFactory {

    public InputStream read(String filename) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(filename);
        return classPathResource.getInputStream();
    }


}
