package com.xcq.blogsearch.core.search.index.op.config;

/**
 * IndexWriter的配置类
 */
public class IndexWriterConfig {

    /**
     * 定义两个词的相似度阈值，高于该阈值，则认为不相似，默认值定义为5
     */
    private int maxSimilarity;


    public IndexWriterConfig() {
        this(5);
    }

    public IndexWriterConfig(int maxSimilarity) {
        this.maxSimilarity = maxSimilarity;
    }

    public boolean isSimilar(String word1, String word2) {
        int similarity = getSimilarityByEDR(word1, word2);
        return similarity <= Math.min(maxSimilarity,Math.min(word1.length(),word2.length()));
    }

    /**
     * 通过EDR算法得到两个字符串的相似程度
     *
     * @param word1
     * @param word2
     * @return
     */
    private int getSimilarityByEDR(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] edr = new int[n][m];
        edr[0][0] = 0;
        for(int i = 0; i < m; ++i) {
            edr[0][i] = i;
        }
        for (int i = 0; i < n; ++i) {
            edr[i][0] = i;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; ++j) {
                int edit = word1.charAt(i) == word2.charAt(j) ? 0 : 1;
                edr[i][j] = Math.min(edr[i - 1][j - 1] + edit,
                        Math.min(edr[i][j - 1] + 1, edr[i - 1][j] + 1));
            }
        }
        return edr[n - 1][m - 1];
    }
}
