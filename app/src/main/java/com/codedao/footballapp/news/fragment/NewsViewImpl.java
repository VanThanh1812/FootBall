package com.codedao.footballapp.news.fragment;

import com.codedao.footballapp.news.models.entity.News;

import java.util.List;

/**
 * Created by vanthanhbk on 11/12/2017.
 */

public interface NewsViewImpl {
    void onLoadToView(List<News> list);
    void onLoadFail(String fail);
}
