package io.petros.posts.kotlin;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

public class App extends Application {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true); // NOTE: This enables the "proxy" trick on the vector images.
    }

}
