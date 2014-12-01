package com.media.aip.testwork.models;

public final class Image {


    private static final String BASE_URL = "http://placekitten.com/g";
    public final int width;
    public final int height;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public String getUrl(){
        return BASE_URL+"/"+width+"/"+height;

    }
}
