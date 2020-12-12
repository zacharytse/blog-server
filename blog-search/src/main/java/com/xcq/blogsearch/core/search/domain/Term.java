package com.xcq.blogsearch.core.search.domain;

import java.io.Serializable;

/**
 * 分词的单位
 */
public class Term implements Serializable {
    //词的值
    private String value;
    //词性
    private String partOfSpeech;

    private Double weight;

    public Term() {
        weight = 0.0;
    }

    public Double getWeight() {
        return weight;
    }

    public Term setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Term setValue(String value) {
        this.value = value;
        return this;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public Term setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
        return this;
    }

    @Override
    public String toString() {
        return "Term{" +
                "value='" + value + '\'' +
                ", partOfSpeech='" + partOfSpeech + '\'' +
                ", weight=" + weight +
                '}';
    }

    /**
     * 根据字符串生成term，格式 value weight attr
     * @param str
     * @return
     */
    public static Term getTerm(String str) {
        String[] splits = str.split(" ");
        Term term = new Term().setValue(splits[0])
                .setWeight(Double.parseDouble(splits[1]));
        if(splits.length == 3) {
            //need set attr
            term.setPartOfSpeech(splits[2]);
        }
        return term;
    }
}
