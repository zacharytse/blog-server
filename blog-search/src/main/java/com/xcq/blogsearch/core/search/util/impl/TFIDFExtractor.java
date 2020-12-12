package com.xcq.blogsearch.core.search.util.impl;

import com.xcq.blogsearch.core.io.WrapperStream;
import com.xcq.blogsearch.core.io.builder.StreamBuilder;
import com.xcq.blogsearch.core.search.domain.Term;
import com.xcq.blogsearch.core.search.util.IWordSegmentationExtractor;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class TFIDFExtractor implements IWordSegmentationExtractor {


    private List<Term> corpus;

    private String corpusPath;

    private boolean isInit = false;

    public TFIDFExtractor(String corpusPath) throws IOException {
        this.corpusPath = corpusPath;
        initCorpus();
    }

    private void initCorpus() throws IOException {
        corpus = new LinkedList<>();
        WrapperStream ws = new StreamBuilder().builder(corpusPath);
        String line;
        while ((line = ws.next()) != null) {
            corpus.add(Term.getTerm(line));
        }
    }

    @Override
    public List<Term> extractTags(String sentence, int topK, boolean withWeight, String[] allowPos) throws IOException {
        PriorityQueue<Term> maxHeap = new PriorityQueue<>(topK, new Comparator<Term>() {
            @Override
            public int compare(Term o1, Term o2) {
                return Double.compare(o2.getWeight(), o1.getWeight());
            }
        });

        return null;
    }

    @Override
    public void setStopWords(String filename) {

    }

}
