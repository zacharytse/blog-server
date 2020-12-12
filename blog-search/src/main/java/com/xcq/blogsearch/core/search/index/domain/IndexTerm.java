package com.xcq.blogsearch.core.search.index.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


/**
 * 存储索引的最小单位
 */
public class IndexTerm {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String val;
    //包含的文档id
    private List<Long> docIds;
    //包含的field id
    //格式<docId:[fieldId....]>
    private Map<Long, List<Integer>> fieldIds;
    //在所有的field中的出现的位置
    //格式<docId:<Field:[startId...]>>
    private Map<Long, Map<Integer, List<Integer>>> startIds;
    //在所有文档中出现的频率
    //格式<docId:value_freq>
    private Map<Long, Integer> freq;

    public IndexTerm(String val, long docId, int fieldId, int startId) {
        this.val = val;
        docIds = new ArrayList<>();
        fieldIds = new HashMap<>();
        startIds = new HashMap<>();
        freq = new HashMap<>();
        addNewWord(val, docId, fieldId, startId);
    }

    public synchronized void addNewWord(String val, long docId, int fieldId, int startId) {
        if (!val.equals(val)) {
            logger.info("[IndexTerm.addNewWord][Add an error word]");
            return;
        }
        addNewDocId(docId);
        addNewFieldId(docId, fieldId);
        addNewStartId(docId, fieldId, startId);
        updateFre(docId);
    }

    private void addNewDocId(long docId) {
        docIds.add(docId);
    }

    private void addNewFieldId(long docId, int fieldId) {
        if (!fieldIds.containsKey(docId)) {
            List<Integer> list = new LinkedList<>();
            list.add(fieldId);
            fieldIds.put(docId, list);
        } else {
            fieldIds.get(docId).add(fieldId);
        }
    }

    private void addNewStartId(long docId, int fieldId, int startId) {
        if (!startIds.containsKey(docId)) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            List<Integer> list = new LinkedList<>();
            list.add(startId);
            map.put(fieldId, list);
            startIds.put(docId, map);
        } else {
            Map<Integer, List<Integer>> map = startIds.get(docId);
            if (!map.containsKey(fieldId)) {
                List<Integer> list = new LinkedList<>();
                list.add(startId);
                map.put(fieldId, list);
            } else {
                map.get(fieldId).add(startId);
            }
        }
    }

    private void updateFre(long docId) {
        if (!freq.containsKey(docId)) {
            freq.put(docId, 1);
        } else {
            freq.put(docId, freq.get(docId) + 1);
        }
    }

    public String getVal() {
        return val;
    }

    public List<Long> getDocIds() {
        return docIds;
    }

    public Map<Long, List<Integer>> getFieldIds() {
        return fieldIds;
    }

    public Map<Integer, List<Integer>> getStartIds(Long docId) {
        return startIds.get(docId);
    }

    public Map<Long, Integer> getFreq() {
        return freq;
    }

    public List<Integer> findAllFieldIdByDocumentId(long docId) {
        return fieldIds.get(docId);
    }

    public List<Integer> findAllStartIdByDocumentIdAndFieldId(long docId, int fieldId) {
        Map<Integer, List<Integer>> map = startIds.get(docId);
        if (map == null) {
            return null;
        } else {
            return map.get(fieldId);
        }
    }
}
