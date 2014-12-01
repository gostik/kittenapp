package com.media.aip.testwork.data;

import com.media.aip.testwork.models.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by user_sca on 01.12.2014.
 */
public class SimpleImageGenerator implements IImageGenerator {

    public List<Image> generateImages(int count){

        List<Image> images = new ArrayList<Image>(count);


        for (int i =0 ; i<count;i++) {
            images.add(new Image( randInt(100,1024), randInt(100,1024)));
        }
        return images;
    }

    public static int randInt(int min, int max) {

        Random rand = new Random();

        return rand.nextInt((max - min) + 1) + min;
    }
}
