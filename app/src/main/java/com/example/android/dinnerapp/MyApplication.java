package com.example.android.dinnerapp;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by noahpatterson on 11/9/15.
 */
public class MyApplication extends Application {
    public Tracker gaTracker;

    public void startTracking() {
        if (gaTracker == null) {
            GoogleAnalytics ga = GoogleAnalytics.getInstance(this);
            gaTracker = ga.newTracker(R.xml.track_app);
            ga.enableAutoActivityReports(this);
            ga.getLogger().setLogLevel(Logger.LogLevel.VERBOSE );
        }
    }

    public Tracker getTracker() {
       startTracking();
        return gaTracker;
    }
}
