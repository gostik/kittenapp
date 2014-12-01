package com.media.aip.testwork.UI;

import android.app.Activity;
import android.view.ViewGroup;

import static butterknife.ButterKnife.findById;

/**
 * Created by user_sca on 01.12.2014.
 */
public interface AppContainer {

    ViewGroup get(Activity activity);


    AppContainer DEFAULT = new AppContainer() {
        @Override
        public ViewGroup get(Activity activity) {
            return findById(activity, android.R.id.content);
        }
    };
}