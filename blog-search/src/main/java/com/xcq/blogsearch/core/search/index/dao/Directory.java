package com.xcq.blogsearch.core.search.index.dao;

import com.xcq.blogsearch.core.search.index.domain.IndexTerm;
import com.xcq.blogsearch.core.search.index.op.config.IndexWriterConfig;

import java.util.List;

/**
 * 持久层接口
 */
public interface Directory {

    void addAllTitleIndex(List<IndexTerm> terms);

    void addTitleIndex(IndexTerm term);

    void addAllContentIndex(List<IndexTerm> terms);

    void addContentIndex(IndexTerm term);

    IndexTerm findTitleTerm(String word);

    IndexTerm findContentTerm(String word);
}
