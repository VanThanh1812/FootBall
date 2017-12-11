package com.codedao.footballapp.model.news;

import com.codedao.footballapp.model.news.entity.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanthanh on 12/11/17.
 */

public class NewsInterator {
    private LoadNewsImpl lisner;

    public NewsInterator (LoadNewsImpl lis){
        this.lisner = lis;
    }

    // handle create data
    public void callDataRss(){
        List<News> list = new ArrayList<>();
        lisner.onLoadNewsSuccess(list);
    }
}
