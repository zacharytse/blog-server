package com.xcq.blogsearch.core.search.index.dao.impl;

import com.xcq.blogsearch.core.search.index.dao.Directory;
import com.xcq.blogsearch.core.search.index.domain.IndexTerm;
import com.xcq.blogsearch.core.search.index.op.config.IndexWriterConfig;

import java.util.*;

public class RAMDirectory implements Directory {

    private Map<String, IndexTerm> titleIndex;
    private Map<String, IndexTerm> contentIndex;
    private IndexWriterConfig config;

    public RAMDirectory() {
        config = new IndexWriterConfig();
        titleIndex = new HashMap<>();
        contentIndex = new HashMap<>();
    }

    @Override
    public void addAllTitleIndex(List<IndexTerm> terms) {
        addAll(titleIndex, terms);
    }

    @Override
    public void addTitleIndex(IndexTerm term) {
        add(titleIndex, term);
    }

    @Override
    public void addAllContentIndex(List<IndexTerm> terms) {
        addAll(contentIndex, terms);
    }

    private void addAll(Map<String, IndexTerm> map,
                        List<IndexTerm> terms) {
        for (IndexTerm term : terms) {
            add(map, term);
        }
    }

    private void add(Map<String, IndexTerm> map,
                     IndexTerm term) {
        synchronized (map) {
            IndexTerm storedTerm = map.get(term.getVal());
            if (storedTerm == null) {
                map.put(term.getVal(), term);
            } else {
                long docId = term.getDocIds().get(0);
                int fieldId = term.findAllFieldIdByDocumentId(docId).get(0);
                int startId = term.findAllStartIdByDocumentIdAndFieldId(docId, fieldId).get(0);
                String val = term.getVal();
                storedTerm.addNewWord(val, docId, fieldId, startId);
            }
        }
    }

    @Override
    public void addContentIndex(IndexTerm term) {
        add(contentIndex, term);
    }

    @Override
    public synchronized IndexTerm findTitleTerm(String word) {
        return doFindTerm(word,titleIndex);
    }

    @Override
    public IndexTerm findContentTerm(String word) {
        return doFindTerm(word,contentIndex);
    }

    private synchronized IndexTerm doFindTerm(String word, Map<String,IndexTerm> map) {
        for (Map.Entry<String, IndexTerm> entry : map.entrySet()) {
            if (config.isSimilar(word, entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
