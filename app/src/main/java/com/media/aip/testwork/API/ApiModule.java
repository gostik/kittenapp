package com.media.aip.testwork.API;

import com.media.aip.testwork.data.IImageGenerator;
import com.media.aip.testwork.data.SimpleImageGenerator;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
/*
* APIModule combines all modules which perform access-to-data functionality
* */
@Module(
    complete = false,
    library = true
)
public final class ApiModule {

    @Provides @Singleton
    IImageGenerator provideImageGenerator(){
        return new SimpleImageGenerator();
    }

}
