package com.airpay.airpaylendingapp.di.module;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.airpay.airpaylendingapp.BuildConfig;
import com.airpay.airpaylendingapp.R;
import com.airpay.airpaylendingapp.data.local.db.dao.DataManager;
import com.airpay.airpaylendingapp.data.local.prefs.PreferencesHelper;
import com.airpay.airpaylendingapp.data.remote.ApiHeader;
import com.airpay.airpaylendingapp.data.remote.ApiHelper;
import com.airpay.airpaylendingapp.data.remote.AppApiHelper;
import com.airpay.airpaylendingapp.data.remote.AppDataManager;
import com.airpay.airpaylendingapp.di.ApiInfo;
import com.airpay.airpaylendingapp.di.PreferenceInfo;
import com.airpay.airpaylendingapp.utils.AppConstants;
import com.airpay.airpaylendingapp.utils.EndlessRecyclerViewScrollListener;
import com.airpay.airpaylendingapp.utils.rx.AppSchedulerProvider;
import com.airpay.airpaylendingapp.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }



    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }


    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey);
    }



    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }


    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }



    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }


    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }
}
