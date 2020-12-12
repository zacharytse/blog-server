package com.xcq.blogsearch.core.search.core;

import com.xcq.blogsearch.core.search.core.dao.IDocumentQuery;
import com.xcq.blogsearch.core.search.core.wrapper.DocumentWrapper;
import com.xcq.blogsearch.core.search.index.dao.Directory;
import com.xcq.blogsearch.core.search.index.dao.impl.RAMDirectory;
import com.xcq.blogsearch.core.search.index.domain.Document;
import com.xcq.blogsearch.core.search.index.domain.IndexTerm;
import com.xcq.blogsearch.core.search.index.op.config.IndexWriterConfig;
import com.xcq.blogsearch.core.search.index.op.search.IndexSearch;
import com.xcq.blogsearch.core.search.index.op.write.IndexWriter;

import java.util.*;

/**
 * 核心的搜索类
 */
public class DocumentSearch {

    private IndexWriter writer;

    private IndexSearch search;

    private IDocumentQuery query;

    public DocumentSearch(IDocumentQuery query) {
        this(query, new RAMDirectory(), new IndexWriterConfig());
    }

    public DocumentSearch(IDocumentQuery query,
                          Directory directory, IndexWriterConfig config) {
        this.query = query;
        initDefaultWriter(directory, config);
        initDefaultSearcher(directory);
    }

    public void addDocument(Document document) {
        writer.addDocument(document);
    }

    public void commit() {
        writer.commit();
    }

    private void initDefaultWriter(Directory directory, IndexWriterConfig config) {
        writer = new IndexWriter(directory, config);
    }

    private void initDefaultSearcher(Directory directory) {
        search = new IndexSearch(directory);
    }

    public List<DocumentWrapper> searchTitle(String word) {
        return doSearch(word, true);
    }

    public List<DocumentWrapper> searchContent(String word) {
        return doSearch(word, false);
    }

    private List<DocumentWrapper> doSearch(String word, boolean isTitle) {
        if (isTitle) {
            return handleSearchResult(search.searchTitle(word));
        } else {
            return handleSearchResult(search.searchContent(word));
        }
    }

    //同样的doc应该被放到一个wrapper里面
    private List<DocumentWrapper> handleSearchResult(IndexTerm term) {
        List<DocumentWrapper> wrappers = new LinkedList<>();
        List<Long> docIds = term.getDocIds();
        deduplicationDocIds(docIds);
        for (Long docId : docIds) {
            wrappers.add(wrapperDocument(docId, term));
        }
        return wrappers;
    }


    private void deduplicationDocIds(List<Long> docIds) {
        Set<Long> set = new HashSet<>(docIds);
        docIds.clear();
        docIds.addAll(set);
    }

    private DocumentWrapper wrapperDocument(Long docId, IndexTerm term) {
        Document doc = query.findDocumentById(docId);
        Map<Integer, List<Integer>> pos = term.getStartIds(docId);
        DocumentWrapper wrapper = new DocumentWrapper(doc, pos);
        return wrapper;
    }
}
