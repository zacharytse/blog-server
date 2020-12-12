package com.xcq.blogsearch.term;

import com.xcq.blogsearch.core.search.index.adapter.TermDividerAdapter;
import com.xcq.blogsearch.core.search.split.divide.TermDivider;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 分词算法测试
 */
public class TestTerm {

    @Test
    public void testDivider() {
        TermDivider divider = new TermDivider();
        List<String> list = divider.divide("访问,中国");
        for (String str : list) {
            System.out.println(str);
        }
    }

    @Test
    public void testDividerAdapter() {
        TermDivider divider = new TermDivider();
        TermDividerAdapter adapter = new TermDividerAdapter(divider);
        List<Pair<String, Integer>> pairs = adapter.divide("访问，中国");
        for(Pair<String,Integer> pair : pairs) {
            System.out.println(pair.getKey() + " " + pair.getValue());
        }
    }
}
