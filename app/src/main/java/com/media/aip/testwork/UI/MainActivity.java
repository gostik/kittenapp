package com.media.aip.testwork.UI;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.media.aip.testwork.R;
import com.media.aip.testwork.TestApplication;

import javax.inject.Inject;

/**
 * Created by user_sca on 01.12.2014.
 */
public class MainActivity extends Activity {

    @Inject
    AppContainer appContainer;

    private ViewGroup container;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TestApplication app = TestApplication.get(this);
        app.inject(this);

        container = appContainer.get(this);

        getLayoutInflater().inflate(R.layout.gallery_view, container);
    }
}
