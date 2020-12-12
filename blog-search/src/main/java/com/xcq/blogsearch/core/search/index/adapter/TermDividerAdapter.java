package com.xcq.blogsearch.core.search.index.adapter;

import com.xcq.blogsearch.core.search.split.divide.TermDivider;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class TermDividerAdapter {

    private TermDivider divider;

    public TermDividerAdapter(TermDivider divider) {
        this.divider = divider;
    }

    public List<Pair<String, Integer>> divide(String word) {
        List<String> divides = divider.divide(word);
        List<Pair<String,Integer>> ans = new LinkedList<>();
        int idx = 0;
        for(String str : divides) {
            ans.add(new Pair<>(str,idx));
            idx += str.length();
        }
        return ans;
    }
}
