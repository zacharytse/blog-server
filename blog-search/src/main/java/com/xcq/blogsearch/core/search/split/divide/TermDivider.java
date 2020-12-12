package com.xcq.blogsearch.core.search.split.divide;

import com.xcq.blogsearch.core.search.split.scanner.IDictionaryScanner;
import com.xcq.blogsearch.core.search.split.scanner.impl.TrieDictionaryScanner;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 输入语句的划分，生成相应的DAG图
 */
public class TermDivider {

    private IDictionaryScanner scanner;

    public TermDivider() {
        scanner = new TrieDictionaryScanner("dict.txt");
    }

    private List<Integer>[] buildGraph(String sentence) {
        List<Integer>[] graph = new List[sentence.length()];
        for (int i = 0; i < sentence.length(); ++i) {
            for (int j = i + 1; j <= sentence.length(); ++j) {
                String word = sentence.substring(i, j);
                if (scanner.hasThisWord(word)) {
                    if (graph[i] != null) {
                        graph[i].add(j);
                    } else {
                        List<Integer> list = new ArrayList<>();
                        list.add(j);
                        graph[i] = list;
                    }
                } else {
                    //处理不在词典中的情况,先暂时空着
                }
            }
        }
        return graph;
    }

    public List<String> divide(String word) {
        List<Pair<Character, Integer>> specialData = cleanData(word);
        List<Integer>[] graph = buildGraph(word);
        double[] dp = new double[word.length() + 1];
        String[] ans = new String[word.length()];
        for (Pair<Character, Integer> data : specialData) {
            ans[data.getValue()] = String.valueOf(data.getKey());
        }
        int n = word.length();
        for (int idx = n - 1; idx >= 0; --idx) {
            String maxWord = "";
            List<Integer> list = graph[idx];
            if (list == null) {
                continue;
            }
            double maxScore = -Double.MAX_VALUE;
            for (Integer x : list) {
                String curWord = word.substring(idx, x);
                double score = scanner.getScore(curWord) + dp[x];
                if (Double.compare(score, maxScore) > 0) {
                    maxScore = score;
                    maxWord = curWord;
                }
            }
            dp[idx] = maxScore;
            ans[idx] = maxWord;
        }
        return handleResult(ans);
    }

    private List<String> handleResult(String[] str) {
        int idx = 0;
        List<String> ans = new LinkedList<>();
        int n = str.length;
        while (idx < n) {
            ans.add(str[idx]);
            idx += str[idx].length();
        }
        return ans;
    }

    private List<Pair<Character, Integer>> cleanData(String word) {
        List<Pair<Character, Integer>> specialCharacters = new ArrayList<>();
        for (int i = 0; i < word.length(); ++i) {
            if (0x4e00 <= word.charAt(i) &&
                    0x9fa5 >= word.charAt(i)) {
                //not special
                continue;
            }
            specialCharacters.add(new Pair<>(word.charAt(i), i));
        }
        return specialCharacters;
    }
}
