package com.xcq.blogsearch.core.search.core.dao;

import com.xcq.blogsearch.core.search.index.domain.Document;

import java.util.List;

/**
 * 文档查询接口,要使用搜索功能，必须要实现该接口
 */
public interface IDocumentQuery {

    List<Document> findAllDocumentsById(List<Long> ids);

    Document findDocumentById(long id);
}
