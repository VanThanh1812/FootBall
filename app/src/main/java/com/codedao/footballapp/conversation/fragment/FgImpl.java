package com.codedao.footballapp.conversation.fragment;

import com.codedao.footballapp.conversation.model.Comment;

import java.util.List;

/**
 * Created by vanthanhbk on 17/12/2017.
 */

public interface FgImpl {
    void onCommentSuccess(List<Comment> list);
    void onLoadFail(String fail);
    void onLoadCommentDone(String fail);
}
