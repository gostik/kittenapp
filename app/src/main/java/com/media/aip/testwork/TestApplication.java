package com.media.aip.testwork;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;
import timber.log.Timber;

import static timber.log.Timber.DebugTree;

/**
 * Created by user_sca on 01.12.2014.
 */
public class TestApplication extends Application {
    private ObjectGraph objectGraph;


    @Override public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree());
        }

        buildObjectGraphAndInject();
    }

    public void buildObjectGraphAndInject() {
        objectGraph = ObjectGraph.create(Modules.list(this));
        objectGraph.inject(this);
    }

    public void inject(Object o) {
        objectGraph.inject(o);
    }

    public static TestApplication get(Context context) {
        return (TestApplication) context.getApplicationContext();
    }
}
