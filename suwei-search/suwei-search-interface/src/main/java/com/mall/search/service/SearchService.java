package com.mall.search.service;


import com.mall.common.pojo.SearchResult;

/**
 * @author weiwei
 * @create 2019-08-26 22:35
 */
public interface SearchService {
    SearchResult search(String keyWord, int page, int rows) throws Exception;
}
