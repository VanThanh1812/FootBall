package com.codedao.footballapp.model.news;

import com.codedao.footballapp.model.news.entity.News;

import java.util.List;

/**
 * Created by vanthanh on 12/11/17.
 */

public interface LoadNewsImpl {
    void onLoadNewsSuccess(List<News> list);
    void onLoadNewFail(String fialString);
}
