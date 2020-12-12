package com.xcq.blogsearch.core.search.index.op.write;

import com.xcq.blogsearch.core.search.index.adapter.TermDividerAdapter;
import com.xcq.blogsearch.core.search.index.dao.Directory;
import com.xcq.blogsearch.core.search.index.domain.Document;
import com.xcq.blogsearch.core.search.index.domain.Field;
import com.xcq.blogsearch.core.search.index.domain.IndexTerm;
import com.xcq.blogsearch.core.search.index.op.config.IndexWriterConfig;
import com.xcq.blogsearch.core.search.split.divide.TermDivider;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 执行文档写入操作
 */
public class DocumentsWriter {

    private IndexWriterConfig config;

    private Directory directory;

    private TermDivider divider;

    private ExecutorService service;

    private CountDownLatch latch;

    private List<Task> tasks;

    public DocumentsWriter(Directory directory, IndexWriterConfig config) {
        //默认使用的线程数量是5
        this(directory, config, 5);
    }

    public DocumentsWriter(Directory directory, IndexWriterConfig config, int threadNum) {
        this.config = config;
        this.directory = directory;
        divider = new TermDivider();
        tasks = new ArrayList<>();
        service = Executors.newFixedThreadPool(threadNum);
    }

    public void add(Document doc) {
        Task task = new Task(doc);
        //这里为了防止多线程同时操作tasks，所以要加锁
        synchronized (tasks) {
            tasks.add(task);
        }
    }

    /**
     * 执行已经提交的任务
     */
    public void commit() {
        try {
            if (!hasTaskNeedExecuted()) {
                return;
            }
            latch = new CountDownLatch(tasks.size());
            executeAllTask();
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void executeAllTask() {
        for (Task task : tasks) {
            service.execute(task);
        }
        clearTask();
    }

    private boolean hasTaskNeedExecuted() {
        return tasks.size() != 0;
    }

    private void clearTask() {
        tasks.clear();
    }
    /**
     * 定义添加索引任务类
     */
    class Task implements Runnable {

        private Document doc;

        public Task(Document doc) {
            this.doc = doc;
        }

        @Override
        public void run() {
            List<Field> fields = doc.getFields();
            TermDividerAdapter adapter = new TermDividerAdapter(divider);
            for (Field field : fields) {
                addIndex(field.getFieldId(), adapter.divide(field.getName()), true);
                addIndex(field.getFieldId(), adapter.divide(field.getContent()), false);
            }
            latch.countDown();
        }

        private void addIndex(int id,
                              List<Pair<String, Integer>> list
                , boolean isTitle) {
            for (Pair<String, Integer> pair : list) {
                IndexTerm term = new IndexTerm(pair.getKey(), doc.getDocId(), id, pair.getValue());
                if (isTitle) {
                    directory.addTitleIndex(term);
                } else {
                    directory.addContentIndex(term);
                }
            }
        }
    }
}
