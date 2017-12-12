package com.codedao.footballapp.news.models;

import android.content.Context;
import android.util.Log;

import com.codedao.footballapp.news.models.entity.News;
import com.codedao.footballapp.rssparser.Article;
import com.codedao.footballapp.rssparser.Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanthanh on 12/11/17.
 */

public class NewsInterator {
    private LoadNewsImpl lisner;
    private Context c;
    public NewsInterator (LoadNewsImpl lis, Context c){
        this.c = c;
        this.lisner = lis;
    }

    public void load(String url, final int type) {
        Parser parser = new Parser();
        parser.execute(url);
        parser.onFinish(new Parser.OnTaskCompleted() {

            @Override
            public void onTaskCompleted(ArrayList<Article> list) {
                List<News> listNews = new ArrayList<>();
                for(int i =0; i < list.size(); i++){
                    News n = new News.Builder()
                            .setmTitle(list.get(i).getTitle())
                            .setmDescription(list.get(i).getDescription())
                            .setmImage(list.get(i).getImage())
                            .setmLink(list.get(i).getLink())
                            .setmType(String.valueOf(type))
                            .setmPubdate(list.get(i).getPubDate().toString())
                            .build();
                    listNews.add(n);

                    Log.d("response", list.get(i).toString());
                }


                lisner.onLoadNewsSuccess(listNews);
            }

            @Override
            public void onError() {
                Log.d("startL", "errr");
                lisner.onLoadNewFail("Load data fail");
            }
        });
    }
}
