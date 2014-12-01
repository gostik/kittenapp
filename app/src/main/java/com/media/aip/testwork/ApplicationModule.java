package com.media.aip.testwork;

/**
 * Created by user_sca on 01.12.2014.
 */
import android.app.Application;

import com.media.aip.testwork.UI.UiModule;
import com.media.aip.testwork.data.DataModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
/*
* Module which combine all included modules
* */
@Module(
        includes = {
                UiModule.class,
                DataModule.class
        },
        injects = {
                TestApplication.class
        }
)
public final class ApplicationModule {
    private final TestApplication app;

    public ApplicationModule(TestApplication app) {
        this.app = app;
    }

    @Provides @Singleton Application provideApplication() {
        return app;
    }
}
