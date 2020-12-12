package com.xcq.blogsearch.core.search.index.op.search;

import com.xcq.blogsearch.core.search.index.dao.Directory;
import com.xcq.blogsearch.core.search.index.domain.IndexTerm;
import com.xcq.blogsearch.core.search.split.divide.TermDivider;
import com.xcq.blogsearch.core.singleton.TermDividerSingleton;

import java.util.ArrayList;
import java.util.List;

public class IndexSearch {

    private Directory directory;

    private TermDivider divider;

    public IndexSearch(Directory directory) {
        this.directory = directory;
        divider = TermDividerSingleton.getDivider();
    }

    public IndexTerm searchTitle(String searchWord) {
        return search(searchWord, true);
    }

    public IndexTerm searchContent(String searchWord) {
        return search(searchWord, false);
    }

    private IndexTerm search(String word, boolean isTitle) {
        IndexTerm term = null;
        if (isTitle) {
            term = directory.findTitleTerm(word);
        } else {
            term = directory.findContentTerm(word);
        }
        return term;
    }
}
