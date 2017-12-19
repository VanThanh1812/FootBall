package com.codedao.footballapp.news.models;

import com.codedao.footballapp.news.models.entity.News;

import java.util.List;

/**
 * Created by Ha Nguyen on 12/11/17.
 */

public interface LoadNewsImpl {
    void onLoadNewsSuccess(List<News> list);
    void onLoadNewFail(String fialString);
}
