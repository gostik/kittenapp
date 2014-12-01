package com.media.aip.testwork.UI;

import com.media.aip.testwork.UI.gallery.GalleryView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/*
* Module which combine UI modules
* */
@Module(
    injects = {
        MainActivity.class,
        GalleryView.class,
    },
    complete = false,
    library = true
)
public class UiModule {
  @Provides @Singleton
  AppContainer provideAppContainer() {
    return AppContainer.DEFAULT;
  }

}
