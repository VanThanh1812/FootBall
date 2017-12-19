package com.codedao.footballapp.news.models.entity;

/**
 * Created by Ha Nguyen on 12/11/17.
 */

public class News {
    private String mTitle;
    private String mLink;
    private String mImage;
    private String mPubdate;
    private String mDescription;
    private String mType;
    
    private News(Builder builder){
        mTitle = builder.mTitle;
        mDescription = builder.mDescription;
        mImage = builder.mImage;
        mPubdate = builder.mPubdate;
        mLink = builder.mLink;
        mType = builder.mType;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmLink() {
        return mLink;
    }

    public String getmImage() {
        return mImage;
    }

    public String getmPubdate() {
        return mPubdate;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmType() {
        return mType;
    }

    public static class Builder{
        private String mTitle;
        private String mLink;
        private String mImage;
        private String mPubdate;
        private String mDescription;
        private String mType;

        public Builder() {
        
        }
        
        public News build(){
            return new News(this);
        }

        public Builder setmType(String mType) {
            this.mType = mType;
            return this;
        }

        public Builder setmTitle(String mTitle) {
            this.mTitle = mTitle;
            return this;
        }

        public Builder setmLink(String mLink) {
            this.mLink = mLink;
            return this;
        }

        public Builder setmImage(String mImage) {
            this.mImage = mImage;
            return this;
        }

        public Builder setmPubdate(String mPubdate) {
            this.mPubdate = mPubdate;
            return this;
        }

        public Builder setmDescription(String mDescription) {
            this.mDescription = mDescription;
            return this;
        }
    }
}
