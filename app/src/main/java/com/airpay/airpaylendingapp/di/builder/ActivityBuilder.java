package com.airpay.airpaylendingapp.di.builder;



import com.airpay.airpaylendingapp.ui.googlemaps.GoogleMapsActivity;
import com.airpay.airpaylendingapp.ui.googlemaps.MapsActivityModule;
import com.airpay.airpaylendingapp.ui.main.BlogActivity;
import com.airpay.airpaylendingapp.ui.main.BlogFragmentModule;
import com.airpay.airpaylendingapp.ui.splash.SplashActivity;
import com.airpay.airpaylendingapp.ui.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {


    @ContributesAndroidInjector(modules = BlogFragmentModule.class)
    abstract BlogActivity bindLoginActivity();



    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();


    @ContributesAndroidInjector(modules = MapsActivityModule.class)
    abstract GoogleMapsActivity bindMapsActivity();

}
