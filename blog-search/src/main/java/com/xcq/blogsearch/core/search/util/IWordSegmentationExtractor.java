package com.xcq.blogsearch.core.search.util;

import com.xcq.blogsearch.core.search.domain.Term;

import java.io.IOException;
import java.util.List;

/**
 * 分词提取器
 */
public interface IWordSegmentationExtractor {

    default List<Term> extractTags(String sentence) throws IOException {
        return extractTags(sentence,20,false,null);
    }
    /**
     * 抽取分词接口
     * @param sentence 待提取的文本
     * @param topK topK权重最大的关键字 默认20
     * @param withWeight 是否一并返回关键词权重值 默认值为false
     * @param allowPos 指定词性 默认值为空
     * @return
     */
    List<Term> extractTags(String sentence,int topK,
                           boolean withWeight,String[] allowPos) throws IOException;

    /**
     * 使用停止词语料库
     * @param filename
     */
    void setStopWords(String filename);
}
