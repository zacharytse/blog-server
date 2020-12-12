package com.xcq.blogsearch.core.search.split.scanner.impl;

import com.xcq.blogsearch.core.io.WrapperStream;
import com.xcq.blogsearch.core.io.builder.StreamBuilder;
import com.xcq.blogsearch.core.search.domain.Term;
import com.xcq.blogsearch.core.search.split.scanner.IDictionaryScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于Trie树的扫描
 */
public class TrieDictionaryScanner implements IDictionaryScanner {

    private String dictPath;

    private Trie trie;

    private double total;

    private Map<String, Double> freq;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public TrieDictionaryScanner(String dictPath) {
        this.dictPath = dictPath;
        init();
    }

    private void init() {
        freq = new HashMap<>();
        trie = new Trie();
        WrapperStream ws = null;
        try {
            ws = new StreamBuilder().builder(dictPath);
            String line = "";
            while ((line = ws.next()) != null) {
                Term term = Term.getTerm(line);
                total += term.getWeight();
                freq.put(term.getValue(), term.getWeight());
                trie.insert(term);
            }
            ws.close();
            for (Map.Entry entry : freq.entrySet()) {
                Double score = Math.log((Double) entry.getValue() / total);
                entry.setValue(score);
            }
        } catch (Exception e) {
            logger.info("[TrieDictionaryScanner.init()][Construct trie not success]");
        }
    }

    @Override
    public void setDictPath(String path) {
        this.dictPath = path;
    }

    public boolean hasThisWord(String word) {
        return trie.hasThisWord(word);
    }

    @Override
    public Term getThisWord(String word) {
        return trie.findTerm(word);
    }

    @Override
    public Double getScore(String word) {
        return freq.get(word);
    }

    class Trie {

        /**
         * 字典树树根
         */
        private Node root;

        public Trie() {
            root = new Node();
        }

        public boolean hasThisWord(String word) {
            return findTerm(word) == null ? false : true;
        }

        public Term findTerm(String str) {
            if (str.length() == 0) {
                return null;
            }
            Node child = root.children.get(str.charAt(0));
            return findTerm(child, str, 0);
        }

        private Term findTerm(Node node, String str, int idx) {
            if (node == null) {
                return null;
            }
            char val = str.charAt(idx);
            if (node.key == val) {
                if (idx == str.length() - 1) {
                    return node.getTerm();
                }
                Node child = node.children.get(str.charAt(idx + 1));
                return findTerm(child, str, idx + 1);
            } else {
                return null;
            }
        }


        public void insert(Term term) {
            if (term.getValue().length() == 0) {
                return;
            }
            insert(root, term, 0);
        }

        private void insert(Node node, Term term, int idx) {
            String str = term.getValue();
            if (idx >= str.length()) {
                node.term = term;
                return;
            }
            char val = str.charAt(idx);
            Node child = node.children.get(val);
            if (child == null) {
                child = new Node(val);
                node.children.put(val, child);
            }
            insert(child, term, idx + 1);
        }

        class Node {
            private char key;
            private Term term;
            private Map<Character, Node> children;

            public Node(char val) {
                this.key = val;
                children = new HashMap<>();
                term = null;
            }

            public Node() {
                this('/');
            }

            public Term getTerm() {
                return term;
            }

            public void setTerm(Term term) {
                this.term = term;
            }
        }
    }
}
