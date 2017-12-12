package com.codedao.footballapp.model.news;

import android.content.Context;
import android.util.Log;

import com.codedao.footballapp.model.news.entity.News;
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
        /*URL url2 = null;
        try {
            url2 = new URL("http://feeds.feedburner.com/ndtv/TqgX");
        } catch (Exception e) {

        }
        try {
            List<com.pkmmte.pkrss.Article> rssItems = PkRSS.with(c).load(String.valueOf(url2)).get();
            com.pkmmte.pkrss.Article article = rssItems.get(0);
            for (com.pkmmte.pkrss.Article item : rssItems) {
                Log.i("PkRSS", item.toShortString());
            }
        } catch (Exception e) {
            Log.i("Error", "eror");
        }*/
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
