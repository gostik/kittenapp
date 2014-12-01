package com.media.aip.testwork.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.media.aip.testwork.models.Image;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by user_sca on 05.11.2014.
 */
public class LastImagesProvider {

    String LAST_IMAGES = "Local settings";

    Gson gson = new Gson();
    private Context applicationContext;

    public LastImagesProvider(Context applicationContext) {

        this.applicationContext = applicationContext;
    }

//    public LastImagesProvider withContext(Context context) {
//        this.context = context;
//        return this;
//    }

    public ArrayList<Image> load() {
        String s = AccessPreferences.get(applicationContext, LAST_IMAGES, "");

        ArrayList<Image> images = new ArrayList<Image>();
        Type listType = new TypeToken<ArrayList<Image>>() {}.getType();
        try {
            images.addAll((ArrayList<Image>) gson.fromJson(s, listType));
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            Timber.e("Error when load last shown images");
        }

        return images;
    }

    public void save(List<Image> images) {

        AccessPreferences.commit(applicationContext, LAST_IMAGES, gson.toJson(images).toString());
    }

}
