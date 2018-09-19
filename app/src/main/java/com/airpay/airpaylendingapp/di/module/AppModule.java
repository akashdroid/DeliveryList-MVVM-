package com.airpay.airpaylendingapp.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.airpay.airpaylendingapp.BuildConfig;
import com.airpay.airpaylendingapp.R;
import com.airpay.airpaylendingapp.data.local.prefs.AppPreferencesHelper;
import com.airpay.airpaylendingapp.data.local.prefs.PreferencesHelper;
import com.airpay.airpaylendingapp.data.remote.ApiHeader;
import com.airpay.airpaylendingapp.data.remote.ApiHelper;
import com.airpay.airpaylendingapp.data.remote.AppApiHelper;
import com.airpay.airpaylendingapp.di.ApiInfo;
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

   /* @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }*/

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

   /* @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }*/

  /*  @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }*/

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

   /* @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }*/

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

  /*  @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,
                preferencesHelper.getCurrentUserId(),
                preferencesHelper.getAccessToken());
    }*/

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }
}
