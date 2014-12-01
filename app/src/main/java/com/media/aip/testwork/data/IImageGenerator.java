package com.media.aip.testwork.data;

import com.media.aip.testwork.models.Image;

import java.util.List;

/**
 * Created by user_sca on 01.12.2014.
 */
/*
* Interface for getting images.
* */
public interface IImageGenerator {
    List<Image> generateImages(int count);
}
