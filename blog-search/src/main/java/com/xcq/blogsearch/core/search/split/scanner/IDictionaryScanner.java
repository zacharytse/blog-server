package com.xcq.blogsearch.core.search.split.scanner;

import com.xcq.blogsearch.core.search.domain.Term;

import java.io.IOException;

public interface IDictionaryScanner {
    /**
     * 设置字典的路径
     *
     * @param path
     */
    void setDictPath(String path);

    /**
     * 判断字典中是否有这个词
     *
     * @param word
     * @return
     */
    boolean hasThisWord(String word);

    Term getThisWord(String word);

    Double getScore(String word);
}
